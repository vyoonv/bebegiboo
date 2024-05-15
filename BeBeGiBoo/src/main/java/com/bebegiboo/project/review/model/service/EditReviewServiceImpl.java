package com.bebegiboo.project.review.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bebegiboo.project.common.util.Utility;
import com.bebegiboo.project.review.model.dto.BoardImg;
import com.bebegiboo.project.review.model.dto.CertificationBoard;
import com.bebegiboo.project.review.model.exception.ImageDeleteException;
import com.bebegiboo.project.review.model.exception.ImageUpdateException;
import com.bebegiboo.project.review.model.exception.ReviewInsertException;
import com.bebegiboo.project.review.model.mapper.EditReviewMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
@PropertySource("classpath:/config.properties")
public class EditReviewServiceImpl implements EditReviewService{
	
	private final EditReviewMapper mapper;
	
	@Value("${my.board.web-path}")
	private String webPath;
	
	@Value("${my.board.folder-path}")
	private String folderPath;
	
	
	
	//게시글 작성
	@Override
	public int boardInsert(CertificationBoard inputBoard, List<MultipartFile> images) throws IllegalStateException, IOException {
		log.debug("test : "+inputBoard);
		//1. 게시글 내용을 먼저 insert하기 + 작성된 게시글 시퀀스 번호 반환받기
		int result = mapper.boardInsert(inputBoard);
		
		if(result == 0) return 0;
		
		int boardNo = inputBoard.getBoardNo();

		//2. 업로드된 이미지가 실제로 존재하는 경우 업로드된 이미지만 별도 저장해서 BOARD_IMG 테이블에 INSERT
		List<BoardImg> uploadList = new ArrayList<>();
		
		//images 에서 하나씩 꺼내서 파일 존재하는지 검사
		for(int i=0; i<images.size();i++) {
			if(!images.get(i).isEmpty()) {
				
				String originalName = images.get(i).getOriginalFilename();
				
				String rename =Utility.fileRename(originalName); //날짜로 재생성
				
				BoardImg img = BoardImg.builder()
							.imgOriginalName(originalName)
							.imgRename(rename)
							.imgPath(webPath)
							.boardNo(boardNo)
							.imgOrder(i)
							.uploadFile(images.get(i))
							.build();
				

				uploadList.add(img);				


			}
		}
		
		//선택한 파일이 없는 경우
		if(uploadList.isEmpty()) {
			return boardNo;
		}
		
		//이미지 insert하기 (삽입된 행의 개수가 나옴)
		result = mapper.insertUploadList(uploadList);
		
		if(result==uploadList.size()) {
			
			//서버에 파일 저장
			for(BoardImg img : uploadList) {
				img.getUploadFile()
				.transferTo(new File(folderPath + img.getImgRename()));
			}
		} else {
			throw new ReviewInsertException("이미지가 정상 삽입되지 않음");
			//롤백됨
		}
		
		
		return boardNo;
	}



	//게시글 수정
	@Override
	public int boardUpdate(CertificationBoard inputBoard, List<MultipartFile> images, String deleteOrder) throws IllegalStateException, IOException {
		
		int result = mapper.boardUpdate(inputBoard);
		
		if(result == 0) return 0;
		
		//삭제된 이미지가 있는 경우
		if(deleteOrder != null && !deleteOrder.equals("")) {
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("deleteOrder", deleteOrder);
			map.put("boardNo", inputBoard.getBoardNo());
			
			result = mapper.deleteImage(map);
			
			if(result == 0) {
				throw new ImageDeleteException();
			}
		}
		
		//선택한 파일이 존재하는 경우
		List<BoardImg> uploadList = new ArrayList<>();
		
		for(int i=0;i<images.size();i++) {
			if(!images.get(i).isEmpty()) {
				String originalName = images.get(i).getOriginalFilename();
				
				String rename =Utility.fileRename(originalName);
				
				BoardImg img = BoardImg.builder()
							.imgOriginalName(originalName)
							.imgRename(rename)
							.imgPath(webPath)
							.boardNo(inputBoard.getBoardNo())
							.imgOrder(i)
							.uploadFile(images.get(i))
							.build();
				uploadList.add(img);


				//4. img 객체를 리스트 에 추가 + 수정 혹은 삽입 수행하기
				result = mapper.updateImage(img);
				
				if(result == 0) { //img_order에 이미지가 없음 -> 삽입하기
					result = mapper.insertImage(img);
				}
			}
			if(result == 0) { //insertImage 도 실패한 경우
				 throw new ImageUpdateException();
			}
			
		}
		
		if(uploadList.isEmpty()) {
			return result;
		}
		for(BoardImg img:uploadList) {
			img.getUploadFile().transferTo(new File(folderPath + img.getImgRename()));
		}
		
		return result;
	}



	//게시글 삭제
	@Override
	public int boardDelete(Map<String, Integer> map) {
		
		return mapper.boardDelete(map);
	}

	

}

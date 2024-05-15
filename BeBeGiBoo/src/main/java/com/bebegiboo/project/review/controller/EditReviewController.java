package com.bebegiboo.project.review.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bebegiboo.project.member.model.dto.Member;
import com.bebegiboo.project.review.model.dto.CertificationBoard;
import com.bebegiboo.project.review.model.service.EditReviewService;
import com.bebegiboo.project.review.model.service.ReviewService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("editReview")
public class EditReviewController {
	private final ReviewService reviewService;
	private final EditReviewService service;
	
	//게시글 작성 화면으로 전환
	@GetMapping("insert")
	public String boardInsert() {
		return "review/reivewWrite"; //templates/review/reviewWrite.html
	}
	
	
	
	
	//게시글 작성하기
	/**
	 * @param inputBoard : 입력된 정보들 세팅됨
	 * @param loginMember : 로그인 회원 정보
	 * @param images : 제출한 file 타입 input태그 데이터들(이미지 파일들)
	 * @param ra : 리다이렉트 시 request scope로 데이터 전달
	 * @return
	 */
	@PostMapping("insert")
	public String boardInsert(CertificationBoard inputBoard,
							@SessionAttribute("loginMember") Member loginMember,
							@RequestParam("images") List<MultipartFile> images,
							RedirectAttributes ra) throws IllegalStateException, IOException {
		
		//제출된 파일이 있는지 확인하고 서버에 보내기
		
		//1. 게시글 내용 insert하기
		inputBoard.setMemberNo(loginMember.getMemberNo());

		//2. insert하기
		int boardNo = service.boardInsert(inputBoard, images);

		//3. 서비스 수행 결과에 따를 경로, message 지정
		String path = null;
		String message = null;
		
		if(boardNo >0 ) {
			message = "게시글이 작성되었습니다";
			path = "/review/"+boardNo;
		}else {
			message = "게시글 작성 실패";
			path = "insert";
		}
		ra.addFlashAttribute("message", message);
		
		return "redirect:"+path;
	}
	
	
	
	/** 게시글 수정 화면으로 전환
	 * @param boardNo
	 * @param loginMember : 작성한 회원 == 로그인 회원인지 확인하기
	 * @param model  : forward - 성공 시
	 * @param ra  : redirect - 실패 시
	 * @return
	 */
	@GetMapping("{boardNo:[0-9]+}/update")
	public String boardUpdate(@PathVariable("boardNo") int boardNo,
							@SessionAttribute("loginMember") Member loginMember,
							Model model,
							RedirectAttributes ra) {
		
		//게시글 제목,내용,이미지 조회하기
		Map<String, Integer> map = new HashMap<>();
		map.put("boardNo", boardNo);
		CertificationBoard board = reviewService.selectOne(map);
		
		
		String path = null;
		String message = null;
		
		if(board == null) {
			message = "존재하지 않는 게시글입니다";
			
			path = "redirect:/"; 
		}else if(board.getMemberNo() != loginMember.getMemberNo()) {
			message = "게시글은 작성자만 수정할 수 있습니다.";
			
			path = String.format("redirect:/board/%d", boardNo );
			
		}else {
			path = "review/reviewUpdate";
			model.addAttribute("board", board);
		}
		
		ra.addFlashAttribute("message", message);
		
		return path;
		

		
	}
	
	
	@PostMapping("{boardNo:[0-9]+}/update")
	public String boardUpdate(@PathVariable("boardNo") int boardNo,
							CertificationBoard inputBoard,
							@SessionAttribute("loginMember") Member loginMember,
							@RequestParam("images") List<MultipartFile> images,
							RedirectAttributes ra,
							@RequestParam(value="deleteOrder", required=false) String deleteOrder,
							@RequestParam(value="queryString", required=false, defaultValue="") String queryString) throws IllegalStateException, IOException {
		
		inputBoard.setBoardNo(boardNo);
		inputBoard.setMemberNo(loginMember.getMemberNo());
		
		int result = service.boardUpdate(inputBoard, images, deleteOrder);
		
		String message =null;
		String path = null;
		
		if(result>0) {
			message = "게시글이 수정되었습니다";
			path = String.format("/review/%d%s", boardNo, queryString);
		}else {
			message = "게시글 수정 실패";
			path = "update"; //상대경로
		}
				
		ra.addFlashAttribute("message", message);
		return "redirect:"+path;
	}

	
	
	
	
	@GetMapping("{boardNo:[0-9]+}/delete")
	public String boardDelete(@PathVariable("boardNo") int boardNo,
							@RequestParam(value="cp", required=false, defaultValue="1") int cp,
							@SessionAttribute("loginMember") Member loginMember,
							RedirectAttributes ra) {
		Map<String, Integer> map = new HashMap<>();
		
		map.put("boardNo", boardNo);
		
		map.put("memberNo", loginMember.getMemberNo());
		
		int result = service.boardDelete(map);
		
		String path = null;
		String message = null;
		
		if(result > 0) {
			path = "/review";
			message = "삭제되었습니다";
		}else {
			path = String.format("/reivew/%d?cp=%d", boardNo, cp);
			message="게시글 삭제 실패";
		}
		
		ra.addFlashAttribute("message", message);
		return "redirect:"+path;
	}
}

package com.bebegiboo.project.review.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bebegiboo.project.member.model.dto.Member;
import com.bebegiboo.project.review.model.dto.CertificationBoard;
import com.bebegiboo.project.review.model.service.ReviewService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("review")
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService service;
	/** 후기 게시판 목록 조회
	 * @param cp
	 * @param model
	 * @return
	 */
	@GetMapping("")
	public String selectBoardList(
			@RequestParam(value="cp", required=false, defaultValue="1") int cp,
			Model model,
			@RequestParam Map<String, Object> paramMap
			) {
		log.debug("map : "+paramMap); //map : {key=t, query=}
		log.debug("cp : "+cp); //map : {key=t, query=}
		
		Map<String, Object> map = null;


		
		if(paramMap.get("key") == null) { //검색 X(그냥 목록 조회)

			map = service.selectReviewList(cp);
			
		}else { //검색 O
			//검색 내용이 없는 경우 그냥 리턴하고 전체 목록 보여주기
			if(paramMap.get("query")== null) {
				return "/review/review";
			}
			map = service.searchList(paramMap, cp);
			
			
		}

		model.addAttribute("reviewList", map.get("reviewList"));
		model.addAttribute("pagination", map.get("pagination"));
		
		return "review/review"; 
	}
	/** 후기 게시판 목록 조회 (main용)
	 * @param cp
	 * @param model
	 * @return
	 */
	@GetMapping("boardList")
	@ResponseBody
	public List<CertificationBoard> selectBoardList() {
		
		List<CertificationBoard> boardList = service.selectBoardList();
		
		return boardList; 
	}
	
	
	
	@GetMapping("{boardNo:[0-9]+}")
	public String selectBoardDetail(
			@PathVariable("boardNo") int boardNo,
			Model model,
			RedirectAttributes ra,
			@SessionAttribute(value="loginMember", required=false) Member loginMember,
			HttpServletRequest req,
			HttpServletResponse resp
			) {
		
		Map<String,Integer> map = new HashMap<>();
		
		map.put("boardNo", boardNo);
		
		//로그인 한 경우 멤버번호 추가
		if(loginMember != null) {
			map.put("memberNo", loginMember.getMemberNo());
		}
		
		CertificationBoard  board = service.selectOne(map);
		
		String path = null;
		
		if(board == null) {
			path = "redirect:/review";
			ra.addFlashAttribute("message", "게시글이 존재하지 않습니다");
			
		}else {
			//쿠키를 이용한 조회수 증가하기
			//1. 비회원 or 글쓴X 가 본 경우(조회수가 증가하는 경우)
			if(loginMember == null || loginMember.getMemberNo() != board.getMemberNo()) {
				
				Cookie[] cookies = req.getCookies();
				Cookie c = null;
				
				for(Cookie temp:cookies) {
					if(temp.getName().equals("readBoardNo")) { //readBoardNo : 사용자가 읽은 보드 번호
						c = temp;
						break;
					}
				}
				
				int result = 0; //조회수 증가 변수
				
				if(c == null) {
					c = new Cookie("readBoardNo", "["+boardNo+"]");
					result = service.updateReadCount(boardNo); //조회수 업데이트
					log.debug("setCookie and update ReadCount");
					
				}else {
					if(c.getValue().indexOf("["+boardNo+"]")== -1) { //쿠키에 boardNo에 해당하는 번호 없는 경우
						
						log.debug("NO boardNO");
						
						c.setValue(c.getValue() + "["+boardNo+"]");
						
						result = service.updateReadCount(boardNo);
						
						log.debug("result : "+result);
					}
				}				
				
				if(result > 0) {
					
					board.setReadCount(result);
					
					c.setPath("/");
					
					LocalDateTime now = LocalDateTime.now();
					
					LocalDateTime nextDayMidnight = now.plusDays(1).withHour(0).withMinute(0).withNano(0);
					
					//다음날 자정까지 남은 시간 계산(초)
					long secondsUntilNextDay = Duration.between(now, nextDayMidnight).getSeconds();
					
					c.setMaxAge((int)secondsUntilNextDay);
					
					resp.addCookie(c);
				}
			} 
			
			path = "review/reviewDetail";
			
			model.addAttribute("board", board);
		}
		return path;
	}
	
	
	
	
	
}

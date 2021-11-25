package com.green.view.controller;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.green.biz.dto.MemberVO;
import com.green.biz.dto.QnaVO;
import com.green.biz.qna.impl.QnaServiceImpl;

@Controller
public class QnaController {
	
	@Autowired
	private QnaServiceImpl qnaService; 
	
	@RequestMapping(value="qna_list", method=RequestMethod.GET)
	public String qnaList(HttpSession session, Model model){
		MemberVO loginUser =(MemberVO) session.getAttribute("loginUser");
		
		if(loginUser == null) { // 현재 로그인 되어있지 않은 상태
			return "member/login";
			
		}else {
			 List<QnaVO> qnaList = qnaService.listQna(loginUser.getId());
			 
			 model.addAttribute("qnaList" ,qnaList );
			 return "qna/qnaList";
		}
		
	}
	
	@RequestMapping(value="qna_view", method=RequestMethod.GET)
	public String qnaView(HttpSession session, QnaVO vo, Model model) {
		MemberVO loginUser =(MemberVO) session.getAttribute("loginUser");
		
		if(loginUser == null) { // 현재 로그인 되어있지 않은 상태
			return "member/login";
			
		}else {
			QnaVO qnaVO = qnaService.getQna(vo.getQseq());
			model.addAttribute("qnaVO",qnaVO);
			return "qna/qnaView";
		}
		
	}
	
	
	@RequestMapping(value="qna_write_form", method=RequestMethod.GET )
	public String qnaWriteView(HttpSession session) {
		
		MemberVO loginUser =(MemberVO) session.getAttribute("loginUser");
		
		if(loginUser == null) { // 현재 로그인 되어있지 않은 상태
			return "member/login";
			
		}else {
			
			return "qna/qnaWrite"; //qna게시글 등록화면 표시
		}
		
		
	}
	
	@RequestMapping(value="qna_write", method=RequestMethod.POST )
	public String qnaWriteAction(QnaVO vo,HttpSession session) {
		MemberVO loginUser =(MemberVO) session.getAttribute("loginUser");
		
		if(loginUser == null) { // 현재 로그인 되어있지 않은 상태
			return "member/login";
			
		}else {
			vo.setId(loginUser.getId()); //나머지는 화면에서 커맨드 객체로 가져온다
			qnaService.insertQna(vo);
			
			return "redirect:qna_list";
		}
			
		
	}
	
}

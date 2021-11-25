package com.green.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.green.biz.dto.MemberVO;
import com.green.biz.dto.QnaVO;
import com.green.biz.qna.QnaService;

@Controller
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	@RequestMapping(value="qna_list", method=RequestMethod.GET)
	public String qnaList(HttpSession session, Model model) {
		
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			
			return "member/login";
		} else {
			List<QnaVO> qnaList = qnaService.listQna(loginUser.getId());
			
			model.addAttribute("qnaList", qnaList);
			
			return "qna/qnaList";
		}
	}
	
	@RequestMapping(value="qna_write_form", method=RequestMethod.GET)
	public String qnaWriteView(HttpSession session) {
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			
			return "member/login";
		} else {
		
			return "qna/qnaWrite";	
		}
	}
	
	@RequestMapping(value="qna_write", method=RequestMethod.POST)
	public String qnaWriteAction(QnaVO vo, HttpSession session) {
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			
			return "member/login";
		} else {
			vo.setId(loginUser.getId());
			
			qnaService.insertQna(vo);
			
			return "redirect:qna_list";
		}
	}
	
	@RequestMapping(value="qna_view", method=RequestMethod.GET)
	public String qnaView(QnaVO vo, HttpSession session, Model model) {
		
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			
			return "member/login";
		} else {
			// ���� �Խñ� ����
			QnaVO qna = qnaService.getQna(vo.getQseq());
			model.addAttribute("qnaVO", qna);
			
			return "qna/qnaView";
		}
	}
}










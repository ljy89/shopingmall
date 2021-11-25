package com.green.view.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.green.biz.dto.AddressVO;
import com.green.biz.dto.MemberVO;
import com.green.biz.member.MemberService;

@Controller
@SessionAttributes("loginUser")
public class MemberController {
	
	
	@Autowired
	private MemberService memberService;
	
	//�α��� ȭ�� ǥ��
	@RequestMapping(value="/login_form", method=RequestMethod.GET)
	public String loginView() {
		//ó�� ���� ���� �α��� ȭ�鸸 ���÷���
		return "member/login";
	}
	
	//����� �α��� ó��
	//id, pwd ����� �Է� ���� - > Ŀ�ǵ� ��ü
	//confirmID�� ������ ID ,PWD����
	//����� ���� �� ����� ���� ��ȸ�� index �� redirect :index
	//����� ���� ���н� login_fail.jsp ǥ��

	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginAction(MemberVO vo, Model model) {
		
		MemberVO loginUser = null;
		int result =memberService.loginID(vo);
		
		if(result ==1) {//���̵� & �н� ���� ��ġ ==> ����� ���� ����
			loginUser = memberService.getMember(vo.getId());
			
			model.addAttribute("loginUser",loginUser);
			return "redirect:/index";
		}else{
			return "member/login_fail";
		}
		
	}
	
	//����� ���� ȭ�� ǥ��
	
	
	// ��� ���� ȭ��
	@RequestMapping(value="/contract", method=RequestMethod.GET)
	public String contractView() {
		
		
		return "member/contract";
	}
	
	//ȸ�������� ȭ�� ���
	@RequestMapping(value="/join_form", method=RequestMethod.POST)
	public String joinView() {
		
		return "member/join";
	}
	//ID �ߺ� üũ ȭ�� ���
	
	@RequestMapping(value="/id_check_form", method=RequestMethod.GET)
	public String idCheckView(@RequestParam(value="id", defaultValue="", required=true) String id, Model model) {
		
		model.addAttribute("id", id);
		return "member/idcheck";
	}
	
	//����� id �ߺ� üũ
	
	@RequestMapping(value="/id_check_form", method=RequestMethod.POST)
	public String idCheckAction(@RequestParam(value="id", defaultValue="", required=true) 
									String id, Model model) {
		//�Էµ� id�� ������ getMember() ���񽺷� ��ȸ����
		
		MemberVO user = memberService.getMember(id);
		
		// �����Ͱ� ������ message �Ӽ��� 1�� ����
		// �����Ͱ� ������ message �Ӽ��� -1�� ����
		if(user != null){ //����� ID�� �����ϸ�
			model.addAttribute("message",1);
		}else {
			model.addAttribute("message",-1);
		}
		//id ���� MODEL�� ����
		model.addAttribute("id", id);
		return "member/idcheck";
	}
	
	@RequestMapping(value="id_check_confirmed" ,method=RequestMethod.GET)
	public String idCheckConfirmed(MemberVO vo, Model model) {
		
		model.addAttribute("id", vo.getId());
		model.addAttribute("reid", vo.getId());
		return "member/join";
	}
	
	@RequestMapping(value="join",method=RequestMethod.POST)
	public String joinAction(@RequestParam(value="addr1a") String addr1,
								@RequestParam(value="addr2") String addr2, 
								MemberVO vo) {
		vo.setAddress(addr1+" "+addr2);
		memberService.insertMember(vo);
		
		return "member/login";
	}
	//�����ȣ ã�� ȭ�� ��û ó��
	@RequestMapping(value="/find_zip_num", method=RequestMethod.GET)
	public String findZipNumView() {
		
		return "member/findZipNum";
	}
	
	//���̸����� �����ȣ ã�� ��ȸ ó��
	@RequestMapping(value="/find_zip_num", method=RequestMethod.POST)
	public String findZipNumAction(AddressVO vo, Model model) {
		
		List<AddressVO> addrList =memberService.selectAddressByDong(vo.getDong());
		
		model.addAttribute("addressList", addrList);
		return "member/findZipNum";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(SessionStatus status) {// HttpSession  �̿����� �ʰ� SessionStatus �̿� ===>���� ������ �����Ѵٴ� �ǹ�
		status.setComplete();
		
		return "member/login";
		
	}
	
	@RequestMapping(value="/find_id_form",method=RequestMethod.GET)
	public String findIdView() {
		
		return "member/findIdAndPassword";
	}
	//���̵� ã�� �żҵ�
	@RequestMapping(value="/find_id",method=RequestMethod.GET)
	public String findIdAction(MemberVO vo, Model model) {
		
		MemberVO member = memberService.getMemberByNameAndEmail(vo);
		
		if(member != null) {// ����� ���̵� ����
			model.addAttribute("message", 1);
			model.addAttribute("id", member.getId());
		}else {// ����� ���̵� ���� ���� ����
			model.addAttribute("message", -1);
		}
		
		return "member/findResult";
	}
	
	//��й�ȣ ã��
	@RequestMapping(value="/find_password",method=RequestMethod.GET)
	public String findPassword(MemberVO vo, Model model) {
		
		MemberVO member = memberService.findPassword(vo);
		
		if(member != null) {// �����  ����
			model.addAttribute("message", 1);
			model.addAttribute("id", member.getId());
		}else {// �����  ���� ���� ����
			model.addAttribute("message", -1);
		}
		
		return "member/findPwdResult";
		
	}
	
	@RequestMapping(value="/change_password" , method=RequestMethod.POST)
	public String changePassword(MemberVO vo, Model model) {
		
		memberService.changePassword(vo);
		
		
		return "member/close"; //������ �󿡼� ����
	}
}

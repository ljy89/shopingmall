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
	
	//로그인 화면 표시
	@RequestMapping(value="/login_form", method=RequestMethod.GET)
	public String loginView() {
		//처리 내용 없이 로그인 화면만 디스플레이
		return "member/login";
	}
	
	//사용자 로그인 처리
	//id, pwd 사용자 입력 얻어옴 - > 커맨드 객체
	//confirmID를 가지고 ID ,PWD인증
	//사용자 인증 후 사용자 정보 조회해 index 로 redirect :index
	//사용자 인증 실패시 login_fail.jsp 표시

	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginAction(MemberVO vo, Model model) {
		
		MemberVO loginUser = null;
		int result =memberService.loginID(vo);
		
		if(result ==1) {//아이디 & 패스 워드 일치 ==> 사용자 인증 성공
			loginUser = memberService.getMember(vo.getId());
			
			model.addAttribute("loginUser",loginUser);
			return "redirect:/index";
		}else{
			return "member/login_fail";
		}
		
	}
	
	//사용자 약정 화면 표시
	
	
	// 약관 동의 화면
	@RequestMapping(value="/contract", method=RequestMethod.GET)
	public String contractView() {
		
		
		return "member/contract";
	}
	
	//회원가입을 화면 출력
	@RequestMapping(value="/join_form", method=RequestMethod.POST)
	public String joinView() {
		
		return "member/join";
	}
	//ID 중복 체크 화면 출력
	
	@RequestMapping(value="/id_check_form", method=RequestMethod.GET)
	public String idCheckView(@RequestParam(value="id", defaultValue="", required=true) String id, Model model) {
		
		model.addAttribute("id", id);
		return "member/idcheck";
	}
	
	//사용자 id 중복 체크
	
	@RequestMapping(value="/id_check_form", method=RequestMethod.POST)
	public String idCheckAction(@RequestParam(value="id", defaultValue="", required=true) 
									String id, Model model) {
		//입력된 id를 가지고 getMember() 서비스로 조회한후
		
		MemberVO user = memberService.getMember(id);
		
		// 데이터가 있으면 message 속성에 1을 설정
		// 데이터가 없으면 message 속성에 -1을 설정
		if(user != null){ //사용자 ID가 존재하면
			model.addAttribute("message",1);
		}else {
			model.addAttribute("message",-1);
		}
		//id 값을 MODEL에 저장
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
	//우편번호 찾기 화면 요청 처리
	@RequestMapping(value="/find_zip_num", method=RequestMethod.GET)
	public String findZipNumView() {
		
		return "member/findZipNum";
	}
	
	//동이름으로 우편번호 찾기 조회 처리
	@RequestMapping(value="/find_zip_num", method=RequestMethod.POST)
	public String findZipNumAction(AddressVO vo, Model model) {
		
		List<AddressVO> addrList =memberService.selectAddressByDong(vo.getDong());
		
		model.addAttribute("addressList", addrList);
		return "member/findZipNum";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(SessionStatus status) {// HttpSession  이용하지 않고 SessionStatus 이용 ===>현재 세션을 종료한다는 의미
		status.setComplete();
		
		return "member/login";
		
	}
	
	@RequestMapping(value="/find_id_form",method=RequestMethod.GET)
	public String findIdView() {
		
		return "member/findIdAndPassword";
	}
	//아이디 찾기 매소드
	@RequestMapping(value="/find_id",method=RequestMethod.GET)
	public String findIdAction(MemberVO vo, Model model) {
		
		MemberVO member = memberService.getMemberByNameAndEmail(vo);
		
		if(member != null) {// 사용자 아이디 존재
			model.addAttribute("message", 1);
			model.addAttribute("id", member.getId());
		}else {// 사용자 아이디 존재 하지 않음
			model.addAttribute("message", -1);
		}
		
		return "member/findResult";
	}
	
	//비밀번호 찾기
	@RequestMapping(value="/find_password",method=RequestMethod.GET)
	public String findPassword(MemberVO vo, Model model) {
		
		MemberVO member = memberService.findPassword(vo);
		
		if(member != null) {// 사용자  존재
			model.addAttribute("message", 1);
			model.addAttribute("id", member.getId());
		}else {// 사용자  존재 하지 않음
			model.addAttribute("message", -1);
		}
		
		return "member/findPwdResult";
		
	}
	
	@RequestMapping(value="/change_password" , method=RequestMethod.POST)
	public String changePassword(MemberVO vo, Model model) {
		
		memberService.changePassword(vo);
		
		
		return "member/close"; //윈도우 상에서 실행
	}
}

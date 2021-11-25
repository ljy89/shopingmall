package com.green.biz.member;

import java.util.*;
import com.green.biz.dto.AddressVO;
import com.green.biz.dto.MemberVO;

public interface MemberService {
	//회원 상세 정보 조회
	MemberVO getMember(String id);
	
	//회원 id 존재확인
	int confirmID(String id);	
	
	//비번 일치 확인
	int loginID(MemberVO vo);
	
	//회원 추가
	void insertMember(MemberVO vo);
	
	//우편번호 찾기
	List<AddressVO> selectAddressByDong(String dong);
	
	//사용자이름과 이메일을 조건으로 사용자 정보 조회
	MemberVO getMemberByNameAndEmail(MemberVO vo);
	
	//사용자 이름, 이메일, 아이디를 조건으로 사용자 정보 조회
	MemberVO findPassword(MemberVO vo);
	
	//비밀번호 변경
	void changePassword(MemberVO vo);
}

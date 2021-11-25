package com.green.biz.dao;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.dto.AddressVO;
import com.green.biz.dto.MemberVO;


@Repository
public class MemberDAO {

	@Autowired //생성되어져 있는 객체 제공
	private SqlSessionTemplate mybatis;
	
	//회원 상세 정보 조회
	public MemberVO getMember(String id) {
		
		return mybatis.selectOne("MemberDAO.getMember", id);
		
	}
	//회원ID 인증
	public int confirmID(String id){
		
		String pwd =mybatis.selectOne("MemberDAO.confirmID", id);
		
		if(pwd !=null) {
			return 1;
		}else {
			return -1;
		}
	}
	
	//비밀번호와 아이디가 일치하는지 확인
	public int loginID(MemberVO vo) {
		int result = -1;
		String pwd = null;
		
		pwd =mybatis.selectOne("MemberDAO.confirmID", vo.getId()); //데이터 베이스에서 조회한 PASSWORD
		//데이터 베이스에서 조회한 PASSWORD와 사용자가 입력한 PASSWORD
		
		if(pwd == null) { // DB에서 비번 넘어오지 않음 --> 비번 일지 하지 않음
			result = -1;
		}else if(pwd.equals(vo.getPwd())) {//사용자가 입력한 비밀번호 vo.getPwd() 비교 일치
			result =1;
		}else { //비밀번호가 일치하지 않음
			result =0;
		}
		
		return result;
	}
	
	//회원 추가
	public void insertMember(MemberVO vo) {
		
		mybatis.insert("MemberDAO.insertMember", vo);
		
	}
	//동이름으로 우편번호 찾기
	public List<AddressVO> selectAddressByDong(String dong) {
		
		return mybatis.selectList("MemberDAO.selectAddressByDong", dong);
		 
		}
	
	public MemberVO getMemberByNameAndEmail(MemberVO vo) {
		return mybatis.selectOne("MemberDAO.getMemberByNameAndEmail",vo);
	}
	
	public MemberVO findPassword(MemberVO vo) {
		
		return mybatis.selectOne("MemberDAO.findPassword",vo);
	}
	
	public void changePassword(MemberVO vo) {
		mybatis.update("MemberDAO.changePassword", vo);
	}
}

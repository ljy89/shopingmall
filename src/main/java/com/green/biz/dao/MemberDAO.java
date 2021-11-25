package com.green.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.dto.AddressVO;
import com.green.biz.dto.MemberVO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// ȸ�� ������ ��ȸ
	public MemberVO getMember(String id) {
		
		return mybatis.selectOne("MemberDAO.getMember", id);
	}
	
	// ȸ��ID ���� Ȯ��
	public int confirmID(String id) {
		
		String pwd = mybatis.selectOne("MemberDAO.confirmID", id);
		
		if (pwd!=null) {
			return 1;
		} else {
			return -1;
		}
	}
	
	/*
	 * ȸ�� �α���
	 */
	public int loginID(MemberVO vo) {
		int result = -1;
		String pwd = null;
		
		pwd = mybatis.selectOne("MemberDAO.confirmID", vo.getId());
		
		// DB���� ��ȸ�� password�� ����ڰ� �Է��� password ��
		if (pwd == null) {
			result = -1;
		} else if (pwd.equals(vo.getPwd())) {	// id�� pwd�� ��ġ�ϴ� ���
			result = 1;
		} else {    // ��й�ȣ�� ��ġ���� �ʴ� ���
			result = 0;
		}
		
		return result;	
	}
	
	// ȸ�� �߰�
	public void insertMember(MemberVO vo) {
		
		mybatis.insert("MemberDAO.insertMember", vo);
	}
	
	// �� �̸����� �����ȣ ã��
	public List<AddressVO> selectAddressByDong(String dong) {
		
		return mybatis.selectList("MemberDAO.selectAddressByDong", dong);
	}
	
	public MemberVO getMemberByNameAndEmail(MemberVO vo) {
		
		return mybatis.selectOne("MemberDAO.getMemberByNameAndEmail", vo);
	}
	
	public MemberVO findPassword(MemberVO vo) {
		
		return mybatis.selectOne("MemberDAO.findPassword", vo);
	}
	
	public void changePassword(MemberVO vo) {
		
		mybatis.update("MemberDAO.changePassword", vo);
	}
	
	public List<MemberVO> listMember(String name){
		
		return mybatis.selectList("MemberDAO.listMember", name);
	}
}







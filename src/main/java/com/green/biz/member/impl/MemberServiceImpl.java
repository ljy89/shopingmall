package com.green.biz.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.dao.MemberDAO;
import com.green.biz.dto.AddressVO;
import com.green.biz.dto.MemberVO;
import com.green.biz.member.MemberService;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDao;
	
	@Override
	public int loginID(MemberVO vo) {
		
		return memberDao.loginID(vo);
	}
	
	@Override
	public MemberVO getMember(String id) {
		
		return memberDao.getMember(id);
	}

	@Override
	public int confirmID(String id) {
		
		return memberDao.confirmID(id);
	}

	@Override
	public void insertMember(MemberVO vo) {
		
		memberDao.insertMember(vo);
	}

	@Override
	public List<AddressVO> selectAddressByDong(String dong) {
		
		return memberDao.selectAddressByDong(dong);
	}

	@Override
	public MemberVO getMemberByNameAndEmail(MemberVO vo) {
		
		return memberDao.getMemberByNameAndEmail(vo);
	}

	@Override
	public MemberVO findPassword(MemberVO vo) {
		
		return memberDao.findPassword(vo);
	}

	@Override
	public void changePassword(MemberVO vo) {
		
		memberDao.changePassword(vo);
	}

	@Override
	public List<MemberVO> listMember(String name) {
		
		return memberDao.listMember(name);
	}

}

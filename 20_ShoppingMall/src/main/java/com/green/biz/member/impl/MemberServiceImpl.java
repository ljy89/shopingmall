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
	private MemberDAO mDao;

	@Override
	public MemberVO getMember(String id) {
		
		return  mDao.getMember(id);
	}

	@Override
	public int confirmID(String id) {
		
		return  mDao.confirmID(id);
	}

	@Override
	public void insertMember(MemberVO memberVO) {
		 mDao.insertMember(memberVO);
	}

	@Override
	public List<AddressVO> selectAddressByDong(String dong) {
		// TODO Auto-generated method stub
		return mDao.selectAddressByDong(dong);
	}

	@Override
	public int loginID(MemberVO vo) {
		
		return mDao.loginID(vo);
	}

	@Override
	public MemberVO getMemberByNameAndEmail(MemberVO vo) {
		
		return mDao.getMemberByNameAndEmail(vo);
	}

	@Override
	public MemberVO findPassword(MemberVO vo) {
		
		return mDao.findPassword(vo);
	}

	@Override
	public void changePassword(MemberVO vo) {
		mDao.changePassword(vo);
		
	}
	
	
	
}

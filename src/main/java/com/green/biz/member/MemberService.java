package com.green.biz.member;

import java.util.List;
import com.green.biz.dto.AddressVO;
import com.green.biz.dto.MemberVO;

public interface MemberService {

	// ȸ�� �α���
	public int loginID(MemberVO vo);
	
	// ȸ�� ������ ��ȸ
	MemberVO getMember(String id);

	// ȸ��ID ����
	int confirmID(String id);

	// ȸ�� �߰�
	void insertMember(MemberVO vo);
	
	// �����ȣ ã��
	public List<AddressVO> selectAddressByDong(String dong);
	
	// ������̸��� �̸����� �������� ��������� ��ȸ
	public MemberVO getMemberByNameAndEmail(MemberVO vo);
	
	// ����� ���̵�� �̸�, �̸����� �������� ��������� ��ȸ
	public MemberVO findPassword(MemberVO vo);
	
	// ����� ��ȣ ����
	public void changePassword(MemberVO vo);

	public List<MemberVO> listMember(String name);
}
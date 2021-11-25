package com.green.biz.member;

import java.util.*;
import com.green.biz.dto.AddressVO;
import com.green.biz.dto.MemberVO;

public interface MemberService {
	//ȸ�� �� ���� ��ȸ
	MemberVO getMember(String id);
	
	//ȸ�� id ����Ȯ��
	int confirmID(String id);	
	
	//��� ��ġ Ȯ��
	int loginID(MemberVO vo);
	
	//ȸ�� �߰�
	void insertMember(MemberVO vo);
	
	//�����ȣ ã��
	List<AddressVO> selectAddressByDong(String dong);
	
	//������̸��� �̸����� �������� ����� ���� ��ȸ
	MemberVO getMemberByNameAndEmail(MemberVO vo);
	
	//����� �̸�, �̸���, ���̵� �������� ����� ���� ��ȸ
	MemberVO findPassword(MemberVO vo);
	
	//��й�ȣ ����
	void changePassword(MemberVO vo);
}

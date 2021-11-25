package com.green.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.dto.CartVO;

@Repository
public class CartDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	// ��ٱ��� ��� ����
	public void insertCart(CartVO vo) {
		
		mybatis.insert("CartDAO.insertCart", vo);
	}
	
	// ��ٱ��� ��� ��ȸ
	public List<CartVO> listCart(String userid) {
		
		return mybatis.selectList("CartDAO.listCart", userid);
	}
	
	// ��ٱ��Ͽ��� �׸� ����
	public void deleteCart(int cseq) {
		
		mybatis.delete("CartDAO.deleteCart", cseq);
	}
	
	// ��ٱ��� �׸��� 'ó��'�� ������Ʈ
	public void updateCart(int cseq) {
		
		mybatis.update("CartDAO.updateCart", cseq);
	}
}







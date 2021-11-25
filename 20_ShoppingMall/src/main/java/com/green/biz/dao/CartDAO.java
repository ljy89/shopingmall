package com.green.biz.dao;

import java.util.*;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.dto.CartVO;
@Repository
public class CartDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	//��ٱ��Ͽ� ���
	public void insertCart(CartVO cartvo) {
		mybatis.insert("CartDAO.insertCart" , cartvo);
	}
	//��ٱ��� ����
	public List<CartVO> listCart(String userId){
		
		return  mybatis.selectList("CartDAO.listCart", userId);
		
		
	}
	//��ٱ��Ͽ��� ����
	public void deleteCart(int cseq) {
		mybatis.delete("CartDAO.deleteCart", cseq);
	}
	
	//��ٱ��� �׸� ó���� ������Ʈ
	public void updateCart(int cseq) {
		mybatis.update("CartDAO.updateCart", cseq);
	}
	
}

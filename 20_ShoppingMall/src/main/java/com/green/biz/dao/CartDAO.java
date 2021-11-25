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
	//장바구니에 담기
	public void insertCart(CartVO cartvo) {
		mybatis.insert("CartDAO.insertCart" , cartvo);
	}
	//장바구니 보기
	public List<CartVO> listCart(String userId){
		
		return  mybatis.selectList("CartDAO.listCart", userId);
		
		
	}
	//장바구니에서 빼기
	public void deleteCart(int cseq) {
		mybatis.delete("CartDAO.deleteCart", cseq);
	}
	
	//장바구니 항목 처리로 업데이트
	public void updateCart(int cseq) {
		mybatis.update("CartDAO.updateCart", cseq);
	}
	
}

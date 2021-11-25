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
	
	// 장바구니 담기 저장
	public void insertCart(CartVO vo) {
		
		mybatis.insert("CartDAO.insertCart", vo);
	}
	
	// 장바구니 목록 조회
	public List<CartVO> listCart(String userid) {
		
		return mybatis.selectList("CartDAO.listCart", userid);
	}
	
	// 장바구니에서 항목 삭제
	public void deleteCart(int cseq) {
		
		mybatis.delete("CartDAO.deleteCart", cseq);
	}
	
	// 장바구니 항목을 '처리'로 업데이트
	public void updateCart(int cseq) {
		
		mybatis.update("CartDAO.updateCart", cseq);
	}
}







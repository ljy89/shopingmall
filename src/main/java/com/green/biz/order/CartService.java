package com.green.biz.order;

import java.util.List;

import com.green.biz.dto.CartVO;

public interface CartService {

	// 장바구니 담기 저장
	void insertCart(CartVO vo);

	// 장바구니 목록 조회
	List<CartVO> listCart(String userid);

	// 장바구니에서 항목 삭제
	void deleteCart(int cseq);

	public void updateCart(int cseq);
}
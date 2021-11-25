package com.green.biz.order;

import java.util.List;

import com.green.biz.dto.CartVO;

public interface CartService {
	
	void insertCart(CartVO cartvo);
	List<CartVO> listCart(String userId);
	void deleteCart(int cseq);
	void updateCart(int cseq);
}

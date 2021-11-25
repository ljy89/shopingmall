package com.green.biz.order.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.order.CartService;
import com.green.biz.dao.CartDAO;
import com.green.biz.dto.CartVO;

@Service("cartService")
public class CartServiceImpl implements com.green.biz.order.CartService {
	@Autowired
	private CartDAO cDao;
	
	@Override
	public void insertCart(CartVO cartvo) {
		cDao.insertCart(cartvo);
		

	}

	@Override
	public List<CartVO> listCart(String userId) {
		
		return cDao.listCart(userId);
	}

	@Override
	public void deleteCart(int cseq) {
		cDao.deleteCart(cseq);
	}

	@Override
	public void updateCart(int cseq) {
		cDao.updateCart(cseq);
		
	}

}

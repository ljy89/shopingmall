package com.green.biz.order.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.dao.OrderDAO;
import com.green.biz.dto.CartVO;
import com.green.biz.dto.OrderVO;
import com.green.biz.order.CartService;
import com.green.biz.order.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDao;
	@Autowired
	private CartService cartService;
	
	@Override
	public int selectMaxOseq() {
		
		return orderDao.selectMaxOseq();
	}

	/*
	 * �ֹ����� ����
	 */
	@Override
	public int insertOrder(OrderVO vo) {
		int oseq = 0;
		
		
		oseq = selectMaxOseq();  
		
		// 주문 테이블에 주문 번호와 회원 아이디 저장
		vo.setOseq(oseq);
		orderDao.insertOrder(vo);
		
		//장바구니 목록을 읽어온다
		List<CartVO> cartList = cartService.listCart(vo.getId());
		
		// 장바구니의 내역을 order_detail 테이블에 저장
		for(CartVO cartVO : cartList) {
			OrderVO order = new OrderVO();
			order.setOseq(oseq);
			order.setPseq(cartVO.getPseq());
			order.setQuantity(cartVO.getQuantity());
			
			insertOrderDetail(order);
			
			// 장바구니 업데이트
			cartService.updateCart(cartVO.getCseq());
		}

		return oseq;
	}

	@Override
	public void insertOrderDetail(OrderVO vo) {
		
		orderDao.insertOrderDetail(vo);
	}

	@Override
	public List<OrderVO> listOrderById(OrderVO vo) {
		
		return orderDao.listOrderById(vo);
	}

	@Override
	public List<Integer> selectSeqOrdering(OrderVO vo) {
		
		return orderDao.selectSeqOrdering(vo);
	}

	@Override
	public List<OrderVO> listOrder(String key) {
		
		return orderDao.listOrder(key);
	}

	@Override
	public void updateOrderResult(int odseq) {
		
		orderDao.updateOrderResult(odseq);
		
	}

}








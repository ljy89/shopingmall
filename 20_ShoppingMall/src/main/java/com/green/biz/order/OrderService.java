package com.green.biz.order;

import java.util.List;

import com.green.biz.dto.OrderVO;

public interface OrderService {

	int selectMaxOseq();

	int insertOrder(OrderVO vo);

	void insertOrderDetail(OrderVO vo);
	
	List<OrderVO> listOrderById(OrderVO vo);
	
	 List<Integer> selectSeqOrdering(OrderVO vo);
}
package com.green.biz.dao;
import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.dto.CartVO;
import com.green.biz.dto.OrderVO;

@Repository
public class OrderDAO{
	//��ٱ����� ��ǰ�� �ֹ� ���̺� �ִ´�
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int selectMaxOseq() {
		
		return mybatis.selectOne("OrderDAO.selectMaxOseq");
	}
	
	
	public void insertOrder(OrderVO vo) {
		
		mybatis.insert("OrderDAO.insertOrder", vo);
	}
	
	public void insertOrderDetail(OrderVO vo) {
		mybatis.insert("OrderDAO.insertOrderDetail" , vo);
	}
	
	public List<OrderVO> listOrderById(OrderVO vo){
		
		return mybatis.selectList("OrderDAO.listOrderById", vo);
	}
	
	public List<Integer> selectSeqOrdering(OrderVO vo){
		
		return mybatis.selectList("OrderDAO.selectSeqOrdering", vo);
	}
}

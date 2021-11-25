package com.green.biz.dao;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.dto.ProductVO;

@Repository
public class ProductDAO {
	
	@Autowired //생성되어져 있는 객체 제공
	private SqlSessionTemplate mybatis;
	
	public List<ProductVO> getNewProductList(){
		
		return mybatis.selectList("ProductDAO.getNewProductList");
	}
	public List<ProductVO> getBestProductList() {
		return mybatis.selectList("ProductDAO.getBestProductList");
	}
	
	public ProductVO getProduct(ProductVO vo) {
		
		return mybatis.selectOne("ProductDAO.getProduct", vo);
	}

	public List<ProductVO> getProductListByKind(String kind) {
		return mybatis.selectList("ProductDAO.getProductListByKind", kind);
	}
	
	public int countProductList(String name) {
		return mybatis.selectOne("ProductDAO.countProductList",name);
	}
	
	public List<ProductVO> listProduct(String name){
		return mybatis.selectList("ProductDAO.listProcut", name);
	}
	
	public void insertProduct(ProductVO vo) {
		mybatis.insert("ProductDAO.insertProduct", vo);
	}
	
	public void updateProduct(ProductVO vo) {
		mybatis.update("ProductDAO.updateProduct", vo);
	}
	
}

package com.green.biz.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.dto.OrderVO;
import com.green.biz.dto.ProductVO;
import com.green.biz.dto.SalesQuantity;
import com.green.biz.utils.Criteria;

@Repository
public class ProductDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	
	public List<ProductVO> getNewProductList() {
		
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
		
		return mybatis.selectOne("ProductDAO.countProductList", name);
	}
	
	public List<ProductVO> listProduct(String name) {
		
		return mybatis.selectList("ProductDAO.listProduct", name);
	}
	
	public void insertProduct(ProductVO vo) {
		
		mybatis.insert("ProductDAO.insertProduct", vo);
	}
	
	public void updateProduct(ProductVO vo) {
		
		mybatis.update("ProductDAO.updateProduct", vo);
	}
	
	public List<ProductVO> getListWithPaging(Criteria criteria, String key){
		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("key", key);
		return mybatis.selectList("ProductDAO.listWithPaging" ,map);
	}
	
	//상품별 판매 실적
	
	public List<SalesQuantity> getProductSales(){
		
		return mybatis.selectList("ProductDAO.getProductSales");
	}
	
	
	
}










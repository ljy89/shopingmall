package com.green.biz.product;

import java.util.List;

import com.green.biz.dto.ProductVO;

public interface ProductService {
	
	public List<ProductVO> getNewProductList();
	public List<ProductVO> getBestProductList();
	
	public ProductVO getProduct(ProductVO vo);

	public List<ProductVO> getProductListByKind(String kind);
	
	public int countProductList(String name);
	
	public List<ProductVO> listProduct(String name);
	
	public void insertProduct(ProductVO vo);
	
	public void updateProduct(ProductVO vo);
}

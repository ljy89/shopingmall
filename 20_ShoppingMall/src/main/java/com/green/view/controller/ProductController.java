package com.green.view.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.green.biz.dto.ProductVO;
import com.green.biz.product.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String ProductKindAction(ProductVO vo, Model model) {
	
		List<ProductVO> listProduct = productService.getProductListByKind(vo.getKind());
		model.addAttribute("productKindList", listProduct);
		return "product/productKind"; 
	}
	
	@RequestMapping(value = "/product_detail", method = RequestMethod.GET)
	public String productDetailAction(ProductVO vo, Model model) {
	
		//상품의 상세 조회 조회 서비스 호출
		ProductVO product = productService.getProduct(vo);
		model.addAttribute("productVO", product);
		return "product/productDetail"; 
	}
}

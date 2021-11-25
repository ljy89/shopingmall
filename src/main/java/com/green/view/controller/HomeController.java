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
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home(Model model) {
		
		// �Ż�ǰ ��ȸ ���� ȣ��
		List<ProductVO> newProdList = productService.getNewProductList();
		model.addAttribute("newProductList", newProdList);
		
		// ����Ʈ��ǰ ��ȸ ����
		List<ProductVO> bestProdList = productService.getBestProductList();
		model.addAttribute("bestProductList", bestProdList);
		
		return "index"; 	// index.jsp ���� ���
	}
	
}

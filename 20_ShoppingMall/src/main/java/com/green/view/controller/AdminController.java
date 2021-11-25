package com.green.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.green.biz.admin.AdminService;
import com.green.biz.dto.ProductVO;
import com.green.biz.dto.WorkerVO;
import com.green.biz.product.ProductService;

@Controller
@SessionAttributes("adminUser")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/admin_login_form")
	public String adminLoginView() {
		
		
		return "admin/main";
	}
	
	@RequestMapping(value="/admin_login")
	public String adminLogin(@RequestParam(value="workerId") String workerId,
							@RequestParam(value="workerPwd") String workerPwd ,Model model) {
		
		int result = adminService.workerCheck(workerId, workerPwd);
		if(result ==1) { //정상 로그인 -> 제품 목록 화면으로 이동
			WorkerVO adminUser = adminService.getEmployee(workerId);
			model.addAttribute("adminUser" ,adminUser);
			
			return "redirect:admin_product_list";
		}else {
			if(result ==0) {
				model.addAttribute("message", "비밀번호를 확인하세요");
			}else {
				model.addAttribute("message", "아이디를 확인하세요");
			}
			return "admin/main";
		}
		
		
	}
	
	@RequestMapping(value="admin_product_list")
	public String adminProductList(@RequestParam(value="key", defaultValue="")String key,
			HttpSession session, Model model) {
		WorkerVO adminUser = (WorkerVO)session.getAttribute("adminUser");
		
		if(adminUser == null) { //관리자로 로그인 되어있지 않을때
			return "admin/main";
		}else {
			List<ProductVO> prodList = productService.listProduct(key);
			
			model.addAttribute("productListSize",prodList.size());
			model.addAttribute("productList", prodList);
			
			return "admin/product/productList";
		}
	}
	//상품 등록 페이지 표시
	
	@RequestMapping(value="admin_product_write_form")
	public String adminProductWriteView(Model model){
		String kindList[] = {"Heels", "Boots", "Sandals", "Slipers", "Sneekers", "Sale"};
		model.addAttribute("kindList", kindList);
		
		return "admin/product/productWrite";
	}
	
	// 상품 등록 처리
	@RequestMapping(value="admin_product_write")
	public String adminProductWrite(ProductVO vo) {
		System.out.println("등록제품= "+vo);
		return "";
	}
}

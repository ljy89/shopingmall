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
		if(result ==1) { //���� �α��� -> ��ǰ ��� ȭ������ �̵�
			WorkerVO adminUser = adminService.getEmployee(workerId);
			model.addAttribute("adminUser" ,adminUser);
			
			return "redirect:admin_product_list";
		}else {
			if(result ==0) {
				model.addAttribute("message", "��й�ȣ�� Ȯ���ϼ���");
			}else {
				model.addAttribute("message", "���̵� Ȯ���ϼ���");
			}
			return "admin/main";
		}
		
		
	}
	
	@RequestMapping(value="admin_product_list")
	public String adminProductList(@RequestParam(value="key", defaultValue="")String key,
			HttpSession session, Model model) {
		WorkerVO adminUser = (WorkerVO)session.getAttribute("adminUser");
		
		if(adminUser == null) { //�����ڷ� �α��� �Ǿ����� ������
			return "admin/main";
		}else {
			List<ProductVO> prodList = productService.listProduct(key);
			
			model.addAttribute("productListSize",prodList.size());
			model.addAttribute("productList", prodList);
			
			return "admin/product/productList";
		}
	}
	//��ǰ ��� ������ ǥ��
	
	@RequestMapping(value="admin_product_write_form")
	public String adminProductWriteView(Model model){
		String kindList[] = {"Heels", "Boots", "Sandals", "Slipers", "Sneekers", "Sale"};
		model.addAttribute("kindList", kindList);
		
		return "admin/product/productWrite";
	}
	
	// ��ǰ ��� ó��
	@RequestMapping(value="admin_product_write")
	public String adminProductWrite(ProductVO vo) {
		System.out.println("�����ǰ= "+vo);
		return "";
	}
}

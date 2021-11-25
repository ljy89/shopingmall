package com.green.view.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.green.biz.admin.AdminService;
import com.green.biz.dto.MemberVO;
import com.green.biz.dto.OrderVO;
import com.green.biz.dto.ProductVO;
import com.green.biz.dto.QnaVO;
import com.green.biz.dto.SalesQuantity;
import com.green.biz.dto.WorkerVO;
import com.green.biz.member.MemberService;
import com.green.biz.order.OrderService;
import com.green.biz.product.ProductService;
import com.green.biz.qna.QnaService;
import com.green.biz.utils.Criteria;
import com.green.biz.utils.PageMaker;

@Controller
@SessionAttributes("adminUser")
public class AdminController {

	@Autowired 
	private AdminService adminService;
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private QnaService qnaService;
	
	@RequestMapping(value="/admin_login_form")
	public String adminLoginView() {
		
		return "admin/main";
	}
	
	@RequestMapping(value="/admin_login")
	public String adminLogin(@RequestParam(value="workerId") String workerId,
							 @RequestParam(value="workerPwd") String workerPwd,
							 Model model) {
		
		int result = adminService.workerCheck(workerId, workerPwd);
		
		if (result == 1) { // ���� �α��� -> ��ǰ��� ȭ������ �̵�
			WorkerVO adminUser = adminService.getEmployee(workerId);
			
			model.addAttribute("adminUser", adminUser);
			
			return "redirect:admin_product_list";
		} else {
			if (result == 0) {
				model.addAttribute("message", "��й�ȣ�� Ȯ���ϼ���!");
			} else {
				model.addAttribute("message", "���̵� Ȯ���ϼ���!");
			}
			
			return "admin/main";
		}
	}
	
	@RequestMapping(value="admin_logout")
	public String adminLogout(SessionStatus status) {
		
		status.setComplete();
		
		return "admin/main";
	}
	
	@RequestMapping(value="admin_product_list")
	public String adminProductList(
			@RequestParam(value="key", defaultValue="") String key,
			Criteria criteria, HttpSession session, Model model) {
		
		WorkerVO adminUser = (WorkerVO)session.getAttribute("adminUser");
		
		if (adminUser == null) {
			return "admin/main";
		} else {
			//List<ProductVO> prodList = productService.listProduct(key);
			
			List<ProductVO> prodList = productService.getListWithPaging(criteria, key);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(criteria); //현재 페이지와 페이지당 항목 수 설정
	
			//전체 게시글의 수 조회
			int totalCount = productService.countProductList(key);
			pageMaker.setTotalCount(totalCount);
			System.out.println("페이징 정보 =" +pageMaker);
			
			model.addAttribute("productListSize", prodList.size());
			model.addAttribute("productList", prodList);
			model.addAttribute("pageMaker", pageMaker);
			
			return "admin/product/productList";
		}
	}
	
	/*
	 * ��ǰ ��������� ǥ��
	 */
	@RequestMapping(value="admin_product_write_form")
	public String adminProductWriteView(Model model) {
		String kindList[] = {"Heels", "Boots", "Sandals", "Slipers", "Sneekers", "Sale"};
		
		model.addAttribute("kindList", kindList);
		
		return "admin/product/productWrite";
	}
	
	/*
	 * ��ǰ ��� ó��
	 */
	@RequestMapping(value="admin_product_write")
	public String adminProductWrite(@RequestParam(value="product_image") MultipartFile uploadFile,
					ProductVO vo, HttpSession session) {
	
		WorkerVO adminUser = (WorkerVO) session.getAttribute("adminUser");
		
		if (adminUser == null) {
			return "admin/main";
		} else {
			String fileName = "";
			
			if (!uploadFile.isEmpty()) {	// ȭ�鿡�� product_image �ʵ忡 �̹����� �Էµ� ���
				fileName = uploadFile.getOriginalFilename();
				vo.setImage(fileName); // VO ��ü�� �̹������ϸ� ����
				
				// �̹��� ������ ���ε� �ϱ� ���� �̹��� ���� ���� ��θ� ���Ѵ�.
				String image_path 
				= session.getServletContext().getRealPath("WEB-INF/resources/product_images/");
				System.out.println("�̵� �̹��� ���: " + image_path);
				
				try {
					File file = new File(image_path+fileName);
					uploadFile.transferTo(file); // ��ǰ�̹��� ���� ��η� �̵���Ŵ
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}	
			}
			
			System.out.println("�����ǰ=" + vo);
			
			productService.insertProduct(vo);
			
			return "redirect:admin_product_list";
		}
		
	}
	
	@RequestMapping(value="admin_product_detail")
	public String adminProductDetail(ProductVO vo, Model model) {
		String[] kindList = {"", "Heels", "Boots", "Sandals",
						     "Slipers", "Sneekers", "Sale"};
		
		
		ProductVO product = productService.getProduct(vo);
		
		model.addAttribute("productVO", product);	
		
		int index = Integer.parseInt(product.getKind());  
		model.addAttribute("kind", kindList[index]);
		
		return "admin/product/productDetail";
	}
	
	@RequestMapping(value="admin_product_update_form")
	public String adminProductUpdateView(ProductVO vo, Model model) {
		String[] kindList = {"Heels", "Boots", "Sandals",
			     "Slipers", "Sneekers", "Sale"};
		
		ProductVO product = productService.getProduct(vo);
		
		model.addAttribute("productVO", product);	// ȭ�鿡 ������ ��ǰ������	
		model.addAttribute("kindList", kindList);
		
		return "admin/product/productUpdate";
	}
	
	@RequestMapping(value="admin_product_update")
	public String adminProductUpdate(@RequestParam(value="product_image") MultipartFile uploadFile,
							ProductVO vo, HttpSession session) {
		WorkerVO adminUser = (WorkerVO) session.getAttribute("adminUser");

		if (adminUser == null) {
			return "admin/main";
		} else {
			
			String fileName = "";
			
			if (!uploadFile.isEmpty()) {	// ȭ�鿡�� product_image �ʵ忡 �̹����� �Էµ� ���
				fileName = uploadFile.getOriginalFilename();
				vo.setImage(fileName); // VO ��ü�� �̹������ϸ� ����
				
				// �̹��� ������ ���ε� �ϱ� ���� �̹��� ���� ���� ��θ� ���Ѵ�.
				String image_path 
				= session.getServletContext().getRealPath("WEB-INF/resources/product_images/");
				System.out.println("�̵� �̹��� ���: " + image_path);
				
				try {
					File file = new File(image_path+fileName);
					uploadFile.transferTo(file); // ��ǰ�̹��� ���� ��η� �̵���Ŵ
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}	
			}
			
			if (vo.getUseyn() == null) {
				vo.setUseyn("n");
			}
			if (vo.getBestyn() == null) {
				vo.setBestyn("n");
			}
			
			
			System.out.println("��ǰ����="+vo);
			productService.updateProduct(vo);
			
			return "redirect:admin_product_detail?pseq="+vo.getPseq();
		}
		
		
	}
	
	@RequestMapping(value="admin_order_list")
	public String adminOrderList(@RequestParam(value="key", defaultValue="")String key, Model model) {
		
		
		List<OrderVO> orderList = orderService.listOrder(key);
		model.addAttribute("orderList", orderList);
		return "admin/order/orderList";
	}
	
	/*
	 * 주문 완료 처리(입금확인)
	 * */
	
	@RequestMapping(value="admin_order_save")
	public String adminOrderSave(@RequestParam(value="result") int[] odseq) {
		for(int i=0; i<odseq.length; i++) {
			orderService.updateOrderResult(odseq[i]);
		}
		
		return "redirect:admin_order_list";
	}
	
	//회원 목록 조회 처리
	
	@RequestMapping(value="admin_member_list")
	public String adminMemberList(@RequestParam(value="key" , defaultValue="")String name, Model model) {
		
		List<MemberVO> listMember = memberService.listMember(name);
		
		model.addAttribute("memberList", listMember); //검색 결과 저장
		
		return "admin/member/memberList";
	}
	
	//Q&A 목록 출력
	@RequestMapping(value = "admin_qna_list")
	public String adminQnaList(Model model) {
		
		List<QnaVO> qnaList = qnaService.listAllQna();
		
		model.addAttribute("qnaList",qnaList);
		return "admin/qna/qnaList";
	}
	
	
	//클릭한 큐엔에이 정보 조회해서 출력
	@RequestMapping(value = "admin_qna_detail")
	public String adminQnaDetail(QnaVO vo , Model model) {
		QnaVO qna = qnaService.getQna(vo.getQseq());
		
		model.addAttribute("qnaVO", qna);
		return "admin/qna/qnaDetail";
	}
	
	//Q&A에 대한 관리자 답변 처리
	
	@RequestMapping(value="admin_qna_repsave")
	public String adminQnaRepSave(QnaVO vo) {
		
		qnaService.updateQna(vo);
		
		return "redirect:admin_qna_list";
	}
	
	//상품별 판매 실적화면 출력
	
	@RequestMapping(value="admin_sales_record_form")
	public String adminProductSalesChart() {
		
		return "admin/order/salesRecords";
	}
	
	//상품별 판매 실적 데이터 조회 및 전송(JSON 데이터 포멧 전송)
	@RequestMapping(value="sales_record_chart" , produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<SalesQuantity> salesRecordChart(){
		
		List<SalesQuantity> listSales = productService.getProductSales();
		
		for(SalesQuantity item : listSales) {
			System.out.println(item);
		}
		
		return listSales;
	}
}


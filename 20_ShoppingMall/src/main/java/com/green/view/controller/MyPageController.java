package com.green.view.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.biz.dto.CartVO;
import com.green.biz.dto.MemberVO;
import com.green.biz.dto.OrderVO;
import com.green.biz.order.impl.CartServiceImpl;
import com.green.biz.order.impl.OrderServiceImpl;

@Controller
public class MyPageController {
	@Autowired
	private CartServiceImpl cartService;
	
	@Autowired
	private OrderServiceImpl orderService;
	//value --> product.js에서 보내준 url로 받아줌
	
	@RequestMapping(value="/cart_insert", method=RequestMethod.POST)
	public  String CartInsert(CartVO vo, Model model, HttpSession session) {
		String url = "member/login";
		
		MemberVO loginUser =(MemberVO) session.getAttribute("loginUser");

		if(loginUser == null) { // 현재 로그인 되어있지 않은 상태
			return url;
		}else {
			vo.setId(loginUser.getId());
			//장바구니 저장을 위해 서비스 호출
			cartService.insertCart(vo);
			//장바구니 목록 요청
			return "redirect:cart_list"; //장바구니 목록을 조회하여 장바구니 목록 화면 표시
		}
		
		
	}
	
	@RequestMapping(value="/cart_list")
	public String cartList(HttpSession session, Model model) {
		String url = null;
		//세션에 저장된 로그인 정보를 가져온다.
		MemberVO loginUser =(MemberVO) session.getAttribute("loginUser");
		
		if(loginUser == null) { // 현재 로그인 되어있지 않은 상태
			url="member/login";
		}else {
			List<CartVO> cartList = cartService.listCart(loginUser.getId());
			//총액계산
			int totalAmount = 0;
			for(CartVO vo : cartList) {
				totalAmount += vo.getQuantity() * vo.getPrice2();
			}
			
			model.addAttribute("cartList", cartList);
			model.addAttribute("totalPrice", totalAmount);
			url ="mypage/cartList";
		}
		
		return url;
	}
	
	@RequestMapping(value="/cart_delete")
	public String cartDelete(@RequestParam(value="cseq") int[] cseq) {
		
		for(int i=0; i<cseq.length;i++) {
			System.out.println("삭제할 cart seq= "+ cseq[i]);
			cartService.deleteCart(cseq[i]);
		}
		return "redirect:cart_list";
	}
	
	@RequestMapping(value="/order_insert")
	public String orderInsert(HttpSession session, OrderVO vo, Model model) {
		String url = "redirect:order_list";
		int oseq = 0;
		//세션에 저장되 로그인 정보를 읽어옴
		MemberVO loginUser =(MemberVO) session.getAttribute("loginUser");
		
		if(loginUser == null) { // 현재 로그인 되어있지 않은 상태
			url="member/login";
			
		}else {
			vo.setId(loginUser.getId());
			
			oseq = orderService.insertOrder(vo);
			
			//주문목록을 표시할 때, 주문 번호를 전달
			model.addAttribute("oseq", oseq);
			}
		return url;
	}
	
	//주문 내역출력
	@RequestMapping(value="/order_list")
	public String orderList(@RequestParam(value="oseq") int  oseq, HttpSession session, OrderVO order, Model model) {
		
		
		MemberVO loginUser=(MemberVO) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			 return "member/login";
		}else {
			order.setId(loginUser.getId());
			order.setOseq(oseq);
			order.setResult("1");
			
			//사용자별 미처리 주문 내역 조회
			List<OrderVO> orderList = orderService.listOrderById(order);
			
			//주문 총액 계산
			int totalAmount =0;
			for(OrderVO vo: orderList) {
				totalAmount += (vo.getQuantity() * vo.getPrice2());
			}
			
			model.addAttribute("orderList" , orderList);
			model.addAttribute("totalPrice", totalAmount);
			
			return "mypage/orderList";
		}
		
		
	}
	
	@RequestMapping(value="mypage")
	public String myPageView(HttpSession session, Model model) {
		MemberVO loginUser=(MemberVO) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			 return "member/login";
		}else {
			
			//사용자가 주문한 모든 주문 번호를 조회
			OrderVO vo = new OrderVO();
			vo.setId(loginUser.getId());
			vo.setResult("1");
			List<Integer> oseqList = orderService.selectSeqOrdering(vo);
			
			//주문 요약 정보 저장 리스트 변수 생성
			List<OrderVO> orderList = new ArrayList<OrderVO>();
			
			//모든 주문 번호에 대해 주문 요약 정보 생성
			for(int oseq : oseqList) {
				
				// 각 주문 번호에 대해 주문 내역 조회
				OrderVO orderVO = new OrderVO();
				orderVO.setId(loginUser.getId());
				orderVO.setOseq(oseq);
				orderVO.setResult("1");
				//각 주문 번호에 대해 주문  내역 조회
				List<OrderVO> listByOseq = orderService.listOrderById(orderVO);
				//위에서 조회한 주문 내역의 요약 정보 생성
				OrderVO order = new OrderVO();
				
				order.setOseq(listByOseq.get(0).getOseq());
				order.setIndate(listByOseq.get(0).getIndate());
				if(listByOseq.size()>1) {
					order.setPname(listByOseq.get(0).getPname()+ " 외"+(listByOseq.size()-1)+"건");
				}else {
					order.setPname(listByOseq.get(0).getPname());
				}
				
				
				int amount =0;
				for(int i=0; i<listByOseq.size(); i++) {
					amount += listByOseq.get(i).getQuantity() * listByOseq.get(i).getPrice2();
				}
				
				order.setPrice2(amount);
				orderList.add(order);
			}
			model.addAttribute("title", "진행중인 주문 내역");
			model.addAttribute("orderList",orderList);
		}
		
		return "mypage/mypage";
		
	}
	
	
	@RequestMapping(value="order_detail")
	public String orderDetailView(HttpSession session, OrderVO vo,Model model) {
	
		MemberVO loginUser=(MemberVO) session.getAttribute("loginUser");
		
		if(loginUser == null) { // 현재 로그인 되어있지 않은 상태
			return "member/login";
			
		}else {
			vo.setId(loginUser.getId());
			vo.setResult("");
			
			List<OrderVO> orderList = orderService.listOrderById(vo);
			//주문자 정보 생성
			OrderVO orderDetail = new OrderVO();
			orderDetail.setIndate(orderList.get(0).getIndate());
			orderDetail.setOseq(orderList.get(0).getOseq());
			orderDetail.setMname(orderList.get(0).getMname());
			orderDetail.setResult(orderList.get(0).getResult());
			
			//주문 합계 금액
			int amount =0;
			
			for(int i=0; i<orderList.size(); i++) {
				amount += (orderList.get(i).getQuantity() * orderList.get(i).getPrice2());
			}
			model.addAttribute("orderDetail",orderDetail);
			model.addAttribute("totalPrice", amount);
			model.addAttribute("orderList" , orderList);
			model.addAttribute("title", "My page(주문 상세 정보)");
			return "mypage/orderDetail";
		}
		
		
	}
	//총 주문 내역 조회 처리 
	// 처리 결과에 상관없이 사용자의 모든 주문을 조회  -- >처리 결과를 지정하지 않음
	@RequestMapping(value="order_all")
	public String orderAllView(HttpSession session, OrderVO vo,Model model) {
		
		MemberVO loginUser=(MemberVO) session.getAttribute("loginUser");
		
		if(loginUser == null) { // 현재 로그인 되어있지 않은 상태
			return "member/login";
			
		}else {
			//사용자의 전체 주문 번호 조회
			vo.setId(loginUser.getId());
			vo.setResult(""); // 처리결과를 지정하지 않음
			
			List<Integer> oseqList = orderService.selectSeqOrdering(vo);
			
			//요약 정보 목록을  저장할 LIST 변수
			List<OrderVO> orderList =new ArrayList<>();
			
				for(int oseq : oseqList) {
				//주문 목록 조회를 위한 입력 설정
					
				OrderVO orderVo = new OrderVO();
				
				orderVo.setId(loginUser.getId());
				orderVo.setOseq(oseq);
				orderVo.setResult("");
				
				//주문번호 별 주문 내역
				List<OrderVO> orders= orderService.listOrderById(orderVo);
				
				//주문 요약 정보 생성
				OrderVO summary = new OrderVO();
				summary = orders.get(0);
				
				if(orders.size() >1) {
					summary.setPname(summary.getPname() + " 외"+(orders.size()-1) + "건");
				}else {
					summary.setPname(summary.getPname());
				}
				
				int amount =0;
				
				for(OrderVO order : orders) {
					amount += order.getQuantity() * order.getPrice2();
				}
				summary.setPrice2(amount);
				
				//주문 요약 정보를 리스트에 추가
				orderList.add(summary);
			}
			
			
			model.addAttribute("title", "총 주문 내역");
			model.addAttribute("orderList", orderList);
			
			return "mypage/mypage";
		}
	}
	
}

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
import com.green.biz.order.CartService;
import com.green.biz.order.OrderService;

@Controller
public class MyPageController {

	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/cart_insert", method=RequestMethod.POST)
	public String cartInsert(CartVO vo, Model model, HttpSession session) {
		String url = "member/login";
		
		
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) { // ���� �α��� �ȵ� ������
			return url;
		} else {
			vo.setId(loginUser.getId());
			
			cartService.insertCart(vo);
			
			return "redirect:cart_list";  // ��ٱ��� ����� ��ȸ�Ͽ� ��ٱ��� ��� ȭ�� ǥ��
		}
	}
	
	@RequestMapping(value="/cart_list")
	public String cartList(HttpSession session, Model model) {
		String url = null;
		
		// ���ǿ� ����� �α��� ������ �о��
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			url = "member/login";
		} else {
			List<CartVO> cartList = cartService.listCart(loginUser.getId());
			
			// �Ѿ� ���
			int totalAmount = 0;
			for (CartVO vo : cartList) {
				totalAmount += vo.getQuantity() * vo.getPrice2();
			}
			
			model.addAttribute("cartList", cartList);
			model.addAttribute("totalPrice", totalAmount);
			
			url = "mypage/cartList";
		}
		
		return url;
	}
	
	@RequestMapping(value="/cart_delete")
	public String cartDelete(@RequestParam(value="cseq") int[] cseq) {
		
		for (int i=0; i<cseq.length; i++) {
			System.out.println("������ cart seq=" + cseq[i]);
			cartService.deleteCart(cseq[i]);
		}
		
		return "redirect:cart_list";
	}
	
	@RequestMapping(value="/order_insert")
	public String orderInsert(OrderVO vo, HttpSession session, Model model) {
		String url = "redirect:order_list";
		int oseq = 0;
		
		
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			url = "member/login";
		} else {
			vo.setId(loginUser.getId());
			
			oseq = orderService.insertOrder(vo);
			
			// �ֹ������ ǥ���� ��, �ֹ���ȣ�� ����
			model.addAttribute("oseq", oseq);
		}
		
		return url;
	}
	
	/*
	 * �ֹ����� ���
	 */
	@RequestMapping(value="/order_list")
	public String orderList(@RequestParam(value="oseq") int oseq, HttpSession session, 
			OrderVO order, Model model) {
		// ���ǿ� ����� �α��� ������ �о��
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login";
		} else {
			order.setId(loginUser.getId());
			order.setOseq(oseq);
			order.setResult("1");
			
			// ����ں� ��ó�� �ֹ����� ��ȸ
			List<OrderVO> orderList = orderService.listOrderById(order);
			
			// �ֹ� �Ѿ� ���
			int totalAmount = 0;
			for (OrderVO vo : orderList) {
				totalAmount += (vo.getQuantity() * vo.getPrice2());
			}
			
			model.addAttribute("orderList", orderList);
			model.addAttribute("totalPrice", totalAmount);
			
			return "mypage/orderList";
		}

	}
	
	@RequestMapping(value="mypage")
	public String myPageView(HttpSession session, Model model) {
		// ���ǿ� ����� �α��� ������ �о��
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login";
		} else {
			
			// ����ڰ� �ֹ��� ��� �ֹ���ȣ�� ��ȸ
			OrderVO vo = new OrderVO();
			vo.setId(loginUser.getId());
			vo.setResult("1");
			List<Integer> oseqList = orderService.selectSeqOrdering(vo);
			
			// �ֹ���� ���� ���� ����Ʈ ���� ����
			List<OrderVO> orderList = new ArrayList<OrderVO>();
			
			// ��� �ֹ���ȣ�� ���� �ֹ�������� ����
			for(int oseq : oseqList) {
				OrderVO orderVO = new OrderVO();
				
				orderVO.setId(loginUser.getId());
				orderVO.setOseq(oseq);
				orderVO.setResult("1");
				// �� �ֹ���ȣ�� ���� �ֹ����� ��ȸ
				List<OrderVO> listByOseq = orderService.listOrderById(orderVO);
				
				// ������ ��ȸ�� �ֹ������� ������� ����
				OrderVO order = new OrderVO();
				order.setOseq(listByOseq.get(0).getOseq());
				order.setIndate(listByOseq.get(0).getIndate());
				if (listByOseq.size() > 1) {
					order.setPname(listByOseq.get(0).getPname() + " ��" + (listByOseq.size()-1) +"��");
				} else {
					order.setPname(listByOseq.get(0).getPname());
				}
				int amount = 0;
				for(int i=0; i<listByOseq.size(); i++) {
					amount += listByOseq.get(i).getQuantity() * listByOseq.get(i).getPrice2();
				}
				
				order.setPrice2(amount);
				
				orderList.add(order);
			}
			
			model.addAttribute("title", "�������� �ֹ� ����");
			model.addAttribute("orderList", orderList);
		}
		
		return "mypage/mypage";
	}
	
	@RequestMapping(value="/order_detail")
	public String orderDetail(OrderVO vo, HttpSession session, Model model) {
		// ���ǿ� ����� �α��� ������ �о��
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login";
		} else {
			
			vo.setId(loginUser.getId());
			vo.setResult("");
			
			List<OrderVO> orderList = orderService.listOrderById(vo);

			// �ֹ��� ���� ����
			OrderVO orderDetail = new OrderVO();
			orderDetail.setIndate(orderList.get(0).getIndate());
			orderDetail.setOseq(orderList.get(0).getOseq());
			orderDetail.setMname(orderList.get(0).getMname());
			orderDetail.setResult(orderList.get(0).getResult());
			
			// �ֹ� �հ� �ݾ� ���
			int amount = 0;
			for (int i=0; i<orderList.size(); i++) {
				amount += orderList.get(i).getQuantity() * orderList.get(i).getPrice2();
			}
			
			model.addAttribute("orderDetail", orderDetail);
			model.addAttribute("totalPrice", amount);
			model.addAttribute("orderList", orderList);
			model.addAttribute("title", "My Page(�ֹ� �� ����)");
			
			return "mypage/orderDetail";
		}
	}
	
	/*
	 * ���ֹ����� ��ȸ ó��
	 * ó������� ������� �������  ��� �ֹ��� ��ȸ
	 */
	@RequestMapping(value="order_all")
	public String orderAllView(OrderVO vo, HttpSession session, Model model) {
		
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login";
		} else {
			// ������� ��ü �ֹ���ȣ ��ȸ
			vo.setId(loginUser.getId());
			vo.setResult("");  // ó������� �������� ����
			
			List<Integer> oseqList = orderService.selectSeqOrdering(vo);
			
			// ������� ��� ����� List����
			List<OrderVO> orderList = new ArrayList<>();
			for (int oseq : oseqList) {
				// �ֹ���� ��ȸ�� ���� �Է� ����
				OrderVO orderVo = new OrderVO();
				orderVo.setId(loginUser.getId());
				orderVo.setOseq(oseq);
				orderVo.setResult("");
				
				// �ֹ���ȣ�� �ֹ�����
				List<OrderVO> orders = orderService.listOrderById(orderVo);
				// �ֹ� ������� ����
				OrderVO summary = new OrderVO();
				summary = orders.get(0);
				if (orders.size() > 1) {
					summary.setPname(summary.getPname() + " �� " + (orders.size()-1) + "��");
				} else {
					summary.setPname(summary.getPname());
				}
				
				int amount = 0;
				for (OrderVO order: orders) {
					amount += order.getQuantity() * order.getPrice2();
				}
				
				summary.setPrice2(amount);
				
				// �ֹ���������� ��� ����Ʈ�� �߰�
				orderList.add(summary);
			}
			
			model.addAttribute("title", "�� �ֹ�����");
			model.addAttribute("orderList", orderList);
			
			return "mypage/mypage";
		}
	}
}







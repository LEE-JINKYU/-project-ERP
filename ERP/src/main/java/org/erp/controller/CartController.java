package org.erp.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.erp.model.CartDTO;
import org.erp.model.CriteriaVO;
import org.erp.model.ProductVO;
import org.erp.model.pageVO;
import org.erp.service.CartService;
import org.erp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CartController {
	
	@Autowired
	ProductService ps;
	@Autowired
	CartService cts;

	// -----------------------------------------------------------------------------------------
	// 사원 구매 신청 화면 이동
	// -----------------------------------------------------------------------------------------
	@RequestMapping(value = "/main/purchase", method = RequestMethod.GET)
	public String purchase(HttpServletRequest request, CriteriaVO product, CriteriaVO cri, CriteriaVO cartlist,
			Model model) {

		HttpSession session = request.getSession();
		String empno = (String) session.getAttribute("empno");
		
		cartlist.setEmpno(empno);
		cts.purchase_list(cartlist);
		cri.setEmpno(empno);
		cts.purchase_total(cri);

		// 거래처 리스트 출력을 위해 ProductService의 group_company 함수를 호출한다.
		model.addAttribute("productlist", ps.group_company(product));
		// 전체 카트 리스트를 출력한다.
		model.addAttribute("cartInfo", cts.getCartList(cartlist));
		
		// 페이징을 위한 total 데이터 수 확인
		int total = cts.purchase_total(cri);
		model.addAttribute("paging", new pageVO(cri, total));
		
		return "/main/purchase";
	}

	// 카트 화면에서 업체 선택
	@RequestMapping(value = "/main/company_select", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<ProductVO>> company_select(ProductVO product, Model model) {
		ArrayList<ProductVO> result = ps.company_select(product);
		return new ResponseEntity<ArrayList<ProductVO>>(result, HttpStatus.OK);
	}

	// 카트 화면에서 상품 선택
	@RequestMapping(value = "/main/product_select", method = RequestMethod.GET)
	public ResponseEntity<ProductVO> product_select(ProductVO product) {
		ProductVO result = ps.product_select(product);
		return new ResponseEntity<ProductVO>(result, HttpStatus.OK);
	}

	// 카트 추가
	@RequestMapping(value = "/cart/add", method = RequestMethod.POST)
	public String addCartPOST(CartDTO cart, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String empno = (String) session.getAttribute("empno");
		cart.setEmpno(empno);
		cts.addCart(cart);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	// 카트 정보 수정
	@RequestMapping(value = "/cart/modifypost", method = RequestMethod.POST)
	public String cart_modifypost(CartDTO cart) {
		cts.cart_modifypost(cart);
		return "redirect:/main/purchase";
	}

	// 카트 정보 불러오기
	@RequestMapping(value = "/cart/modify", method = RequestMethod.GET)
	public ResponseEntity<CartDTO> cart_modify(CartDTO cart) {
		CartDTO result = cts.cart_modify(cart);
		System.out.println(cart);
		return new ResponseEntity<CartDTO>(result, HttpStatus.OK);
	}

	// 카트 삭제 설계
	@RequestMapping(value = "/cart/delete", method = RequestMethod.GET)
	public String deleteCart(CartDTO cartid) {
		cts.deleteCart(cartid);
		return "redirect:/main/purchase";
	}

	// -----------------------------------------------------------------------------------------
	// 사원 주문 내역 화면 이동
	// -----------------------------------------------------------------------------------------
	@RequestMapping(value = "/main/purchase_list", method = RequestMethod.GET)
	public String purchase_list(HttpServletRequest request, CriteriaVO product, CriteriaVO cri, CriteriaVO cartlist,
			Model model) {

		HttpSession session = request.getSession();
		String empno = (String) session.getAttribute("empno");
		cartlist.setEmpno(empno);
		cts.purchase_list(cartlist);

		System.out.println(empno);
		cri.setEmpno(empno);
		cts.purchase_list_total(cri);

		model.addAttribute("productlist", ps.group_company(product));
		model.addAttribute("cartInfo", cts.purchase_list(cartlist));

		int total = cts.purchase_list_total(cri);
		System.out.println(total);

		model.addAttribute("paging", new pageVO(cri, total));

		return "/main/purchase_list";
	}

	// 카트 정보 수정
	@RequestMapping(value = "/cart/list_modifypost", method = RequestMethod.POST)
	public String list_modifypost(CartDTO cart) {
		cts.cart_modifypost(cart);
		return "redirect:/main/purchase_list";
	}

	// 카트 삭제 설계
	@RequestMapping(value = "/cart/list_delete", method = RequestMethod.GET)
	public String list_delete(CartDTO cartid) {
		cts.deleteCart(cartid);
		// manager/manager.jsp 에서 삭제된 결과를 확인하기 위한 화면이동
		return "redirect:/main/purchase_list";
	}
	
	// -----------------------------------------------------------------------------------------
	// 관리자 구매 신청 대기 화면 이동
	// -----------------------------------------------------------------------------------------
	@RequestMapping(value = "/main/purchase_wait", method = RequestMethod.GET)
	public String purchase_wait(HttpServletRequest request, CriteriaVO product, CriteriaVO cri, CriteriaVO cartlist,
			Model model) {

		HttpSession session = request.getSession();
		String empno = (String) session.getAttribute("empno");
		cartlist.setEmpno(empno);
		cts.purchase_wait(cartlist);

		cri.setEmpno(empno);
		cts.purchase_wait_total(cri);

		model.addAttribute("productlist", ps.group_company(product));
		model.addAttribute("cartInfo", cts.purchase_wait(cartlist));
		int total = cts.purchase_wait_total(cri);
		System.out.println(total);
		model.addAttribute("paging", new pageVO(cri, total));

		return "/main/purchase_wait";
	}

	// 카트 승인 설계
	@RequestMapping(value = "/cart/confirm", method = RequestMethod.GET)
	public String confirm(CartDTO cartid) {
		cts.cart_confirm(cartid);
		// manager/manager.jsp 에서 삭제된 결과를 확인하기 위한 화면이동
		return "redirect:/main/purchase_wait";
	}

	// 거래처 정보 수정
	@RequestMapping(value = "/cart/manager_modifypost", method = RequestMethod.POST)
	public String manager_modifypost(CartDTO cart) {
		cts.cart_modifypost(cart);
		return "redirect:/main/purchase_wait";
	}

	// 멤버 삭제 설계
	@RequestMapping(value = "/cart/manager_delete", method = RequestMethod.GET)
	public String manager_delete(CartDTO cartid) {
		cts.deleteCart(cartid);
		// manager/manager.jsp 에서 삭제된 결과를 확인하기 위한 화면이동
		return "redirect:/main/purchase_wait";
	}
	
	// -----------------------------------------------------------------------------------------
	// 사원 주문 내역 화면 이동
	// -----------------------------------------------------------------------------------------
	@RequestMapping(value = "/main/purchase_all_list", method = RequestMethod.GET)
	public String purchase_all_list(HttpServletRequest request, CriteriaVO product, CriteriaVO cri, CriteriaVO cartlist,
			Model model) {

		HttpSession session = request.getSession();
		String empno = (String) session.getAttribute("empno");
		cartlist.setEmpno(empno);
		cri.setEmpno(empno);
		cts.total(cri);

		model.addAttribute("productlist", ps.group_company(product));
		model.addAttribute("cartInfo", cts.purchase_all_list(cartlist));
		int total = cts.total(cri);
		System.out.println(total);

		model.addAttribute("paging", new pageVO(cri, total));

		return "/main/purchase_all_list";
	}

	// 카트 승인 설계
	@RequestMapping(value = "/cart/list_confirm", method = RequestMethod.GET)
	public String list_confirm(CartDTO cartid) {
		cts.cart_confirm(cartid);
		return "redirect:/main/purchase_all_list";
	}

	// 거래처 정보 수정
	@RequestMapping(value = "/cart/manager_list_modifypost", method = RequestMethod.POST)
	public String manager_list_modifypost(CartDTO cart) {
		cts.cart_modifypost(cart);
		return "redirect:/main/purchase_all_list";
	}

	// 멤버 삭제 설계
	@RequestMapping(value = "/cart/manager_list_delete", method = RequestMethod.GET)
	public String manager_list_delete(CartDTO cartid) {
		cts.deleteCart(cartid);
		// manager/manager.jsp 에서 삭제된 결과를 확인하기 위한 화면이동
		return "redirect:/main/purchase_all_list";
	}
	
}

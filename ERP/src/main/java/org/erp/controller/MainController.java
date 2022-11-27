package org.erp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.erp.model.MemberVO;
import org.erp.model.ProductVO;
import org.erp.service.CartService;
import org.erp.service.CompanyService;
import org.erp.service.MemberService;
import org.erp.service.ProductService;
import org.erp.model.CartDTO;
import org.erp.model.CompanyVO;
import org.erp.model.CriteriaVO;
import org.erp.model.pageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@Autowired
	MemberService ms;
	@Autowired
	CompanyService cs;
	@Autowired
	ProductService ps;
	@Autowired
	CartService cts;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "/login/login";
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main() {
		return "/main/main";
	}

	// 로그인 유지 세션전달
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO member, HttpSession session, HttpServletResponse response) throws IOException {
		MemberVO a = ms.login(member);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (a != null) {
			String empno = a.getEmpno();
			System.out.println(empno);
			session.setAttribute("empno", empno);
			if (empno.equals("manager")) {
				return "/main/main_manager";
			} else {
				return "/main/main";
			}

		} else {
			out.println("<script>alert('아이디, 비밀번호를 확인해 주세요'); </script>");
			out.flush();
			return "/login/login";
		}
	}

	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

	@RequestMapping(value = "/idsearch", method = RequestMethod.GET)
	public String idsearch() {
		return "/login/idsearch";
	}

	// 로그인 아이디 체크
	@RequestMapping(value = "/login/idsearch", method = RequestMethod.POST)
	public String idsearch(MemberVO member, HttpServletResponse response) throws IOException {
		String empno = ms.idsearch(member);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (empno != null) {
			out.println("<script>alert('사원번호는 " + empno + "'); </script>");
			out.flush();
			return "/login/login";
		} else {
			out.println("<script>alert('사원번호를 찾을 수 없습니다.'); </script>");
			out.flush();
			return "/login/idsearch";
		}
	}

	// -----------------------------------------------------------------------------------------
	// 구매 신청 화면 이동
	// -----------------------------------------------------------------------------------------
	@RequestMapping(value = "/main/purchase", method = RequestMethod.GET)
	public String purchase(HttpServletRequest request, CriteriaVO product, CriteriaVO cri, CriteriaVO cartlist,
			Model model) {

		HttpSession session = request.getSession();
		String empno = (String) session.getAttribute("empno");
		cartlist.setEmpno(empno);
		cts.purchase_list(cartlist);

		System.out.println(empno);
		cri.setEmpno(empno);
		cts.purchase_total(cri);

		model.addAttribute("productlist", ps.group_company(product));
		model.addAttribute("cartInfo", cts.getCartList(cartlist));
		int total = cts.purchase_total(cri);
		System.out.println(total);

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
	// 주문 내역 화면 이동
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
	// 신청 대기 화면 이동
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

	// -----------------------------------------------------------------------------------------
	// 사원 정보 화면 이동
	// -----------------------------------------------------------------------------------------
	@RequestMapping(value = "/main/empinfo", method = RequestMethod.GET)
	public String empinfo(HttpServletRequest request, MemberVO member, Model model, CriteriaVO cri, Model totalmem) {
		model.addAttribute("list", ms.emplist(cri));
		int total = ms.total(cri);
		model.addAttribute("paging", new pageVO(cri, total));

		HttpSession session = request.getSession();
		String empno = (String) session.getAttribute("empno");
		System.out.println(empno);

		return "/main/empinfo";
	}

	// 사원 등록
	@RequestMapping(value = "/main/signup", method = RequestMethod.POST)
	public String signup(MemberVO member, HttpServletRequest request) {
		member.setPhone(member.getPhone().replace(",", ""));
		member.setAddr(member.getAddr().replace(",", ""));
		ms.signup(member);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	// 아이디 중복체크
	@RequestMapping(value = "/main/idcheck", method = RequestMethod.POST)
	public ResponseEntity<Integer> idcheck(MemberVO member, HttpServletResponse response) throws IOException {
		System.out.println(member);
		int result = ms.idcheck(member);
		System.out.println(result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// 사원 정보 불러오기
	@RequestMapping(value = "/main/modify", method = RequestMethod.GET)
	public ResponseEntity<MemberVO> modify(MemberVO member) {
		MemberVO result = ms.modify(member);
		System.out.println(result);
		return new ResponseEntity<MemberVO>(result, HttpStatus.OK);
	}

	// 사원 정보 수정
	@RequestMapping(value = "/main/modifypost", method = RequestMethod.POST)
	public String modifypost(MemberVO member) {
		member.setPhone(member.getPhone().replace(",", "-"));
		member.setAddr(member.getAddr().replace(",", "/"));
		ms.modifypost(member);
		return "redirect:/main/empinfo";
	}

	// 사원 정보 삭제
	@RequestMapping(value = "/main/delete", method = RequestMethod.GET)
	public String remove(MemberVO bremove, HttpServletRequest request) {
		ms.remove(bremove);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	// -----------------------------------------------------------------------------------------
	// 내정보 화면 이동
	// -----------------------------------------------------------------------------------------
	@RequestMapping(value = "/main/mypage", method = RequestMethod.GET)
	public String mypage(HttpServletRequest request, MemberVO member, Model model) {

		HttpSession session = request.getSession();
		String empno = (String) session.getAttribute("empno");
		System.out.println(empno);
		member.setEmpno(empno);
		
		model.addAttribute("list", ms.modify(member));

		return "/main/mypage";
	}

	@RequestMapping(value = "/main/mypagemodify", method = RequestMethod.GET)
	public ResponseEntity<MemberVO> mypageload(HttpServletRequest request, MemberVO member, Model model) {

		HttpSession session = request.getSession();
		String empno = (String) session.getAttribute("empno");
		System.out.println(empno);

		member.setEmpno(empno);
		MemberVO result = ms.modify(member);

		return new ResponseEntity<MemberVO>(result, HttpStatus.OK);
	}
	
	// 사원 정보 수정
		@RequestMapping(value = "/main/mypagemodify", method = RequestMethod.POST)
		public String mypagemodify(MemberVO member) {
			member.setPhone(member.getPhone().replace(",", "-"));
			member.setAddr(member.getAddr().replace(",", "/"));
			ms.mypagemodify(member);
			return "redirect:/main/mypage";
		}

	// -----------------------------------------------------------------------------------------
	// 거래처 화면 이동
	// -----------------------------------------------------------------------------------------
	@RequestMapping(value = "/main/account", method = RequestMethod.GET)
	public String account(HttpServletRequest request, Model model, CriteriaVO cri, Model totalmem) {
		model.addAttribute("companylist", cs.companylist(cri));
		int total = cs.total(cri);
		model.addAttribute("paging", new pageVO(cri, total));

		HttpSession session = request.getSession();
		String empno = (String) session.getAttribute("empno");
		System.out.println(empno);

		return "/main/account";
	}

	// 거래처 등록
	@RequestMapping(value = "/main/account_signup", method = RequestMethod.POST)
	public String account_signup(CompanyVO company, HttpServletRequest request) {
		company.setCompanyphone(company.getCompanyphone().replace(",", "-"));
		company.setCompanyaddr(company.getCompanyaddr().replace(",", "/"));
		cs.account_signup(company);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	// 거래처 코드 중복확인
	@RequestMapping(value = "/main/companyidcheck", method = RequestMethod.POST)
	public ResponseEntity<Integer> companyidcheck(CompanyVO company, HttpServletResponse response) throws IOException {
		int result = cs.companyidcheck(company);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// 거래처 정보 불러오기
	@RequestMapping(value = "/main/account_modify", method = RequestMethod.GET)
	public ResponseEntity<CompanyVO> account_modify(CompanyVO company) {
		CompanyVO result = cs.account_modify(company);
		System.out.println(result);
		return new ResponseEntity<CompanyVO>(result, HttpStatus.OK);
	}

	// 거래처 정보 수정
	@RequestMapping(value = "/main/account_modifypost", method = RequestMethod.POST)
	public String account_modifypost(CompanyVO company) {
		company.setCompanyphone(company.getCompanyphone().replace(",", "-"));
		company.setCompanyaddr(company.getCompanyaddr().replace(",", "/"));
		cs.account_modifypost(company);
		return "redirect:/main/account";
	}

	// 거래처 삭제 설계
	@RequestMapping(value = "/main/account_delete", method = RequestMethod.GET)
	public String account_delete(CompanyVO delete) {
		cs.account_delete(delete);
		return "redirect:/main/account";
	}

	// -----------------------------------------------------------------------------------------
	// 상품 등록 화면 이동
	// -----------------------------------------------------------------------------------------
	@RequestMapping(value = "/main/product", method = RequestMethod.GET)
	public String product(Model model, CriteriaVO cri, CriteriaVO product, Model totalmem) {
		model.addAttribute("companylist", cs.companylist(cri));
		model.addAttribute("productlist", ps.productlist(product));
		int total = ps.total(cri);
		model.addAttribute("paging", new pageVO(cri, total));
		return "/main/product";
	}

	// 상품 등록
	@RequestMapping(value = "/main/product_signup", method = RequestMethod.POST)
	public String product_signup(ProductVO product, HttpServletRequest request) {
		ps.product_signup(product);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	// 상품 코드 중복확인
	@RequestMapping(value = "/main/productidcheck", method = RequestMethod.POST)
	public ResponseEntity<Integer> productidcheck(ProductVO product, HttpServletResponse response) throws IOException {
		int result = ps.productidcheck(product);
		System.out.println(result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// 거래처id 불러오기
	@RequestMapping(value = "/main/companyid_select", method = RequestMethod.GET)
	public ResponseEntity<CompanyVO> companyid_select(CompanyVO company) {
		CompanyVO result = cs.companyid_select(company);
		System.out.println(result);
		return new ResponseEntity<CompanyVO>(result, HttpStatus.OK);
	}

	// 상품 정보 불러오기
	@RequestMapping(value = "/main/product_modify", method = RequestMethod.GET)
	public ResponseEntity<ProductVO> product_modify(ProductVO product) {
		ProductVO result = ps.product_modify(product);
		System.out.println(result);
		return new ResponseEntity<ProductVO>(result, HttpStatus.OK);
	}

	// 상품 정보 수정
	@RequestMapping(value = "/main/product_modifypost", method = RequestMethod.POST)
	public String product_modifypost(ProductVO product) {
		ps.product_modifypost(product);
		return "redirect:/main/product";
	}

	// 상품 정보 삭제
	@RequestMapping(value = "/main/product_delete", method = RequestMethod.GET)
	public String product_delete(ProductVO delete) {
		ps.product_delete(delete);
		return "redirect:/main/product";
	}

	// -----------------------------------------------------------------------------------------
	// 재고 관리 화면 이동
	// -----------------------------------------------------------------------------------------
	@RequestMapping(value = "/main/inventory", method = RequestMethod.GET)
	public String inventory(HttpServletRequest request, CriteriaVO inventory, CriteriaVO product, Model model,
			CriteriaVO cri) {
		HttpSession session = request.getSession();
		String empno = (String) session.getAttribute("empno");
		System.out.println(empno);

		model.addAttribute("productlist", ps.group_company(product));
		model.addAttribute("inventory", cts.inventory(inventory));
		int total = cts.inventory_total(cri);
		System.out.println(total);
		model.addAttribute("paging", new pageVO(cri, total));

		return "/main/inventory";
	}

	// 카트 화면에서 상품 선택
	@RequestMapping(value = "/main/inventory_select", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<CartDTO>> inventory_select(CartDTO inventory) {
		ArrayList<CartDTO> result = cts.inventory_category(inventory);
		return new ResponseEntity<ArrayList<CartDTO>>(result, HttpStatus.OK);
	}

	// 카트 화면에서 업체 선택
	@RequestMapping(value = "/main/inventory_select1", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<CartDTO>> inventory_category1(CartDTO inventory, Model model) {
		ArrayList<CartDTO> result = cts.inventory_category1(inventory);
		System.out.println(result);
		model.addAttribute("result", cts.inventory_category1(inventory));
		return new ResponseEntity<ArrayList<CartDTO>>(result, HttpStatus.OK);
	}

	// -----------------------------------------------------------------------------------------

}

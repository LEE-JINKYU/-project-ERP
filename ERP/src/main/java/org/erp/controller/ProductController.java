package org.erp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.erp.model.CompanyVO;
import org.erp.model.CriteriaVO;
import org.erp.model.ProductVO;
import org.erp.model.pageVO;
import org.erp.service.CompanyService;
import org.erp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {
	
	@Autowired
	CompanyService cs;
	@Autowired
	ProductService ps;

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

}

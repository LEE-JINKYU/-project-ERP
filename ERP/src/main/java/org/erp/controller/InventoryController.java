package org.erp.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.erp.model.CartDTO;
import org.erp.model.CriteriaVO;
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
public class InventoryController {
	
	@Autowired
	ProductService ps;
	@Autowired
	CartService cts;
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
		System.out.println(result);

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

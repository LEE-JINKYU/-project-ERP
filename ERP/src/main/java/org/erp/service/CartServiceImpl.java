package org.erp.service;

import java.util.ArrayList;

import org.erp.mapper.CartMapper;
import org.erp.model.CartDTO;
import org.erp.model.CriteriaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartMapper ctm;

	// 카트 추가
	@Override
	public void addCart(CartDTO cart) {
		ctm.addCart(cart);
	}

	public int deleteCart(CartDTO cartid) {
		return ctm.deleteCart(cartid);
	}

	public ArrayList<CartDTO> getCartList(CriteriaVO cartlist) {

		ArrayList<CartDTO> cart = ctm.getCartList(cartlist);

		for (CartDTO dto : cart) {
			dto.initSaleTotal();
		}

		return cart;
	}

	public ArrayList<CartDTO> purchase_wait(CriteriaVO cartlist) {

		ArrayList<CartDTO> cart = ctm.purchase_wait(cartlist);

		for (CartDTO dto : cart) {
			dto.initSaleTotal();
		}

		return cart;
	}

	public ArrayList<CartDTO> purchase_list(CriteriaVO cartlist) {

		ArrayList<CartDTO> cart = ctm.purchase_list(cartlist);

		for (CartDTO dto : cart) {
			dto.initSaleTotal();
		}

		return cart;
	}

	public ArrayList<CartDTO> purchase_all_list(CriteriaVO cartlist) {

		ArrayList<CartDTO> cart = ctm.purchase_all_list(cartlist);

		for (CartDTO dto : cart) {
			dto.initSaleTotal();
		}

		return cart;
	}

	// 거래처 수 조회
	public int purchase_total(CriteriaVO cri) {
		return ctm.purchase_total(cri);
	}
	public int purchase_list_total(CriteriaVO cri) {
		return ctm.purchase_list_total(cri);
	}
	public int purchase_wait_total(CriteriaVO cri) {
		return ctm.purchase_wait_total(cri);
	}
	public int total(CriteriaVO cri) {
		return ctm.total(cri);
	}

	// 선택한 상품 정보 불러오기
	public CartDTO cart_modify(CartDTO cart) {
		return ctm.cart_modify(cart);
	}

	// 선택한 상품 정보 수정
	public void cart_modifypost(CartDTO cart) {
		ctm.cart_modifypost(cart);
	}

	// 선택한 상품 정보 수정
	public void cart_confirm(CartDTO cart) {
		ctm.cart_confirm(cart);
	}
	
	public ArrayList<CartDTO> inventory(CriteriaVO inventory) {
		return ctm.inventory(inventory);
	}

	public ArrayList<CartDTO> inventory_category(CartDTO inventory) {
		return ctm.inventory_category(inventory);
	}
	
	public ArrayList<CartDTO> inventory_category1(CartDTO inventory) {
		return ctm.inventory_category1(inventory);
	}
	
	public int inventory_total(CriteriaVO cri) {
		return ctm.inventory_total(cri);
	}
}

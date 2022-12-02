package org.erp.service;

import java.util.ArrayList;

import org.erp.model.CartDTO;
import org.erp.model.CriteriaVO;

public interface CartService {

	// 카트 추가
	public void addCart(CartDTO cart);
	
	public int deleteCart(CartDTO cartid);
	
	// 구매 신청 리스트(개인)
	public ArrayList<CartDTO> getCartList(CriteriaVO cartlist);
	
	// 신청 대기 리스트(전체)
	public ArrayList<CartDTO> purchase_wait(CriteriaVO cartlist);
	
	public ArrayList<CartDTO> purchase_list(CriteriaVO cartlist);

	public ArrayList<CartDTO> purchase_all_list(CriteriaVO cartlist);

	public int purchase_total(CriteriaVO cri);
	
	public int purchase_list_total(CriteriaVO cri);

	public int purchase_wait_total(CriteriaVO cri);

	public int total(CriteriaVO cri);

	
	// 선택한 상품 정보 불러오기
	public CartDTO cart_modify(CartDTO cart);

	// 선택한 상품 정보 수정
	public void cart_modifypost(CartDTO cart);
	
	// 선택한 상품 정보 수정
	public void cart_confirm(CartDTO cart);
		
	public ArrayList<CartDTO> inventory(CriteriaVO inventory);

	public ArrayList<CartDTO> inventory_category(CartDTO inventory);
	
	public ArrayList<CartDTO> inventory_category1(CartDTO inventory);

	public int inventory_total(CriteriaVO cri);

}

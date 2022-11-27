package org.erp.mapper;

import java.util.ArrayList;

import org.erp.model.CartDTO;
import org.erp.model.CriteriaVO;
import org.erp.model.ProductVO;

public interface CartMapper {

	// 카트추가
	public void addCart(CartDTO cart);

	// 카트 삭제
	public int deleteCart(CartDTO cartid);

	// 카트 수량 수정
	public int modifyCount(CartDTO cart);

	// 카트 목록 (개인)
	public ArrayList<CartDTO> getCartList(CriteriaVO cartlist);

	// 카트 목록 (전체)
	public ArrayList<CartDTO> purchase_wait(CriteriaVO cartlist);

	public ArrayList<CartDTO> purchase_list(CriteriaVO cartlist);

	public ArrayList<CartDTO> purchase_all_list(CriteriaVO cartlist);

	// 거래처 수 조회

	public int purchase_total(CriteriaVO cri);

	public int purchase_list_total(CriteriaVO cri);

	public int purchase_wait_total(CriteriaVO cri);

	public int total(CriteriaVO cri);

	// 선택한 거래처 정보 불러오기
	public CartDTO cart_modify(CartDTO cart);

	// 선택한 거래처 정보 수정
	public void cart_modifypost(CartDTO cart);

	public void cart_confirm(CartDTO cart);

	public ArrayList<CartDTO> inventory(CriteriaVO inventory);

	public ArrayList<CartDTO> inventory_category(CartDTO inventory);

	public ArrayList<CartDTO> inventory_category1(CartDTO inventory);

	public int inventory_total(CriteriaVO cri);

}

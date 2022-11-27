package org.erp.service;

import java.util.ArrayList;

import org.erp.model.CriteriaVO;
import org.erp.model.ProductVO;

public interface ProductService {

	// 상품 등록
	public void product_signup(ProductVO product);

	// 상품코드 중복확인
	public int productidcheck(ProductVO product);

	// 상품 리스트
	public ArrayList<ProductVO> productlist(CriteriaVO product);
	
	// 거래처 수 조회
	public int total(CriteriaVO cri);

	// 선택한 상품 정보 불러오기
	public ProductVO product_modify(ProductVO product);

	// 선택한 상품 정보 수정
	public void product_modifypost(ProductVO product);
	
	// 거래처 그룹 리스트
	public ArrayList<ProductVO> group_company(CriteriaVO product);
	
	// 선택한 상품 정보 불러오기
	public ArrayList<ProductVO> company_select(ProductVO product);
	
	// 선택한 상품 정보 불러오기
	public ProductVO product_select(ProductVO product);
	
	public void product_delete(ProductVO delete);

	
}

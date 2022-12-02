package org.erp.mapper;

import java.util.ArrayList;

import org.erp.model.CartDTO;
import org.erp.model.CompanyVO;
import org.erp.model.CriteriaVO;
import org.erp.model.ProductVO;

public interface ProductMapper {
	// 상품 등록
	public void product_signup(ProductVO product);

	// 상품코드 중복확인
	public int productidcheck(ProductVO product);

	// 거래처 리스트
	public ArrayList<ProductVO> productlist(CriteriaVO product);

	// 거래처 수 조회
	public int total(CriteriaVO cri);

	// 선택한 거래처 정보 불러오기
	public ProductVO product_modify(ProductVO product);

	// 선택한 거래처 정보 수정
	public void product_modifypost(ProductVO product);
	
	// 거래처 그룹 리스트
	public ArrayList<ProductVO> group_company(CriteriaVO product);
	
	// 카트 화면에서 업체 선택
	public ArrayList<ProductVO> company_select(ProductVO product);
	
	// 카트 화면에서 상품 선택
	public ProductVO product_select(ProductVO product);
	
	public void product_delete(ProductVO delete);

}

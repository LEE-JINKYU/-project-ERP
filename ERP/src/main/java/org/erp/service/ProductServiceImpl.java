package org.erp.service;

import java.util.ArrayList;

import org.erp.mapper.CompanyMapper;
import org.erp.mapper.ProductMapper;

import org.erp.model.CriteriaVO;
import org.erp.model.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	CompanyMapper cm;
	@Autowired
	ProductMapper pm;

	// 거래처 등록
	public void product_signup(ProductVO product) {
		pm.product_signup(product);
	}

	// 상품코드 중복확인
	public int productidcheck(ProductVO product) {
		int result = pm.productidcheck(product);
		return result;
	}

	// 상품 리스트
	public ArrayList<ProductVO> productlist(CriteriaVO product) {
		return pm.productlist(product);
	}

	// 거래처 수 조회
	public int total(CriteriaVO cri) {
		return pm.total(cri);
	}

	// 선택한 상품 정보 불러오기
	public ProductVO product_modify(ProductVO product) {
		return pm.product_modify(product);
	}

	// 선택한 상품 정보 수정
	public void product_modifypost(ProductVO product) {
		pm.product_modifypost(product);
	}

	// 거래처 그룹 리스트
	public ArrayList<ProductVO> group_company(CriteriaVO product) {
		return pm.group_company(product);
	}

	// 선택한 상품 정보 불러오기
	public ArrayList<ProductVO> company_select(ProductVO product) {
		return pm.company_select(product);
	}

	// 선택한 상품 정보 불러오기
	public ProductVO product_select(ProductVO product) {
		return pm.product_select(product);
	}

	// 멤버 삭제
	public void product_delete(ProductVO delete) {
		pm.product_delete(delete);
	}
}

package org.erp.service;

import java.util.ArrayList;

import org.erp.mapper.CompanyMapper;
import org.erp.model.CompanyVO;
import org.erp.model.CriteriaVO;
import org.erp.model.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyMapper cm;

	// 거래처 등록
	public void account_signup(CompanyVO company) {
		cm.account_signup(company);
	}

	// 거래처코드 중복확인
	public int companyidcheck(CompanyVO company) {
		int result = cm.companyidcheck(company);
		return result;
	}

	// 거래처 리스트
	public ArrayList<CompanyVO> companylist(CriteriaVO cri) {
		return cm.companylist(cri);
	}

	// 거래처 수 조회
	public int total(CriteriaVO cri) {
		return cm.total(cri);
	}

	// 선택한 거래처 정보 불러오기
	public CompanyVO account_modify(CompanyVO company) {
		return cm.account_modify(company);
	}

	// 선택한 거래처 정보 수정
	public void account_modifypost(CompanyVO company) {
		cm.account_modifypost(company);
	}

	// 멤버 삭제
	public void account_delete(CompanyVO delete) {
		cm.account_delete(delete);
	}

	// 선택한 상품 정보 불러오기
	public CompanyVO companyid_select(CompanyVO company) {
		return cm.companyid_select(company);
	}
}

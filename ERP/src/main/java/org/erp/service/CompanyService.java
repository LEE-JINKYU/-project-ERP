package org.erp.service;

import java.util.ArrayList;

import org.erp.model.CompanyVO;
import org.erp.model.CriteriaVO;

public interface CompanyService {

	// 거래처 등록
	public void account_signup(CompanyVO company);

	// 거래처코드 중복확인
	public int companyidcheck(CompanyVO company);

	// 거래처 리스트
	public ArrayList<CompanyVO> companylist(CriteriaVO cri);

	// 거래처 수 조회
	public int total(CriteriaVO cri);

	// 선택한 거래처 정보 불러오기
	public CompanyVO account_modify(CompanyVO company);

	// 선택한 거래처 정보 수정
	public void account_modifypost(CompanyVO company);

	public void account_delete(CompanyVO delete);
	
	public CompanyVO companyid_select(CompanyVO company);


}

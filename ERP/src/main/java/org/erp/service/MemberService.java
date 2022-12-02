package org.erp.service;

import java.util.ArrayList;

import org.erp.model.MemberVO;
import org.erp.model.CriteriaVO;

public interface MemberService {

	// 사원 등록
	public void signup(MemberVO member);
	
	// 사원 등록 ID 중복확인
	public int idcheck(MemberVO member);
	
	// 사원 리스트
	public ArrayList<MemberVO> emplist(CriteriaVO cri);
	
	// 사원 수 조회
	public int total(CriteriaVO cri);
		
	// 사원 정보 불러오기
	public MemberVO modify(MemberVO member);

	// 사원 정보 수정
	public void modifypost(MemberVO member);
	
	public void mypagemodify(MemberVO member);

	// 멤버 삭제 설계
	public void remove(MemberVO remove);
	
	// 로그인 체크
	public MemberVO login(MemberVO member);

	// 아이디 찾기
	public String idsearch(MemberVO member);

	// 아이디 찾기
	public String pwsearch(MemberVO member);

}
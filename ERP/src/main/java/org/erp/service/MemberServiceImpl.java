package org.erp.service;

import java.util.ArrayList;

import org.erp.mapper.MemberMapper;
import org.erp.model.MemberVO;
import org.erp.model.CriteriaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberMapper mm;

	// 회원가입
	public void signup(MemberVO member) {
		mm.signup(member);
	}
	// 사원 등록 ID 중복확인
	public int idcheck(MemberVO member) {
		int result = mm.idcheck(member);
		return result;
	}
	// 사원 리스트
	public ArrayList<MemberVO> emplist(CriteriaVO cri) {
		return mm.emplist(cri);
	}
	
	// 멤버 리스트에서 전체 회원수 조회
	public int total(CriteriaVO cri){
		return mm.total(cri);	
	}
	
	// 사원 정보 불러오기
	public MemberVO modify(MemberVO member) {
		return mm.modify(member);
	}
	
	// 사원 정보 수정
	public void modifypost(MemberVO member) {
		mm.modifypost(member);
	}
	
	// 사원 정보 수정
		public void mypagemodify(MemberVO member) {
			mm.mypagemodify(member);
		}

	// 멤버 삭제
	public void remove(MemberVO remove) {
		mm.remove(remove);
	}
	
	public MemberVO login(MemberVO member) {
		return mm.login(member);
	}
	
	public String idsearch(MemberVO member) {
		return mm.idsearch(member);
	}
}
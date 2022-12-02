package org.erp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.erp.model.CriteriaVO;
import org.erp.model.MemberVO;
import org.erp.model.pageVO;
import org.erp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmpController {

	@Autowired
	MemberService ms;
	
	// -----------------------------------------------------------------------------------------
	// 사원등록 화면 이동
	// -----------------------------------------------------------------------------------------
	@RequestMapping(value = "/main/empinfo", method = RequestMethod.GET)
	public String empinfo(HttpServletRequest request, MemberVO member, Model model, CriteriaVO cri, Model totalmem) {
		model.addAttribute("list", ms.emplist(cri));
		int total = ms.total(cri);
		model.addAttribute("paging", new pageVO(cri, total));

		HttpSession session = request.getSession();
		String empno = (String) session.getAttribute("empno");
		System.out.println(empno);

		return "/main/empinfo";
	}

	// 사원 등록
	@RequestMapping(value = "/main/signup", method = RequestMethod.POST)
	public String signup(MemberVO member, HttpServletRequest request) {
		member.setPhone(member.getPhone().replace(",", ""));
		member.setAddr(member.getAddr().replace(",", ""));
		ms.signup(member);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	// 사원 정보 수정
	@RequestMapping(value = "/main/modifypost", method = RequestMethod.POST)
	public String modifypost(MemberVO member) {
		member.setPhone(member.getPhone().replace(",", "-"));
		member.setAddr(member.getAddr().replace(",", "/"));
		ms.modifypost(member);
		return "redirect:/main/empinfo";
	}

	// 아이디 중복 확인
	@RequestMapping(value = "/main/idcheck", method = RequestMethod.POST)
	public ResponseEntity<Integer> idcheck(MemberVO member, HttpServletResponse response) throws IOException {
		System.out.println(member);
		int result = ms.idcheck(member);
		System.out.println(result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	

	// 사원 정보 불러오기
	@RequestMapping(value = "/main/modify", method = RequestMethod.GET)
	public ResponseEntity<MemberVO> modify(MemberVO member) {
		MemberVO result = ms.modify(member);
		System.out.println(result);
		return new ResponseEntity<MemberVO>(result, HttpStatus.OK);
	}

	// 사원 정보 삭제
	@RequestMapping(value = "/main/delete", method = RequestMethod.GET)
	public String remove(MemberVO bremove, HttpServletRequest request) {
		ms.remove(bremove);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}
	
	@RequestMapping(value = "/main/mypage", method = RequestMethod.GET)
	public String mypage(HttpServletRequest request, MemberVO member, Model model) {

		HttpSession session = request.getSession();
		String empno = (String) session.getAttribute("empno");
		System.out.println(empno);
		member.setEmpno(empno);

		model.addAttribute("list", ms.modify(member));

		return "/main/mypage";
	}

	// -----------------------------------------------------------------------------------------
	// 내정보 화면 이동
	// -----------------------------------------------------------------------------------------	@RequestMapping(value = "/main/mypagemodify", method = RequestMethod.GET)
	public ResponseEntity<MemberVO> mypageload(HttpServletRequest request, MemberVO member, Model model) {

		HttpSession session = request.getSession();
		String empno = (String) session.getAttribute("empno");
		System.out.println(empno);

		member.setEmpno(empno);
		MemberVO result = ms.modify(member);

		return new ResponseEntity<MemberVO>(result, HttpStatus.OK);
	}

	// 마이페이지 사원 정보 수정
	@RequestMapping(value = "/main/mypagemodify", method = RequestMethod.POST)
	public String mypagemodify(MemberVO member) {
		member.setPhone(member.getPhone().replace(",", "-"));
		member.setAddr(member.getAddr().replace(",", "/"));
		ms.mypagemodify(member);
		return "redirect:/main/mypage";
	}
	
	
}

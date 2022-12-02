package org.erp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.erp.model.MemberVO;
import org.erp.model.ProductVO;
import org.erp.service.CartService;
import org.erp.service.CompanyService;
import org.erp.service.MemberService;
import org.erp.service.ProductService;
import org.erp.model.CartDTO;
import org.erp.model.CompanyVO;
import org.erp.model.CriteriaVO;
import org.erp.model.pageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@Autowired
	MemberService ms;
	@Autowired
	CompanyService cs;
	@Autowired
	ProductService ps;
	@Autowired
	CartService cts;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "/login/login";
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main() {
		return "/main/main";
	}

	// 로그인 체크
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO member, HttpSession session, HttpServletResponse response) throws IOException {
		MemberVO a = ms.login(member);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (a != null) {
			String empno = a.getEmpno();
			System.out.println(empno);
			session.setAttribute("empno", empno);
			if (empno.equals("manager")) {
				return "/main/main_manager";
			} else {
				return "/main/main";
			}

		} else {
			out.println("<script>alert('아이디, 비밀번호를 확인해 주세요'); </script>");
			out.flush();
			return "/login/login";
		}
	}

	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

	@RequestMapping(value = "/idsearch", method = RequestMethod.GET)
	public String idsearch() {
		return "/login/idsearch";
	}

	// 아이디 찾기
	@RequestMapping(value = "/login/idsearch", method = RequestMethod.POST)
	public String idsearch(MemberVO member, HttpServletResponse response) throws IOException {
		String empno = ms.idsearch(member);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (empno != null) {
			out.println("<script>alert('사원번호는 " + empno + " 입니다.'); </script>");
			out.flush();
			return "/login/login";
		} else {
			out.println("<script>alert('사원번호를 찾을 수 없습니다.'); </script>");
			out.flush();
			return "/login/idsearch";
		}
	}

	// 아이디 찾기
	@RequestMapping(value = "/login/pwsearch", method = RequestMethod.POST)
	public String pwsearch(MemberVO member, HttpServletResponse response) throws IOException {
		String password = ms.pwsearch(member);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (password != null) {
			out.println("<script>alert('비밀번호는 " + password + " 입니다.'); </script>");
			out.flush();
			return "/login/login";
		} else {
			out.println("<script>alert('비밀번호를 찾을 수 없습니다.'); </script>");
			out.flush();
			return "/login/idsearch";
		}
	}
	


}

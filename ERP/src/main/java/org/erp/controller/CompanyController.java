package org.erp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.erp.model.CompanyVO;
import org.erp.model.CriteriaVO;
import org.erp.model.pageVO;
import org.erp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CompanyController {
	
	@Autowired
	CompanyService cs;
	
	// -----------------------------------------------------------------------------------------
		// 거래처 화면 이동
		// -----------------------------------------------------------------------------------------
		@RequestMapping(value = "/main/account", method = RequestMethod.GET)
		public String account(HttpServletRequest request, Model model, CriteriaVO cri, Model totalmem) {
			model.addAttribute("companylist", cs.companylist(cri));
			int total = cs.total(cri);
			model.addAttribute("paging", new pageVO(cri, total));

			HttpSession session = request.getSession();
			String empno = (String) session.getAttribute("empno");
			System.out.println(empno);

			return "/main/account";
		}

		// 거래처 등록
		@RequestMapping(value = "/main/account_signup", method = RequestMethod.POST)
		public String account_signup(CompanyVO company, HttpServletRequest request) {
			company.setCompanyphone(company.getCompanyphone().replace(",", "-"));
			company.setCompanyaddr(company.getCompanyaddr().replace(",", "/"));
			cs.account_signup(company);
			String referer = request.getHeader("Referer");
			return "redirect:" + referer;
		}

		// 거래처 코드 중복확인
		@RequestMapping(value = "/main/companyidcheck", method = RequestMethod.POST)
		public ResponseEntity<Integer> companyidcheck(CompanyVO company, HttpServletResponse response) throws IOException {
			int result = cs.companyidcheck(company);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

		// 거래처 정보 불러오기
		@RequestMapping(value = "/main/account_modify", method = RequestMethod.GET)
		public ResponseEntity<CompanyVO> account_modify(CompanyVO company) {
			CompanyVO result = cs.account_modify(company);
			System.out.println(result);
			return new ResponseEntity<CompanyVO>(result, HttpStatus.OK);
		}

		// 거래처 정보 수정
		@RequestMapping(value = "/main/account_modifypost", method = RequestMethod.POST)
		public String account_modifypost(CompanyVO company) {
			company.setCompanyphone(company.getCompanyphone().replace(",", "-"));
			company.setCompanyaddr(company.getCompanyaddr().replace(",", "/"));
			cs.account_modifypost(company);
			return "redirect:/main/account";
		}

		// 거래처 삭제 설계
		@RequestMapping(value = "/main/account_delete", method = RequestMethod.GET)
		public String account_delete(CompanyVO delete) {
			cs.account_delete(delete);
			return "redirect:/main/account";
		}
}

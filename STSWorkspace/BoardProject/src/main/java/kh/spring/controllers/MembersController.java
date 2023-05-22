package kh.spring.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import commons.SecurityUtils;
import kh.spring.dto.MembersDTO;
import kh.spring.repositories.MembersDAO;

@Controller
@RequestMapping("/member/")
public class MembersController {
	
	@Autowired
	private MembersDAO dao;
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/signup")
	public String signup() {
		return "member/signup";
	}
	
	@RequestMapping("/create")
	public String create(MembersDTO dto) throws Exception {
		dto.setPw(SecurityUtils.sha512(dto.getPw()));
		int result = dao.insert(dto);
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping(value="/idCheck", produces="text/html;charset=utf8")
	public String idCheck(String id) {
		System.out.println("전달 된 ID : " + id);
		boolean result = dao.isIdExist(id);
		return String.valueOf(result);
	}
	
	@RequestMapping("/login")
	public String login(String id, String pw) throws Exception {
		pw = SecurityUtils.sha512(pw);
		System.out.println("전달 된 ID : " + id);
		if(dao.login(id, pw)) {
			System.out.println("로그인 성공");
			session.setAttribute("loginID", id);
		}
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/memberout")
	public String memberOut() {
		int result = dao.delete((String)session.getAttribute("loginID"));
		return this.logout();
	}
	
	@RequestMapping("/mypage")
	public String mypage(Model model) {
		model.addAttribute("myDto", dao.selectById((String)session.getAttribute("loginID")));
		return "member/mypage";
	}
	
	@RequestMapping("/update")
	public String update(MembersDTO dto) throws Exception {
		dto.setPw(SecurityUtils.sha512(dto.getPw()));
		int result = dao.update(dto);
		return "redirect:/member/mypage";
	}
	
//	@ExceptionHandler(Exception.class)
//	public String exceptionHandler(Exception e) {
//		e.printStackTrace();
//		return "redirect:/error";
//	}
}

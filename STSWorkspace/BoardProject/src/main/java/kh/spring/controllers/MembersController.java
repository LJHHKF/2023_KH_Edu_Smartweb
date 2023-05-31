package kh.spring.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import commons.SecurityUtils;
import kh.spring.dto.MembersDTO;
import kh.spring.services.MembersService;

@Controller
@RequestMapping("/member/")
public class MembersController {
	
	@Autowired
	private MembersService membersService;
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/signup")
	public String signup() {
		return "member/signup";
	}
	
	@RequestMapping("/create")
	public String create(MembersDTO dto) throws Exception {
		int result = membersService.insert(dto);
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping(value="/idCheck", produces="text/html;charset=utf8")
	public String idCheck(String id) {
		boolean result = membersService.isIdExist(id);
		return String.valueOf(result);
	}
	
	@RequestMapping("/login")
	public String login(String id, String pw) throws Exception {
		if(membersService.login(id, pw)) {
			session.setAttribute("loginID", id);
		}
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		session.removeAttribute("loginID");
		return "redirect:/";
	}
	
	@RequestMapping("/memberout")
	public String memberOut() {
		int result = membersService.delete((String)session.getAttribute("loginID"));
		return this.logout();
	}
	
	@RequestMapping("/mypage")
	public String mypage(Model model) {
		model.addAttribute("myDto", membersService.selectById((String)session.getAttribute("loginID")));
		return "member/mypage";
	}
	
	@RequestMapping("/update")
	public String update(MembersDTO dto) throws Exception {
		int result = membersService.update(dto);
		return "redirect:/member/mypage";
	}
	
//	@ExceptionHandler(Exception.class)
//	public String exceptionHandler(Exception e) {
//		e.printStackTrace();
//		return "redirect:/error";
//	}
}

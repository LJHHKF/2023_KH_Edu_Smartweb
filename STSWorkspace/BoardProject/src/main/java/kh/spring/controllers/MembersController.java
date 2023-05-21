package kh.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.spring.dto.MembersDTO;
import kh.spring.repositories.MembersDAO;

@Controller
@RequestMapping("/member/")
public class MembersController {
	
	@Autowired
	private MembersDAO dao;
	
	@RequestMapping("/signup")
	public String signup() {
		return "member/signup";
	}
	
	@RequestMapping("/create")
	public String create(MembersDTO dto) {
		int result = dao.insert(dto);
		return "/";
	}
	
	@ResponseBody
	@RequestMapping(value="/idCheck", produces="text/html;charset=utf8")
	public String idCheck(String id) {
		System.out.println("전달 된 ID : " + id);
		boolean result = dao.isIdExist(id);
		return String.valueOf(result);
	}
	
	@RequestMapping("/login")
	public String login() {
		return "/";
	}
}

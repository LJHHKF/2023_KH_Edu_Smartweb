package kh.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/")
	public String home(HttpServletRequest request) {
		session.setAttribute("ip", request.getRemoteAddr());
		return "home";
	}
	
}

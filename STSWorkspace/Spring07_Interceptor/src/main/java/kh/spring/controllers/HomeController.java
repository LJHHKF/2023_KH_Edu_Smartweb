package kh.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/guest")
	public String guset() {
		return "guest";
	}
	
	@RequestMapping("/member")
	public String member() {
		return "member";
	}
	
	@RequestMapping("/error")
	public String error() {
		return "error";
	}
	
}

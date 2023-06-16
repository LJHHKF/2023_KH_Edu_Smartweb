package kh.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/react/")
public class ReactController {
	
	@RequestMapping("/**")
	public String react() {
		return "forward:/test/index.html";
	}
	
}

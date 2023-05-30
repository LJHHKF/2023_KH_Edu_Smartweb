package kh.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat/")
public class ChatController {
	@RequestMapping("main")
	public String main() {
		return "chat/main";
	}
}

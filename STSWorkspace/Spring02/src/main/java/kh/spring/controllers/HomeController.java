package kh.spring.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String home() {
		//아래의 return이 'request.getDispatcher("WEB-INF/views/home.jsp").forward(request, response)'와 같음.
		return "home";
	}
	
	@RequestMapping("/toInput")
	public String toInput() {
		return "inputForm";
	}
	
	@RequestMapping("/inputProc")
	public String inputProc(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf8");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String title = request.getParameter("title");
		String genre = request.getParameter("genre");
		
		System.out.println(title + " : " + genre);
		return "redirect:/";
	}
}

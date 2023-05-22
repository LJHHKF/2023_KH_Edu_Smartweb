package kh.spring.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	

	@RequestMapping("/")
	public String home() {

		return "home";
	}
	
//	@ExceptionHandler(Exception.class)
//	public String exceptionHandler(Exception e) {
//		e.printStackTrace();
//		return "redirect:/error";
//	}
}

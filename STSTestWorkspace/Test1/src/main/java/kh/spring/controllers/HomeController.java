package kh.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kh.spring.dto.MessageDTO;
import kh.spring.repositories.MessageDAO;

@Controller
public class HomeController {
	
	@Autowired
	private MessageDAO messageDAO;
	@Autowired
	private Gson gson;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@ResponseBody
	@RequestMapping("/insertMessage")
	public String insertMessage(String name, String message){
		int result = messageDAO.insert(name, message);
		if(result > 0) {
			return "성공";
		}
		return this.exceptionHandler(null);
	}
	
	@ResponseBody
	@RequestMapping("/listMessage")
	public String listMessage() {
		List<MessageDTO> list = messageDAO.selectAll();
		return gson.toJson(list);
	}
	
	@ExceptionHandler
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "error";
	}
}

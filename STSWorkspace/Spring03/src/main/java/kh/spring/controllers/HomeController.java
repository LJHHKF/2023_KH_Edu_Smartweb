package kh.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.dto.MessageDTO;
import kh.spring.repositories.MessageDAO;

@Controller
public class HomeController {
	
	@Autowired
	private MessageDAO dao;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/toInput")
	public String toInput() {
		return "inputForm";
	}
	
	@RequestMapping("/toOutput")
	public String toOutput(Model model) throws Exception {
		List<MessageDTO> list = dao.selectAll();
		model.addAttribute("list", list);
		return "list";
	}
	
	@RequestMapping("/inputProc")
	public String inputProc(MessageDTO dto) throws Exception {
		int result = dao.insert(dto);
		return "redirect:/";
	}
	
	@RequestMapping("/deleteProc")
	public String deleteProc(int seq) throws Exception{
		int result = dao.delete(seq);
		return "redirect:/toOutput";
	}
	
	@RequestMapping("/modifyProc")
	public String modifyProc(MessageDTO dto) throws Exception{
		int result = dao.update(dto);
		return "redirect:/toOutput";
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "redirect:/error";
	}
	
}

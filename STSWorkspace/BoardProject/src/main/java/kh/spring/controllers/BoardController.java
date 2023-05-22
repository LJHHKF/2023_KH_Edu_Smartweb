package kh.spring.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kh.spring.dto.BoardDTO;
import kh.spring.repositories.BoardDAO;

@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	private BoardDAO dao;
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", dao.selectAll());
		return "board/list";
	}
	
	@RequestMapping("/toWriteForm")
	public String toWriteForm() {
		return "board/writeForm";
	}
	
	@RequestMapping("/write")
	public String write(BoardDTO dto, RedirectAttributes rttb) {
		dto.setWriter((String)session.getAttribute("loginID"));
		int result = dao.insert(dto);
		rttb.addFlashAttribute("list", dao.selectAll());
		return "redirect:/board/list";
	}
	
	@RequestMapping("/viewContent")
	public String view(int seq, Model model) {
		dao.addViewCount(seq);
		model.addAttribute("dto", dao.selectBySeq(seq));
		return "board/viewContent";
	}
	
	@RequestMapping("/update")
	public String update(BoardDTO dto) {
		int result = dao.update(dto);
		return "redirect:/board/viewContent?seq="+dto.getSeq();
	}
	
	@RequestMapping("/delete")
	public String delete(int seq) {
		int result = dao.delete(seq);
		return "redirect:/board/list";
	}
}

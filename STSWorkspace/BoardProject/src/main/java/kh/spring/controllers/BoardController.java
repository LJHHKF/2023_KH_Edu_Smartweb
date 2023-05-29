package kh.spring.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kh.spring.dto.BoardDTO;
import kh.spring.services.BoardService;
import kh.spring.services.FilesService;

@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private FilesService filesService;
	@Autowired
	private HttpSession session;
	
	@RequestMapping("list")
	public String list(Model model) {
		model.addAttribute("list", boardService.selectAll());
		return "board/list";
	}
	
	@RequestMapping("toWriteForm")
	public String toWriteForm() {
		return "board/writeForm";
	}
	
	@Transactional
	@RequestMapping("write")
	public String write(BoardDTO dto, MultipartFile[] files) throws Exception {
		//보드 기본 쓰기 설정
		dto.setWriter((String)session.getAttribute("loginID"));
		int parent_seq = boardService.insertReturnSeq(dto);
		String realPath = session.getServletContext().getRealPath("upload");
		filesService.insert(files, realPath, parent_seq);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("viewContent")
	public String view(int seq, Model model) {
		boardService.addViewCount(seq);
		model.addAttribute("dto", boardService.selectBySeq(seq));
		model.addAttribute("fileList", filesService.selectByParentSeq(seq));
		return "board/viewContent";
	}
	
	@RequestMapping("update")
	public String update(BoardDTO dto) {
		int result = boardService.update(dto);
		return "redirect:/board/viewContent?seq="+dto.getSeq();
	}
	
	@RequestMapping("delete")
	public String delete(int seq) {
		int result = boardService.delete(seq);
		return "redirect:/board/list";
	}
}

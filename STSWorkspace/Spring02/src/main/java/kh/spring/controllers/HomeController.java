package kh.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.dto.MoviesDTO;
import kh.spring.repositories.MoviesDAO;
import kh.spring.services.MoviesService;

@Controller
public class HomeController {
	
	@Autowired
	private MoviesService moviesService;
	
	@RequestMapping("/")
	public String home() {
		//아래의 return이 'request.getDispatcher("WEB-INF/views/home.jsp").forward(request, response)'와 같음.
		return "home";
	}
	
	@RequestMapping("/transactional")
	public String transactionTest() {
		MoviesDTO dto = new MoviesDTO(0, "나홀로집에", "코미디");
		moviesService.transactionTest(dto);
		return "home";
	}
	
	@RequestMapping("/toInput")
	public String toInput() {
		return "inputForm";
	}
	
	@RequestMapping("/toOutput")
	public String toOutput(Model model) throws Exception {
		List<MoviesDTO> list = moviesService.selectAll();
		model.addAttribute("list", list);
		return "list";
	}
	
	@RequestMapping("/toMyBatis")
	public String toMyBatis() throws Exception{
		return "mybatis";
	}
	
	@RequestMapping("/inputProc")
	public String inputProc(MoviesDTO dto) throws Exception {
		int result = moviesService.insert(dto);
		System.out.println(dto.getTitle() + " : " + dto.getGenre());
		return "redirect:/";
	}
	
	@RequestMapping("/deleteProc")
	public String deleteProc(int id) throws Exception{
		int result = moviesService.delete(id);
		return "redirect:/toOutput";
	}
	
	@RequestMapping("/modifyProc")
	public String modifyProc(MoviesDTO dto) throws Exception{
		int result = moviesService.update(dto);
		return "redirect:/toOutput";
	}
	
	@RequestMapping("/selectByCon")
	public String selectByCon(String column, String value) throws Exception{
		List<MoviesDTO> list = moviesService.selectByCon(column, value);
		
		for(MoviesDTO dto : list) {
			System.out.println(dto.getId() + " : " + dto.getTitle() +" : "+ dto.getGenre());
		}
		return "mybatis";
	}
	
	@RequestMapping("/selectByMultiCon")
	public String selectByMultiCon(MoviesDTO dto) throws Exception{
		List<MoviesDTO> list = moviesService.selectByMultiCon(dto);
		
		for(MoviesDTO e : list) {
			System.out.println(e.getId() + " : " + e.getTitle() +" : "+ e.getGenre());
		}
		return "mybatis";
	}
	
//	@RequestMapping("/selectById")
//	public String selectById(int id) {
//		MoviesDTO dto = dao.selectById(id);
//		System.out.println(dto.getId() + " : " + dto.getTitle() + " : " + dto.getGenre());
//		return "redirect:/";
//	}
//	
//	@RequestMapping("/selectCount")
//	public String selectCount() {
//		int result = dao.selectCount();
//		System.out.println(result);
//		return "redirect:/";
//	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		return "redirect:/error";
	}
	
	
}

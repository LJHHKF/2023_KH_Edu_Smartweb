package kh.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kh.spring.dto.MoviesDTO;

@RestController
@RequestMapping("/data/")
public class DataController {
	private List<MoviesDTO> list = new ArrayList<>();
	private int id = 1001;
	
	@RequestMapping("selectAll")
	public List<MoviesDTO> selectAll(){
		System.out.println("Axios 요청 확인");
		return list;
	}
	
	@RequestMapping("insert")
	public MoviesDTO insert(MoviesDTO dto) {
		dto.setId(id++);
		System.out.println(dto);
		list.add(dto);
		return dto;
	}
	
	@DeleteMapping("deleteById")
	public void delete(@RequestParam(value="id") int id) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId() == id) {
				list.remove(i);
				return;
			}
		}
	}
}

package kh.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kh.spring.dto.MoviesDTO;
import kh.spring.repositories.MoviesDAO;

@Service
public class MoviesService {
	
	@Autowired
	private MoviesDAO dao;
	
	@Transactional
	public void transactionTest(MoviesDTO movies) {
		dao.insert(movies);
		dao.insertHistory(movies);
	}
	
	public List<MoviesDTO> selectAll(){
		return dao.selectAll();
	}
	
	public int insert(MoviesDTO dto) {
		return dao.insert(dto);
	}
	
	public int delete(int id) {
		return dao.delete(id);
	}
	
	public int update(MoviesDTO dto) {
		return dao.update(dto);
	}
	
	public List<MoviesDTO> selectByCon(String column, String value){
		return dao.selectByCon(column, value);
	}
	
	public List<MoviesDTO> selectByMultiCon(MoviesDTO dto){
		return dao.selectByMultiCon(dto);
	}
}

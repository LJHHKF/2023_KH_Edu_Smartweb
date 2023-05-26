package kh.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.dto.BoardDTO;
import kh.spring.repositories.BoardDAO;
import kh.spring.repositories.FilesDAO;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	public List<BoardDTO> selectAll(){
		return boardDAO.selectAll();
	}
	
	public int insertReturnSeq(BoardDTO dto) {
		return boardDAO.insertReturnSeq(dto);
	}
	
	public int addViewCount(int seq) {
		return boardDAO.addViewCount(seq);
	}
	
	public BoardDTO selectBySeq(int seq) {
		return boardDAO.selectBySeq(seq);
	}
	
	public int update(BoardDTO dto) {
		return boardDAO.update(dto);
	}
	
	public int delete(int seq) {
		return boardDAO.delete(seq);
	}
}

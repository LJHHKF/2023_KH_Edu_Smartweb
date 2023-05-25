package kh.spring.repositories;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.dto.BoardDTO;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<BoardDTO> selectAll() {
		return mybatis.selectList("Board.selectAll");
	}
	
	public BoardDTO selectBySeq(int seq) {
		return mybatis.selectOne("Board.selectBySeq", seq);
	}
	
	public int insert(BoardDTO dto) {
		return mybatis.insert("Board.insert", dto);
	}
	
	public int update(BoardDTO dto) {
		return mybatis.update("Board.update", dto);
	}
	
	public int delete(int seq) {
		return mybatis.delete("Board.delete", seq);
	}
	
	public int addViewCount(int seq) {
		return mybatis.update("Board.addViewCount", seq);
	}
	
	public int getCurSeq() {
		return mybatis.selectOne("Board.getCurSeq");
	}
	
	public int getNextVal() {
		return mybatis.selectOne("Board.getNextVal");
	}
}

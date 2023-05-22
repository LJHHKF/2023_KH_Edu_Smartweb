package kh.spring.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kh.spring.dto.BoardDTO;

@Repository
public class BoardDAO {
	@Autowired
	private JdbcTemplate jdbc;
	
	public List<BoardDTO> selectAll() {
		String sql = "select * from BOARD order by SEQ desc";
		return jdbc.query(sql, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class));
	}
	
	public BoardDTO selectBySeq(int seq) {
		String sql = "select * from BOARD where SEQ=?";
		return jdbc.queryForObject(sql, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class), seq);
	}
	
	public int insert(BoardDTO dto) {
		String sql = "insert into BOARD(SEQ, WRITER, TITLE, CONTENTS, VIEW_COUNT, WRITE_DATE)"
				+ " values(BOARD_SEQ.nextval, ?, ?, ?, default, sysdate)";
		return jdbc.update(sql, dto.getWriter(), dto.getTitle(), dto.getContents());
	}
	
	public int update(BoardDTO dto) {
		String sql = "update BOARD"
				+ " set TITLE=?, CONTENTS=?"
				+ " where SEQ=?";
		return jdbc.update(sql, dto.getTitle(), dto.getContents(), dto.getSeq());
	}
	
	public int delete(int seq) {
		String sql = "delete from BOARD where SEQ=?";
		return jdbc.update(sql, seq);
	}
	

	
	public int addViewCount(int seq) {
		String sql = "update BOARD set VIEW_COUNT=VIEW_COUNT+1 where SEQ=?";
		return jdbc.update(sql, seq);
	}
}

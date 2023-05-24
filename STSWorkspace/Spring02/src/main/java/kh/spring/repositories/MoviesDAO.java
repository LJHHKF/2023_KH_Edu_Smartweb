package kh.spring.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.dto.MoviesDTO;

@Repository
public class MoviesDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int insert(MoviesDTO dto) {
		return mybatis.insert("Movies.insert", dto);
	}
	
	public List<MoviesDTO> selectAll(){
		return mybatis.selectList("Movies.selectAll");
	}
	
	public int delete(int id) {
		return mybatis.delete("Movies.delete", id);
	}
	
	public int update(MoviesDTO dto) {
		return mybatis.update("Movies.update", dto);
	}
	
	public List<MoviesDTO> selectByCon(String column, String value){
		Map<String, String> param = new HashMap<>();
		param.put("column", column);
		param.put("value", value);
		
		return mybatis.selectList("Movies.selectByCon", param);
	}
	
	public List<MoviesDTO> selectByMultiCon(MoviesDTO dto){		
		return mybatis.selectList("Movies.selectByMultiCon", dto);
	}

	
	
//	@Autowired
//	private JdbcTemplate jdbc;
//	
//	//Spring JDBC
//	// select => 단일 값 select는 queryForObject / 대량 값 select 는 query
//	// insert update delete 등 => 이런 쿼리들은 모두 update method 사용
//	
//	public int insert(MoviesDTO dto) {
//		String sql = "insert into MOVIES values(MOVIES_SEQ.nextval, ?, ?)";
//		return jdbc.update(sql, dto.getTitle(), dto.getGenre());
//	}
//	public int delete(int id) {
//		String sql = "delete from MOVIES where ID = ?";
//		return jdbc.update(sql, id);
//	}
//	public int update(MoviesDTO dto) {
//		String sql = "update MOVIES set TITLE=?, GENRE=? where ID=?";
//		return jdbc.update(sql,dto.getTitle(), dto.getGenre(), dto.getId());
//	}
//	
//	public List<MoviesDTO> selectAll(){
//		String sql = "select * from MOVIES order by ID";
//		return jdbc.query(sql, new BeanPropertyRowMapper<MoviesDTO>(MoviesDTO.class));
//	}
//	
//	public MoviesDTO selectById(int id) {
//		String sql = "select * from movies where id=?";
//		return jdbc.queryForObject(sql, new BeanPropertyRowMapper<MoviesDTO>(MoviesDTO.class), id);
//	}
//	
//	public int selectCount() {
//		String sql = "select count(*) from movies";
//		return jdbc.queryForObject(sql, Integer.class);
//	}
}

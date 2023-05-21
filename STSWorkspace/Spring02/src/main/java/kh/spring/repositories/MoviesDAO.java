package kh.spring.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kh.spring.dto.MoviesDTO;

@Repository
public class MoviesDAO {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	//Spring JDBC
	// select => 단일 값 select는 queryForObject / 대량 값 select 는 query
	// insert update delete 등 => 이런 쿼리들은 모두 update method 사용
	
	public int insert(MoviesDTO dto) {
		String sql = "insert into MOVIES values(MOVIES_SEQ.nextval, ?, ?)";
		return jdbc.update(sql, dto.getTitle(), dto.getGenre());
	}
	public int delete(int id) {
		String sql = "delete from MOVIES where ID = ?";
		return jdbc.update(sql, id);
	}
	public int update(MoviesDTO dto) {
		String sql = "update MOVIES set TITLE=?, GENRE=? where ID=?";
		return jdbc.update(sql,dto.getTitle(), dto.getGenre(), dto.getId());
	}
	
	public List<MoviesDTO> selectAll(){
		String sql = "select * from MOVIES order by ID";
		return jdbc.query(sql, new BeanPropertyRowMapper<MoviesDTO>(MoviesDTO.class));
	}
	
	public MoviesDTO selectById(int id) {
		String sql = "select * from movies where id=?";
		return jdbc.queryForObject(sql, new BeanPropertyRowMapper<MoviesDTO>(MoviesDTO.class), id);
	}
	
	public int selectCount() {
		String sql = "select count(*) from movies";
		return jdbc.queryForObject(sql, Integer.class);
	}
	
//	public List<MoviesDTO> selectAll(){
//		String sql = "select * from MOVIES order by ID";
//		return jdbc.query(sql, new RowMapper<MoviesDTO>() {
//			@Override
//			public MoviesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				int id = rs.getInt("ID");
//				String title = rs.getString("TITLE");
//				String genre = rs.getString("GENRE");
//				return new MoviesDTO(id, title, genre);
//			}
//		});
//	}
	
//oracle-jdbc 사용 전
//	@Autowired
//	private DataSource ds;
//	
//	public int insert(MoviesDTO dto) throws Exception {
//		String sql = "insert into MOVIES values(movies_seq.nextval, ?, ?)";
//		try(	Connection con = ds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);){
//			pstat.setString(1, dto.getTitle());
//			pstat.setString(2, dto.getGenre());
//			return pstat.executeUpdate();
//		}
//	}
//	
//	public List<MoviesDTO> selectAll() throws Exception{
//		String sql = "select * from MOVIES order by ID";
//		try(	Connection con = ds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);
//				ResultSet rs = pstat.executeQuery();){
//			return this.transAllRsToList(rs);
//		}
//	}
//	
//	public int delete(int id) throws Exception{
//		String sql = "delete from MOVIES where ID=?";
//		try(	Connection con = ds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);){
//			pstat.setInt(1, id);
//			return pstat.executeUpdate();
//		}
//	}
//	
//	public int update(MoviesDTO dto) throws Exception {
//		String sql = "update MOVIES set TITLE=?, GENRE=? where ID=?";
//		try(	Connection con = ds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);){
//			pstat.setString(1, dto.getTitle());
//			pstat.setString(2, dto.getGenre());
//			pstat.setInt(3, dto.getId());
//			return pstat.executeUpdate();
//		}
//	}
//	
//	private List<MoviesDTO> transAllRsToList(ResultSet rs) throws Exception{
//		List<MoviesDTO> result = new ArrayList<>();
//		while(rs.next()) {
//			int id = rs.getInt("ID");
//			String title = rs.getString("TITLE");
//			String genre = rs.getString("GENRE");
//			
//			result.add(new MoviesDTO(id, title, genre));
//		}
//		return result;
//	}
}

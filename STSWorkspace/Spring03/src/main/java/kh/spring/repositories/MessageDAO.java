package kh.spring.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kh.spring.dto.MessageDTO;

@Repository
public class MessageDAO {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	public int insert(MessageDTO dto) {
		String sql = "insert into MESSAGE values (MESSAGE_SEQ.nextval, ?, ?)";
		return jdbc.update(sql, dto.getWriter(), dto.getMessage());
	}
	
	public List<MessageDTO> selectAll(){
		String sql = "select * from MESSAGE order by SEQ";
		return jdbc.query(sql, new BeanPropertyRowMapper<MessageDTO>(MessageDTO.class));
	}
	
	public int delete(int seq) {
		String sql = "delete from MESSAGE where SEQ=?";
		return jdbc.update(sql, seq);
	}
	
	public int update(MessageDTO dto) {
		String sql = "update MESSAGE set WRITER=?, MESSAGE=? where SEQ=?";
		return jdbc.update(sql, dto.getWriter(), dto.getMessage(), dto.getSeq());
	}
	
	
//	@Autowired
//	private DataSource ds;
//	
//	public int insert(MessageDTO dto) throws Exception{
//		String sql = "insert into MESSAGE values (MESSAGE_SEQ.nextval, ?, ?)";
//		try(	Connection con = ds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);){
//			pstat.setString(1, dto.getWriter());
//			pstat.setString(2, dto.getMessage());
//			return pstat.executeUpdate();
//		}
//	}
//	
//	public List<MessageDTO> selectAll() throws Exception{
//		String sql = "select * from MESSAGE order by SEQ";
//		try(	Connection con = ds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);
//				ResultSet rs = pstat.executeQuery();){
//			return this.transAllRsToList(rs);
//		}
//	}
//	
//	public int delete(int seq) throws Exception{
//		String sql = "delete from MESSAGE where SEQ=?";
//		try(	Connection con = ds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);){
//			pstat.setInt(1, seq);
//			return pstat.executeUpdate();
//		}
//	}
//	
//	public int update(MessageDTO dto) throws Exception{
//		String sql = "update MESSAGE set WRITER=?, MESSAGE=? where SEQ=?";
//		try(	Connection con = ds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);){
//			pstat.setString(1, dto.getWriter());
//			pstat.setString(2, dto.getMessage());
//			pstat.setInt(3, dto.getSeq());
//			return pstat.executeUpdate();
//		}
//	}
//	
//	private List<MessageDTO> transAllRsToList(ResultSet rs) throws Exception{
//		List<MessageDTO> result = new ArrayList<>();
//		while(rs.next()) {
//			int seq = rs.getInt("SEQ");
//			String writer = rs.getString("WRITER");
//			String message = rs.getString("MESSAGE");
//			
//			result.add(new MessageDTO(seq, writer, message));
//		}
//		return result;
//	}
}

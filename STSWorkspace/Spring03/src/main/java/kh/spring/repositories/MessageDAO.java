package kh.spring.repositories;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.dto.MessageDTO;

@Repository
public class MessageDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int insert(MessageDTO dto) {
		return mybatis.insert("Message.insert", dto);
	}
	
	public List<MessageDTO> selectAll(){
		return mybatis.selectList("Message.selectAll");
	}
	
	public int delete(int seq) {
		return mybatis.delete("Message.delete", seq);
	}
	
	public int update(MessageDTO dto) {
		return mybatis.update("Message.update", dto);
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

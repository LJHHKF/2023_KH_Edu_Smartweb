package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.ReplyDTO;

public class ReplyDAO {
	private static ReplyDAO instance = null;
	public static ReplyDAO getInstance() {
		if(instance == null) {
			instance = new ReplyDAO();
		}
		return instance;
	}
	
	private ReplyDAO() {
		super();
	}
	
	private Connection getConnection() throws Exception {
		Context iContext = new InitialContext();
		DataSource ds = (DataSource)iContext.lookup("java:/comp/env/jdbc/ora");
		return ds.getConnection();
	}
	
	public int insert(ReplyDTO dto) throws Exception {
		// seq, writer, contents, write_date, parent_seq 
		String sql = "insert into reply values(reply_seq.nextval, ?, ?, default, ?)";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getWriter());
			pstat.setString(2, dto.getContents());
			pstat.setInt(3, dto.getParent_seq());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public ArrayList<ReplyDTO> selectPSeq(int p_seq) throws Exception{
		String sql = "select * from reply where parent_seq = ?";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, p_seq);
			try(ResultSet rs = pstat.executeQuery()){
				return this.transAllRsToList(rs);
			}
		}
	}
	
	public int delete(int seq) throws Exception{
		String sql = "delete from reply where seq = ?";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public int update(int seq, String contents) throws Exception{
		String sql = "update reply set contents = ? where seq = ?";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
			pstat.setString(1, contents);
			pstat.setInt(2, seq);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	private ArrayList<ReplyDTO> transAllRsToList(ResultSet rs) throws Exception{
		ArrayList<ReplyDTO> result = new ArrayList<>();
		while(rs.next()) {
			int seq = rs.getInt("seq");
			String writer = rs.getString("writer");
			String contents = rs.getString("contents");
			Timestamp write_date = rs.getTimestamp("write_date");
			int parent_seq = rs.getInt("parent_seq");
			
			result.add(new ReplyDTO(seq, writer, contents, write_date, parent_seq));
		}
		return result;
	}
}

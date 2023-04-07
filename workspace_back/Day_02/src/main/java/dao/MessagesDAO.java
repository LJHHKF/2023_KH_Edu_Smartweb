package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MessagesDTO;

public class MessagesDAO {
	private static MessagesDAO instance = null;
	
	public synchronized static MessagesDAO getInstance() {
		if(instance == null) {
			instance = new MessagesDAO();
		}
		return instance;
	}
	
	private Connection getConnection() throws Exception {
		Context iContext = new InitialContext();
		DataSource ds = (DataSource)iContext.lookup("java:/comp/env/jdbc/ora");
		return ds.getConnection();
	}
	
	public int insert(MessagesDTO dto) throws Exception {
		String sql = "Insert into messages values (msg_seq.nextval, ?, ?)";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getWriter());
			pstat.setString(2, dto.getMessage());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public ArrayList<MessagesDTO> select() throws Exception{
		String sql = "select * from messages order by id desc";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			try(ResultSet rs = pstat.executeQuery();){
				return this.transAllRsToList(rs);
			}
		}
	}
	
	public int delete(int id) throws Exception{
		String sql = "delete from messages where id=?";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, id);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public int update(MessagesDTO dto) throws Exception {
		String sql = "update messages set writer=?,message=? where id=?";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getWriter());
			pstat.setString(2, dto.getMessage());
			pstat.setInt(3, dto.getId());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	private ArrayList<MessagesDTO> transAllRsToList(ResultSet rs) throws Exception{
		ArrayList<MessagesDTO> result = new ArrayList<>();
		while(rs.next()) {
			int id = rs.getInt("id");
			String writer = rs.getString("writer");
			String message = rs.getString("message");
			
			result.add(new MessagesDTO(id, writer, message));
		}
		return result;
	}
}

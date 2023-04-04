package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.MessagesDTO;

public class MessagesDAO {
	private String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	private String dbId = "kh";
	private String dbPw = "kh";
	
	public MessagesDAO() {
		super();
	}
	
	public MessagesDAO(String dbUrl, String dbId, String dbPw) {
		super();
		this.dbUrl = dbUrl;
		this.dbId = dbId;
		this.dbPw = dbPw;
	}

	public int insert(MessagesDTO dto) throws SQLException {
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
	
	public ArrayList<MessagesDTO> select() throws SQLException{
		String sql = "select * from messages order by id";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			try(ResultSet rs = pstat.executeQuery();){
				return this.transAllRsToList(rs);
			}
		}
	}
	
	public int delete(int id) throws SQLException{
		String sql = "delete from messages where id=?";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, id);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public int update(MessagesDTO dto) throws SQLException {
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
	
	private Connection getConnection() throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("ojdbc를 못 찾았습니다.");
		}
		return DriverManager.getConnection(dbUrl, dbId, dbPw);
	}
	
	private ArrayList<MessagesDTO> transAllRsToList(ResultSet rs) throws SQLException{
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

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import dto.ContactsDTO;

public class ContactsDAO {
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String dbId = "kh";
	private String dbPw = "kh";
	
	public ContactsDAO() {
		super();
	}
	public ContactsDAO(String url, String dbId, String dbPw) {
		super();
		this.url = url;
		this.dbId = dbId;
		this.dbPw = dbPw;
	}

	private Connection getConnection() throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("ojdbc 로드 중 오류가 났습니다.");
		}
		return DriverManager.getConnection(url, dbId, dbPw);
	}
	
	public int insert(ContactsDTO dto) throws SQLException{
		String sql = "insert into contacts values (contacts_seq.nextval, ?, ?, ?)";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getContact());
			pstat.setTimestamp(3, dto.getBirthday());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public ArrayList<ContactsDTO> selectAll() throws SQLException{
		String sql = "select * from contacts order by id";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			try(ResultSet rs = pstat.executeQuery();){
				return this.transAllRsToList(rs);
			}
		}
	}
	
	public int delete(int id) throws SQLException {
		String sql = "delete from contacts where id=?";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, id);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public int update(ContactsDTO dto) throws SQLException {
		String sql = "update contacts set name=?,contact=?,birthday=? where id=?";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getContact());
			pstat.setTimestamp(3, dto.getBirthday());
			pstat.setInt(4, dto.getId());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	private ArrayList<ContactsDTO> transAllRsToList(ResultSet rs) throws SQLException{
		ArrayList<ContactsDTO> result = new ArrayList<>();
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String contact = rs.getString("contact");
			Timestamp birthday = rs.getTimestamp("birthday");
			
			result.add(new ContactsDTO(id,name,contact,birthday));
		}
		return result;
	}
}

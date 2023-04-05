package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.ContactsDTO;

public class ContactsDAO {
	private static ContactsDAO instance = null;
	
	public synchronized static ContactsDAO getInstance() {
		if(instance == null) {
			instance = new ContactsDAO();
		}
		return instance;
	}
	
	private ContactsDAO() {
		super();
	}

	private Connection getConnection() throws Exception {
		Context iContext = new InitialContext();
		DataSource ds = (DataSource)iContext.lookup("java:/comp/env/jdbc/ora");
		return ds.getConnection();
	}
	
	public int insert(ContactsDTO dto) throws Exception{
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
	
	public ArrayList<ContactsDTO> selectAll() throws Exception{
		String sql = "select * from contacts order by id";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			try(ResultSet rs = pstat.executeQuery();){
				return this.transAllRsToList(rs);
			}
		}
	}
	
	public int delete(int id) throws Exception {
		String sql = "delete from contacts where id=?";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, id);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public int update(ContactsDTO dto) throws Exception {
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
	
	private ArrayList<ContactsDTO> transAllRsToList(ResultSet rs) throws Exception{
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

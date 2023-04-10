package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MembersDTO;

public class MembersDAO {
	private static MembersDAO instance = null;
	
	public static MembersDAO getInstance() {
		if(instance == null) {
			instance = new MembersDAO();
		}
		return instance;
	}
	
	private MembersDAO() {
		super();
	}
	
	private Connection getConnection() throws Exception {
		Context iContext = new InitialContext();
		DataSource ds = (DataSource)iContext.lookup("java:/comp/env/jdbc/ora");
		return ds.getConnection();
	}
	
	public boolean isIdExist(String id) throws Exception {
		String sql = "select id from members where id=?";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			try(ResultSet rs = pstat.executeQuery();){
				return rs.next();
			}
		}
	}
	
	public int insert(MembersDTO dto) throws Exception {
		//id(string), pw, name, phone, email, zipcode, address, adress2, join_date(default)
		String sql = "insert into members values (?, ?, ?, ?, ?, ?, ?, ?, default)";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPw());
			pstat.setString(3, dto.getName());
			pstat.setString(4, dto.getPhone());
			pstat.setString(5, dto.getEmail());
			pstat.setString(6, dto.getZipcode());
			pstat.setString(7, dto.getAddress1());
			pstat.setString(8, dto.getAddress2());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public boolean login(String id, String pw) throws Exception{
		String sql = "select id,pw from members where id=? and pw=?";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			pstat.setString(2, pw);
			try(ResultSet rs = pstat.executeQuery();){
				return rs.next();
			}
		}
	}
	
	public int delete(String id) throws Exception{
		String sql = "delete from members where id=?";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public MembersDTO getMyDto(String id) throws Exception{
		String sql = "select * from members where id=?";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			try(ResultSet rs = pstat.executeQuery();){
				return this.transAllRsToList(rs).get(0);
			}
		}
	}
	
	private ArrayList<MembersDTO> transAllRsToList(ResultSet rs) throws Exception{
		ArrayList<MembersDTO> result = new ArrayList<>();
		while(rs.next()) {
			String id = rs.getString("id");
			String pw = rs.getString("pw");
			String name = rs.getString("name");
			String phone = rs.getString("phone");
			String email = rs.getString("email");
			String zipcode = rs.getString("zipcode");
			String address1 = rs.getString("address1");
			String address2 = rs.getString("address2");
			Timestamp join_date = rs.getTimestamp("join_date");
			
			result.add(new MembersDTO(id, pw, name, phone, email, zipcode, address1, address2, join_date));
		}
		return result;
	}
}

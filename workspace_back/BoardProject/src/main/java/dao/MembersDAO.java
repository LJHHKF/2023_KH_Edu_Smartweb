package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}

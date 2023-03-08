package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

import dto.MembersDTO;

public class MembersDAO {
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String dbID = "kh";
	private String dbPW = "kh";
	private BasicDataSource bds = new BasicDataSource();
	private final int poolSize = 5;
	
	public MembersDAO() throws Exception {
		super();
		this.init();
	}
	
	public MembersDAO(String url, String dbID, String dbPW) throws Exception {
		super();
		this.url = url;
		this.dbID = dbID;
		this.dbPW = dbPW;
		
		this.init();
	}
	
	private void init() throws Exception{
		//this.bds = new BasicDataSource();
		bds.setUrl(url);
		bds.setUsername(dbID);
		bds.setPassword(dbPW);
		bds.setInitialSize(poolSize);
	}
	
//	private Connection createConnection() throws Exception {
//		return DriverManager.getConnection(url, dbPW, dbID);
//	}
	
	public int insert(MembersDTO dto) throws Exception{
		String sql = "insert into MEMBERS(ID, PW, NAME, CONTACT) values(?, ?, ?, ?)";
		try(	Connection con = this.bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPw());
			pstat.setString(3, dto.getName());
			pstat.setString(4, dto.getContact());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public boolean isLogin(String id, String pw) throws Exception {
		//PreparedStatement
		String sql = "select * from MEMBERS where ID = ? and PW = ?";
		try(	Connection con = this.bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			pstat.setString(2, pw);
			try(ResultSet rs = pstat.executeQuery();) {
				return rs.next();
			}
		}
	}
	
	public boolean isIdExist(String id) throws Exception{
		String sql = "select ID from MEMBERS where ID = ?";
		try(	Connection con = this.bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
			pstat.setString(1, id);
			try(ResultSet rs = pstat.executeQuery();){
				return rs.next();
			}
		}
	}
	
	public ArrayList<MembersDTO> selectAll() throws Exception{
		String sql = "select * from members";
		try(	Connection con = this.bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){
			return this.transAllRsToList(rs);
		}
	}
	
	private ArrayList<MembersDTO> transAllRsToList(ResultSet rs) throws Exception{
		ArrayList<MembersDTO> result = new ArrayList<>();
		while(rs.next()) {
			String id = rs.getString("ID");
			String pw = rs.getString("PW");
			String name = rs.getString("NAME");
			String contact = rs.getString("CONTACT");
			Timestamp reg_date = rs.getTimestamp("REG_DATE");
			result.add(new MembersDTO(id, pw, name, contact, reg_date));
		}
		return result;
	}
	
}

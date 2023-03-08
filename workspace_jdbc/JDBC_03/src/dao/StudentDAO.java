package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

import dto.StudentDTO;

public class StudentDAO {
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String dbID = "kh";
	private String dbPW = "kh";
	private BasicDataSource bds = new BasicDataSource();
	private final int poolSize = 5;
	
	public StudentDAO() throws Exception {
		super();
		this.init();
	}
	public StudentDAO(String url, String dbID, String dbPW) throws Exception{
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
	
	
//	private Connection createConnection() throws Exception{
//		return DriverManager.getConnection(url,dbID,dbPW);
//	}
	
	public int insert(StudentDTO dto) throws Exception {
		String sql = "insert into STUDENTS values(STUDENTS_SEQ.nextval, ?, ?, ?, ?)";
		try(	Connection con = this.bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
			pstat.setString(1, dto.getName());
			pstat.setInt(2, dto.getKor());
			pstat.setInt(3, dto.getEng());
			pstat.setInt(4, dto.getMath());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
		
	}
	
	public ArrayList<StudentDTO> selectAll() throws Exception{
		String sql = "select * from STUDENTS";
		try(	Connection con = this.bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery()){
			return this.transAllRsToList(rs);
		}
	}
	
	public boolean isIdExist(int id) throws Exception{
		String sql = "select ID from STUDENTS where ID = ?";
		try(	Connection con = this.bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, id);
			try(ResultSet rs = pstat.executeQuery()){
				return rs.next();
			}
		}
	}
	
	public int updateByID(StudentDTO dto) throws Exception{
		String sql = "update STUDENTS set "
					+"NAME = ?, KOR = ?, ENG = ?, MATH = ?"
					+" where ID = ?";
		try(	Connection con = this.bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getName());
			pstat.setInt(2, dto.getKor());
			pstat.setInt(3, dto.getEng());
			pstat.setInt(4, dto.getMath());
			pstat.setInt(5, dto.getId());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
		
	}
	
	public int deleteByID(int id) throws Exception{
		String sql = "delete from STUDENTS where ID = ?";
		try(	Connection con = this.bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, id);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public ArrayList<StudentDTO> searchByName(String name) throws Exception{
		String sql = "select * from STUDENTS where NAME like ?";
		try(	Connection con = this.bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+name+"%");
			try(ResultSet rs = pstat.executeQuery()){
				return this.transAllRsToList(rs);
			}
		}
	}
	
	private ArrayList<StudentDTO> transAllRsToList(ResultSet rs) throws Exception{
		ArrayList<StudentDTO> result = new ArrayList<>();
		while(rs.next()) {
			int id = rs.getInt("ID");
			String name = rs.getString("NAME");
			int kor = rs.getInt("KOR");
			int eng = rs.getInt("ENG");
			int math = rs.getInt("MATH");
			result.add(new StudentDTO(id, name, kor, eng, math));
		}
		return result;
	}
}
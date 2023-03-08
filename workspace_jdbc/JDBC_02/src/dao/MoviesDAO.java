package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.MoviesDTO;

// �����Ϳ� ���õ� ��� �۾��� �Ѱ��ϴ� Ŭ����
// DAO : Data Access Object
public class MoviesDAO {
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String dbID = "kh";
	private String dbPW = "kh";
	
	public MoviesDAO() throws Exception {
		super();
	}
	
	public MoviesDAO(String url, String dbID, String dbPW) throws Exception {
		super();
		this.url = url;
		this.dbID = dbID;
		this.dbPW = dbPW;
	}
	
	public int insert(MoviesDTO dto) throws Exception {
		String sql = "insert into MOVIES values(MOVIES_SEQ.nextval, ?, ?)";
		try(	Connection con = this.createConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getGenre());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public boolean isIdExist(int id) throws Exception {
		String sql = "select * from MOVIES where ID = ?";
		try(	Connection con = this.createConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, id);
			try(ResultSet rs = pstat.executeQuery()){
				return rs.next();
			}
		}
	}

	
	public int updateToID(MoviesDTO dto) throws Exception {
		String sql = "update MOVIES set "
				+"TITLE = ?, GENRE = ?"
				+"where ID = ?";
		try(	Connection con = this.createConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getGenre());
			pstat.setInt(3, dto.getId());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public int deleteToID(int id) throws Exception {
		String sql = "delete from MOVIES where ID = ?";
		try(	Connection con = this.createConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, id);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
		
	}
	
	public ArrayList<MoviesDTO> selectAll() throws Exception {
		String sql = "select * from MOVIES";
		try(	Connection con = this.createConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery()){
			return this.transAllRsToList(rs);
		}
	}
	
	public ArrayList<MoviesDTO> searchByTitle(String title) throws Exception {
		String sql = "select * from MOVIES where TITLE like ?";
		try(	Connection con = this.createConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+title+"%");
			try(ResultSet rs = pstat.executeQuery()){
				return this.transAllRsToList(rs);
			}
		}
	}
	
	private Connection createConnection() throws Exception {
		return DriverManager.getConnection(url, dbID, dbPW);
	}
	
	private ArrayList<MoviesDTO> transAllRsToList(ResultSet rs) throws Exception{
		ArrayList<MoviesDTO> result = new ArrayList<>();
		while(rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String genre = rs.getString("genre");
			result.add(new MoviesDTO(id, title, genre));
		}
		return result;
	}
}
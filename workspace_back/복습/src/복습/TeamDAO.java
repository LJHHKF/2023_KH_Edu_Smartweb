package 복습;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

public class TeamDAO {
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String dbId = "team";
	private String dbPw = "team";
	private BasicDataSource bds = new BasicDataSource();
	private final int poolsize = 7;
	
	public TeamDAO() throws Exception{
		super();
		this.init();
	}
	public TeamDAO(String url, String dbId, String dbPw) throws Exception {
		super();
		this.url = url;
		this.dbId = dbId;
		this.dbPw = dbPw;
		
		this.init();
	}
	
	private void init() throws Exception{
		bds.setUrl(url);
		bds.setUsername(dbId);
		bds.setPassword(dbPw);
		bds.setInitialSize(poolsize);
	}
	
	public int insert(TeamDTO dto) throws Exception {
		String sql = "insert into TEAM_CONTACT values(TEAM_CONTACT_SEQ.nextval,?,?,?,?)";
		try(	Connection con = this.bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getName());
			pstat.setInt(2, dto.getAge());
			pstat.setString(3, dto.getContact());
			pstat.setTimestamp(4, dto.getBirthday());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public ArrayList<TeamDTO> selectAll() throws Exception{
		String sql = "select * from TEAM_CONTACT";
		try(	Connection con = this.bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			try(ResultSet rs = pstat.executeQuery();){
				return this.transAllRsToList(rs);
			}
		}
	}
	
	private ArrayList<TeamDTO> transAllRsToList(ResultSet rs) throws Exception{
		ArrayList<TeamDTO> result = new ArrayList<>();
		while(rs.next()) {
			int id = rs.getInt("ID");
			String name = rs.getString("NAME");
			int age = rs.getInt("AGE");
			String contact = rs.getString("CONTACT");
			Timestamp update_date = rs.getTimestamp("BIRTHDAY");
			result.add(new TeamDTO(id, name, age, contact, update_date));
		}
		return result;
	}
}

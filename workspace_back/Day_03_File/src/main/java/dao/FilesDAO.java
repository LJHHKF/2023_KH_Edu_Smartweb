package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.FilesDTO;

public class FilesDAO {
	private static FilesDAO instance = null;
	public synchronized static FilesDAO getInstance() {
		if(instance == null) {
			instance = new FilesDAO();
		}
		return instance;
	}
	
	private FilesDAO(){
		super();
	}
	
	private Connection getConnection() throws Exception{
		Context iCtx = new InitialContext();
		DataSource ds = (DataSource)iCtx.lookup("java:/comp/env/jdbc/ora");
		return ds.getConnection();
	}
	
	public int insert(FilesDTO dto) throws Exception{
		String sql = "insert into files values(files_seq.nextval,?,?,?)";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getOriName());
			pstat.setString(2, dto.getSysName());
			pstat.setInt(3, dto.getParent_seq());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public ArrayList<FilesDTO> selectAll() throws Exception{
		String sql = "select * from files";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){
			return this.transAllRsToList(rs);
		}
	}
	
	private ArrayList<FilesDTO> transAllRsToList(ResultSet rs) throws Exception{
		ArrayList<FilesDTO> result = new ArrayList<>();
		while(rs.next()) {
			int seq = rs.getInt("seq");
			String oriName = rs.getString("oriname");
			String sysName = rs.getString("sysname");
			int parent_seq = rs.getInt("parent_seq");
			
			result.add(new FilesDTO(seq, oriName, sysName, parent_seq));
		}
		return result;
	}
}

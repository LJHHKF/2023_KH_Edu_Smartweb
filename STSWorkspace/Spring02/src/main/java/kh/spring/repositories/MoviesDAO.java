package kh.spring.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.dto.MoviesDTO;

@Repository
public class MoviesDAO {
	
	@Autowired
	private DataSource ds;
	
	public int insert(MoviesDTO dto) throws Exception {
		String sql = "insert into MOVIES values(movies_seq.nextval, ?, ?)";
		try(	Connection con = ds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getGenre());
			return pstat.executeUpdate();
		}
	}
	
	public List<MoviesDTO> selectAll() throws Exception{
		String sql = "select * from MOVIES order by ID";
		try(	Connection con = ds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){
			return this.transAllRsToList(rs);
		}
	}
	
	public int delete(int id) throws Exception{
		String sql = "delete from MOVIES where ID=?";
		try(	Connection con = ds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, id);
			return pstat.executeUpdate();
		}
	}
	
	public int update(MoviesDTO dto) throws Exception {
		String sql = "update MOVIES set TITLE=?, GENRE=? where ID=?";
		try(	Connection con = ds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getGenre());
			pstat.setInt(3, dto.getId());
			return pstat.executeUpdate();
		}
	}
	
	private List<MoviesDTO> transAllRsToList(ResultSet rs) throws Exception{
		List<MoviesDTO> result = new ArrayList<>();
		while(rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String genre = rs.getString("GENRE");
			
			result.add(new MoviesDTO(id, title, genre));
		}
		return result;
	}
}

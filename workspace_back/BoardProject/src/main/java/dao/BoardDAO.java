package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardDTO;

public class BoardDAO {
	private static BoardDAO instance = null;
	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	private BoardDAO() {
		super();
	}
	
	private Connection getConnection() throws Exception {
		Context iContext = new InitialContext();
		DataSource ds = (DataSource)iContext.lookup("java:/comp/env/jdbc/ora");
		return ds.getConnection();
	}
	
	public int create(BoardDTO dto) throws Exception {
		//seq(def), writer, title, contents, view_count(def), write_date(def)
		String sql = "insert into board values (board_seq.nextval, ?, ?, ?, default, default)";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getWriter());
			pstat.setString(2, dto.getTitle());
			pstat.setString(3, dto.getContents());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public ArrayList<BoardDTO> readAll() throws Exception{
		String sql = "select * from board";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			try(ResultSet rs = pstat.executeQuery()){
				return this.transAllRsToList(rs);
			}
		}
	}
	
	private ArrayList<BoardDTO> transAllRsToList(ResultSet rs) throws Exception{
		ArrayList<BoardDTO> result = new ArrayList<>();
		while(rs.next()) {
			int seq = rs.getInt("seq");
			String writer = rs.getString("writer");
			String title = rs.getString("title");
			String contents = rs.getString("contents");
			int view_count = rs.getInt("view_count");
			Timestamp write_date = rs.getTimestamp("write_date");
			
			result.add(new BoardDTO(seq, writer, title, contents, view_count, write_date));
		}
		return result;
	}
}

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
import dto.BoardNaviDTO;
import statics.Settings;

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

//	public ArrayList<BoardDTO> readAll() throws Exception{
//		String sql = "select * from board order by seq desc";
//		try(	Connection con = this.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);){
//			try(ResultSet rs = pstat.executeQuery()){
//				return this.transAllRsToList(rs);
//			}
//		}
//	}

	public BoardDTO readOne(int seq) throws Exception {
		String sql = "select * from board where seq=?";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			try(ResultSet rs = pstat.executeQuery()){
				return this.transAllRsToList(rs).get(0);
			}
		}
	}
	
	public int addViewCount(int seq) throws Exception {
		String sql = "update board set view_count=view_count+1 where seq=?";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)){
			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public int update(int seq, String title, String contents) throws Exception {
		String sql = "update board set title=?,contents=? where seq=?";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, title);
			pstat.setString(2, contents);
			pstat.setInt(3, seq);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public int delete(int seq) throws Exception{
		String sql = "delete from board where seq=?";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	private int getRecordCount() throws Exception{
		String sql = "select count(*) from board";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){
			rs.next();
			return rs.getInt(1);
		}
	}
	
	private int getSearchedRecordCount(String option, String keyword) throws Exception{
		String sql = null;
		if(option.equals("title")) {
			sql = "select count(*) from board where title like ?";
		}else if(option.equals("writer")) {
			sql = "select count(*) from board where writer like ?";
		}
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+keyword+"%");
			try(ResultSet rs = pstat.executeQuery();){
				rs.next();
				return rs.getInt(1);
			}
		}
	}
	
	public int getMaxSeq() throws Exception{
		String sql = "select seq from board order by seq desc";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){
			rs.next();
			return rs.getInt(1);
		}
	}
	
	public BoardNaviDTO getPageNavi(int currentPage) throws Exception{
		//1. 전체 글의 개수
		int recordTotalCount = this.getRecordCount();
		//2. 페이지당 보여줄 글의 개수
		int recordCountPerPage = Settings.BOARD_NAVI_COUNT_PER_PAGE;
		//3. 페이지당 보여줄 네비게이터의 수
		int naviCountPerPage = Settings.BOARD_RECORD_COUNT_PER_PAGE;
		
		//4. 1번과 2번 항목에 의해 총 페이지의 개수가 정해짐.
		//전체 글의 개수를 페이지당 
		int pageTotalCount = recordTotalCount%recordCountPerPage > 0 ?
				recordTotalCount/recordCountPerPage + 1
				:recordTotalCount/recordCountPerPage;
		
		if(currentPage < 1) {
			currentPage = 1;
		}else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int startNavi = (currentPage-1)/naviCountPerPage*naviCountPerPage+1;
		int endNavi = startNavi + (naviCountPerPage-1);
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		ArrayList<Integer> list = new ArrayList<>();
		
		if(startNavi == 1) {needPrev = false;}
		if(endNavi == pageTotalCount) {needNext = false;}
		for(int i = startNavi; i <= endNavi; i++) {
			list.add(i);
		}

		return new BoardNaviDTO(list, needPrev, needNext);
	}
	
	public BoardNaviDTO getSearchedPageNavi(int currentPage, String option, String keyword) throws Exception {
		//1. 전체 글의 개수
				int recordTotalCount = this.getSearchedRecordCount(option, keyword);
				//2. 페이지당 보여줄 글의 개수
				int recordCountPerPage = Settings.BOARD_NAVI_COUNT_PER_PAGE;
				//3. 페이지당 보여줄 네비게이터의 수
				int naviCountPerPage = Settings.BOARD_RECORD_COUNT_PER_PAGE;
				
				//4. 1번과 2번 항목에 의해 총 페이지의 개수가 정해짐.
				//전체 글의 개수를 페이지당 
				int pageTotalCount = recordTotalCount%recordCountPerPage > 0 ?
						recordTotalCount/recordCountPerPage + 1
						:recordTotalCount/recordCountPerPage;
				
				if(currentPage < 1) {
					currentPage = 1;
				}else if(currentPage > pageTotalCount) {
					currentPage = pageTotalCount;
				}
				
				int startNavi = (currentPage-1)/naviCountPerPage*naviCountPerPage+1;
				int endNavi = startNavi + (naviCountPerPage-1);
				
				if(endNavi > pageTotalCount) {
					endNavi = pageTotalCount;
				}
				
				boolean needPrev = true;
				boolean needNext = true;
				ArrayList<Integer> list = new ArrayList<>();
				
				if(startNavi == 1) {needPrev = false;}
				if(endNavi == pageTotalCount) {needNext = false;}
				for(int i = startNavi; i <= endNavi; i++) {
					list.add(i);
				}

				return new BoardNaviDTO(list, needPrev, needNext);
	}
	
	public ArrayList<BoardDTO> selectBound(int start, int end) throws Exception{
		String sql = "select * "
				+ "from "
				+ "(select board.*, row_number() over(order by seq desc) rn "
				+ "from board) "
				+ "where rn between ? and ?";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			try(ResultSet rs = pstat.executeQuery();){
				return this.transAllRsToList(rs);
			}
		}
	}
	
	public ArrayList<BoardDTO> selectBoundSearched(int start, int end, String option, String keyword) throws Exception{
		String sql = null;
		if(option.equals("title")) {
			sql = "select * "
					+ "from "
					+ "(select board.*, row_number() over(order by seq desc) rn "
					+ "from board "
					+ "where title like ?)"
					+ "where rn between ? and ?";
		}else if(option.equals("writer")) {
			sql = "select * "
					+ "from "
					+ "(select board.*, row_number() over(order by seq desc) rn "
					+ "from board "
					+ "where writer like ?)"
					+ "where rn between ? and ?";
		}
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+keyword+"%");
			pstat.setInt(2, start);
			pstat.setInt(3, end);
			try(ResultSet rs = pstat.executeQuery();){
				return this.transAllRsToList(rs);
			}
		}
	}
	
	public int getCurrVal() throws Exception{
		String sql = "select board_seq.currval from dual";
		try(	Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){
			rs.next();
			return rs.getInt(1);
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

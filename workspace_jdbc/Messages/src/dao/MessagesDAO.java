package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

import dto.MessagesDTO;

public class MessagesDAO {
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String dbID = "board";
	private String dbPW = "board";
	private final BasicDataSource bds = new BasicDataSource();
	private final int poolSize = 5;
	
	public MessagesDAO(){
		super();
		this.init();
	}
	
	
	public MessagesDAO(String url, String dbID, String dbPW){
		super();
		this.url = url;
		this.dbID = dbID;
		this.dbPW = dbPW;
		this.init();
	}

	private void init() {
		bds.setUrl(url);
		bds.setUsername(dbID);
		bds.setPassword(dbPW);
		bds.setInitialSize(poolSize);
	}
	
	public int insert(MessagesDTO dto) throws Exception {
		String sql = null;
		if(dto.getWrite_date() != null) {
			sql = "insert into MESSAGES values(SEQ_MESSAGES.nextval, ?, ?, ?)";
		}else {
			sql = "insert into MESSAGES(SEQ,WRITER,MESSAGE) values(SEQ_MESSAGES.nextval, ?, ?)";
		}
		try(	Connection con = this.bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, dto.getWriter());
			pstat.setString(2, dto.getMessage());
			if(dto.getWrite_date() != null) {
				pstat.setTimestamp(3, dto.getWrite_date());
			}
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public ArrayList<MessagesDTO> selectAll() throws Exception{
		String sql = "select * from MESSAGES";
		try(	Connection con = this.bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){
			return this.transAllRsToList(rs);
		}
	}
	
	public boolean isSeqExist(int seq) throws Exception{
		String sql = "select SEQ from MESSAGES where SEQ = ?";
		try(	Connection con = this.bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			try(ResultSet rs = pstat.executeQuery()){
				return rs.next();
			}
		}
	}
	
	public int deleteBySeq(int seq) throws Exception{
		String sql = "delete from MESSAGES where SEQ = ?";
		try(	Connection con = this.bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public String getWriterBySeq(int seq) throws Exception{
		String sql = "select WRITER from MESSAGES where SEQ = ?";
		try(	Connection con = this.bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			try(ResultSet rs = pstat.executeQuery()){
				if(rs.next()) {
					return rs.getString("WRITER");
				}
				return null;
			}
		}
	}

	
	public int updateBySeq(int seq, String newMessage) throws Exception {
		String sql = "update MESSAGES set MESSAGE = ? where SEQ = ?";
		try(	Connection con = this.bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, newMessage);
			pstat.setInt(2, seq);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public int updateBySeq(int seq, String newMessage, Timestamp newTime) throws Exception {
		String sql = "update MESSAGES set MESSAGE = ?, WRITE_DATE = ? where SEQ = ?";
		try(	Connection con = this.bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, newMessage);
			pstat.setTimestamp(2, newTime);
			pstat.setInt(3, seq);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public ArrayList<MessagesDTO> search(String keyword) throws Exception{
		String sql = "select * from MESSAGES where WRITER like ? or MESSAGE like ?"
					+ "or WRITER = (select ID from MEMBERS where NAME like ?)";
		try(	Connection con = this.bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, "%"+keyword+"%");
			pstat.setString(2, "%"+keyword+"%");
			pstat.setString(3, "%"+keyword+"%");
			try(ResultSet rs = pstat.executeQuery()){
				return this.transAllRsToList(rs);
			}
		}
	}
	
	public ArrayList<MessagesDTO> searchById(String id) throws Exception{
		String sql = "select * from MESSAGES where WRITER = ?";
		try(	Connection con = this.bds.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, id);
			try(ResultSet rs = pstat.executeQuery()){
				return this.transAllRsToList(rs);
			}
		}
	}
	
	private ArrayList<MessagesDTO> transAllRsToList(ResultSet rs) throws Exception{
		ArrayList<MessagesDTO> result = new ArrayList<>();
		while(rs.next()) {
			int seq = rs.getInt("SEQ");
			String writer = rs.getString("WRITER");
			String message = rs.getString("MESSAGE");
			Timestamp write_date = rs.getTimestamp("WRITE_DATE");
			result.add(new MessagesDTO(seq, writer, message, write_date));
		}
		return result;
	}
}

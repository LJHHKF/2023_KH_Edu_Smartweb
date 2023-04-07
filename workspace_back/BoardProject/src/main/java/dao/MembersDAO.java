package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
}

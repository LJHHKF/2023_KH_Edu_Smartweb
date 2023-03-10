package test06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDAO {
	public ShopMember searchUser(String memberId) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "test_01";
		String password = "1234";
		ShopMember shopMember = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			String query = "select * from SHOP_MEMBER where MEMBER_ID = ?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				shopMember = new ShopMember();
				shopMember.setMemberId(rs.getString("MEMBER_ID"));
				shopMember.setMemberPw(rs.getString("MEMBER_PW"));
				shopMember.setMemberName(rs.getString("MEMBER_NAME"));
				shopMember.setMemberAge(rs.getInt("MEMBER_AGE"));
				shopMember.setPhone(rs.getString("MEMBER_PHONE"));
				shopMember.setAddr(rs.getString("MEMBER_ADDR"));
			}
			rs.close();
			stmt.close();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return shopMember;
	}
}

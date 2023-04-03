package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SelectMessage")
public class SelectMessage extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("ojdbc를 못 찾았습니다.");
		}
		try {
			String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			String dbId = "kh";
			String dbPw = "kh";
			String sql = "Select * from messages";
			try(	Connection con = DriverManager.getConnection(dbUrl, dbId, dbPw);
					PreparedStatement pstat = con.prepareStatement(sql);){
				try(ResultSet rs = pstat.executeQuery()){
					while(rs.next()) {
						int id = rs.getInt("id");
						String writer = rs.getString("writer");
						String message = rs.getString("message");
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB 사용 중 오류가 났습니다.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

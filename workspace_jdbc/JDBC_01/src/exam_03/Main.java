package exam_03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println("라이브러리 탐색 실패");
			System.exit(0);
		}
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbID = "kh";
		String dbPW = "kh";
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, dbID, dbPW);
			System.out.println("연결 성공");
			
			Statement stat = con.createStatement();
			String sql
				= "update CAFE set PRICE = 3000 where NAME = 'Americano'";
			int result = stat.executeUpdate(sql);
			if(result > 0) {
				System.out.println("갱신 성공");
			}else {
				System.out.println("갱신 실패");
			}
			
			con.commit();
			con.close();
			
		}catch (Exception e){
			System.out.println("데이터베이스 연결 실패");
			e.printStackTrace();
		}

	}
}

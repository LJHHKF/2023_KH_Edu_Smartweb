package exam_04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println("라이브러리 탐색 실패");
			System.exit(0);
		}
		
		Connection con = null;
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String dbID = "kh";
			String dbPW = "kh";
			con = DriverManager.getConnection(url, dbID, dbPW);
			System.out.println("연결 성공");
			
			Statement stat = con.createStatement();
			String sql
				= "select * from department";
			
			ResultSet rs = stat.executeQuery(sql);
			
			while(true) {
				if(rs.next())
				{
					//반드시 이름이 같아야 할 이유는 없지만, 가급적이면 이름이 동일한 것이 권장됨.
					String dept_id = rs.getString("dept_id"); 
					String dept_title = rs.getString("dept_title");
					String location_id = rs.getString("location_id");
					
					System.out.println(dept_id + "\t" + dept_title + "\t" + location_id);
				}else {
					break;
				}
			}
			con.close();
		}catch (Exception e){
			System.out.println("데이터베이스 연결 실패");
			e.printStackTrace();
		}
	}
}

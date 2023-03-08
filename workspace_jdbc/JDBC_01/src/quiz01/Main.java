package quiz01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {
		//employee 테이블에서 사번, 사원명, 급여를 가져와 출력하는 코드를 작성하세요.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println("라이브러리 탐색 실패");
			System.exit(0);
		}

		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String dbID = "kh";
			String dbPW = "kh";
			Connection con = DriverManager.getConnection(url, dbID, dbPW);

			Statement stat = con.createStatement();
			String sql =
						"select "
						+ "emp_id,emp_name,salary"
						+ " from employee";

			ResultSet rs = stat.executeQuery(sql);

			System.out.println("사번\t사원명\t급여");
			while(rs.next()) {
				String emp_id = rs.getString("emp_id");
				String emp_name = rs.getString("emp_name");
				int salary = rs.getInt("salary");

				System.out.println(emp_id + "\t" + emp_name + "\t" + salary);
			}
			con.close();
		}catch(Exception e) {
			System.out.println("데이터베이스 연결 실패");
			e.printStackTrace(); // 에러 발생 시 에러 메시지 띄움.
		}

	}
}

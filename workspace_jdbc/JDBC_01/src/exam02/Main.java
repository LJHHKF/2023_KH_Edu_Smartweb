package exam02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//삭제하는 쿼리 작성
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println("라이브러리 탐색 실패");
			System.exit(0);
		}
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbID = "kh";
		String dbPW = "kh";
		
		
		try(Scanner sc = new Scanner(System.in)){
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			System.out.println("연결 성공");
			
			Statement stat = con.createStatement();
			
			System.out.print("삭제하려는 메뉴 이름을 입력 : ");
			String name = sc.nextLine();
			
			String sql
				= "delete from CAFE where NAME = '" + name +"'";
			int result = stat.executeUpdate(sql);
			if(result > 0) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
			
			con.commit();
			con.close();
			
		}catch (Exception e){
			System.out.println("데이터베이스 연결 실패");
			e.printStackTrace();
		}
	}
}

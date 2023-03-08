package exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//JDBC
		//Java DataBase Connectivity
		//Java 프로그램에서 DBMS를 사용하기 위한 라이브러리 및 문법.
		
		
		//커리큘럼 상 없는 부분.
		//. . . 으로 들어가서 특정 클래스를 실행해달라.
		//초기화 블럭. 일종의 등록 과정. '나 지금 데이터베이스에 접근할건데, 접근할 때 오라클 드라이버를 사용하겠다.'
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println("라이브러리를 찾을 수 없습니다.");
			System.exit(0);
		}
		
		//먼저 세션을 만들어야 함. 접속 정보를 만들어야 함.
		//이하 url 방식이 난이도는 가장 쉬운 대신에 성능은 좋지 못함.
		//xe = eXpress Edition
		String url = "jdbc:oracle:thin:@localhost:1521:xe";	
		String dbID = "kh";
		String dbPW = "kh";
		
		
		try (Scanner sc = new Scanner(System.in)) {
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			System.out.println("연결 성공");
			
			Statement stat = con.createStatement();
			
			System.out.print("신규 등록할 메뉴의 이름 : ");
			String name = sc.nextLine();
			int price = 0;
			while(true) {
				try {
					System.out.print(name + "메뉴의 가격 : ");
					price = Integer.parseInt(sc.nextLine());
					break;
				}catch(Exception e) {
					System.out.println("숫자 형식으로 입력해주셔야 합니다.");
					e.printStackTrace();
				}
			}
			String isIce = null;
			while(true) {
				System.out.print("ICE 가능 여부 ('Y','N') : ");
				isIce = sc.nextLine();
				if(isIce.equals("Y") || isIce.equals("N")) {
					break;
				}else {
					System.out.println("오류. Y또는 N만 가능합니다. 다시 입력해주세요.");
				}
			}
			
			
			String sql
				= "insert into cafe values(cafe_seq.nextval, '" + name + "',"+ price+",'" + isIce + "')";
			int result = stat.executeUpdate(sql);
			
			if(result > 0) {
				System.out.println("입력 성공");
			}else {
				System.out.println("입력 실패");
			}
			
			con.commit();
			con.close();
			
		}catch(Exception e) {
			System.out.println("데이터베이스 연결 실패");
			e.printStackTrace();
		}		
	}
}

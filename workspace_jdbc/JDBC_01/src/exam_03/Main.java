package exam_03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println("���̺귯�� Ž�� ����");
			System.exit(0);
		}
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbID = "kh";
		String dbPW = "kh";
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, dbID, dbPW);
			System.out.println("���� ����");
			
			Statement stat = con.createStatement();
			String sql
				= "update CAFE set PRICE = 3000 where NAME = 'Americano'";
			int result = stat.executeUpdate(sql);
			if(result > 0) {
				System.out.println("���� ����");
			}else {
				System.out.println("���� ����");
			}
			
			con.commit();
			con.close();
			
		}catch (Exception e){
			System.out.println("�����ͺ��̽� ���� ����");
			e.printStackTrace();
		}

	}
}

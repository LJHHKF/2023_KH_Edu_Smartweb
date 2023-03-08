package exam02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//�����ϴ� ���� �ۼ�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println("���̺귯�� Ž�� ����");
			System.exit(0);
		}
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbID = "kh";
		String dbPW = "kh";
		
		
		try(Scanner sc = new Scanner(System.in)){
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			System.out.println("���� ����");
			
			Statement stat = con.createStatement();
			
			System.out.print("�����Ϸ��� �޴� �̸��� �Է� : ");
			String name = sc.nextLine();
			
			String sql
				= "delete from CAFE where NAME = '" + name +"'";
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

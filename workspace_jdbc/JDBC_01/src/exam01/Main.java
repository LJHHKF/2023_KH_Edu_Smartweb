package exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//JDBC
		//Java DataBase Connectivity
		//Java ���α׷����� DBMS�� ����ϱ� ���� ���̺귯�� �� ����.
		
		
		//Ŀ��ŧ�� �� ���� �κ�.
		//. . . ���� ���� Ư�� Ŭ������ �����ش޶�.
		//�ʱ�ȭ ��. ������ ��� ����. '�� ���� �����ͺ��̽��� �����Ұǵ�, ������ �� ����Ŭ ����̹��� ����ϰڴ�.'
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println("���̺귯���� ã�� �� �����ϴ�.");
			System.exit(0);
		}
		
		//���� ������ ������ ��. ���� ������ ������ ��.
		//���� url ����� ���̵��� ���� ���� ��ſ� ������ ���� ����.
		//xe = eXpress Edition
		String url = "jdbc:oracle:thin:@localhost:1521:xe";	
		String dbID = "kh";
		String dbPW = "kh";
		
		
		try (Scanner sc = new Scanner(System.in)) {
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			System.out.println("���� ����");
			
			Statement stat = con.createStatement();
			
			System.out.print("�ű� ����� �޴��� �̸� : ");
			String name = sc.nextLine();
			int price = 0;
			while(true) {
				try {
					System.out.print(name + "�޴��� ���� : ");
					price = Integer.parseInt(sc.nextLine());
					break;
				}catch(Exception e) {
					System.out.println("���� �������� �Է����ּž� �մϴ�.");
					e.printStackTrace();
				}
			}
			String isIce = null;
			while(true) {
				System.out.print("ICE ���� ���� ('Y','N') : ");
				isIce = sc.nextLine();
				if(isIce.equals("Y") || isIce.equals("N")) {
					break;
				}else {
					System.out.println("����. Y�Ǵ� N�� �����մϴ�. �ٽ� �Է����ּ���.");
				}
			}
			
			
			String sql
				= "insert into cafe values(cafe_seq.nextval, '" + name + "',"+ price+",'" + isIce + "')";
			int result = stat.executeUpdate(sql);
			
			if(result > 0) {
				System.out.println("�Է� ����");
			}else {
				System.out.println("�Է� ����");
			}
			
			con.commit();
			con.close();
			
		}catch(Exception e) {
			System.out.println("�����ͺ��̽� ���� ����");
			e.printStackTrace();
		}		
	}
}

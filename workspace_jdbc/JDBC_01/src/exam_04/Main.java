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
			System.out.println("���̺귯�� Ž�� ����");
			System.exit(0);
		}
		
		Connection con = null;
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String dbID = "kh";
			String dbPW = "kh";
			con = DriverManager.getConnection(url, dbID, dbPW);
			System.out.println("���� ����");
			
			Statement stat = con.createStatement();
			String sql
				= "select * from department";
			
			ResultSet rs = stat.executeQuery(sql);
			
			while(true) {
				if(rs.next())
				{
					//�ݵ�� �̸��� ���ƾ� �� ������ ������, �������̸� �̸��� ������ ���� �����.
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
			System.out.println("�����ͺ��̽� ���� ����");
			e.printStackTrace();
		}
	}
}

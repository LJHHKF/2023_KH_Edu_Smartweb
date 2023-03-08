package quiz01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {
		//employee ���̺��� ���, �����, �޿��� ������ ����ϴ� �ڵ带 �ۼ��ϼ���.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println("���̺귯�� Ž�� ����");
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

			System.out.println("���\t�����\t�޿�");
			while(rs.next()) {
				String emp_id = rs.getString("emp_id");
				String emp_name = rs.getString("emp_name");
				int salary = rs.getInt("salary");

				System.out.println(emp_id + "\t" + emp_name + "\t" + salary);
			}
			con.close();
		}catch(Exception e) {
			System.out.println("�����ͺ��̽� ���� ����");
			e.printStackTrace(); // ���� �߻� �� ���� �޽��� ���.
		}

	}
}

package ����;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// 0. ����Ŭ �ű� ���� �����ϱ�
		// ������ : team
		// �н����� : team
		// ���� : resource, connect

		//1. ����Ŭ�� ���̺� �����ϱ�
		// ���̺� �� : team_contact
		// id ��Ű ���� 1001~����
		// name ���ڿ�(30) not null
		// age ���� not null
		// contact ���ڿ�(11) not null
		// birthday timestamp default sysdate not null

		// ������ �� : team_contact_seq

		// 2. TeamDTO ����

		// 3. TeamDAO ����
		// > DB ������ DBCP�� ����� ��
		// > �ڿ������� try~resource �� ����� ��

		// 4. InsertExam���� ���� ������ ������ �Է��ϴ� �ڵ� �ۼ��� ��
		// (�޴� ���ų� scanner�� �Է��� �ʿ� ����.)

		// 5. SelectExam ���� �Էµ� ��� ���� ������ ����� ��.

		final String url = "jdbc:oracle:thin:@localhost:1521:xe";
		final String dbId = "team";
		final String dbPw = "team";
		TeamDAO dao_team = null;
		InsertExam insert = new InsertExam();
		SelectExam select = new SelectExam();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dao_team = new TeamDAO(url, dbId, dbPw);
		}catch(Exception e) {
			System.out.println("Driver Ž�� �Ǵ� DAO ���� �� ����");
		}

		try(Scanner sc = new Scanner(System.in)) {
		while(true) {
			
				System.out.println("���� �޴� �� ���ϴ� ����� �������ּ���.");
				System.out.println("1. �� ��� ����ó �Է�");
				System.out.println("2. �� ����ó ���̺� ��ü ���");
				System.out.println("0. ���α׷� ����");
				int menu = 0;
				while(true) {
					try {
						menu = Integer.parseInt(sc.nextLine());
						break;
					}catch(Exception e) {
						e.printStackTrace();
						System.out.println("�Է� ���� ���� �����̾�� �մϴ�.");
					}
				}
				if(menu == 0) {
					System.out.println("���α׷��� �����մϴ�.");
					System.exit(0);
				}else if(menu == 1){
					System.out.println("����� �Է��մϴ�.");
					System.out.println("�Է��� ��� �� : " + insert.insertsContatacts(dao_team));
				}else if(menu == 2){
					System.out.println("����� ����մϴ�.");
					select.printSelectAll(dao_team);
				}else {
					System.out.println("�޴��� Ȯ���ϰ� �޴� ���� ���� ���� �Է����ּ���.");
				}
			}
		}catch(Exception e) {
			System.out.println("DB ��� �� ������ �����ϴ�.");
			e.printStackTrace();
			System.exit(0);
		}
	}
}

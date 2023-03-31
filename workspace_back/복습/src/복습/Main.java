package 복습;

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
			System.out.println("Driver 탐색 또는 dao 생성 중 오류가 났습니다.");
		}

		try(Scanner sc = new Scanner(System.in)) {
		while(true) {
			
				System.out.println("이하 메뉴를 확인하고 원하는 기능 메뉴 값을 선택해주세요.");
				System.out.println("1.멤버 등록");
				System.out.println("2.등록된 멤버 확인");
				System.out.println("0.프로그램 종료");
				int menu = 0;
				while(true) {
					try {
						menu = Integer.parseInt(sc.nextLine());
						break;
					}catch(Exception e) {
						e.printStackTrace();
						System.out.println("숫자 형식으로 입력해주셔야 합니다.");
					}
				}
				if(menu == 0) {
					System.out.println("프로그램 종료");
					System.exit(0);
				}else if(menu == 1){
					System.out.println("멤버를 입력합니다.");
					System.out.println("입력된 멤버 수 : " + insert.insertsContatacts(dao_team));
				}else if(menu == 2){
					System.out.println("멤버를 출력합니다.");
					select.printSelectAll(dao_team);
				}else {
					System.out.println("메뉴를 확인하고 다시 입력해주세요.");
				}
			}
		}catch(Exception e) {
			System.out.println("DB 사용 중 오류가 났습니다.");
			e.printStackTrace();
			System.exit(0);
		}
	}
}

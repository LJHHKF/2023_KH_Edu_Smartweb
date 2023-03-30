package 복습;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// 0. 오라클 신규 계정 생성하기
		// 계정명 : team
		// 패스워드 : team
		// 권한 : resource, connect

		//1. 오라클에 테이블 생성하기
		// 테이블 명 : team_contact
		// id 주키 숫자 1001~무한
		// name 문자열(30) not null
		// age 숫자 not null
		// contact 문자열(11) not null
		// birthday timestamp default sysdate not null

		// 시퀀스 명 : team_contact_seq

		// 2. TeamDTO 생성

		// 3. TeamDAO 생성
		// > DB 연결은 DBCP를 사용할 것
		// > 자원관리는 try~resource 를 사용할 것

		// 4. InsertExam에서 팀원 전원의 정보를 입력하는 코드 작성할 것
		// (메뉴 띄우거나 scanner로 입력할 필요 없음.)

		// 5. SelectExam 에서 입력된 모든 팀원 정보를 출력할 것.

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
			System.out.println("Driver 탐색 또는 DAO 생성 중 오류");
		}

		try(Scanner sc = new Scanner(System.in)) {
		while(true) {
			
				System.out.println("이하 메뉴 중 원하는 기능을 선택해주세요.");
				System.out.println("1. 팀 멤버 연락처 입력");
				System.out.println("2. 팀 연락처 테이블 전체 출력");
				System.out.println("0. 프로그램 종료");
				int menu = 0;
				while(true) {
					try {
						menu = Integer.parseInt(sc.nextLine());
						break;
					}catch(Exception e) {
						e.printStackTrace();
						System.out.println("입력 값은 숫자 형식이어야 합니다.");
					}
				}
				if(menu == 0) {
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
				}else if(menu == 1){
					System.out.println("멤버를 입력합니다.");
					System.out.println("입력한 멤버 수 : " + insert.insertsContatacts(dao_team));
				}else if(menu == 2){
					System.out.println("멤버를 출력합니다.");
					select.printSelectAll(dao_team);
				}else {
					System.out.println("메뉴를 확인하고 메뉴 범위 내의 값을 입력해주세요.");
				}
			}
		}catch(Exception e) {
			System.out.println("DB 사용 중 오류가 났습니다.");
			e.printStackTrace();
			System.exit(0);
		}
	}
}

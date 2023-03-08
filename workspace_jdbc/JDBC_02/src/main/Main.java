package main;

import java.util.ArrayList;
import java.util.Scanner;

import dao.MoviesDAO;
import dto.MoviesDTO;

public class Main {
	public static void main(String[] args) {
		//DB CRUD 프로그램 작성
		//CRUD에 사용할 데이터 소재는... 예전에 해봤던 Netflix로. 영화 관리 프로그램.

		//테이블 설게
		//테이블 이름 : movies
		//id - 1001 ~ 무한까지 1씩 증가
		//title - 문자열 최대 50 바이트까지
		//genre - 문자열 최대 20바이트
		//모두 not null
		//SQL Developer 에서 kh 계정에 제작

		//완성된 다음의 코드를 MVC 패턴으로 Refactoring 한다.

		//아래 부분은 DAO에서 다시금 제작해줘서 주석화.
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		}catch(Exception e) {
//			System.out.println("라이브러리 탐색 실패");
//			System.exit(0);
//		}
		
		Scanner sc = new Scanner(System.in);
		final String url = "jdbc:oracle:thin:@localhost:1521:xe";
		final String dbID = "kh";
		final String dbPW = "kh";

		Main self = new Main();
		MoviesDAO dao = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dao = new MoviesDAO(url, dbID, dbPW);
		}catch(Exception e) {
			System.out.println("라이브러리 탐색 또는 DAO 생성 실패");
			e.printStackTrace();
			System.exit(0);
		}

		try {
			//기능 하나 끝날 때 마다 새로 접속을 열고 닫는 것을 권장함
			//프로그램을 켜놓고서 밥먹으러 가는 등.. 쓰지도 않는데 점유 되고 있을 때 있음.
			while(true) {
				int menu = 0;
				while(true)
				{
					try {
						System.out.println("<< Netflix 영화 관리 프로그램 >> ");
						System.out.println("1. 신규 영화 등록"); //C
						System.out.println("2. 영화 목록 출력"); //R
						System.out.println("3. 영화 정보 수정"); //U -- ID를 기준으로 모든 정보 수정
						System.out.println("4. 영화 정보 삭제"); //D -- ID를 기준으로 삭제
						System.out.println("5. 영화 정보 검색"); //--이름으로
						System.out.println("0. 프로그램 종료");
						System.out.print(">> ");
						menu = Integer.parseInt(sc.nextLine());
						break;
					}catch(Exception e) {
						System.out.println("메뉴 번호는 숫자 형식입니다. 숫자만 입력해주세요.");
						e.printStackTrace();
					}
				}

				if(menu == 0) {
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
				}else if(menu == 1) { // C
					String title = self.inputText(sc, "신규 등록할 영화의 이름 : ");
					String genre = self.inputText(sc, title + " 영화의 장르 : ");
					MoviesDTO dto = new MoviesDTO(0, title, genre);
					int result = dao.insert(dto);
					if(result > 0) {
						System.out.println("입력 성공");
					}else {
						System.out.println("입력 실패");
					}
				}else if(menu == 2) { // R
					self.printMovies(dao.selectAll());
				}else if(menu == 3) { // U -- ID를 통해
					//self.printMovies(dao.selectAll());
					//System.out.println();
					System.out.println("수정하고 싶은 영화의 ID를 입력해주세요. ");
					int id = self.inputID(sc);
					if(!dao.isIdExist(id)) {
						System.out.println("해당 id의 영화가 없습니다. 재시작합니다.");
						continue;
					}
					String title = self.inputText(sc, "덮어 쓸 영화의 이름 : ");
					String genre = self.inputText(sc, title + " 영화의 장르 : ");
					MoviesDTO dto = new MoviesDTO(id,title,genre);
					int result = dao.updateToID(dto);
					if(result > 0) {
						System.out.println("수정 성공");
					}else {
						System.out.println("수정 실패. 대상 영화를 찾지 못하였습니다.");
					}
				}else if(menu == 4) { // D -- ID를 통해
					//self.printMovies(dao.selectAll());
					//System.out.println();
					System.out.println("삭제하고 싶은 영화의 ID를 입력해주세요. ");
					int id = self.inputID(sc);
					if(!dao.isIdExist(id)) {
						System.out.println("해당 id의 영화가 없습니다. 재시작합니다.");
						continue;
					}
					int result = dao.deleteToID(id);
					if(result > 0) {
						System.out.println("삭제 성공");
					}else {
						System.out.println("삭제 실패");
					}
				}else if(menu == 5) {
					String title = self.inputText(sc, "검색 할 영화의 제목 키워드 : ");
					self.printMovies(dao.searchByTitle(title));
				}else {
					System.out.println("없는 메뉴 번호입니다. 재시작합니다.");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private int inputID(Scanner sc) {
		int id = 0;
		while(true) {
			try {
				System.out.print(">>");
				id = Integer.parseInt(sc.nextLine());
				break;
			}catch(Exception e) {
				System.out.println("id는 숫자 형식을 입력해주셔야 합니다.");
				e.printStackTrace();
			}
		}
		return id;
	}
	
	private String inputText(Scanner sc, String infoText) {
		System.out.print(infoText);
		return sc.nextLine();
	}
	
	private void printMovies(ArrayList<MoviesDTO> list){
		if(list.isEmpty()){
			System.out.println("출력할 정보가 없습니다.");
		}else{
			System.out.println("ID\tName\tGenre");
			for(MoviesDTO dto : list) {
				System.out.println(dto.getId() + "\t" + dto.getTitle() + "\t" + dto.getGenre());
			}
		}
	}
}

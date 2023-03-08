package exam_01;

import java.util.ArrayList;
import java.util.Scanner;

public class Practice_02 {
	public static void main(String[] args) {

		//MVC 패턴
		//Model view control
		//Model : 

		Scanner sc = new Scanner(System.in);
		Manager manager = new Manager();

		while(true) {
			System.out.println("<< Netflix Simulator >>");
			System.out.println("1. 신규 영화 등록");
			System.out.println("2. 영화 목록 출력");
			System.out.println("3. 영화 검색 (제목으로 검색)");
			System.out.println("4. 영화 수정 (제목으로 검색)");
			System.out.println("5. 영화 삭제 (제목으로 검색)");
			System.out.println("0. 종료");
			System.out.print(">>");
			int menu = -1;
			try {
				menu = Integer.parseInt(sc.nextLine());
			}catch(Exception e) {
				e.printStackTrace();
				continue;
			}

			if(menu == 0) {
				System.out.println("Netflix를 종료합니다.");
				System.exit(0);
			}else if(menu == 1) { // 신규 영화 등록
				//manager.addMovie(createMovie(sc, "신규"));

				manager.addMovie(new Movie("Avatar2", "SF", 8.0));
				manager.addMovie(new Movie("범죄도시2", "액션", 9.0));
				manager.addMovie(new Movie("나홀로집에", "코미디", 8.5));;

			}else if(menu == 2) { // 영화 목록 출력
				printMovieList(manager.getMovies());
			}else if(menu == 3) {
				String input = inputSearch(sc);
				ArrayList<Movie> m_list = manager.searchMultiContainsToTitle(input);
				if(m_list.size() > 0) {
					printMovieList(manager.searchMultiContainsToTitle(input));
				}else {
					System.out.println("찾은 영화가 없습니다.");
				}
			}else if(menu == 4) {
				String input = inputSearch(sc);
				int index = manager.getIndexByTitle(input);
				if(index > -1) {
					manager.updateMovie(index, createMovie(sc, "수정"));
				}else {
					System.out.println("수정하려는 영화를 찾지 못하였습니다.");
				}
			}else if(menu == 5) {
				String input = inputSearch(sc);
				//못 찾으면 -1 반환.
				int index = manager.getIndexByTitle(input);
				if(index > -1) {
					manager.deleteMovie(index);
					System.out.println("영화를 삭제했습니다.");
				}else {
					System.out.println("삭제하려는 영화를 찾지 못하였습니다.");
				}

			}else {
				System.out.println("메뉴 번호를 다시 확인해주세요.");
			}
		}
	}

	private static Movie createMovie(Scanner sc, String prefix) {
		String title = null;
		while(true) {
			try {
				System.out.print(prefix + " 영화 제목 : ");
				title = sc.nextLine();
				break;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		//영화 장르
		String genre = null;
		while(true) {
			try {
				System.out.print(prefix + " 영화 장르 : ");
				genre = sc.nextLine();
				break;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		//평점
		double grade = 0.0;
		while(true) {
			try {
				System.out.print(prefix + " 영화 평점 : ");
				grade = Double.parseDouble(sc.nextLine());
				break;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		return new Movie(title, genre, grade);
	}

	private static void printMovieList(ArrayList<Movie> movies) {
		System.out.println("제목\t장르\t평점");
		for(Movie m : movies) {
			System.out.println(m.getTitle() + "\t" +
					m.getGenre() + "\t" +
					m.getGrade());
		}
	}

	private static String inputSearch(Scanner sc) {
		while(true) {
			try {
				System.out.print("검색을 원하는 영화의 제목을 입력해주세요. : ");
				String input = sc.nextLine();
				return input;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}

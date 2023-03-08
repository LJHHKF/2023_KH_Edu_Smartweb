package exam_01;

import java.util.Scanner;

public class Exam_01_Practice {
	public static void main(String[] args) {

		//MVC 패턴
		//Model view control
		
		Scanner sc = new Scanner(System.in);

		Movie[] movies = new Movie[5];
		int curIndex = 0;

		while(true) {
			System.out.println("<< Netflix Simulator >>");
			System.out.println("1. 신규 영화 등록(최대 "+movies.length+"개)");
			System.out.println("2. 영화 목록 출력");
			System.out.println("3. 영화 삭제(이름)");
			System.out.println("4. 영화 목록 수정(이름)");
			System.out.println("5. 영화 검색(이름)");
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
				if(curIndex < movies.length){
					movies[curIndex++] = createMovie(sc);
				}else {
					System.out.println("신규 영화를 등록할 자리가 없습니다.");
				}
			}else if(menu == 2) { // 영화 목록 출력
				printMovieList(movies, curIndex);
			}else if(menu == 3) {
				//System.out.println("이하 목록을 보시고서 삭제를 원하는 영화 Index를 쓰시오.");
				//printMovieList(movies, curIndex);
				//int choice = inputChoice(sc, curIndex);

				System.out.println("삭제를 원하는 영화 제목을 정확히 쓰시오");
				System.out.print(">>");
				int choice = searchIndexToTitle(movies, curIndex, sc.nextLine());

				if(choice != -1) {
					deleteMovie(movies, curIndex, choice);
					curIndex--;
				}else {
					System.out.println("찾는 영화가 없습니다.");
				}
			}else if(menu == 4){
				//System.out.println("이하 목록을 보시고서 수정을 원하는 영화 Index를 쓰시오");
				//printMovieList(movies, curIndex);
				//int choice = inputChoice(sc, curIndex);

				System.out.println("수정을 원하는 영화 제목을 정확히 쓰시오");
				System.out.print(">>");
				int choice = searchIndexToTitle(movies, curIndex, sc.nextLine());
				if(choice != -1) {
					updateMovie(sc,movies[choice]);
				}else {
					System.out.println("찾는 영화가 없습니다.");
				}
			}else if(menu == 5){
				System.out.println("찾는 영화 제목을 정확히 입력해 주세요.");
				//				String search = null;
				//				while(true) {
				//					try {
				//						System.out.print(">>");
				//						search = sc.nextLine();
				//						break;
				//					}catch(Exception e) {
				//						e.printStackTrace();
				//					}
				//				}
				//searchMovie(movies, curIndex, search);
				int i = searchIndexToTitle(movies, curIndex, sc.nextLine());
				if(i != -1) {
					System.out.println("찾았습니다.");
					System.out.println("Index\t제목\t장르\t평점");
					System.out.println(i+1 + "\t" +
							movies[i].getTitle() + "\t" +
							movies[i].getGenre() + "\t" +
							movies[i].getGrade());
				}else {
					System.out.println("찾는 영화가 없습니다.");
				}
			}else {
				System.out.println("메뉴 번호를 다시 확인해주세요.");
			}
		}
	}

	private static Movie createMovie(Scanner sc) {
		String title = null;
		while(true) {
			try {
				System.out.print("신규 영화 제목 : ");
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
				System.out.print("신규 영화 장르 : ");
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
				System.out.print("신규 영화 평점 : ");
				grade = Double.parseDouble(sc.nextLine());
				break;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		return new Movie(title, genre, grade);
	}

	private static void updateMovie(Scanner sc, Movie target) {
		while(true) {
			try {
				System.out.print("신규 영화 제목 : ");
				target.setTitle(sc.nextLine());
				break;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		while(true) {
			try {
				System.out.print("신규 영화 장르 : ");
				target.setGenre(sc.nextLine());
				break;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		while(true) {
			try {
				System.out.print("신규 영화 평점 : ");
				target.setGrade(Double.parseDouble(sc.nextLine()));
				break;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void deleteMovie(Movie[] movies, int curIndex, int choice) {
		if(choice == curIndex-1) {
			movies[choice] = null;
		}else {
			for(int i = choice; i < curIndex-1; i++) {
				movies[i] = movies[i+1];
			}
		}
	}

	private static void searchMovie(Movie[] movies, int curIndex, String search) {
		boolean isFind = false;
		for(int i = 0; i < curIndex; i++) {
			if(movies[i].getTitle().equals(search)) {
				System.out.println("찾았습니다.");
				System.out.println("Index\t제목\t장르\t평점");
				System.out.println(i+1 + "\t" +
						movies[i].getTitle() + "\t" +
						movies[i].getGenre() + "\t" +
						movies[i].getGrade());
				isFind = true;
				break;
			}
		}
		if(!isFind) {
			System.out.println("찾지 못했습니다.");
		}
	}

	private static void printMovieList(Movie[] movies, int curIndex) {
		System.out.println("Index\t제목\t장르\t평점");
		//for(int i = 0 ; movies[i] != null; i++)
		for(int i = 0; i < curIndex; i++) {
			System.out.println(i+1 + "\t" +
					movies[i].getTitle() + "\t" +
					movies[i].getGenre() + "\t" +
					movies[i].getGrade());
		}
	}

	private static int inputChoice(Scanner sc, int max) {
		int choice = 0;
		while(true) {
			try {
				System.out.print(">>");
				choice = Integer.parseInt(sc.nextLine());
				choice--; // 1~5 식으로 보여주고, 0~4 범위로 맞춰주기.
				if(choice < 0 || choice >= max) {
					System.out.println("값 범위에서 벗어났습니다.");
					continue;
				}
				break;
			}catch(Exception e) {
				System.out.println("오류입니다. 숫자를 입력해주세요");
				e.printStackTrace();
			}
		}
		return choice;
	}

	private static int searchIndexToTitle(Movie[] movies, int curIndex, String title) {
		for(int i = 0; i < curIndex; i++) {
			if(movies[i].getTitle().equals(title)){
				return i;
			}
		}
		return -1;
	}
}

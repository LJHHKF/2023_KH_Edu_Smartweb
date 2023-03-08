package exam_02;

import java.util.ArrayList;
import java.util.Scanner;

import exam_01.Movie;

public class Exam_02 {
	public static void main(String[] args) {
		//CafeMenu 클래스 설계
		// 상품번호(숫자), 상품명(문자열), 가격(숫자)
		// getter / setter / constructor / 정보은닉

		//1001 / Americano / 2500
		//1002 / CafeLatte / 3000

		Scanner sc = new Scanner(System.in);
		Manager manager = new Manager();

		manager.addMenu(new CafeMenu(1001, "Americano", 2500));
		manager.addMenu(new CafeMenu(1002, "CafeLatte", 3000));

		while(true) {
			System.out.println("카페 메뉴 시뮬레이터");
			System.out.println("1. 메뉴 추가");
			System.out.println("2. 메뉴 조회");
			System.out.println("3. 메뉴 검색(메뉴명)");
			System.out.println("4. 메뉴 수정(ID)");
			System.out.println("5. 메뉴 삭제(ID)");
			System.out.println("0. 시스템 종료");
			int menu = -1;
			while(true) {
				try {
					System.out.print(">>");
					menu = Integer.parseInt(sc.nextLine());
					break;
				}catch(Exception e) {
					e.printStackTrace();
					System.out.print("형식에 맞춰 다시 입력해주세요.");
					System.out.println(" 숫자 형식이어야 합니다.");
				}
			}

			if(menu == 0) {
				System.out.println("시스템을 종료합니다.");
				System.exit(0);
			}else if(menu == 1) {
				manager.addMenu(createMenu(sc, "추가할"));
			}else if(menu == 2) {
				printMovieList(manager.getMenus());
			}else if(menu == 3) {
				String input = inputSearch(sc, "메뉴명");
				ArrayList<CafeMenu> temp = manager.searchMultiContainsToName(input);
				if(temp.size() > 0) {
					printMovieList(temp);
				}else {
					System.out.println("찾은 메뉴가 없습니다.");
				}
			}else if(menu == 4) {
				int input = inputSearchInt(sc, "ID");
				int index = manager.getIndexByID(input);
				if(index > -1) {
					manager.updateMenu(index, createMenu(sc, "수정할"));
				}else {
					System.out.println("수정하려는 메뉴 ID를 찾지 못했습니다.");
				}
			}else if(menu == 5) {
				int input = inputSearchInt(sc, "ID");
				int index = manager.getIndexByID(input);
				if(index > -1) {
					manager.deleteMenu(index);
					System.out.println("메뉴를 삭제했습니다.");
				}else {
					System.out.println("삭제하려는 메뉴 ID를 찾지 못했습니다.");
				}
				
			}else {
				System.out.println("메뉴를 확인하고 다시 입력해주세요.");
			}
		}
	}

	private static CafeMenu createMenu(Scanner sc, String prefix) {
		int id = 0;
		while(true) {
			try {
				System.out.print(prefix + " 메뉴 ID을 입력해주세요 : ");
				id = Integer.parseInt(sc.nextLine());
				break;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		String name = null;
		while(true) {
			try {
				System.out.print(prefix + " 메뉴 이름을 입력해주세요 : ");
				name = sc.nextLine();
				break;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		int price = 0;
		while(true) {
			try {
				System.out.print(prefix + " 메뉴 가격을 입력해주세요 : ");
				price = Integer.parseInt(sc.nextLine());
				break;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		return new CafeMenu(id, name, price);
	}

	private static void printMovieList(ArrayList<CafeMenu> menus) {
		System.out.println("ID\t메뉴명\t가격");
		for(CafeMenu m : menus) {
			System.out.println(m.getId() + "\t" +
								m.getName() + "\t" +
								m.getPrice());
		}
	}

	private static String inputSearch(Scanner sc, String target) {
		while(true) {
			try {
				System.out.print("검색을 원하는 "+ target +"을/를 입력해주세요. : ");
				String input = sc.nextLine();
				return input;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private static int inputSearchInt(Scanner sc, String target) {
		int input = 0;
		while(true) {
			try {
				input = Integer.parseInt(inputSearch(sc, target));
				break;
			}catch(Exception e) {
				System.out.println("ID에 맞게 숫자 형식을 입력해주셔야 합니다.");
				e.printStackTrace();
			}
		}
		return input;
	}


}


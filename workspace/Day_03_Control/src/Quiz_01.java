import java.util.Scanner;

public class Quiz_01 {
	public static void main(String[] args) {
		System.out.println("오늘 점심메뉴를 선택하세요.");
		System.out.println("1. 햄버거  2. 샌드위치  3. 분식");
		
		// 선택한 메뉴에 따라 가까운 가게 이름을 출력하는 프로그램 작성
		// 1,2,3 이 아닌 다른 메뉴 선택 시 안내 메시지
		
		Scanner sc = new Scanner(System.in);
		System.out.print(">>");
		int menu = Integer.parseInt(sc.nextLine());
		
		if(menu == 1)
		{
			System.out.println("버거킹");
		}
		else if(menu == 2)
		{
			System.out.println("서브웨이");
		}
		else if(menu == 3)
		{
			System.out.println("김밥천국");
		}
		else
		{
			System.out.println("없는 메뉴입니다");
		}
	}
}

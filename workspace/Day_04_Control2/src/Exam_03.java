import java.util.Scanner;

public class Exam_03 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("좋아하는 OTT는?");
		System.out.println("1. Netflix  2.Disney  3.Watcha");
		System.out.print(">>");
		int select = Integer.parseInt(sc.nextLine());

		switch(select){
		case 1: // if(select == 1)
				// == 외의 비교가 불가능.
			System.out.println("Netflix 는 글로리가 인기입니다.");
			break; //구분하는 기능은 있는데 나가는 기능이 없어서, 나가는 기능 구현 용도.
					//일부러 구현 안하고 아래로 내려 보낼 수도 있음.
		case 2:
			System.out.println("Disney 는 카지노가 인기입니다.");
			break;
		case 3:
			System.out.println("Watcha 는 모르겠습니다.");
			break;
		default:
			System.out.println("메뉴를 다시 확인해주세요.");
			break;
		}
	}
}

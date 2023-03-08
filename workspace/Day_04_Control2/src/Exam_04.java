import java.util.Scanner;

public class Exam_04 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("메시지를 입력하세요 : ");
		String msg = sc.nextLine();
		
		//msg == "Apple"는 작동하지 않음.
		if(msg.equals("Apple")) {
			System.out.println("사과 입니다.");
		}else if(msg.equals("Strawberry")) {
			System.out.println("딸기 입니다.");
		}else {
			System.out.println("메시지를 확인할 수 없습니다.");
		}
	}
}

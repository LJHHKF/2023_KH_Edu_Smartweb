import java.util.Scanner;

public class Exam_08 {
	public static void main(String[] args) {
		
		//숫자 받아서 인식시키기
		Scanner sc = new Scanner(System.in);
		
		System.out.print("숫자 입력 : ");
		String str = sc.nextLine();
		
		int num = Integer.parseInt(str);
		
		System.out.println(num+ 20);
	}
}

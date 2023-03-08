import java.util.Scanner;

public class Exam_07 {
	public static void main(String[] args) {
		//문장을 입력받는 기능
		Scanner sc = new Scanner(System.in);
		
		//System.in.read(); // 아직 정해지지 않은 숫자 하나.
							//그러나 int형이란 것은 확실.
		
		System.out.println("메시지를 남겨주세요.");
		
		//사용자가 입력하게 될 미래의 문자열 하나
		String str = sc.nextLine();
		System.out.println("<< 당신이 남긴 메시지는 >>");
		System.out.println(str);
	}
}

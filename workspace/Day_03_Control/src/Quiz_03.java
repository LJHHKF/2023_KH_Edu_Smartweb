import java.util.Scanner;

public class Quiz_03 {
	public static void main(String[] args) {
		//1~100사이를 입력 받아 짝/홀수를 판단하세요.
		//1~100사이의 수가 아닐 경우 예외 처리 후 종료.
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("1~100 사이의 수 입력 : ");
		int num = Integer.parseInt(sc.nextLine());
		
		System.out.println("=== 결과 === ");
		
		if(num > 0 && num < 101)
		{
			if(num % 2 == 0)
			{
				System.out.println("입력한 수는 짝수입니다.");
			}
			else
			{
				System.out.println("입력한 수는 홀수입니다.");
			}
		}
		else
		{
			System.out.println("입력 범위를 벗어났습니다.");
		}
	}
}

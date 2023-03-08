import java.util.Scanner;

public class Quiz_08 {
	public static void main(String[] args) {
		//사용자가 선택한 구구단을 출력
		//변수를 적절히 사용하여 두 수의 곱을 출력.

		Scanner sc = new Scanner(System.in);

		while(true)
		{
			System.out.print("2~9단 중 선택 (0 -> 종료) : ");
			int n_input = Integer.parseInt(sc.nextLine());
			
			if(n_input == 0)
			{
				System.out.println("구구단을 종료합니다.");
				System.exit(0); // 프로그램 종료 코드
			}
			
			if(n_input > 1 && n_input < 10)
			{
				int i = 0;
				while(i < 9)
				{
					i++;
					System.out.println(n_input + " * " + i + " = " + (n_input * i));
				}
			}
			else
			{
				System.out.println("입력한 값이 구구단 범위를 벗어났습니다.");
			}
		}
	}
}

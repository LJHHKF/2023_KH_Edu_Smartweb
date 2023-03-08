import java.util.Scanner;

public class Quiz_09 {
	public static void main(String[] args) {
		//1~n까지의 합 구하기
		//합계를 누적시키며 더한다.


		Scanner sc = new Scanner(System.in);
		System.out.print("1을 넘는 숫자 n을 입력해주세요. : ");
		int n_input = Integer.parseInt(sc.nextLine());
		
		if(n_input >= 1)
		{
			int i = 0;
			int sum = 0;
			while(i < n_input)
			{
				i++;
				sum += i;
			}
			System.out.println("1부터 n까지의 모든 수의 합은 " + sum + " 입니다.");
		}
		else
		{
			System.out.println("값은 1 이상이어야 합니다.");
		}
		
	}
}

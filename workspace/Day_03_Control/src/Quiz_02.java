import java.util.Scanner;

public class Quiz_02
{
	public static void main(String[] args) 
	{
		//두 숫자를 입력받아 대소를 비교하세요.
		//같은 경우도 처리.
		//첫번째 입력한 값이 더 크다, 두번째 입력한 값이 더 크다.
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("첫번째 숫자 입력 : ");
		int n1 = Integer.parseInt(sc.nextLine());
		System.out.print("두번째 숫자 입력 : ");
		int n2 = Integer.parseInt(sc.nextLine());
		
		//System.out.println(n1 + " : " + n2);
		
		System.out.println();
		System.out.println("==== 결과 ====");
		System.out.println();
		
		if(n1 > n2)
		{
			System.out.println("첫번째 입력한 값이 더 큽니다.");
		}
		else if(n1 < n2)
		{
			System.out.println("두번째 입력한 값이 더 큽니다.");
		}
		else
		{
			System.out.println("두 값이 동일합니다.");
		}
	}
}

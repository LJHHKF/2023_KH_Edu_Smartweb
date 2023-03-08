import java.util.Scanner;

public class Quiz_07 {
	public static void main(String[] args) {
		//Hello Java를 몇번 출력할까요?
		//입력값만큼 반복.
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Hello Java를 몇번 출력할까요? : ");
		int n_input = Integer.parseInt(sc.nextLine());
		int i = 0;
		
		while(i < n_input)
		{
			i++;
			System.out.println("Hello Java");
		}
		
	}
}

import java.util.Scanner;

public class Quiz_07 {
	public static void main(String[] args) {
		//Hello Java�� ��� ����ұ��?
		//�Է°���ŭ �ݺ�.
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Hello Java�� ��� ����ұ��? : ");
		int n_input = Integer.parseInt(sc.nextLine());
		int i = 0;
		
		while(i < n_input)
		{
			i++;
			System.out.println("Hello Java");
		}
		
	}
}

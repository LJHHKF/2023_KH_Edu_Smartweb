import java.util.Scanner;

public class Quiz_03 {
	public static void main(String[] args) {
		//1~100���̸� �Է� �޾� ¦/Ȧ���� �Ǵ��ϼ���.
		//1~100������ ���� �ƴ� ��� ���� ó�� �� ����.
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("1~100 ������ �� �Է� : ");
		int num = Integer.parseInt(sc.nextLine());
		
		System.out.println("=== ��� === ");
		
		if(num > 0 && num < 101)
		{
			if(num % 2 == 0)
			{
				System.out.println("�Է��� ���� ¦���Դϴ�.");
			}
			else
			{
				System.out.println("�Է��� ���� Ȧ���Դϴ�.");
			}
		}
		else
		{
			System.out.println("�Է� ������ ������ϴ�.");
		}
	}
}

import java.util.Scanner;

public class Quiz_08 {
	public static void main(String[] args) {
		//����ڰ� ������ �������� ���
		//������ ������ ����Ͽ� �� ���� ���� ���.

		Scanner sc = new Scanner(System.in);

		while(true)
		{
			System.out.print("2~9�� �� ���� (0 -> ����) : ");
			int n_input = Integer.parseInt(sc.nextLine());
			
			if(n_input == 0)
			{
				System.out.println("�������� �����մϴ�.");
				System.exit(0); // ���α׷� ���� �ڵ�
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
				System.out.println("�Է��� ���� ������ ������ ������ϴ�.");
			}
		}
	}
}

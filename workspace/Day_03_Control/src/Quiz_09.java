import java.util.Scanner;

public class Quiz_09 {
	public static void main(String[] args) {
		//1~n������ �� ���ϱ�
		//�հ踦 ������Ű�� ���Ѵ�.


		Scanner sc = new Scanner(System.in);
		System.out.print("1�� �Ѵ� ���� n�� �Է����ּ���. : ");
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
			System.out.println("1���� n������ ��� ���� ���� " + sum + " �Դϴ�.");
		}
		else
		{
			System.out.println("���� 1 �̻��̾�� �մϴ�.");
		}
		
	}
}

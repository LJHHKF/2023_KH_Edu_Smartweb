import java.util.Scanner;

public class Quiz_02
{
	public static void main(String[] args) 
	{
		//�� ���ڸ� �Է¹޾� ��Ҹ� ���ϼ���.
		//���� ��쵵 ó��.
		//ù��° �Է��� ���� �� ũ��, �ι�° �Է��� ���� �� ũ��.
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("ù��° ���� �Է� : ");
		int n1 = Integer.parseInt(sc.nextLine());
		System.out.print("�ι�° ���� �Է� : ");
		int n2 = Integer.parseInt(sc.nextLine());
		
		//System.out.println(n1 + " : " + n2);
		
		System.out.println();
		System.out.println("==== ��� ====");
		System.out.println();
		
		if(n1 > n2)
		{
			System.out.println("ù��° �Է��� ���� �� Ů�ϴ�.");
		}
		else if(n1 < n2)
		{
			System.out.println("�ι�° �Է��� ���� �� Ů�ϴ�.");
		}
		else
		{
			System.out.println("�� ���� �����մϴ�.");
		}
	}
}

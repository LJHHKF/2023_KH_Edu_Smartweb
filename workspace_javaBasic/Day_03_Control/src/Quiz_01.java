import java.util.Scanner;

public class Quiz_01 {
	public static void main(String[] args) {
		System.out.println("���� ���ɸ޴��� �����ϼ���.");
		System.out.println("1. �ܹ���  2. ������ġ  3. �н�");
		
		// ������ �޴��� ���� ����� ���� �̸��� ����ϴ� ���α׷� �ۼ�
		// 1,2,3 �� �ƴ� �ٸ� �޴� ���� �� �ȳ� �޽���
		
		Scanner sc = new Scanner(System.in);
		System.out.print(">>");
		int menu = Integer.parseInt(sc.nextLine());
		
		if(menu == 1)
		{
			System.out.println("����ŷ");
		}
		else if(menu == 2)
		{
			System.out.println("�������");
		}
		else if(menu == 3)
		{
			System.out.println("���õ��");
		}
		else
		{
			System.out.println("���� �޴��Դϴ�");
		}
	}
}

import java.util.Scanner;

public class Quiz_04 {
	public static void main(String[] args) {
		//�� ������ �Է¹޾� ������ ����� ����ϴ� ���α׷��� �ۼ����ּ���.
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("ù��° ������ �Է��ϼ���. : ");
		int num1 = Integer.parseInt(sc.nextLine());
		
		System.out.print("�ι�° ���ڸ� �Է��ϼ���. : ");
		int num2 = Integer.parseInt(sc.nextLine());
		
		System.out.println("�� ������ ���� " + (num1 + num2) + " �Դϴ�.");
	}
}

import java.util.Scanner;

public class Exam_04 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("�޽����� �Է��ϼ��� : ");
		String msg = sc.nextLine();
		
		//msg == "Apple"�� �۵����� ����.
		if(msg.equals("Apple")) {
			System.out.println("��� �Դϴ�.");
		}else if(msg.equals("Strawberry")) {
			System.out.println("���� �Դϴ�.");
		}else {
			System.out.println("�޽����� Ȯ���� �� �����ϴ�.");
		}
	}
}

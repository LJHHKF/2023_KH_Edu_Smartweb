import java.util.Scanner;

public class Exam_07 {
	public static void main(String[] args) {
		//������ �Է¹޴� ���
		Scanner sc = new Scanner(System.in);
		
		//System.in.read(); // ���� �������� ���� ���� �ϳ�.
							//�׷��� int���̶� ���� Ȯ��.
		
		System.out.println("�޽����� �����ּ���.");
		
		//����ڰ� �Է��ϰ� �� �̷��� ���ڿ� �ϳ�
		String str = sc.nextLine();
		System.out.println("<< ����� ���� �޽����� >>");
		System.out.println(str);
	}
}

import java.util.Scanner;

public class Exam_08 {
	public static void main(String[] args) {
		
		//���� �޾Ƽ� �νĽ�Ű��
		Scanner sc = new Scanner(System.in);
		
		System.out.print("���� �Է� : ");
		String str = sc.nextLine();
		
		int num = Integer.parseInt(str);
		
		System.out.println(num+ 20);
	}
}

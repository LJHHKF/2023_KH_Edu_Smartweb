import java.util.Scanner;

public class Exam_03 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("�����ϴ� OTT��?");
		System.out.println("1. Netflix  2.Disney  3.Watcha");
		System.out.print(">>");
		int select = Integer.parseInt(sc.nextLine());

		switch(select){
		case 1: // if(select == 1)
				// == ���� �񱳰� �Ұ���.
			System.out.println("Netflix �� �۷θ��� �α��Դϴ�.");
			break; //�����ϴ� ����� �ִµ� ������ ����� ���, ������ ��� ���� �뵵.
					//�Ϻη� ���� ���ϰ� �Ʒ��� ���� ���� ���� ����.
		case 2:
			System.out.println("Disney �� ī���밡 �α��Դϴ�.");
			break;
		case 3:
			System.out.println("Watcha �� �𸣰ڽ��ϴ�.");
			break;
		default:
			System.out.println("�޴��� �ٽ� Ȯ�����ּ���.");
			break;
		}
	}
}

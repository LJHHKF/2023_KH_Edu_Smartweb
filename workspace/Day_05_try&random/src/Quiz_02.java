import java.util.Scanner;

public class Quiz_02 {
	public static void main(String[] args) {
		//�������� if���� ȥ���� ����.
		//���� �� �� ���߱�
		//1 �Ǵ� 2�� ���� ���� ������ �� ����ڰ� �Է��� ���� ��

		Scanner sc = new Scanner(System.in);

		while(true) {
			System.out.println("===���� �� �� ���߱�===");
			int n_input = 0;
			while(true){
				try {
					System.out.print("���ڸ� �Է����ּ��� (1.�ո� / 2.�޸� / 0.����): ");
					n_input = Integer.parseInt(sc.nextLine());
					break;
				}catch(Exception e) {
					System.out.println("���ڸ� �Է����ּž� �մϴ�.");
				}
			}
			
			if(n_input == 0) {
				System.out.println("���α׷��� �����մϴ�.");
				break; // or System.exit(0);
			}else if(n_input == 1
					|| n_input == 2){
				int rand = (int)(Math.random() * 2 + 1);
				if(rand == n_input) {
					System.out.println("������ϴ�.");
				}else {
					System.out.println("Ʋ�Ƚ��ϴ�.");
				}
			}else {
				System.out.println("�߸��� ���� ���Դϴ�. �޴��� Ȯ���ϰ� �ٽ� �Է����ּ���.");
			}
			System.out.println("--------------------------------> restart");
		}

	}
}

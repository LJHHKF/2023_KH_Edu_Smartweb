import java.util.Scanner;

public class Quiz_03 {
	public static void main(String[] args) {
		//���������� ����.
		//���и� �����ϴ� if���� ��ø if�� ����. (||, && �����ڸ� ������ Ȱ��)

		Scanner sc = new Scanner(System.in);

		while(true) {
			System.out.println("===���� ���� �� ����===");

			int n_input = 0;
			while(true) {
				try {
					System.out.print("���ڸ� �����ϼ���(1.���� / 2.���� / 3. �� / 4. ����) : ");
					n_input = Integer.parseInt(sc.nextLine());
					break;
				}catch(Exception e) {
					System.out.println("���� ������ �ƴմϴ�. ���ڸ� �Է����ּ���.");
				}
			}
			if(n_input == 4) {
				System.out.println("���α׷��� �����մϴ�.");
				break; //or System.exit(0);
			}else if((n_input >= 1 && n_input <= 3)) {
				int rand = (int)(Math.random() * 3 + 1); //(3 - 1 + 1) + 1

				System.out.println("========���========");

				System.out.print("����� ");
				switch(n_input) {
				case 1:
					System.out.print("����");
					break;
				case 2:
					System.out.print("����");
					break;
				case 3:
					System.out.print("��");
					break;
				}
				System.out.println("�� �½��ϴ�.");

				System.out.print("��ǻ�ʹ� ");
				switch(rand) {
				case 1:
					System.out.print("����");
					break;
				case 2:
					System.out.print("����");
					break;
				case 3:
					System.out.print("��");
					break;
				}
				System.out.println("�� �½��ϴ�.");

				System.out.println("==================");

				if(n_input == rand) {
					System.out.println("�����ϴ�.");
				}else if((n_input == 1 && rand == 2)
						||(n_input == 2 && rand == 3)
						||(n_input == 3 && rand == 1)) {
					System.out.println("��ǻ�Ͱ� �̰���ϴ�.");
				}else if((n_input == 1 && rand == 3)
						||(n_input == 2 && rand == 1)
						||(n_input == 3 && rand == 2)) {
					System.out.println("����� �̰���ϴ�.");
				}
				//�� 3 ��� �� �ϳ��� else�� ���� ����.
			}else {
				System.out.println("�޴� ��ȣ�� �ش����� �ʽ��ϴ�. ������մϴ�.");
				continue;
			}
			System.out.println("---------------------> restart");
		}
	}
}

import java.util.Scanner;

public class Quiz_04 {
	public static void main(String[] args) {
		//ATM + ���� ���� ����.
		//�渶 ����

		Scanner sc = new Scanner(System.in);
		int curMoney = 0;

		TOPLOOP : while(true) {
			System.out.println("�渶 ���ӿ� ���� ���� ȯ���մϴ�.");
			System.out.println("1.���� ����");
			System.out.println("2.�ܾ� ����");
			System.out.println("3.�ܾ� ��ȸ");
			System.out.println("4.����");

			int n_input = 0;
			while(true)
			{
				try {
					System.out.print("�޴��� �������ּ���. >> ");
					n_input = Integer.parseInt(sc.nextLine());
					break;
				}catch(Exception e) {
					System.out.println("�޴� ��ȣ�� ���� ������ �Է����ּž� �մϴ�.");
				}
			}

			switch(n_input) {
			case 1: // ���� ����
				if(curMoney <= 0) {
					System.out.println("������ �ܾ��� �������� �ʽ��ϴ�. �޴��� ���ư��ϴ�.");
					continue TOPLOOP;
				}else {
					System.out.println("�渶 ������ �����ϰڽ��ϴ� !!");
					System.out.println("1. 1����");
					System.out.println("2. 2����");
					System.out.println("3. 3����");

					int choice = 0;
					while(true) {
						try {
							System.out.print("���� �ϰ� ���� ���� �������ּ���. >> ");
							choice = Integer.parseInt(sc.nextLine());

							if(choice < 1 || choice > 3) {
								System.out.println("���� �� ��ȣ�Դϴ�. �ĺ� ������ ����ּ���.");
								continue;
							}

							break;
						}catch(Exception e) {
							System.out.println("�� ��ȣ�� ���� ������ �Է����ּž� �մϴ�.");
						}
					}

					boolean gameStop = false;
					int bet = 0;
					int rating = 2;
					while(true) {
						try {
							System.out.print("�󸶸� ���� �Ͻðڽ��ϱ�? (���� �ߴ��� 0)>> ");
							n_input = Integer.parseInt(sc.nextLine());
							if(n_input == 0) {
								System.out.println("������ �ߴ��ϰ� ���ӿ��� �����ϴ�.");
								gameStop = true;
								break;
							}else if(n_input <= curMoney) {
								bet = n_input;
								curMoney -= n_input;
								break;
							}else {
								System.out.println("�ܾ��� ���ڶ��ϴ�. �ٽ� �Է����ֽʽÿ�. �ߴ��� ���Ͻø� 0�� �Է����ּ���.");
								continue;
							}
						}catch(Exception e) {
							System.out.println("�Ž� �ݾ��� ���� ������ �Է����ּž� �մϴ�.");
						}
					}

					if(gameStop) {
						break;
					}

					int rand = (int)(Math.random() * 3 + 1); //1,2,3

					if(rand == choice) {
						curMoney += bet * rating;
						System.out.println("���ÿ� �����ϼ̽��ϴ�. " + bet * rating + "�� ���̽��ϴ�. ���� �ܾ��� " + curMoney +"�� �Դϴ�.");
					}else {
						System.out.println("���ÿ� �����ϼ̽��ϴ�. " + bet +"�� �����̽��ϴ�. ���� �ܾ��� " + curMoney + "�� �Դϴ�.");
					}
				}
				break;
			case 2: // ����
				while(true) {
					try {
						System.out.print("�󸶸� �����Ͻðڽ��ϱ�? >> ");
						n_input = Integer.parseInt(sc.nextLine());
						if(n_input >= 0) {
							curMoney += n_input;
							System.out.println(n_input + "���� �����Ǿ����ϴ�. ���� �ܾ��� " + curMoney + "�� �Դϴ�.");
						}else {
							System.out.println("�Ա� �ݾ��� ������ �� �� �����ϴ�.");
						}
						break;
					}catch(Exception e) {
						System.out.println("�Ա� �ݾ��� ���� ������ �Է����ּž� �մϴ�.");
					}
				}
				break;
			case 3: // ��ȸ
				System.out.println("���� �ܾ��� " + curMoney + "�� �Դϴ�.");
				break;
			case 4:
				System.out.println("���α׷��� �����մϴ�.");
				break TOPLOOP; // or System.exit(0);
			default:
				System.out.println("�޴� ��ȣ ���� ���Դϴ�. �޴� ��ȣ �߿��� ����ֽʽÿ�.");
				break;
			}
		}

	}
}

import java.util.Scanner;

public class Quiz_01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int curMoney = 0;
		int n_input = 0;

		while(true){
			while(true){
				try {
					System.out.println("***ATM �ùķ�����***");
					System.out.println("1. �ܾ���ȸ");
					System.out.println("2. �Ա��ϱ�");
					System.out.println("3. ����ϱ�");
					System.out.println("4. �����ϱ�");
					System.out.print(">>");
					n_input = Integer.parseInt(sc.nextLine());
					break;
				}catch(Exception e)
				{
					System.out.println("�޴��� ���ڸ� �Է��ؾ� �մϴ�.");
				}
			}

			if(n_input == 4) {
				System.out.println("ATM Simulator�� �����մϴ�.");
				break; // or System.exit(0);
			}

			switch(n_input)
			{
			case 1: // if (n_input == 1)
				System.out.println("���� ���� �ܾ��� "+ curMoney +"���Դϴ�.");
				break;
			case 2:
				System.out.println("�󸶸� �Ա��Ͻðڽ��ϱ�?");
				while(true){
					try {
						System.out.print(">>");
						n_input = Integer.parseInt(sc.nextLine());
						break;
					}
					catch(Exception e) {
						System.out.println("�ݾ��� ���ڸ� �Է��ؾ� �մϴ�.");
					}
				}
				if(n_input >= 0){
					curMoney += n_input;
					System.out.println(n_input + "���� �Ա޵Ǿ����ϴ�. ���� ���� �ܾ��� " + curMoney +"�� �Դϴ�.");
				}else{
					System.out.println("�Ա� �ݾ��� ������ �� �� �����ϴ�.");
				}
				break;
			case 3:
				System.out.println("�󸶸� ����Ͻðڽ��ϱ�?");
				while(true){
					try {
						System.out.print(">>");
						n_input = Integer.parseInt(sc.nextLine());
						break;
					}catch(Exception e) {
						System.out.println("�ݾ��� ���ڸ� �Է��ؾ� �մϴ�.");
					}
				}
				if(curMoney >= n_input){
					curMoney -= n_input;
					System.out.println(n_input + "���� ��ݵǾ����ϴ�. ���� ���� �ܾ��� " + curMoney +"�� �Դϴ�.");
				}else{
					System.out.println("�ܾ��� ���ڶ��ϴ�. ���� ���� �ܾ��� "+ curMoney +"���Դϴ�.");
				}
				break;
			default:
				System.out.println("���� ��� ��ȣ�Դϴ�. �޴��� �ٽ� Ȯ�����ּ���.");
				break;
			}
		}
	}
}

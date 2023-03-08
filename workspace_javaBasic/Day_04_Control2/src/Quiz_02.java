import java.util.Scanner;

public class Quiz_02 {
	public static void main(String[] args) {
		//���Ǳ� �ùķ�����. (vending machine simulator)
		
		Scanner sc = new Scanner(System.in);
		
		int n_input;
		int price_min = 800;
		int curMoney = 3000;
		
		int has_coke = 0;
		int price_coke = 1000;
		
		int has_cider = 0;
		int price_cider = 800;
		
		int has_greenPlum = 0;		//or Japaness apricot
		int price_greenPlum = 1500; 
	
		
		TOPLOOP : while(true) {
			
			if(curMoney < price_min) {
				System.out.println("���� �ݾ��� �����������ϴ�. ���Ǳ⸦ ��Ȱ��ȭ�մϴ�.");
				
				System.out.println("======������ ����ǰ ���======");
				System.out.println("������: " + curMoney);
				System.out.println("�ݶ�: " + has_coke);
				System.out.println("���̴�: " + has_cider);
				System.out.println("�Ž���: " + has_greenPlum);
				System.out.println("=====================");//20
				
				break; // or System.exit(0);
			}
			

			
			while(true) {
				try {
					System.out.println("=== ���Ǳ� �ùķ����� ===");
					System.out.println("������� �����ϼ���.");
					System.out.print("1. �ݶ�("+price_coke+")  ");
					System.out.print("2. ���̴�("+price_cider+")  ");
					System.out.print("3. �Ž���("+price_greenPlum+")  ");
					System.out.print("4. ����  ");
					System.out.println("[0. ����ǰȮ��]");
					
					System.out.print(">>");
					n_input = Integer.parseInt(sc.nextLine());
					break;
				}catch(Exception e) {
					System.out.println("�޴��� ���ڸ� �Է��ؾ� �մϴ�.");
				}
			}

			
			switch(n_input) {
			case 0:
				System.out.println("======����ǰ ���======");
				System.out.println("������: " + curMoney);
				System.out.println("�ݶ�: " + has_coke);
				System.out.println("���̴�: " + has_cider);
				System.out.println("�Ž���: " + has_greenPlum);
				System.out.println("=====================");//20
				break;
			case 1:
				if(curMoney >= price_coke) {
					curMoney -= price_coke;
					has_coke++;
					System.out.println("�ݶ� �����߽��ϴ�.");
					System.out.println("�ݶ�: " + has_coke);
					System.out.println("������ -" + price_coke);
				}
				else {
					System.out.println("�ܾ��� ���ڶ��ϴ�.");
					System.out.println("���� �ܾ� : "+ curMoney);
				}
				break;
			case 2:
				if(curMoney >= price_cider) {
					curMoney -= price_cider;
					has_cider++;
					System.out.println("���̴ٸ� �����߽��ϴ�.");
					System.out.println("���̴�: " + has_cider);
					System.out.println("������ -" + price_cider);
				}
				else {
					System.out.println("�ܾ��� ���ڶ��ϴ�.");
					System.out.println("���� �ܾ� : "+ curMoney);
				}
				break;
			case 3:
				if(curMoney >= price_greenPlum) {
					curMoney -= price_greenPlum;
					has_greenPlum++;
					System.out.println("�Ž����� �����߽��ϴ�.");
					System.out.println("�Ž���: " + has_greenPlum);
					System.out.println("������ -" + price_greenPlum);
				}
				else {
					System.out.println("�ܾ��� ���ڶ��ϴ�");
					System.out.println("���� �ܾ� : "+ curMoney);
				}
				break;
			case 4:
				System.out.println("���ᰡ ���õǾ����ϴ�. ���Ǳ⸦ ��Ȱ��ȭ�մϴ�.");
				break TOPLOOP; //or System.exit(0);
			default:
				System.out.println("���� �޴� ��ȣ�Դϴ�. �޴��� �ٽ� Ȯ�����ּ���.");
				break;
			}
			
		}
		
	}
}

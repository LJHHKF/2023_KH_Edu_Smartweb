import java.util.Scanner;

public class Quiz_02 {
	public static void main(String[] args) {
		//자판기 시뮬레이터. (vending machine simulator)
		
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
				System.out.println("투입 금액이 부족해졌습니다. 자판기를 비활성화합니다.");
				
				System.out.println("======마지막 소지품 목록======");
				System.out.println("소지금: " + curMoney);
				System.out.println("콜라: " + has_coke);
				System.out.println("사이다: " + has_cider);
				System.out.println("매실차: " + has_greenPlum);
				System.out.println("=====================");//20
				
				break; // or System.exit(0);
			}
			

			
			while(true) {
				try {
					System.out.println("=== 자판기 시뮬레이터 ===");
					System.out.println("음료수를 선택하세요.");
					System.out.print("1. 콜라("+price_coke+")  ");
					System.out.print("2. 사이다("+price_cider+")  ");
					System.out.print("3. 매실차("+price_greenPlum+")  ");
					System.out.print("4. 종료  ");
					System.out.println("[0. 소지품확인]");
					
					System.out.print(">>");
					n_input = Integer.parseInt(sc.nextLine());
					break;
				}catch(Exception e) {
					System.out.println("메뉴는 숫자를 입력해야 합니다.");
				}
			}

			
			switch(n_input) {
			case 0:
				System.out.println("======소지품 목록======");
				System.out.println("소지금: " + curMoney);
				System.out.println("콜라: " + has_coke);
				System.out.println("사이다: " + has_cider);
				System.out.println("매실차: " + has_greenPlum);
				System.out.println("=====================");//20
				break;
			case 1:
				if(curMoney >= price_coke) {
					curMoney -= price_coke;
					has_coke++;
					System.out.println("콜라를 구매했습니다.");
					System.out.println("콜라: " + has_coke);
					System.out.println("소지금 -" + price_coke);
				}
				else {
					System.out.println("잔액이 모자랍니다.");
					System.out.println("현재 잔액 : "+ curMoney);
				}
				break;
			case 2:
				if(curMoney >= price_cider) {
					curMoney -= price_cider;
					has_cider++;
					System.out.println("사이다를 구매했습니다.");
					System.out.println("사이다: " + has_cider);
					System.out.println("소지금 -" + price_cider);
				}
				else {
					System.out.println("잔액이 모자랍니다.");
					System.out.println("현재 잔액 : "+ curMoney);
				}
				break;
			case 3:
				if(curMoney >= price_greenPlum) {
					curMoney -= price_greenPlum;
					has_greenPlum++;
					System.out.println("매실차를 구매했습니다.");
					System.out.println("매실차: " + has_greenPlum);
					System.out.println("소지금 -" + price_greenPlum);
				}
				else {
					System.out.println("잔액이 모자랍니다");
					System.out.println("현재 잔액 : "+ curMoney);
				}
				break;
			case 4:
				System.out.println("종료가 선택되었습니다. 자판기를 비활성화합니다.");
				break TOPLOOP; //or System.exit(0);
			default:
				System.out.println("없는 메뉴 번호입니다. 메뉴를 다시 확인해주세요.");
				break;
			}
			
		}
		
	}
}

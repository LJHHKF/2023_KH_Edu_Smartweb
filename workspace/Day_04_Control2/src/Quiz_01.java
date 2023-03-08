import java.util.Scanner;

public class Quiz_01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int curMoney = 0;
		int n_input = 0;

		while(true){
			while(true){
				try {
					System.out.println("***ATM 시뮬레이터***");
					System.out.println("1. 잔액조회");
					System.out.println("2. 입금하기");
					System.out.println("3. 출금하기");
					System.out.println("4. 종료하기");
					System.out.print(">>");
					n_input = Integer.parseInt(sc.nextLine());
					break;
				}catch(Exception e)
				{
					System.out.println("메뉴는 숫자를 입력해야 합니다.");
				}
			}

			if(n_input == 4) {
				System.out.println("ATM Simulator을 종료합니다.");
				break; // or System.exit(0);
			}

			switch(n_input)
			{
			case 1: // if (n_input == 1)
				System.out.println("현재 보유 잔액은 "+ curMoney +"원입니다.");
				break;
			case 2:
				System.out.println("얼마를 입급하시겠습니까?");
				while(true){
					try {
						System.out.print(">>");
						n_input = Integer.parseInt(sc.nextLine());
						break;
					}
					catch(Exception e) {
						System.out.println("금액은 숫자를 입력해야 합니다.");
					}
				}
				if(n_input >= 0){
					curMoney += n_input;
					System.out.println(n_input + "원이 입급되었습니다. 현재 보유 잔액은 " + curMoney +"원 입니다.");
				}else{
					System.out.println("입금 금액은 음수가 될 수 없습니다.");
				}
				break;
			case 3:
				System.out.println("얼마를 출금하시겠습니까?");
				while(true){
					try {
						System.out.print(">>");
						n_input = Integer.parseInt(sc.nextLine());
						break;
					}catch(Exception e) {
						System.out.println("금액은 숫자를 입력해야 합니다.");
					}
				}
				if(curMoney >= n_input){
					curMoney -= n_input;
					System.out.println(n_input + "원이 출금되었습니다. 현재 보유 잔액은 " + curMoney +"원 입니다.");
				}else{
					System.out.println("잔액이 모자랍니다. 현재 보유 잔액은 "+ curMoney +"원입니다.");
				}
				break;
			default:
				System.out.println("없는 기능 번호입니다. 메뉴를 다시 확인해주세요.");
				break;
			}
		}
	}
}

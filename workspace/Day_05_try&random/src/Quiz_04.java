import java.util.Scanner;

public class Quiz_04 {
	public static void main(String[] args) {
		//ATM + 난수 결합 문제.
		//경마 게임

		Scanner sc = new Scanner(System.in);
		int curMoney = 0;

		TOPLOOP : while(true) {
			System.out.println("경마 게임에 오신 것을 환영합니다.");
			System.out.println("1.게임 시작");
			System.out.println("2.잔액 충전");
			System.out.println("3.잔액 조회");
			System.out.println("4.종료");

			int n_input = 0;
			while(true)
			{
				try {
					System.out.print("메뉴를 선택해주세요. >> ");
					n_input = Integer.parseInt(sc.nextLine());
					break;
				}catch(Exception e) {
					System.out.println("메뉴 번호는 숫자 형식을 입력해주셔야 합니다.");
				}
			}

			switch(n_input) {
			case 1: // 게임 시작
				if(curMoney <= 0) {
					System.out.println("충전된 잔액이 존재하지 않습니다. 메뉴로 돌아갑니다.");
					continue TOPLOOP;
				}else {
					System.out.println("경마 게임을 시작하겠습니다 !!");
					System.out.println("1. 1번말");
					System.out.println("2. 2번말");
					System.out.println("3. 3번말");

					int choice = 0;
					while(true) {
						try {
							System.out.print("베팅 하고 싶은 말을 선택해주세요. >> ");
							choice = Integer.parseInt(sc.nextLine());

							if(choice < 1 || choice > 3) {
								System.out.println("없는 말 번호입니다. 후보 내에서 골라주세요.");
								continue;
							}

							break;
						}catch(Exception e) {
							System.out.println("말 번호는 숫자 형식을 입력해주셔야 합니다.");
						}
					}

					boolean gameStop = false;
					int bet = 0;
					int rating = 2;
					while(true) {
						try {
							System.out.print("얼마를 베팅 하시겠습니까? (게임 중단은 0)>> ");
							n_input = Integer.parseInt(sc.nextLine());
							if(n_input == 0) {
								System.out.println("베팅을 중단하고 게임에서 나갑니다.");
								gameStop = true;
								break;
							}else if(n_input <= curMoney) {
								bet = n_input;
								curMoney -= n_input;
								break;
							}else {
								System.out.println("잔액이 모자랍니다. 다시 입력해주십시오. 중단을 원하시면 0을 입력해주세요.");
								continue;
							}
						}catch(Exception e) {
							System.out.println("거실 금액은 숫자 형식을 입력해주셔야 합니다.");
						}
					}

					if(gameStop) {
						break;
					}

					int rand = (int)(Math.random() * 3 + 1); //1,2,3

					if(rand == choice) {
						curMoney += bet * rating;
						System.out.println("베팅에 성공하셨습니다. " + bet * rating + "원 버셨습니다. 현재 잔액은 " + curMoney +"원 입니다.");
					}else {
						System.out.println("베팅에 실패하셨습니다. " + bet +"원 잃으셨습니다. 현재 잔액은 " + curMoney + "원 입니다.");
					}
				}
				break;
			case 2: // 충전
				while(true) {
					try {
						System.out.print("얼마를 충전하시겠습니까? >> ");
						n_input = Integer.parseInt(sc.nextLine());
						if(n_input >= 0) {
							curMoney += n_input;
							System.out.println(n_input + "원이 충전되었습니다. 현재 잔액은 " + curMoney + "원 입니다.");
						}else {
							System.out.println("입금 금액은 음수가 될 수 없습니다.");
						}
						break;
					}catch(Exception e) {
						System.out.println("입금 금액은 숫자 형식을 입력해주셔야 합니다.");
					}
				}
				break;
			case 3: // 조회
				System.out.println("현재 잔액은 " + curMoney + "원 입니다.");
				break;
			case 4:
				System.out.println("프로그램을 종료합니다.");
				break TOPLOOP; // or System.exit(0);
			default:
				System.out.println("메뉴 번호 범위 밖입니다. 메뉴 번호 중에서 골라주십시오.");
				break;
			}
		}

	}
}

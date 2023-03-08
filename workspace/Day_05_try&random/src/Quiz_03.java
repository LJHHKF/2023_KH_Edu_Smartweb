import java.util.Scanner;

public class Quiz_03 {
	public static void main(String[] args) {
		//가위바위보 게임.
		//승패를 결정하는 if문은 중첩 if문 금지. (||, && 연산자를 적절히 활용)

		Scanner sc = new Scanner(System.in);

		while(true) {
			System.out.println("===가위 바위 보 게임===");

			int n_input = 0;
			while(true) {
				try {
					System.out.print("숫자를 선택하세요(1.가위 / 2.바위 / 3. 보 / 4. 종료) : ");
					n_input = Integer.parseInt(sc.nextLine());
					break;
				}catch(Exception e) {
					System.out.println("숫자 형식이 아닙니다. 숫자를 입력해주세요.");
				}
			}
			if(n_input == 4) {
				System.out.println("프로그램을 종료합니다.");
				break; //or System.exit(0);
			}else if((n_input >= 1 && n_input <= 3)) {
				int rand = (int)(Math.random() * 3 + 1); //(3 - 1 + 1) + 1

				System.out.println("========결과========");

				System.out.print("당신은 ");
				switch(n_input) {
				case 1:
					System.out.print("가위");
					break;
				case 2:
					System.out.print("바위");
					break;
				case 3:
					System.out.print("보");
					break;
				}
				System.out.println("를 냈습니다.");

				System.out.print("컴퓨터는 ");
				switch(rand) {
				case 1:
					System.out.print("가위");
					break;
				case 2:
					System.out.print("바위");
					break;
				case 3:
					System.out.print("보");
					break;
				}
				System.out.println("를 냈습니다.");

				System.out.println("==================");

				if(n_input == rand) {
					System.out.println("비겼습니다.");
				}else if((n_input == 1 && rand == 2)
						||(n_input == 2 && rand == 3)
						||(n_input == 3 && rand == 1)) {
					System.out.println("컴퓨터가 이겼습니다.");
				}else if((n_input == 1 && rand == 3)
						||(n_input == 2 && rand == 1)
						||(n_input == 3 && rand == 2)) {
					System.out.println("당신이 이겼습니다.");
				}
				//윗 3 경우 중 하나는 else로 생략 가능.
			}else {
				System.out.println("메뉴 번호에 해당하지 않습니다. 재시작합니다.");
				continue;
			}
			System.out.println("---------------------> restart");
		}
	}
}

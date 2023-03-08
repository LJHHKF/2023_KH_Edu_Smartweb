import java.util.Scanner;

public class Quiz_02 {
	public static void main(String[] args) {
		//난수문과 if문을 혼합한 문제.
		//동전 앞 뒤 맞추기
		//1 또는 2의 랜덤 수를 추출한 뒤 사용자가 입력한 값과 비교

		Scanner sc = new Scanner(System.in);

		while(true) {
			System.out.println("===동전 앞 뒤 맞추기===");
			int n_input = 0;
			while(true){
				try {
					System.out.print("숫자를 입력해주세요 (1.앞면 / 2.뒷면 / 0.종료): ");
					n_input = Integer.parseInt(sc.nextLine());
					break;
				}catch(Exception e) {
					System.out.println("숫자를 입력해주셔야 합니다.");
				}
			}
			
			if(n_input == 0) {
				System.out.println("프로그램을 종료합니다.");
				break; // or System.exit(0);
			}else if(n_input == 1
					|| n_input == 2){
				int rand = (int)(Math.random() * 2 + 1);
				if(rand == n_input) {
					System.out.println("맞췄습니다.");
				}else {
					System.out.println("틀렸습니다.");
				}
			}else {
				System.out.println("잘못된 숫자 값입니다. 메뉴를 확인하고 다시 입력해주세요.");
			}
			System.out.println("--------------------------------> restart");
		}

	}
}

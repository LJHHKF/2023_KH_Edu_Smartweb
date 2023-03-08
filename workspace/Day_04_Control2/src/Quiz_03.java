import java.util.Scanner;

public class Quiz_03 {
	
	public static int validNum(String input) {
		Scanner sc = new Scanner(System.in);
		int num = 0;
		while(true){
			try {
				System.out.print(input);
				num = Integer.parseInt(sc.nextLine());
				return num; // break 대신 함수 자체서 return으로 빠져나감.
			} catch(Exception e) {
				System.out.println("숫자를 입력해야 합니다.");
			}
		}
	}
	
	//사칙연산 계산기 만들기
	//+, -, *, / 중 하나 입력.
	//연산자에 q를 입력 받기 전까지 프로그램을 종료하지 않는다.
	//나눗셈은 소수점까지 포함해서 나와야한다.
	//q를 입력받는 즉시 프로그램이 끝나야 한다.
	//그 외가 입력되면 '연산자를 확인하고 다시 입력해주세요.'

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		TOPLOOP : while(true) {
			System.out.println("=== 계산기 프로그램 ===");
			System.out.println();
			System.out.print("연산자 입력 (+,-,*,/) [종료는 q] : ");			
			String s_op = sc.nextLine();

			if(s_op.equals("q")) {
				System.out.println("프로그램을 종료합니다.");
				break;// or System.exit(0);
			}else if(!(
					s_op.equals("+")
					|| s_op.equals("-")
					|| s_op.equals("*")
					|| s_op.equals("/")
					)) {
				System.out.println("연산자를 확인하고 다시 입력해주세요.");
				continue;
			}

			int num1 = validNum("첫 번째 숫자 입력 : "),
				num2 = validNum("두 번째 숫자 입력 : ");
			// try 구문 안에서 선언 시 try 구문 안의 지역변수화.
//			while(true){
//				try {
//					System.out.print("첫 번째 숫자 입력 : ");
//					num1 = Integer.parseInt(sc.nextLine());
//					break;
//				} catch(Exception e) {
//					System.out.println("숫자를 입력해야 합니다.");
//				}
//			}
//			while(true) {
//				try {
//					System.out.print("두 번째 숫자 입력 : ");
//					num2 = Integer.parseInt(sc.nextLine());
//					break;
//				} catch(Exception e) {
//					System.out.println("숫자를 입력해야 합니다.");
//				}
//			}

			System.out.println();
			System.out.println("======= 결과 =======");
			System.out.println();

			int result = 0;
			switch(s_op) {
			case "+":
				result = num1 + num2;
				break;
			case "-":
				result = num1 - num2;
				break;
			case "*" :
				result = num1 * num2;
				break;
			case "/" : // 실수로 결과를 내야하니 따로 처리.
				System.out.println(num1 +" " + s_op + " " + num2 + " = " + ((double)num1 / num2));
				System.out.println();
				continue TOPLOOP;
			default:
				System.out.println("오류입니다.");
				break TOPLOOP; // or System.exit(0)
			}
			System.out.println(num1 +" " + s_op + " " + num2 + " = " + result);
			System.out.println();
		}
	}
		
}

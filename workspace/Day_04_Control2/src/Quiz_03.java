import java.util.Scanner;

public class Quiz_03 {
	
	public static int validNum(String input) {
		Scanner sc = new Scanner(System.in);
		int num = 0;
		while(true){
			try {
				System.out.print(input);
				num = Integer.parseInt(sc.nextLine());
				return num; // break ��� �Լ� ��ü�� return���� ��������.
			} catch(Exception e) {
				System.out.println("���ڸ� �Է��ؾ� �մϴ�.");
			}
		}
	}
	
	//��Ģ���� ���� �����
	//+, -, *, / �� �ϳ� �Է�.
	//�����ڿ� q�� �Է� �ޱ� ������ ���α׷��� �������� �ʴ´�.
	//�������� �Ҽ������� �����ؼ� ���;��Ѵ�.
	//q�� �Է¹޴� ��� ���α׷��� ������ �Ѵ�.
	//�� �ܰ� �ԷµǸ� '�����ڸ� Ȯ���ϰ� �ٽ� �Է����ּ���.'

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		TOPLOOP : while(true) {
			System.out.println("=== ���� ���α׷� ===");
			System.out.println();
			System.out.print("������ �Է� (+,-,*,/) [����� q] : ");			
			String s_op = sc.nextLine();

			if(s_op.equals("q")) {
				System.out.println("���α׷��� �����մϴ�.");
				break;// or System.exit(0);
			}else if(!(
					s_op.equals("+")
					|| s_op.equals("-")
					|| s_op.equals("*")
					|| s_op.equals("/")
					)) {
				System.out.println("�����ڸ� Ȯ���ϰ� �ٽ� �Է����ּ���.");
				continue;
			}

			int num1 = validNum("ù ��° ���� �Է� : "),
				num2 = validNum("�� ��° ���� �Է� : ");
			// try ���� �ȿ��� ���� �� try ���� ���� ��������ȭ.
//			while(true){
//				try {
//					System.out.print("ù ��° ���� �Է� : ");
//					num1 = Integer.parseInt(sc.nextLine());
//					break;
//				} catch(Exception e) {
//					System.out.println("���ڸ� �Է��ؾ� �մϴ�.");
//				}
//			}
//			while(true) {
//				try {
//					System.out.print("�� ��° ���� �Է� : ");
//					num2 = Integer.parseInt(sc.nextLine());
//					break;
//				} catch(Exception e) {
//					System.out.println("���ڸ� �Է��ؾ� �մϴ�.");
//				}
//			}

			System.out.println();
			System.out.println("======= ��� =======");
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
			case "/" : // �Ǽ��� ����� �����ϴ� ���� ó��.
				System.out.println(num1 +" " + s_op + " " + num2 + " = " + ((double)num1 / num2));
				System.out.println();
				continue TOPLOOP;
			default:
				System.out.println("�����Դϴ�.");
				break TOPLOOP; // or System.exit(0)
			}
			System.out.println(num1 +" " + s_op + " " + num2 + " = " + result);
			System.out.println();
		}
	}
		
}

import java.util.Scanner;

public class Quiz_05 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("==================="); // 20
		
		System.out.print("이름: ");
		//String name =
		sc.nextLine();
		
		System.out.println("==================="); // 20
		
		System.out.print("국어 : ");
		//String str = sc.nextLine();
		int n_kor = Integer.parseInt(sc.nextLine());
		//Double kor = Double.parseDouble(sc.nextLine()); 
		
		System.out.print("영어 : ");
		//str = sc.nextLine();
		int n_eng = Integer.parseInt(sc.nextLine());
		
		System.out.print("수학 : ");
		//str = sc.nextLine();
		int n_math = Integer.parseInt(sc.nextLine());
		
		System.out.println("==================="); // 20
		
		int sum = n_kor + n_eng + n_math;
		System.out.println("합계 : " + (sum));
		System.out.println("평균 : " + (sum / 3.0f));
		
		System.out.println("==================="); // 20

	}
}

import java.util.Scanner;

public class Exam_03 {
	public static void main(String[] args) {
		//사용자로부터 3개의 값을 입력받고, 입력 받은 값이 무엇인지 출력하라.
		
		Scanner sc = new Scanner(System.in);
	
//		int x = 0;
//		int y = 0;
//		int z = 0;
		
//		System.out.print("x : ");
//		x = Integer.parseInt(sc.nextLine());
//		
//		System.out.print("y : ");
//		y = Integer.parseInt(sc.nextLine());
//		
//		System.out.print("z : ");
//		z = Integer.parseInt(sc.nextLine());
		
//		System.out.println("입력된 값은 ");
//		System.out.println("x : " + x);
//		System.out.println("y : " + y);
//		System.out.println("z : " + z);
		
		//변수 3개로 처리하던 것을 배열로 처리해 볼 것.
		
		int[] arr1 = new int[3];
		System.out.println("3개의 수를 입력해주세요.");
		for(int i = 0; i < arr1.length; i++) {
			System.out.print("arr["+i+"] : ");
			arr1[i] = Integer.parseInt(sc.nextLine());
		}
		System.out.println("입력된 값은 ");
		for(int i = 0; i < arr1.length; i++) {
			System.out.println("arr["+i+"] : " + arr1[i]);
		}
	}
}

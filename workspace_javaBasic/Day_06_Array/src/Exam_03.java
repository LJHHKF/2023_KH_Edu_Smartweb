import java.util.Scanner;

public class Exam_03 {
	public static void main(String[] args) {
		//����ڷκ��� 3���� ���� �Է¹ް�, �Է� ���� ���� �������� ����϶�.
		
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
		
//		System.out.println("�Էµ� ���� ");
//		System.out.println("x : " + x);
//		System.out.println("y : " + y);
//		System.out.println("z : " + z);
		
		//���� 3���� ó���ϴ� ���� �迭�� ó���� �� ��.
		
		int[] arr1 = new int[3];
		System.out.println("3���� ���� �Է����ּ���.");
		for(int i = 0; i < arr1.length; i++) {
			System.out.print("arr["+i+"] : ");
			arr1[i] = Integer.parseInt(sc.nextLine());
		}
		System.out.println("�Էµ� ���� ");
		for(int i = 0; i < arr1.length; i++) {
			System.out.println("arr["+i+"] : " + arr1[i]);
		}
	}
}

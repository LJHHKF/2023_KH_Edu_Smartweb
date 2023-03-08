
public class Exam_01 {
	public static void main(String[] args) {
		int[] arr = new int[] {10, 20, 30, 40};
		//int[] arr = new int[4];
		
//		arr[0] = 10;
//		arr[1] = 20;
//		arr[2] = 30;
//		arr[3] = 40;
		
		//arr[4] = 50;
		//ArrayIndexOutOfBoundsException:
		//Index 4 out of bounds for length 4
		
//		System.out.println(arr[0]);
//		System.out.println(arr[1]);
//		System.out.println(arr[2]);
//		System.out.println(arr[3]);
		
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		
	}
}

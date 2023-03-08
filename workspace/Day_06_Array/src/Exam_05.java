
public class Exam_05 {
	public static void main(String[] args) {
		//Bubble sort.
		//평가문제 기출 가능성이 있어서 실행함.
		
		//24, 86, 13, 46
		int[] arr1 = new int[] {99, 48, 27, 15};
		
		//정렬 전 출력
		System.out.print("정렬 전 : ");
		for(int i = 0; i < arr1.length; i++) {
			System.out.print(arr1[i] + " ");
		}
		System.out.println();
		// ---------------------------- 정렬코드
//		for(int i = 0; i < arr1.length-1; i++) {
//			for(int j = 0; j < arr1.length-1; j++) {
//				if(arr1[j] > arr1[j+1]) {
//					int temp = arr1[j];
//					arr1[j] = arr1[j+1];
//					arr1[j+1] = temp;
//				}
//			}
//		}
		
		//1회차만 비교.
		if(arr1[0] > arr1[1]) {
			int temp = arr1[0];
			arr1[0] = arr1[1];
			arr1[1] = temp;
		}
		
		//----------------------------- 정렬코드
		System.out.println("===================");
		System.out.print("정렬 후 : ");
		for(int i = 0; i < arr1.length; i++) {
			System.out.print(arr1[i] + " ");
		}
		
	}
}

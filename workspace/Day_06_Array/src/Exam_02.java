
public class Exam_02 {
	public static void main(String[] args) {
		//int형 변수 3칸짜리를 arr1로 작성하고,
		// 0: 48, 1: 99, 2: 33을 저장한 후
		// 화면에 출력
		
		int[] arr1 = new int[] {48,99,33};
		for(int i = 0; i < arr1.length; i++) {
			System.out.println("arr1["+i+"] : " + arr1[i]);
		}
		
		//String 형 변수 2칸짜리를 arr2로 작성하고,
		// 0: "Hello", 1: "World"를 저장한 후
		//화면에 출력
		
		String[] arr2 = new String[2];
		arr2[0] = "Hello";
		arr2[1] = "World";
		for(int i = 0; i < arr2.length; i++) {
			System.out.println("arr2["+i+"] : " + arr2[i]);
		}
		
		//char형 변수 5칸짜리를 arr3으로 작성하고,
		// 0: 'A', 1: 'r', 2: 'r', 3: 'a', 4: 'y'를 저장한 후
		//화면에 출력해
		char[] arr3 = new char[] {'A', 'r', 'r', 'a', 'y'};
		for(int i = 0; i < arr3.length; i++) {
			System.out.print(arr3[i] + " ");
		}
	}
}

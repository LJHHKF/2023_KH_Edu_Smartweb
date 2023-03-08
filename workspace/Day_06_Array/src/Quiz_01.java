
public class Quiz_01 {
	public static void main(String[] args) {
		//int형 변수 100개를 arr1로 작성하고,
		//1부터 100까지 저장한 후 출력해보세요.
		
		int[] arr1 = new int[100];
		for(int i = 0; i < arr1.length; i++) {
			arr1[i] = i+1;
			System.out.print(arr1[i] + " ");
			
			if((i+1)%10 == 0)
				System.out.println();
		}
		
		//char형 변수 26개를 arr2로 생성하고,
		//알파벳 A부터 Z까지 저장한 후 출력해보세요.
		
		char[] arr2 = new char[26];
		
		for(int i = 0; i < arr2.length; i++) {
			arr2[i] = (char)(65 + i);
			System.out.print(arr2[i] + " ");
		}
		
		//int형 변수 100개를 arr3으로 생성하고,
		//100부터 1까지 저장해보세요.
		System.out.println();
		int[] arr3 = new int[100];
		for(int i = 0; i < arr3.length; i++) {
			arr3[i] = 100 - i;
			System.out.print(arr3[i] + " ");
			if((i+1) % 10 == 0)
				System.out.println();
		}
		
	}
}


public class Quiz_01 {
	public static void main(String[] args) {
		//int�� ���� 100���� arr1�� �ۼ��ϰ�,
		//1���� 100���� ������ �� ����غ�����.
		
		int[] arr1 = new int[100];
		for(int i = 0; i < arr1.length; i++) {
			arr1[i] = i+1;
			System.out.print(arr1[i] + " ");
			
			if((i+1)%10 == 0)
				System.out.println();
		}
		
		//char�� ���� 26���� arr2�� �����ϰ�,
		//���ĺ� A���� Z���� ������ �� ����غ�����.
		
		char[] arr2 = new char[26];
		
		for(int i = 0; i < arr2.length; i++) {
			arr2[i] = (char)(65 + i);
			System.out.print(arr2[i] + " ");
		}
		
		//int�� ���� 100���� arr3���� �����ϰ�,
		//100���� 1���� �����غ�����.
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

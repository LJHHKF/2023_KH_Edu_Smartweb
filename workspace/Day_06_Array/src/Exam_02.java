
public class Exam_02 {
	public static void main(String[] args) {
		//int�� ���� 3ĭ¥���� arr1�� �ۼ��ϰ�,
		// 0: 48, 1: 99, 2: 33�� ������ ��
		// ȭ�鿡 ���
		
		int[] arr1 = new int[] {48,99,33};
		for(int i = 0; i < arr1.length; i++) {
			System.out.println("arr1["+i+"] : " + arr1[i]);
		}
		
		//String �� ���� 2ĭ¥���� arr2�� �ۼ��ϰ�,
		// 0: "Hello", 1: "World"�� ������ ��
		//ȭ�鿡 ���
		
		String[] arr2 = new String[2];
		arr2[0] = "Hello";
		arr2[1] = "World";
		for(int i = 0; i < arr2.length; i++) {
			System.out.println("arr2["+i+"] : " + arr2[i]);
		}
		
		//char�� ���� 5ĭ¥���� arr3���� �ۼ��ϰ�,
		// 0: 'A', 1: 'r', 2: 'r', 3: 'a', 4: 'y'�� ������ ��
		//ȭ�鿡 �����
		char[] arr3 = new char[] {'A', 'r', 'r', 'a', 'y'};
		for(int i = 0; i < arr3.length; i++) {
			System.out.print(arr3[i] + " ");
		}
	}
}

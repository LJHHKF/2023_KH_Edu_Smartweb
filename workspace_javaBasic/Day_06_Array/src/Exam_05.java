
public class Exam_05 {
	public static void main(String[] args) {
		//Bubble sort.
		//�򰡹��� ���� ���ɼ��� �־ ������.
		
		//24, 86, 13, 46
		int[] arr1 = new int[] {99, 48, 27, 15};
		
		//���� �� ���
		System.out.print("���� �� : ");
		for(int i = 0; i < arr1.length; i++) {
			System.out.print(arr1[i] + " ");
		}
		System.out.println();
		// ---------------------------- �����ڵ�
//		for(int i = 0; i < arr1.length-1; i++) {
//			for(int j = 0; j < arr1.length-1; j++) {
//				if(arr1[j] > arr1[j+1]) {
//					int temp = arr1[j];
//					arr1[j] = arr1[j+1];
//					arr1[j+1] = temp;
//				}
//			}
//		}
		
		//1ȸ���� ��.
		if(arr1[0] > arr1[1]) {
			int temp = arr1[0];
			arr1[0] = arr1[1];
			arr1[1] = temp;
		}
		
		//----------------------------- �����ڵ�
		System.out.println("===================");
		System.out.print("���� �� : ");
		for(int i = 0; i < arr1.length; i++) {
			System.out.print(arr1[i] + " ");
		}
		
	}
}


public class Exam_02 {
	public static void main(String[] args) {
		//���� ����
		double rand = Math.random(); // 0~1.0 ����(1.0 �̸�)�� �Ǽ�(double)�� �������� ����.
		System.out.println(rand);
		
		
		int result = (int)(rand * 10); // ���� 0~9������ ���� ����ϴ� �ڵ�.
		System.out.println(result);
		
		result = (int)(rand * (6)) + 1; // 0~5 + 1 // �ֻ��� ��.
		System.out.println(result);
		
		
		//Math.random() * (�ִ� - �ּ� + 1) + �ּ� 
		//���� ���� ����
		//���ϰ��� �ϴ� ������ �ּҰ��� X
		//���ϰ��� �ϴ� ������ �ִ밪�� Y
		//(Y-X+1)+X
		result = (int)(rand * (52 - 37 + 1) + 37); 
		System.out.println(result);
	}
}

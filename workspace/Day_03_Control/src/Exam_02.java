
public class Exam_02 {
	public static void main(String[] args) {
		int i = 49; // �ʱⰪ ����
		
		while(i<100) // ���ǹ�
		{
			i++; //������
			
			//�Ǵ�
			//i += 2; & ���ǹ� '<100' �� �ƴ� '<99' ��ȭ.
			
			if(i % 2 == 1)
			{
				System.out.println("i = " + i);
			}
		}
	}
}

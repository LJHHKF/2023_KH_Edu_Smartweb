
public class Quiz_01 {
	
	//Math.random() * (�ִ� - �ּ� + 1) + �ּ�
	public static int myRand(int min, int max) {
		return (int)(Math.random() * (max - min + 1) + min);
	}
	
	public static void main(String[] args) {
		//����ڰ� ���ϴ� ���������� �� ����.
		//1. 0~9 ������ ������
		//2. 1~10������ ������
		//3. 20~35 ������ ���� ��
		//4. 0 �Ǵ� 1

		for(int i = 0; i < 10; i++)
		{
			//double rand = Math.random();
			//1
			System.out.print("0~9:" + myRand(0,9));
			//2
			System.out.print("\t, 1~10:" + myRand(1, 10));
			//3
			System.out.print("\t, 20~35:" + myRand(20,35));
			//4
			System.out.println("\t, 0~1:" + myRand(0,1));
		}
	}
}


public class Exam_03 {
	public static void main(String[] args) {
		byte b= 10;
		char ch = 'A';
		int i = 100;
		long l = 1000L;
		
		
		//���� 5�� ĳ������ �ʿ���µ� �� ����?
		//4~5. (���� �ڵ弭�� �̹� ĳ���� ����)
		
		/*1*/b = (byte)i;
		/*2*/ch = (char)b;
		/*3*/short s = (short)ch;
			
		/*4*/float f = l;
			//�Ǽ����� ���������� ũ��.
		/*5*/i = ch;
			//int�� char�� ������ �����Ѵ�.
	}
}

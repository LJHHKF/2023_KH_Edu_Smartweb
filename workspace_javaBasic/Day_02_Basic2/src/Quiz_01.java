
public class Quiz_01 {
	public static void main(String[] args) {
		byte b = 100;
		short s = 20;
		char c = 'A';
		int i;
		float f;
		long l = 100L;
		
		//�ܼ� �������δ� �������� �κе��� ������ ���� �ʰ� �ٲ����. (ĳ���� �غ���)
		
		s = b; 			//���θ�� short
		c = (char)b; 	//ǥ�� ������ ���Ե��� ������ ���θ�� �Ұ�. ĳ����.
		s = (short)c; 	//''
		c = (char)s;	//''
		i = (int)100L;	//�� ���, ���� ��Ȳ���� ĳ���ú��� L�� ���� ��.
		l = 500; 		//���θ�� int to long
		f = l;			//���θ�� �Ǽ�ȭ
		f = (float)5.11;//double to float. �ڿ� F�ٿ��� ��.
		
		//����2
		b = 10;
		c = (char)b;
		
		System.out.println(c);
			//ASCII �ڵ�ǥ�� 10�� 'Ctrl-J LF'.
				//LF ���� ��� ����, �Ǵ� �̽������� ������. ���⼱ '�ٹٲ�'
			//65(A)�� ��ȯ�غ��� �� ����.
			//char�� �ڽ��� �ǵ��� �°�, ���ڸ� ����Ϸ� ��.
				//�⺻ unicode ����̴ٺ��� ASCII �̻� ���� ��.
		
	}
}

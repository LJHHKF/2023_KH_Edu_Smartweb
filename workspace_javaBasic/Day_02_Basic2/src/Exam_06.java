
public class Exam_06 {
	public static void main(String[] args) throws Exception{
		
		System.out.print("�� ���ڸ� �Է����ּ��� : ");
		
		int input = System.in.read();
		
		//System.out.println("�Է��Ͻ� ���� " + (char)input +" �� �ƽ�Ű�ڵ� 10������ " + input + " �Դϴ�.");		
		
		System.out.println("�Է��� ���ڴ� : " + (char)input);
		
		System.in.read();
		System.in.read();
		
		//1�� �� (�⺻ �ȵ�)
		//why? �Է� ���ۿ� ���� �Է� ���� ����.
		System.out.print("�� ���ڸ� �� �Է����ּ��� : ");
		input = System.in.read();
		System.out.println("�Է��� ���ڴ� :" + (char)input);
		
		//1. System.in.read()�� ���۰� ���������, ������ �ڵ尡 ������ �Է����� ���� �ѱ�� ��ٸ�. 
		//2. ��� �Է� A�� ���ۿ� 'A \r \n'  ���� �����. 
		//3. ���⼭ �� ���� A�� System.in.read()�� �о�鿩 ������. 
		//4. �ι�°�� System.in.read()�� ����Ǹ�, ���۸� ���� ã�µ�, ������ ���ڰ� �̹� ������ �Է����� ��⸦ �ѱ� �͵� ���� �׳� �ٷ� ���� ��.
			//\r�� ������ ��. 
				//���� ���̴� ���̰� �ƴ� ��� ����. ������ (char) ĳ������ ����, ASCII int ���� ����.
		//5. ���ۿ� �̹� \n�� ���������Ƿ� 3��° System.in.read()�� �ҷ��� ���������� �������� �� ��. 
		
	}
}


public class Exam_01 {
	
	//�޼���� ������ return ������ Ÿ���� �Է��ؾ� �Ѵ�.
	//�������� �Ű�����
	//���ο��� ����
	//���ϴ� '�޼��� ���Ǻ�'(definition)
	//�ϴ��� �޼��带 ����� ������ �������, 'public static'�� �ٿ��� ��. 
	public static int plus(int num1, int num2){
		int result = num1 + num2;
		return result;
	}
	
	//�� ������ ���ڷ� ���޹޾� �߻��� ����� ��ȯ�ϴ� minus �޼���
	public static int minus(int num1, int num2) {
		return num1 - num2;
	}
	
	//�� ������ ���ڷ� ���޹޾� �������� ����� �Ǽ��� ��ȯ�ϴ� divide �޼���
	public static double divide(int num1, int num2) {
		return (double)num1 / num2;
	}
	
	//�� ������ ���ڷ� ���޹޾� �� ū ���� ��ȯ�ϴ� bigger �޼���
	//(�� ���� ���ٸ� 0�� ��ȯ)
	public static int bigger(int num1,int num2) {
		if(num1 > num2) {
			return num1;
		}else if(num1 < num2) {
			return num2;
		}else {
			return 0;
		}
	}
	
	//"���"�� ���ڷ� �����ϸ� Apple ��, "����"�� ���ڷ� �����ϸ� Strawberry ��
	//��ȯ�ϴ� translate �޼��� ����
	//(����� ���Ⱑ �ƴ� �ٸ� ���� ���޵Ǹ� none ��ȯ)
	public static String translate(String word) {
		if(word.equals("���")) {
			return "Apple";
		}else if(word.equals("����")) {
			return "Strawberry";
		}else {
			return "none";
		}
			
	}
	
	public static void main(String[] args) {
		System.out.println(plus(10,5)); //�޼��� ȣ���
		int result = minus(10,5);
		System.out.println(result);
		System.out.println(divide(10,5));
		System.out.println(bigger(10,5));
		System.out.println(translate("���"));
		System.out.println(translate("����"));
		System.out.println(translate("����"));
		
		//plus method�� �����ϴ� 10�� 5�� ���ڰ�(�Ǵ� �μ���, �Ű�����) �̶�� �θ���.
		//����δ� parameter, argument
		//�޼��忡 ���޵Ǿ����� ���� �޴� ������ �Ű�������� �θ���. (���� �̸��� ����)
		//�޼����� ��ȯ ���� �޼��尡 call �� �������� return �ȴ�.
	}
	
}

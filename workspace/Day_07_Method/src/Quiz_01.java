
public class Quiz_01 {
	
	public static int countA(String str, char ch) {
		int result = 0;
		char ch2 = 0;
		if(Character.isUpperCase(ch)) {
			ch2 = Character.toLowerCase(ch);
		}else {
			ch2 = Character.toUpperCase(ch);
		}
		
		String[] arr = str.split(Character.toString(ch));
		String[] arr2 = str.split(Character.toString(ch2));
		
		//������ ���ڰ� �������̸� �ϳ� ���Ծ����Ƿ�, ���� ���.
		if(str.endsWith(Character.toString(ch)) || str.endsWith(Character.toString(ch2))) { 
			result += 1;
		}
		
		//���ڿ� ��ΰ� �����ڷθ� �����Ǽ� ������ ���ŵ� ���.
		//���ڿ��� ��� �ش��Ѵٴ°Ŵ� ���̸� ��ȯ�ϸ� ��.
		if(arr.length == 0 || arr2.length == 0) { 
			return str.length();
		}
		else { //������ ���
			//��� ��¥�� �ϳ� ����.
			//result�� ���� üũ�ϴ� ������ �Ǹ����� ���� ���� ��� ���ؼ�.
			result += (arr.length - 1) + (arr2.length - 1); 
			return result; 
		}
	}
	
	public static int countB(String str, char ch) {
		int count = 0;
		char ch2 = 0;
		if(Character.isUpperCase(ch)) {
			ch2 = Character.toLowerCase(ch);
		}else {
			ch2 = Character.toUpperCase(ch);
		}
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == ch || str.charAt(i) == ch2) {
				count++;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		
		//Ư�����ڳ� ���� �� ����, ��� ���� �ĺ� ��.
		String str = "awerlnqpwruadlnfmcznvihriatjklarklnghfaklhiahuihjkqbrjkhakdhbvkjhuzxhueraadfbvzxgcqkr";
		
		String test = "abacadaeaf"; //5
		String test2 = "sdfghjl";
		String test3 = "aaa";
		String test4 = "abacada";
		String test5 = "AAA";
		
		int count = countA(str, 'a');
		System.out.println("���ĺ� A�� ���� : " + count);
		
	}
}

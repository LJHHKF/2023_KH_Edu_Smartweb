
public class Exam_02 {
	public static void main(String[] args) {
		String str = "Hello World";//"asdfnlkewqrnqlkjadisfakaejrlkjaklfnadflka";
		String str2 = "1,2,3,4,5,6,7,8,9,10";
		
//		int length = str.length();
//		System.out.println(length);
		
//		char n = str.charAt(3);
//		System.out.println(n);
		
//		int index = str.indexOf('Z');
//		System.out.println(index);
		
//		System.out.println(str.startsWith("Hell"));
//		System.out.println(str.endsWith("rld"));
		
//		str.equals("AAA");
//		System.out.println(str.contains("World"));
		
		//regex (����ǥ����, regular expression�� ���)
		String[] words = str.split(" ");//str2.split(",");
			//�����ڴ� ������ ���ҷ� �Ҹ�ǰ� �����. (l�� �����ڷ��ϸ� He o Wor d ��)
		for(int i = 0; i < words.length; i++)
		{
			System.out.println(words[i]);
		}
		
	}
}

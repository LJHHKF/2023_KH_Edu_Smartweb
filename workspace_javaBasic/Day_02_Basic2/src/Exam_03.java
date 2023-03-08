
public class Exam_03 {
	public static void main(String[] args) {
		byte b= 10;
		char ch = 'A';
		int i = 100;
		long l = 1000L;
		
		
		//이하 5중 캐스팅이 필요없는데 한 것은?
		//4~5. (이하 코드서는 이미 캐스팅 벗김)
		
		/*1*/b = (byte)i;
		/*2*/ch = (char)b;
		/*3*/short s = (short)ch;
			
		/*4*/float f = l;
			//실수형은 정수형보다 크다.
		/*5*/i = ch;
			//int는 char의 범위를 포함한다.
	}
}

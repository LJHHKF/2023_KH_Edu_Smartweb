
public class Quiz_01 {
	public static void main(String[] args) {
		byte b = 100;
		short s = 20;
		char c = 'A';
		int i;
		float f;
		long l = 100L;
		
		//단순 대입으로는 오류나는 부분들을 오류가 나지 않게 바꿔봐라. (캐스팅 해봐라)
		
		s = b; 			//프로모션 short
		c = (char)b; 	//표현 범위가 포함되지 않으니 프로모션 불가. 캐스팅.
		s = (short)c; 	//''
		c = (char)s;	//''
		i = (int)100L;	//이 경우, 실제 상황서는 캐스팅보다 L을 지울 것.
		l = 500; 		//프로모션 int to long
		f = l;			//프로모션 실수화
		f = (float)5.11;//double to float. 뒤에 F붙여도 됨.
		
		//문제2
		b = 10;
		c = (char)b;
		
		System.out.println(c);
			//ASCII 코드표서 10은 'Ctrl-J LF'.
				//LF 등은 펑션 문자, 또는 이스케이프 시퀀스. 여기선 '줄바꿈'
			//65(A)로 변환해보면 잘 나옴.
			//char은 자신의 의도에 맞게, 문자를 출력하려 함.
				//기본 unicode 방식이다보니 ASCII 이상 값도 들어감.
		
	}
}

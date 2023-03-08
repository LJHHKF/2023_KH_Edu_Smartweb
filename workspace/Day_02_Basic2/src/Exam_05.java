
public class Exam_05 {
	public static void main(String[] args) {
		// [Operator], 연산자
		
		//int a = 10;
		//자료형 변수명 대입연산자(=) 리터럴10
		
		//대입 연산자 규칙 1. 우항이 좌항으로 대입된다.(예외 없음)
		//대입 연산자 규칙 2. 그러므로 좌항은 반드시 공간이어야만 한다(변수)
		
		// A += B
		// A = A+B
		
		// int A = 10, B = 20;
		// 10 = 10 + 20 (X) //10 값에(X)
		// A = 10 + 20 (O) //A라는 공간에(O)
		//좌항은 공간, 우항은 값.
		
		int A = 10, B = 20;
		//A += B;
		//System.out.println("A : " + A);
		//System.out.println("B : " + B);
		
		//A++ // A = A + 1 // A += 1
		
		//A++ // 후치형 증감연산자
		//++A // 전치형 증감연산자
		
		//관계 연산자
		//A == B (A와 B는 같은가?)
		//A != B (A와 B는 다른가?)
		
		System.out.println(A == B);
		System.out.println(A != B);
		System.out.println(A < B);
		System.out.println(A > B);
	}
}

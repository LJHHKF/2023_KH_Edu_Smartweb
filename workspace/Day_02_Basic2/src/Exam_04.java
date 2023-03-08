
public class Exam_04 {
	public static void main(String[] args) {
		int i1 = 10;
		int i2= 20;
		float f1 = 3.14f;
		char c1 = 'A';
		char c2 = 'B';
		
		System.out.println("결과 : " + i1 + i2);
		System.out.println("결과 : " + (i1 + i2));
		System.out.println("결과 : " + i1 + f1);
		System.out.println("결과 : " + (i1 + f1));
		System.out.println("결과 : " + f1 + c1);
		System.out.println("결과 : " + (f1 + c1));
		System.out.println("결과 : " + c1 + c2);
		System.out.println("결과 : " + (c1 + c2));
		
		/* 결과 예상
		결과 : 1020;  (ㅇ)
		결과 : 30     (ㅇ)
		결과 : 103.14	(ㅇ)
		결과 : 13.14	(ㅇ)
		결과 : 3.14A	(ㅇ)
		결과 : 68.14	(ㅇ)
		결과 : AB		(ㅇ)
		결과 : 131	(ㅇ)
		
		"결과 : " + 란 점 때문에,
		()가 없으면 다 그냥 String +(Append) 로 볼 것.
		 */
	}
}

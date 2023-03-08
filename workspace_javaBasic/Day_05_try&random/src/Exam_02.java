
public class Exam_02 {
	public static void main(String[] args) {
		//난수 예제
		double rand = Math.random(); // 0~1.0 사이(1.0 미만)의 실수(double)를 무작위로 생성.
		System.out.println(rand);
		
		
		int result = (int)(rand * 10); // 난수 0~9까지의 값을 출력하는 코드.
		System.out.println(result);
		
		result = (int)(rand * (6)) + 1; // 0~5 + 1 // 주사위 값.
		System.out.println(result);
		
		
		//Math.random() * (최대 - 최소 + 1) + 최소 
		//난수 범위 공식
		//구하고자 하는 난수의 최소값을 X
		//구하고자 하는 난수의 최대값을 Y
		//(Y-X+1)+X
		result = (int)(rand * (52 - 37 + 1) + 37); 
		System.out.println(result);
	}
}

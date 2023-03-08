
public class Exam_01 {
	public static void main(String[] args) {
		//예외 처리 구문.
		try {
			int num = Integer.parseInt("110");
			System.out.println("분석된 숫자는 : " + num);
		} catch(Exception e) {
			System.out.println("분석하는 데이터가 숫자의 형태가 아닙니다.");
		}
		
		System.out.println("프로그램 정상 종료");
	}
}

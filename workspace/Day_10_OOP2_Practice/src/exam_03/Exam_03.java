package exam_03;

public class Exam_03 {
	public static void main(String[] args) {
		//Contact 클래스 설계 (명함?)
		// 이름 / 전화번호 / 이메일
		//getter / setter / constructor / 정보은닉
		
		// 홍길동 / 01012344321 / hong@naver.com
		// 조성태 강사님 / 01090062139 / stj@gmail.com
		
		// 저장 된 값 출력해보기
		
		Contact contact1 = new Contact("홍길동", "01012344321", "hong@naver.com");
		Contact contact2 = new Contact("조성태 강사님", "01090062139", "stj@gmail.com");
		
		contact1.myPrint();
		contact2.myPrint();
	}
}

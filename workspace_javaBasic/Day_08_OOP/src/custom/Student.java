package custom;

public class Student {
	private String name;
	private int kor, eng, math;
	
	// Constructor - 생성자
	// 목적 - 만들어진 인스턴스에 초기값을 세팅하게 하기 위한 목적으로 사용되는 메서드
	// 생성자 메서드의 이름은 클래스의 이름과 동일해야 한다.
	// 생성자 메서드는 여타 다른 메서드들과 다르게 return 값을 가질 수 없다.
	// 생성자 메서드는 다른 메서드들과 다르게 method call 타이밍을 지정할 수 없다.
	// 그 외 기타 특성은 일반 메서드와 동일하다.
	
//	Student(){
//	}
	//Default Constructor : 개발자가 생성자를 명시하지 않았을 경우, 내장되어 숨겨져 있는 생성자.
	//Default Constructor는 개발자의 명시적 생성자에 의해 지워진다.
	
	public Student(String name, int kor, int eng, int math) {
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	
	public int getTotal() {
		return kor + eng + math;
	}//합계 반환
	public double getAverage() {
		return this.getTotal() / 3.0;
	}//평균 반환
}

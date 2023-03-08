package custom;

public class Student {
	private String name;
	private int kor, eng, math;
	
	// Constructor - ������
	// ���� - ������� �ν��Ͻ��� �ʱⰪ�� �����ϰ� �ϱ� ���� �������� ���Ǵ� �޼���
	// ������ �޼����� �̸��� Ŭ������ �̸��� �����ؾ� �Ѵ�.
	// ������ �޼���� ��Ÿ �ٸ� �޼����� �ٸ��� return ���� ���� �� ����.
	// ������ �޼���� �ٸ� �޼����� �ٸ��� method call Ÿ�̹��� ������ �� ����.
	// �� �� ��Ÿ Ư���� �Ϲ� �޼���� �����ϴ�.
	
//	Student(){
//	}
	//Default Constructor : �����ڰ� �����ڸ� ������� �ʾ��� ���, ����Ǿ� ������ �ִ� ������.
	//Default Constructor�� �������� ����� �����ڿ� ���� ��������.
	
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
	}//�հ� ��ȯ
	public double getAverage() {
		return this.getTotal() / 3.0;
	}//��� ��ȯ
}

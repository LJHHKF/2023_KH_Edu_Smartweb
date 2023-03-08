package exams;
import custom.Student;

public class Exam_03 {
	public static void main(String[] args) {
		Student s1 = new Student("Jack", 95, 84, 68);
		
//		s1.setName("Jack");
//		s1.setKor(95);
//		s1.setEng(84);
//		s1.setMath(68);
		
		System.out.print(s1.getName() + " / ");
		System.out.print(s1.getKor() + " / ");
		System.out.print(s1.getEng() + " / ");
		System.out.print(s1.getMath() + " / ");
		System.out.print(s1.getTotal() + " / ");
		System.out.println(s1.getAverage());
		
		Student s2 = new Student("Jane", 100, 50, 95);
		
//		s2.setName("Jane");
//		s2.setKor(100);
//		s2.setEng(50);
//		s2.setMath(95);
		
		System.out.print(s2.getName() + " / ");
		System.out.print(s2.getKor() + " / ");
		System.out.print(s2.getEng() + " / ");
		System.out.print(s2.getMath() + " / ");
		System.out.print(s2.getTotal() + " / ");
		System.out.println(s2.getAverage());
		
	}
}

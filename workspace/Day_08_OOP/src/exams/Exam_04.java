package exams;

import custom.Temp;

public class Exam_04 {
	public static void main(String[] args) {
		Temp t1 = new Temp();
		Temp t2 = new Temp();
		t1.a = 10;
		t2.a = 20;
		
		System.out.println(t1.a);
		System.out.println(t2.a);
		
		Temp.b = 30;
		System.out.println(t2.b);
		
		t1.func1();
		//Scanner sc = new Scanner(System.in);
		//sc.nextLine()
		
		Temp.func2();
		//Math.random();
		//Integer.parseInt(null);
	}

}

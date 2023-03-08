package quiz;

import custom.Calc;

public class Quiz_01 {
	public static void main(String[] args) {
		Calc c = new Calc();
		
		System.out.println(Calc.plus(10,20));
		System.out.println(c.minus(20,10));
		System.out.println(Calc.mpy(5,4));
		System.out.println(c.divide(10,2));
	}

}

package exams;
import java.awt.Robot;
import java.util.Scanner;

import custom.Car;
import custom.TV;

public class Exam_01 {
	public static void main(String[] args) throws Exception {
		
		
		//Scanner sc = new Scanner(System.in);
		
		//Robot r = new Robot(); //일종의 장난감 클래스
			//매크로 사용 가능.
		//r.mouseMove(100, 100);
		
//		for(int i = 0; i < 100;i++) {
//			//r.mouseMove(400+i, 300);
//			int x = (int)(Math.random() * (500-200+1)+200);
//			int y = (int)(Math.random() * (500-200+1)+200);
//			r.mouseMove(x, y);
//			r.delay(100);
//		}
		
		TV samsung = new TV();
		
		samsung.setChannel(10);
		samsung.setBrand("Saumsung");
		samsung.setPrice(1000000);
		System.out.println(samsung.getBrand());
		System.out.println(samsung.getPrice());
		System.out.println(samsung.getChannel());
		
		System.out.println();
		
		Car hyundai = new Car();
		hyundai.setModel("Genesis GV80");
		hyundai.setColor("Gray");
		hyundai.setPrice(50000000);
		System.out.println(hyundai.getModel());
		System.out.println(hyundai.getColor());
		System.out.println(hyundai.getPrice());
		
		//체이닝 기법.
		//.getThis().somethingMethod()
		//hyundai.getThis().getThis().getThis();
		
	}
}

package exams;

abstract class Animal{
	abstract public void sound();
}
class Dog extends Animal{
	public void sound() {
		System.out.println("멍멍");
	}
}
class Cat extends Animal{
	public void sound() {
		System.out.println("야옹");
	}
}
class Crow extends Animal{
	public void sound() {
		System.out.println("까악");
	}
}

public class Exam_01 {
	public static void main(String[] args) {
		//코드 결합도를 낮추기 위한 기법 중 하나
		//다형성
		//난이도 높고, 사용빈도 높고, 당장 사용해야 할 놈.
		//클래스 간 상속관계에서 부모클래스 자료형으로 만든 참조변수에게 발현되는 성질.
		//상위 클래스 참조 변수는 하위클래스 인스턴스의 주소를 저장할 수 있다.

		//		A a = new B();
		//		a.funcA();
		//		((B)a).funcB();

		//다형성을 가지는 부모클래스 참조변수는 기본적으로 자신의 Type만큼의 참조만 가능 
		//만약 자신의 Type이 아닌 대입된 인스턴스의 모든 기능을 사용하고 싶다면 Down casting 필요 
		// * 예외 상황 : 자식 클래스에서 Overriding한 메서드는 Down casting 없이 사용 가능 

//		Animal cat = new Cat();
//		Animal dog = new Dog();
//		Animal crow = new Crow();
//		
//		System.out.print("개 : ");
//		dog.sound();
//		System.out.print("고양이 : ");
//		cat.sound();
//		System.out.print("까마귀 : ");
//		crow.sound();
		
		Animal[] animals = new Animal[3];
		
		animals[0] = new Cat();
		animals[1] = new Dog();
		animals[2] = new Crow();
		
		for(int i = 0; i < animals.length;i++) {
			animals[i].sound();
		}

	}
}

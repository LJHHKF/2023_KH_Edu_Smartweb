package exam01;

class Worker extends Thread{
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			System.out.println("두 번째 for : " + i);
		}
	}
}

class Worker2 extends Thread{
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			System.out.println("세 번째 for : " + i);
		}
	}
}

public class Main {	
	public static void main(String[] args) {
		
		//Thread 적용법
		//Step1. Thread 클래스를 상속받는 사용자 정의 클래스를 작성한다.
		//Step2. Thread 클래스로부터 상속받는 public void run 메서드를 overriding 한다.
		//Step3. 병렬처리하고자 하는 소스코드를 run 내부에 구현한다.
		//Step4. 제작 완료된 클래스를 인스턴스화 한다.
		//Step5. 만들어진 인스턴스로부터 start() 메서드를 call한다.
		
		Worker w = new Worker();
		Worker2 w2 = new Worker2();
		w.start();
		w2.start();
		
		for(int i = 1; i <= 10; i++) {
			System.out.println("첫 번째 for : " + i);
		}
	}
}

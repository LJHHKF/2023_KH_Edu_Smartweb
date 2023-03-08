package quiz01;

class PlusThread extends Thread {
	@Override
	public void run() {
		for(int i = 0; i < 100; i++) {
			System.out.print("+");
		}
	}
}

class MinusThread extends Thread {
	@Override
	public void run() {
		for(int i = 0; i < 100; i++) {
			System.out.print("-");
		}
	}
}


class MPlyThread extends Thread {
	@Override
	public void run() {
		for(int i = 0; i < 100; i++) {
			System.out.print("*");
		}
	}
}


public class Main {
	public static void main(String[] args) {
		// + 를 출력하는 PlusThread
		// - 를 출력하는 MinusThread
		// * 를 출력하는 MPlyThread
		// 를 각각 생성하여 100개씩 기호를 동시에 출력하게 만들어 봅시다.
		// 출력은 가로 방향으로. ex) ------****+++***++
		
		PlusThread instance1 = new PlusThread();
		MinusThread instance2 = new MinusThread();
		MPlyThread instance3 = new MPlyThread();
		
		instance1.start();
		instance2.start();
		instance3.start();
		
	}
}

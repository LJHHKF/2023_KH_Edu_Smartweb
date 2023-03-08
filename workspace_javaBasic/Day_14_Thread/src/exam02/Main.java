package exam02;

class TargetPlus extends Thread{
	
	@Override
	public void run() {
		while(true) {
			Main.target++;
		}
	}
}

class TargetMinus extends Thread{
	@Override
	public void run() {
		while(true) {
			Main.target--;
		}
	}
}

public class Main {
	public static int target = 0;
	public static void main(String[] args) {
		
		new TargetPlus().start();
		new TargetMinus().start();
		
		while(true) {
			System.out.println(target);
		}
	}
}

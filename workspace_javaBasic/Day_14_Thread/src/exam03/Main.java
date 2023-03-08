package exam03;

import javax.swing.JOptionPane;

class CountDown extends Thread{
	public static int count = 10;
	public static int score = 0;
	public void run() {
		while(true) {
			if(count <= 0) {
				break;
			}else {
				System.out.println(count);
				count--;
				score++;
				try{Thread.sleep(1000);}
				catch(InterruptedException e) {e.printStackTrace();}
			}
		}
//		for(int i = 10; i > 0; i--) {
//			System.out.println(i);
//			try{Thread.sleep(1000);}
//			catch(InterruptedException e) {e.printStackTrace();}
//		}
		System.out.println("버틴 시간 : " + score);
		System.out.println("Game Over");
		System.exit(0);
	}
}

public class Main {
	public static void main(String[] args) throws Exception {
		
		//1문장짜리 타이핑 연습 게임
		//String quiz = "public static void main(String[] ar)";
		//여러 문장형 게임으로 확장
		
		String[] quiz = new String[] {
				"public static void main(String[] args)",
				"Scanner sc = new Scanner(System.in)",
				"DataOuputStream dos = new DataOutputStream(os)"
		};
		
		new CountDown().start(); // 카운트다운 시작
		
		while(true) {
			int index = (int)(Math.random() * 3);
			
			String msg = JOptionPane.showInputDialog(quiz[index]);
			if(msg.equals(quiz[index])) {
				System.out.println("Success");
				CountDown.count += 5;
			}else {
				System.out.println("Typo");
				CountDown.count -= 3;
			}
		}
	}
}

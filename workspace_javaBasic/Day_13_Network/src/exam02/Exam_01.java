package exam02;

import javax.swing.JOptionPane;

public class Exam_01 {
	public static void main(String[] args) {
		String msg = JOptionPane.showInputDialog("클라이언트에게 보낼 메시지");
		JOptionPane.showMessageDialog(null, msg);
		
		//System.out.println(msg);
		
	}
}

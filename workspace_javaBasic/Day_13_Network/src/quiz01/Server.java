package quiz01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	public static void main (String[] args) throws Exception{
		ServerSocket server = new ServerSocket(16500);
		System.out.println("서버가 가동 되었습니다.");
		Socket socket = server.accept();
		System.out.println(socket.getInetAddress() + " 로 부터 연결");
		
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("연산자 입력 대기 중입니다.");
			String op = dis.readUTF();
			System.out.println("입력된 연산자는 : " + op + " 입니다.");
			System.out.println("첫번째 숫자 입력 대기 중입니다.");
			int num1 = dis.readInt();
			System.out.println("첫번째 숫자는 : " + num1 + " 입니다.");
			System.out.println("두번째 숫자 입력 대기 중입니다.");
			int num2 = dis.readInt();
			System.out.println("두번째 숫자는 : " + num2 + " 입니다.");
			
			int result = 0;
			String result_msg = null;
			if(op.equals("+")) {
				result = num1 + num2;
				//묵시적 String 프로모션 진행.
				result_msg = num1 + " + " + num2 + " = " + result + " 입니다."; 
			}else if(op.equals("-")) {
				result = num1 - num2;
				result_msg = num1 + " - " + num2 + " = " + result + " 입니다.";
			}else if(op.equals("*")) {
				result = num1 * num2;
				result_msg = num1 + " * " + num2 + " = " + result + " 입니다.";
			}else {
				result_msg = "적절치 않은 연산자 오류입니다.";
				System.out.println("적절치 않은 연산자 오류입니다.");
			}
			
			dos.writeUTF(result_msg);
			dos.flush();
			System.out.println("연산 결과를 반환했습니다.");
		}
		
	}
}

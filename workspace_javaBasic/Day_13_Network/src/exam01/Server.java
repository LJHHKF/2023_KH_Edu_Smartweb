package exam01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

class ReadThread extends Thread{
	
	public void run(){
		while(true) {
			try {
				String msg = Server.dis.readUTF();
				//JOptionPane.showMessageDialog(null, msg);
				System.out.println("클라이언트가 보낸 메세지 : " + msg);
			}catch(Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}
}

public class Server {
	//원래 DataOutputStream의 static화는 위험하지만 테스트 용도.
	public static DataInputStream dis;
	//원래 전가하면 안되는데 최대한 간결하게 만들기 위해서 일단 전가.
	public static void main(String[] args) throws Exception{
		ServerSocket server = new ServerSocket(16500);
		
		// 허가하다. 램카드 주시하기 시작함. 내부에 while(true)가 걸려있음.
		Socket socket = server.accept(); 
		
		System.out.println(socket.getInetAddress() + " 로 부터 연결");
		
		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		
		InputStream is = socket.getInputStream();
		dis = new DataInputStream(is);
		
		Scanner sc = new Scanner(System.in);
		
		new ReadThread().start();
		
		while(true) {
			System.out.print("클라이언트에게 보낼 메시지 : ");
			try {
				dos.writeUTF(sc.nextLine());
						//JOptionPane.showInputDialog("클라이언트에게 보낼 메시지"));
				dos.flush(); //데이터 덜 담겼지만 일단 지금 바로 출발시키는 것.
			}catch(Exception e) {
				e.printStackTrace();
				break;
			}

			
		}
	}
}

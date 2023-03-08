package exam01;

import java.io.DataInputStream;
import java.net.Socket;

class ClientThread extends Thread{
	private Socket socket;
	
	public ClientThread(Socket socket) {
		this.socket = socket;
	}
	
	
	@Override
	public void run() {
		while(true) {
			try {
				System.out.println(socket.getInetAddress() + " 로 부터 연결");
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				String msg = dis.readUTF();
				System.out.println(socket.getInetAddress() + " : " + msg);
			}catch(Exception e) {
				System.out.println(socket.getInetAddress() + " 접속 해제");
				break;
			}
		}
	}
}

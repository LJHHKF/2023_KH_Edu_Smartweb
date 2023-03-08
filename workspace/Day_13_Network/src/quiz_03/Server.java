package quiz_03;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws Exception{
		ServerSocket server = new ServerSocket(16500);
		System.out.println("서버가 가동 되었습니다.");
//		Socket socket = server.accept();
//		System.out.println(socket.getInetAddress() + " 로 부터 연결");
		
//		InputStream is = socket.getInputStream();
//		DataInputStream dis = new DataInputStream(is);
//		OutputStream os = socket.getOutputStream();
//		DataOutputStream dos = new DataOutputStream(os);
		//Scanner sc = new Scanner(System.in);
		
		Manager manager = new Manager();
		
		manager.addContact(new Contact(1050, "홍길동", "000000000"));
		manager.addContact(new Contact(1051, "이순신", "100000000"));
		
		while(true) {
			Socket sock = null;
			try {
				sock = server.accept();
				ClientThread ct = new ClientThread(sock, manager);
				ct.start();
			}catch(Exception e) {
				System.out.println(sock.getInetAddress() + " 접속 해제");
			}
		}
	}
}

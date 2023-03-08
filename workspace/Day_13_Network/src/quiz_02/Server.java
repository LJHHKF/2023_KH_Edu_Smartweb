package quiz_02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//1. 클라이언트는 서버에 접속한다.
//2. 클라이언트는 사람의 고유 ID(임의. 1001~1100)를 입력하여 서버에 전송한다.
//3. 클라이언트는 사람의 이름을 입력하여 서버에 전송한다.
//4. 클라이언트는 사람의 연락처(전화번호)를 입력하여 서버에 전송한다.
//5. 서버는 클라이언트로부터 전달된 3가지 데이터를 Contact Instance로 만든다.
//   Contact -> id, name, phone 3가지에 멤버필드에 대하여
//		getter/setter/constructor/default constructor
//		[정보은닉저용]
//6. 서버는 만들어진 인스턴스를 데이터 관리 전용 인스턴스인 Manager에게 전달하여
//		Contact를 저장하는 ArrayList에 보관한다.
//      (저장 한 후, ArrayList에 현재 몇개의 데이터가 있는지 Server Console에 출력하세요.)
//7. 보관을 완료한 후, 서버는 클라이언트에게 저장 완료의 의미인 ack를 반환한다.
//8. 클라이언트는 ack가 반환되면, 사용자에게 '저장 완료' 메시지를 출력한다.
//9. 2~8번부터의 과정은 무한히 반복한다.
public class Server {
	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(16500);
		System.out.println("서버가 가동 되었습니다.");
		Socket socket = server.accept();
		System.out.println(socket.getInetAddress() + " 로 부터 연결");
		
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		Scanner sc = new Scanner(System.in);
		
		Manager manager = new Manager();
		
		TOPLOOP : while(true) {
			System.out.println("ID 입력 대기 중입니다.");
			int id = dis.readInt();
			System.out.println("입력된 ID는 " + id + " 입니다");
			
			System.out.println("이름 입력 대기 중입니다.");
			String name = dis.readUTF();
			System.out.println("입력된 이름은 " + name + " 입니다");
			
			System.out.println("전화번호 입력 대기 중입니다.");
			String phone = dis.readUTF();
			System.out.println("입력된 전화번호는 " + phone + " 입니다");
			
			for(int i = 0; i < manager.getContactList().size(); i++) {
				if(manager.getContactList().get(i).getId() == id) {
					System.out.println("이미 해당 아이디 값에 값이 존재합니다.");
					dos.writeBoolean(false);
					dos.flush();
					continue TOPLOOP;
				}
			}
			
			manager.addContact(new Contact(id, name, phone));
			System.out.println("현재 저장되어 있는 연락처 수는 "
								+ manager.getContactList().size() +
								"개 입니다.");
			
			System.out.println("정상 동작 결과를 반환합니다.");
			dos.writeBoolean(true);
			dos.flush();
			
			//printContactList(manager);
		}
	}
	
	private static void printContactList(Manager manager) {
		System.out.println("현재 저장된 연락처 목록을 출력합니다.");
		System.out.println("ID\t이름\t전화번호");
		for(Contact c : manager.getContactList()) {
			System.out.println(c.getId() + "\t" +
								c.getName() + "\t" +
								c.getPhone());	
		}
	}
}

package quiz_02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws Exception {
		Socket client = new Socket("192.168.50.44", 53120);
		
		InputStream is = client.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		OutputStream os = client.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.print("ID를 입력해주세요.(1001~1100) : ");
			dos.writeInt(Integer.parseInt(sc.nextLine()));
			dos.flush();
			System.out.print("이름을 입력해주세요. : ");
			dos.writeUTF(sc.nextLine());
			dos.flush();
			System.out.print("전화번호를 입력해주세요. : ");
			dos.writeUTF(sc.nextLine());
			dos.flush();			
			
			if(dis.readBoolean()) {
				System.out.println("저장 완료");
			}else {
				System.out.println("저장 실패");
			}
		}
		
	}
}

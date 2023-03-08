package exam01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

class SendThread extends Thread{
	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			//System.out.println("서버에게 보낼 메시지 : ");
			try {
				Client.dos.writeUTF(sc.nextLine());
				//JOptionPane.showInputDialog("서버에게 보낼 메시지"));
				Client.dos.flush();
			}catch(Exception e) {
				e.printStackTrace();
				break;
			}

		}
	}
}

public class Client{
	public static DataOutputStream dos;
	public static void main(String[] args) throws Exception {
		//여긴 그냥 따라 써보는 것. 지금은 서버 역할. 돌리지 않음.
		Socket client = new Socket("192.168.50.39", 16500); // 이 자리 IP

		InputStream is = client.getInputStream();
		DataInputStream dis = new DataInputStream(is);

		OutputStream os = client.getOutputStream();
		dos = new DataOutputStream(os);

		//Scanner sc = new Scanner(System.in);
		
		new SendThread().start();

		while(true) {
			String msg = dis.readUTF();
			System.out.println("서버가 보낸 메세지 : " + msg);
			//JOptionPane.showMessageDialog(null, msg);


		}
	}

}

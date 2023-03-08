package exam02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		Socket client = null;
		try {
			client = new Socket("192.168.50.44", 15000);
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		
		try {
			//DataOutputStream dos = new DataOutputStream(client.getOutputStream());
			DataInputStream s_dis = new DataInputStream(client.getInputStream());
			
			//Scanner sc = new Scanner(System.in);D:\2023_01_스마트웹\download
			
//			while(true) {
//				dos.writeUTF(sc.nextLine());
//			}
			
			
			int length = s_dis.readInt();
			byte[] myFile = new byte[length];
			s_dis.readFully(myFile);
			
			File dst = new File("D:\\2023_01_스마트웹\\download\\mySong.mp3");
			FileOutputStream fos = new FileOutputStream(dst);
			DataOutputStream f_dos = new DataOutputStream(fos);
			
			f_dos.write(myFile);
			f_dos.flush();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

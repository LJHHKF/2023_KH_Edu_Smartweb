package exam03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		Socket client = null;
		try {
			client = new Socket("192.168.50.44", 15000);
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		Scanner sc = new Scanner(System.in);
		
		try {
			DataInputStream dis = new DataInputStream(client.getInputStream());
			DataOutputStream dos = new DataOutputStream(client.getOutputStream());
			int length = dis.readInt();
			
			System.out.println("=======파일 목록=======");
			for(int i = 0; i < length; i++) {
				System.out.println(dis.readUTF());
			}
			
			System.out.print("다운로드를 희망하는 파일 이름(확장자 포함) : ");
			String fileName = sc.nextLine();
			dos.writeUTF(fileName);
			
			length = dis.readInt();
			byte[] fileContents = new byte[length];
			dis.readFully(fileContents);
			
			DataOutputStream fileDos = new DataOutputStream(new FileOutputStream(
					"D:\\2023_01_스마트웹\\download\\"+fileName));
			fileDos.write(fileContents);
			fileDos.flush();
			fileDos.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		
	}
}

package quiz_03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThread extends Thread{
	private Socket sock;
	private Manager manager;
	DataInputStream dis;
	DataOutputStream dos;
	
	public ClientThread(Socket sock, Manager manager) {
		this.sock = sock;
		this.manager = manager;
		try {
			this.dis = new DataInputStream(sock.getInputStream());
			this.dos = new DataOutputStream(sock.getOutputStream());
		}catch(Exception e) {
			System.out.println(sock.getInetAddress() + " 접속 해제");
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				int menu = dis.readInt();
				if(menu == 0) {
					System.exit(0);
				}else if(menu == 1) {
					int id = dis.readInt();
					System.out.println("id : " + id);
					String name = dis.readUTF();
					System.out.println("name : " + name);
					String phone = dis.readUTF();
					System.out.println("phone : " + phone);
					
					manager.addContact(new Contact(id, name, phone));
					
					//결과값 반환
					dos.writeBoolean(true);
					dos.flush();
				}else if(menu == 2) {
					dos.writeInt(manager.getContacts().size());
					dos.flush();
					System.out.println(manager.getContacts().size());
					for(Contact c : manager.getContacts()) {
						dos.writeInt(c.getId());
						dos.writeUTF(c.getName());
						dos.writeUTF(c.getPhone());
						dos.flush();
					}
				}else if(menu == 3) {
					String keyword = dis.readUTF();
					
					ArrayList<Contact> tempList = manager.searchMultiContainsToName(keyword);
					dos.writeInt(tempList.size());
					dos.flush();
					for(Contact c : tempList) {
						dos.writeInt(c.getId());
						dos.writeUTF(c.getName());
						dos.writeUTF(c.getPhone());
						dos.flush();
					}
				}else if(menu == 4) {
					int index = manager.searchIndexToID(dis.readInt());
					if(index >= 0) {
						manager.deleteContact(index);
						dos.writeBoolean(true);
						dos.flush();
					}else {
						dos.writeBoolean(false);
						dos.flush();
					}
				}
			}
		}catch(Exception e) {
			System.out.println(sock.getInetAddress() + " 접속 해제");
		}		
	}

}

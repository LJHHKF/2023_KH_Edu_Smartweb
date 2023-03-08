package quiz_03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
	public static void main (String[] args) throws Exception{
		Socket client = new Socket("192.168.50.44", 53120);

		InputStream is = client.getInputStream();
		OutputStream os = client.getOutputStream();
		DataInputStream dis = new DataInputStream(is);
		DataOutputStream dos = new DataOutputStream(os);
		Scanner sc = new Scanner(System.in);

		while(true) {
			System.out.println("<< 연락처 관리 프로그램 >>");
			System.out.println("1. 연락처 신규 등록");
			System.out.println("2. 연락처 목록 확인");
			System.out.println("3. 검색 ( 이름으로 검색 )");
			System.out.println("4. 연락처 삭제 ( ID로 삭제 )");
			System.out.println("0. 프로그램 종료");
			System.out.print(">> ");
			int menu = 0;
			while(true) {
				try {
					menu = Integer.parseInt(sc.nextLine());
					break;
				}catch(Exception e) {
					System.out.println("숫자 형식으로 입력해주셔야 합니다.");
					e.printStackTrace();
				}
			}
			//메뉴 값 발신
			dos.writeInt(menu);
			dos.flush();
			if(menu == 0) {
				System.exit(0);
			}else if(menu == 1) {
				int id = 0;
				while(true) {
					try {
						System.out.print("ID 입력(1001~1100) : ");
						id = Integer.parseInt(sc.nextLine());
						break;
					}catch(Exception e) {
						System.out.println("숫자 형식으로 입력해주셔야 합니다.");
						e.printStackTrace();
					}
				}

				String name = null, phone = null;
				while(true) {
					try {
						System.out.print("이름 입력 : ");
						name = sc.nextLine();
						break;
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				while(true) {
					try {
						System.out.print("전화번호 입력 : ");
						phone = sc.nextLine();
						break;
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				dos.writeInt(id);
				dos.writeUTF(name);
				dos.writeUTF(phone);
				dos.flush();

				if(dis.readBoolean()) {
					System.out.println("저장 성공");
				}else {
					System.out.println("저장 실패");
				}
			}else if(menu == 2) {

				//사이즈 값 수신 대기
				int size = dis.readInt();
				if(size > 0)
				{
					ArrayList<Contact> tempList = new ArrayList<>();

					for(int i = 0; i < size; i++) {
						int id = dis.readInt();
						String name = dis.readUTF();
						String phone = dis.readUTF();
						tempList.add(new Contact(id, name, phone));
					}
					System.out.println("ID\t이름\t전화번호");
					for(Contact c : tempList) {
						System.out.println(c.getId() + "\t" +
								c.getName() + "\t" +
								c.getPhone());
					}
				}else {
					System.out.println("조회할 정보가 없습니다.");
				}
			}else if(menu == 3) {

				String keyword = null;
				while(true) {
					try {
						System.out.print("검색할 키워드 입력 : ");
						keyword = sc.nextLine();
						break;
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				dos.writeUTF(keyword);
				dos.flush();

				int size = dis.readInt();
				if(size > 0) {
					ArrayList<Contact> tempList = new ArrayList<>();
					for(int i = 0; i < size; i++) {
						int id = dis.readInt();
						String name = dis.readUTF();
						String phone = dis.readUTF();
						tempList.add(new Contact(id, name, phone));
					}
					System.out.println("ID\t이름\t전화번호");
					for(Contact c : tempList) {
						System.out.println(c.getId() + "\t" +
								c.getName() + "\t" +
								c.getPhone());
					}
				}else {
					System.out.println("찾은 정보가 없습니다.");
				}
			}else if(menu == 4) {

				int id = 0;
				while(true) {
					try {
						System.out.print("삭제하려는 ID를 입력 : ");
						id = Integer.parseInt(sc.nextLine());
						break;
					}catch(Exception e) {
						System.out.println("숫자 형식으로 입력해주셔야 합니다.");
						e.printStackTrace();
					}
				}
				dos.writeInt(id);
				dos.flush();

				if(dis.readBoolean()) {
					System.out.println("삭제에 성공했습니다.");
				}else {
					System.out.println("삭제에 실패했습니다.");
				}
			}else {
				System.out.println("없는 메뉴 번호입니다. 메뉴를 확인하고 다시 입력해 주세요.");
			}
			System.out.println();
		}
	}
}

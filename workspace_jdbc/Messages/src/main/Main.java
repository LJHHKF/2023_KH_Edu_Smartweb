package main;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import commons.EncryptionUtils;
import dao.MembersDAO;
import dao.MessagesDAO;
import dto.MembersDTO;
import dto.MessagesDTO;

public class Main {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			System.out.println("���̺귯�� Ž�� ����");
			e.printStackTrace();
			System.exit(0);
		}
		
		final Main self = new Main();
		final String url = "jdbc:oracle:thin:@localhost:1521:xe";
		final String dbID = "board";
		final String dbPW = "board";
		MembersDAO dao_member = new MembersDAO(url, dbID, dbPW);
		MessagesDAO dao_message = new MessagesDAO(url, dbID, dbPW);
		int menu = 0;
		String curID = null;

		try(Scanner sc = new Scanner(System.in)){
			//�α��� ��Ʈ
			while(true) {
				String infoText = "<< menu >>" + System.lineSeparator()
									+"1. �α���" + System.lineSeparator()
									+"2. ȸ������" + System.lineSeparator()
									+"0. ����" + System.lineSeparator()
									+">>";
				menu = self.inputNumber(sc, infoText);
				if(menu == 0) {
					System.out.println("�ý����� �����մϴ�.");
					System.exit(0);
				}else if(menu == 1) {
					System.out.println("<< �α��� >>");
					String id = self.inputText(sc, "ID�� �Է����ּ��� : ");
					String pw = self.inputText(sc, "PW�� �Է����ּ��� : ");
					pw = EncryptionUtils.sha256(pw);
					if(dao_member.isLogin(id, pw)) {
						//curName = dao_member.getNameById(id);
						curID = id;
						System.out.println(id + "�� ȯ���մϴ�.");
						break;
					}else {
						System.out.println("�α��� ����. ID �Ǵ� PW�� �ٽ� Ȯ�����ּ���.");
					}
				}else if(menu == 2) {
					while(true) {
						System.out.println("<< ȸ�� ���� >>"); //���̵�, �н�����, �̸�, ����ó
						System.out.println("ȸ������ �� ���Ḧ ���ϸ� �������� 0�� �Է����ּ���.");
						String id = self.inputText(sc, "ID�� �Է����ּ��� : ");
						if(id.equals("0")) break;
						if(dao_member.isIdExist(id)) {
							System.out.println("�̹� ������� ���̵��Դϴ�.");
							continue;
						}
						String pw = self.inputText(sc, "PW�� �Է����ּ��� : ");
						if(pw.equals("0"))break;
						pw = EncryptionUtils.sha256(pw);
						String name = self.inputText(sc, "�̸��� �Է����ּ��� : ");
						if(name.equals("0")) break;
						String contact = self.inputText(sc, "����ó�� �Է����ּ��� : ");
						if(contact.equals("0")) break;
						if(0 < dao_member.insert(new MembersDTO(id,pw,name,contact,null))) {
							System.out.println("ȸ������ ����");
						}else {
							System.out.println("ȸ������ ����");
						}
						break;
					}
				}else {
					System.out.println("�޴��� �ٽ� Ȯ�����ּ���.");
				}
			}
			
			//Message ��Ʈ
			while(true) {
				String infoText = "<< �淮 �Խ��� ���α׷� >>" + System.lineSeparator()
									+"1. �ű� �޽��� ���" + System.lineSeparator() //�ۼ���, �޽��� ���� �Է�
									+"2. �޽��� ��� ���" + System.lineSeparator()
									+"3. �޽��� ����" + System.lineSeparator() // seq�� ����
									+"4. �޽��� ����" + System.lineSeparator() //seq�� �����ϵ�, writer �� message �� ��������
																				//(���� & �ɼ� 2)message�� ����. writer �� ���� id
									+"5. �޽��� �˻�" + System.lineSeparator() //writer �Ǵ� message�� �˻�� �����ϴ� ���
									+"6. �� ��������" + System.lineSeparator()
									+"0. ����" + System.lineSeparator()
									+">>";
				menu = self.inputNumber(sc, infoText);
				if(menu == 0) {
					System.out.println("�ý����� �����մϴ�.");
					System.exit(0);
				}else if(menu == 1) { // �ű� �޽��� ��� // �ۼ���, �޽��� �Է�
					String message = self.inputText(sc, "����� �޽��� : ");
					long date = self.inputDate(sc, self);
					if(0 < dao_message.insert(new MessagesDTO(0, curID, message, new Timestamp(date)))) {
						System.out.println("�Է� ����");
					}else {
						System.out.println("�Է� ����");
					}
				}else if(menu == 2) { // �޽��� ��� ���
					self.printMessages(dao_message.selectAll(), dao_member);
				}else if(menu == 3) { // �޽��� ���� // seq
					int seq = self.inputNumber(sc, "������ �Խñ��� ��Ϲ�ȣ : ");
					if(!dao_message.getWriterBySeq(seq).equals(curID)) {
						System.out.println("�ٸ� �ۼ����� �Խñ��Դϴ�. ������ ������ �����ϴ�.");
						continue;
					}
					if(0 < dao_message.deleteBySeq(seq)) {
						System.out.println("������ �����߽��ϴ�.");
					}else {
						System.out.println("������ �����߽��ϴ�.");
					}
				}else if(menu == 4) { // �޽��� ���� // seq�� �����ϵ� ~ // ���Ƿ� message�� ����. ��, ���� �ۼ����� ���.
					int seq = self.inputNumber(sc, "������ �Խñ��� ��Ϲ�ȣ");
					if(!dao_message.getWriterBySeq(seq).equals(curID)) {
						System.out.println("�ٸ� �ۼ����� �Խñ��Դϴ�. ������ ������ �����ϴ�.");
						continue;
					}
					String newMessage = self.inputText(sc, "���� �� �޽��� : ");
					long date = self.inputDate(sc, self);
					if(0 < dao_message.updateBySeq(seq, newMessage, new Timestamp(date))) {
						System.out.println("���� ����");
					}else {
						System.out.println("���� ����");
					}
				}else if(menu == 5) { // �޽��� �˻� // writer �Ǵ� message�� �˻�� ����
					String keyword = self.inputText(sc, "�˻��� Ű���� : ");
					self.printMessages(dao_message.search(keyword), dao_member);
				}else if(menu == 6) { // �� ��������
					System.out.println("<< ���� ���� >>");
					self.printMembers(dao_member.searchById(curID));
					System.out.println("<< �Խñ� ���� >>");
					self.printMessages(dao_message.searchById(curID), dao_member);
				}else {
					System.out.println("�޴��� �ٽ� Ȯ�����ּ���.");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	private int inputNumber(Scanner sc, String infoText) {
		int result = 0;
		while(true) {
			try {
				System.out.print(infoText);
				result = Integer.parseInt(sc.nextLine());
				break;
			}catch(Exception e) {
				System.out.println("���� �������� �Է��� �ּž� �մϴ�.");
				e.printStackTrace();
			}
		}
		return result;
	}
	
	private String inputText(Scanner sc, String infoText) {
		System.out.print(infoText);
		return sc.nextLine();
	}
	
	private void printMessages(ArrayList<MessagesDTO> list, MembersDAO dao) throws Exception {
		if(list.isEmpty()) {
			System.out.println("����� ������ �����ϴ�.");
		}else {
			System.out.println("��Ϲ�ȣ\t�ۼ���\t�޽���\t�ۼ��ð�");
			for(MessagesDTO dto : list) {
				System.out.println(dto.getSeq() + "\t"
									+ dao.getNameById(dto.getWriter())
									+ "\t" + dto.getMessage()
									+ "\t" + dto.getFormedDate());
			}
		}
	}
	
	private void printMembers(ArrayList<MembersDTO> list) throws Exception{
		if(list.isEmpty()) {
			System.out.println("����� ������ �����ϴ�.");
		}else {
			System.out.println("ID\t�̸�\t����ó\t���Գ�¥"); //pw�� �Ϻη� �M.
			for(MembersDTO dto : list) {
				System.out.println(dto.getId() + "\t"
									+ dto.getName() + "\t"
									+ dto.getContact() + "\t"
									+ dto.getFormedDate());
			}
		}
	}
	
	private long inputDate(Scanner sc, Main self) throws Exception{
		System.out.println("��¥ �Է��� �����ϰڽ��ϴ�.");
		System.out.println("��¥ �Է��� �ߴ��Ϸ��� -1�� �Է��ϼ���. ��� ���� �ð��� �Էµ˴ϴ�.");
		int year = 0;
		while(true) {
			 year = self.inputNumber(sc, "����(ex - 2020) : ");
			if(year == -1) {return System.currentTimeMillis();}
			else if(year > 0) {break;}
			else {System.out.println("���� ������ ������ϴ�.");}
		}

		int month = 0;
		while(true) {
			month = self.inputNumber(sc, "��(ex - 12) : ");
			if(month == -1) {return System.currentTimeMillis();}
			else if(month > 0 && month <= 12){break;}
			else {System.out.println("�� ������ ������ϴ�.");}
		}
		int day = 0;
		while(true) {
			day = self.inputNumber(sc, "��(ex - 31) : ");
			if(day == -1) {return System.currentTimeMillis();}
			else if(day > 0 && day <= 31) {break;}
			else {System.out.println("�� ������ ������ϴ�.");}
		}
		int hour = 0;
		while(true) {
			hour = self.inputNumber(sc, "��(ex - 23) : ");
			if(hour == -1) {return System.currentTimeMillis();}
			else if(hour >= 0 && hour < 24) {break;}
			else {System.out.println("�� ������ ������ϴ�.");}
		}
		int minute = 0;
		while(true) {
			minute = self.inputNumber(sc, "��(ex - 59) : ");
			if(minute == -1) {return System.currentTimeMillis();}
			else if(minute >= 0 && minute < 60) {break;}
			else {System.out.println("�� ������ ������ϴ�.");}
		}
		int second = 0;
		while(true) {
			second = self.inputNumber(sc, "��(ex - 59) : ");
			if(second == -1) {return System.currentTimeMillis();}
			else if(second >= 0 && second < 60) {break;}
			else {System.out.println("�� ������ ������ϴ�.");}
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		StringBuilder sb = new StringBuilder();
		if(year < 10) {sb.append("000");}
		else if(year < 100) {sb.append("00");}
		else if(year < 1000) {sb.append("0");}
		sb.append(year);
		
		if(month < 10) {sb.append("0");}
		sb.append(month);
		
		if(day < 10) {sb.append("0");}
		sb.append(day);
		
		if(hour < 10) {sb.append("0");}
		sb.append(hour);
		
		if(minute < 10) {sb.append("0");}
		sb.append(minute);
		
		if(second < 10) {sb.append("0");}
		sb.append(second);
		
		return sdf.parse(sb.toString()).getTime();
	}
}
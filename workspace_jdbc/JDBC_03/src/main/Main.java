package main;

import java.util.ArrayList;
import java.util.Scanner;

import commons.EncryptionUtils;
import dao.MembersDAO;
import dao.StudentDAO;
import dto.MembersDTO;
import dto.StudentDTO;

public class Main {
	public static void main(String[] args) {
		final String url = "jdbc:oracle:thin:@localhost:1521:xe";
		final String dbID = "kh";
		final String dbPW = "kh";
		final Main self = new Main();
		MembersDAO mDao = null;
		StudentDAO sDao = null;
		int menu = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			mDao = new MembersDAO(url, dbID, dbPW);
			sDao = new StudentDAO(url, dbID, dbPW);
		}catch(Exception e) {
			System.out.println("���̺귯�� Ž�� �Ǵ� DAO ���� ����");
			e.printStackTrace();
			System.exit(0);
		}
		
		try(Scanner sc = new Scanner(System.in)){
			//�α��� ��Ʈ
			while(true) {
				while(true) {
					try {
						System.out.println("<< Index >>");
						System.out.println("1. �α���");
						System.out.println("2. ȸ������");
						System.out.println("3. ȸ�� ��� ���"); // ����. Ư�� ��¥ �����͸� ��� �ٷ� ���ΰ�.
						System.out.println("0. ����");
						System.out.print(">> ");
						menu = Integer.parseInt(sc.nextLine());
						break;
					}catch(Exception e) {
						System.out.println("���� �������� �Է����ּž� �մϴ�.");
						e.printStackTrace();
					}
				}
				if(menu == 0) {
					System.out.println("�ý����� �����մϴ�.");
					System.exit(0);
				}else if(menu == 1) {
					//�α��� Ȯ�� �ڵ�
					System.out.println("<< �α��� >> ");
					String id = self.inputText(sc, "ID�� �Է����ּ��� : ");
					String pw = self.inputText(sc, "PW�� �Է����ּ��� : ");
					pw = EncryptionUtils.sha512(pw);
					if(mDao.isLogin(id, pw)) {
						System.out.println(id + "�� ȯ���մϴ�.");
						break;
					}else {
						System.out.println("�α��� ����. ID �Ǵ� PW�� �ٽ� Ȯ�����ּ���.");
					}
				}else if(menu == 2) {
					while(true) {
						System.out.println("<< ȸ������ >> : ȸ������ ���Ḧ ���ϸ� �������� 0�� �Է����ּ���");
						String id = self.inputText(sc, "ID�� �Է����ּ��� : ");
						if(id.equals("0")) break;
						if(mDao.isIdExist(id)) {
							System.out.println("�̹� ������� ���̵��Դϴ�.");
							continue;
						}
						String pw = self.inputText(sc, "PW�� �Է����ּ��� : ");
						if(pw.equals("0")) break;
						pw = EncryptionUtils.sha512(pw);
						String name = self.inputText(sc,  "�̸��� �Է����ּ��� : ");
						if(name.equals("0")) break;
						String contact = self.inputText(sc, "����ó�� �Է����ּ��� : ");
						if(contact.equals("0")) break;
						int result = mDao.insert(new MembersDTO(id, pw, name, contact, null));
						if(result > 0 ) {
							System.out.println("ȸ������ ����");
						}else {
							System.out.println("ȸ������ ����");
						}
						break;
					}
				}else if(menu == 3) {
					self.printMembers(mDao.selectAll());
				}else {
					System.out.println("�޴��� �ٽ� Ȯ�����ּ���.");
				}
			}
			//���α׷� ��ü
			while(true) {
				while(true) {
					try {
						System.out.println("<< �л����� ���� ���α׷� >>");
						System.out.println("1. �л����� �Է�");
						System.out.println("2. �л���� ���");
						System.out.println("3. �л����� ����"); // --�й�
						System.out.println("4. �л����� ����"); // --�й�
						System.out.println("5. �л����� �˻�"); // --�̸�
						System.out.println("0. �ý��� ����");
						System.out.print(">> ");
						menu = Integer.parseInt(sc.nextLine());
						break;
					}catch(Exception e) {
						System.out.println("�޴� ��ȣ�� ���� �������� �Է����ּž� �մϴ�.");
						e.printStackTrace();
					}
				}
				if(menu == 0) {
					System.out.println("���α׷��� �����մϴ�.");
					System.exit(0);
				}else if(menu == 1) {
					String name = self.inputText(sc, "�ű� ����� �л� �̸� : ");
					int kor = self.inputNumber(sc, name + " �л��� ���� ���� : ");
					int eng = self.inputNumber(sc, name + " �л��� ���� ���� : ");
					int math = self.inputNumber(sc, name + " �л��� ���� ���� : ");
					StudentDTO dto = new StudentDTO(0, name, kor, eng, math);
					if(sDao.insert(dto) > 0) {
						System.out.println("�Է� ����");
					}else {
						System.out.println("�Է� ����");
					}
				}else if(menu == 2) {
					self.printStudents(sDao.selectAll());
				}else if(menu == 3) {
					int id = self.inputNumber(sc, "������ �л��� �й� : ");
					if(!sDao.isIdExist(id)) {
						System.out.println("�ش� �й��� �л��� �����ϴ�.");
						continue;
					}
					String name = self.inputText(sc, "������ �л� �̸� : ");
					int kor = self.inputNumber(sc, name + " �л��� ���� ���� : ");
					int eng = self.inputNumber(sc, name + " �л��� ���� ���� : ");
					int math = self.inputNumber(sc, name + " �л��� ���� ���� : ");
					StudentDTO dto = new StudentDTO(id, name, kor, eng, math);
					if(sDao.updateByID(dto) > 0) {
						System.out.println("���� ����");
					}else {
						System.out.println("���� ����");
					}
				}else if(menu == 4) {
					int id = self.inputNumber(sc, "������ �л��� �й� : ");
					if(!sDao.isIdExist(id)) {
						System.out.println("�ش� �й��� �л��� �����ϴ�.");
						continue;
					}
					if(sDao.deleteByID(id) > 0) {
						System.out.println("���� ����");
					}else {
						System.out.println("���� ����");
					}
				}else if(menu == 5) {
					String name = self.inputText(sc, "�˻� �� �л� �̸� Ű���� : ");
					ArrayList<StudentDTO> result = sDao.searchByName(name);
					if(result.isEmpty()) {
						System.out.println("ã�� ������ �����ϴ�.");
					}else {
						self.printStudents(result);
					}
				}else {
					System.out.println("���� �޴� ��ȣ�Դϴ�.");
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
				System.out.println("���� �������� �Է��� �ּž� �մϴ�");
				e.printStackTrace();
			}
		}
		return result;
	}
	
	private String inputText(Scanner sc, String infoText) {
		System.out.print(infoText);
		return sc.nextLine();
	}
	
	private void printStudents(ArrayList<StudentDTO> list) {
		if(list.isEmpty()) {
			System.out.println("����� ������ �����ϴ�.");
		}else {
			System.out.println("�й�\t�̸�\t����\t����\t����\t���");
			for(StudentDTO dto : list) {
				System.out.println(dto.getId() + "\t" +
									dto.getName() + "\t" +
									dto.getKor() + "\t" +
									dto.getEng() + "\t" +
									dto.getMath() + "\t"+
									dto.getAvarage());
			}
		}
	}
	
	private void printMembers(ArrayList<MembersDTO> list) {
		if(list.isEmpty()) {
			System.out.println("����� ������ �����ϴ�.");
		}else {
//			System.out.println("���̵�\t��ȣȭ���\t�̸�\t����ó\t�������� ��¥");
//			for(MembersDTO dto : list) {
//				System.out.println(dto.getId() + "\t" +
//									dto.getPw() + "\t" +
//									dto.getName() + "\t" +
//									dto.getContact() + "\t" +
//									dto.getReg_date());
//			}
			
			System.out.println("���̵�\t��������_��¥");
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd(E) HH:mm:ss.SSS");
			for(MembersDTO dto : list) {
				//String formedDate = sdf.format(dto.getReg_date());
				System.out.println(dto.getId() + "\t" + dto.getFormedDate() + "\t");
			}
		}
	}

}

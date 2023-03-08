package main;

import java.util.ArrayList;
import java.util.Scanner;

import dao.MoviesDAO;
import dto.MoviesDTO;

public class Main {
	public static void main(String[] args) {
		//DB CRUD ���α׷� �ۼ�
		//CRUD�� ����� ������ �����... ������ �غô� Netflix��. ��ȭ ���� ���α׷�.

		//���̺� ����
		//���̺� �̸� : movies
		//id - 1001 ~ ���ѱ��� 1�� ����
		//title - ���ڿ� �ִ� 50 ����Ʈ����
		//genre - ���ڿ� �ִ� 20����Ʈ
		//��� not null
		//SQL Developer ���� kh ������ ����

		//�ϼ��� ������ �ڵ带 MVC �������� Refactoring �Ѵ�.

		//�Ʒ� �κ��� DAO���� �ٽñ� �������༭ �ּ�ȭ.
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		}catch(Exception e) {
//			System.out.println("���̺귯�� Ž�� ����");
//			System.exit(0);
//		}
		
		Scanner sc = new Scanner(System.in);
		final String url = "jdbc:oracle:thin:@localhost:1521:xe";
		final String dbID = "kh";
		final String dbPW = "kh";

		Main self = new Main();
		MoviesDAO dao = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dao = new MoviesDAO(url, dbID, dbPW);
		}catch(Exception e) {
			System.out.println("���̺귯�� Ž�� �Ǵ� DAO ���� ����");
			e.printStackTrace();
			System.exit(0);
		}

		try {
			//��� �ϳ� ���� �� ���� ���� ������ ���� �ݴ� ���� ������
			//���α׷��� �ѳ����� ������� ���� ��.. ������ �ʴµ� ���� �ǰ� ���� �� ����.
			while(true) {
				int menu = 0;
				while(true)
				{
					try {
						System.out.println("<< Netflix ��ȭ ���� ���α׷� >> ");
						System.out.println("1. �ű� ��ȭ ���"); //C
						System.out.println("2. ��ȭ ��� ���"); //R
						System.out.println("3. ��ȭ ���� ����"); //U -- ID�� �������� ��� ���� ����
						System.out.println("4. ��ȭ ���� ����"); //D -- ID�� �������� ����
						System.out.println("5. ��ȭ ���� �˻�"); //--�̸�����
						System.out.println("0. ���α׷� ����");
						System.out.print(">> ");
						menu = Integer.parseInt(sc.nextLine());
						break;
					}catch(Exception e) {
						System.out.println("�޴� ��ȣ�� ���� �����Դϴ�. ���ڸ� �Է����ּ���.");
						e.printStackTrace();
					}
				}

				if(menu == 0) {
					System.out.println("���α׷��� �����մϴ�.");
					System.exit(0);
				}else if(menu == 1) { // C
					String title = self.inputText(sc, "�ű� ����� ��ȭ�� �̸� : ");
					String genre = self.inputText(sc, title + " ��ȭ�� �帣 : ");
					MoviesDTO dto = new MoviesDTO(0, title, genre);
					int result = dao.insert(dto);
					if(result > 0) {
						System.out.println("�Է� ����");
					}else {
						System.out.println("�Է� ����");
					}
				}else if(menu == 2) { // R
					self.printMovies(dao.selectAll());
				}else if(menu == 3) { // U -- ID�� ����
					//self.printMovies(dao.selectAll());
					//System.out.println();
					System.out.println("�����ϰ� ���� ��ȭ�� ID�� �Է����ּ���. ");
					int id = self.inputID(sc);
					if(!dao.isIdExist(id)) {
						System.out.println("�ش� id�� ��ȭ�� �����ϴ�. ������մϴ�.");
						continue;
					}
					String title = self.inputText(sc, "���� �� ��ȭ�� �̸� : ");
					String genre = self.inputText(sc, title + " ��ȭ�� �帣 : ");
					MoviesDTO dto = new MoviesDTO(id,title,genre);
					int result = dao.updateToID(dto);
					if(result > 0) {
						System.out.println("���� ����");
					}else {
						System.out.println("���� ����. ��� ��ȭ�� ã�� ���Ͽ����ϴ�.");
					}
				}else if(menu == 4) { // D -- ID�� ����
					//self.printMovies(dao.selectAll());
					//System.out.println();
					System.out.println("�����ϰ� ���� ��ȭ�� ID�� �Է����ּ���. ");
					int id = self.inputID(sc);
					if(!dao.isIdExist(id)) {
						System.out.println("�ش� id�� ��ȭ�� �����ϴ�. ������մϴ�.");
						continue;
					}
					int result = dao.deleteToID(id);
					if(result > 0) {
						System.out.println("���� ����");
					}else {
						System.out.println("���� ����");
					}
				}else if(menu == 5) {
					String title = self.inputText(sc, "�˻� �� ��ȭ�� ���� Ű���� : ");
					self.printMovies(dao.searchByTitle(title));
				}else {
					System.out.println("���� �޴� ��ȣ�Դϴ�. ������մϴ�.");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private int inputID(Scanner sc) {
		int id = 0;
		while(true) {
			try {
				System.out.print(">>");
				id = Integer.parseInt(sc.nextLine());
				break;
			}catch(Exception e) {
				System.out.println("id�� ���� ������ �Է����ּž� �մϴ�.");
				e.printStackTrace();
			}
		}
		return id;
	}
	
	private String inputText(Scanner sc, String infoText) {
		System.out.print(infoText);
		return sc.nextLine();
	}
	
	private void printMovies(ArrayList<MoviesDTO> list){
		if(list.isEmpty()){
			System.out.println("����� ������ �����ϴ�.");
		}else{
			System.out.println("ID\tName\tGenre");
			for(MoviesDTO dto : list) {
				System.out.println(dto.getId() + "\t" + dto.getTitle() + "\t" + dto.getGenre());
			}
		}
	}
}
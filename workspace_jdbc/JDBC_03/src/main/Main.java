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
			System.out.println("라이브러리 탐색 또는 DAO 생성 실패");
			e.printStackTrace();
			System.exit(0);
		}
		
		try(Scanner sc = new Scanner(System.in)){
			//로그인 파트
			while(true) {
				while(true) {
					try {
						System.out.println("<< Index >>");
						System.out.println("1. 로그인");
						System.out.println("2. 회원가입");
						System.out.println("3. 회원 목록 출력"); // 연습. 특히 날짜 데이터를 어떻게 다룰 것인가.
						System.out.println("0. 종료");
						System.out.print(">> ");
						menu = Integer.parseInt(sc.nextLine());
						break;
					}catch(Exception e) {
						System.out.println("숫자 형식으로 입력해주셔야 합니다.");
						e.printStackTrace();
					}
				}
				if(menu == 0) {
					System.out.println("시스템을 종료합니다.");
					System.exit(0);
				}else if(menu == 1) {
					//로그인 확인 코드
					System.out.println("<< 로그인 >> ");
					String id = self.inputText(sc, "ID를 입력해주세요 : ");
					String pw = self.inputText(sc, "PW를 입력해주세요 : ");
					pw = EncryptionUtils.sha512(pw);
					if(mDao.isLogin(id, pw)) {
						System.out.println(id + "님 환영합니다.");
						break;
					}else {
						System.out.println("로그인 실패. ID 또는 PW를 다시 확인해주세요.");
					}
				}else if(menu == 2) {
					while(true) {
						System.out.println("<< 회원가입 >> : 회원가입 종료를 원하면 언제든지 0을 입력해주세요");
						String id = self.inputText(sc, "ID를 입력해주세요 : ");
						if(id.equals("0")) break;
						if(mDao.isIdExist(id)) {
							System.out.println("이미 사용중인 아이디입니다.");
							continue;
						}
						String pw = self.inputText(sc, "PW를 입력해주세요 : ");
						if(pw.equals("0")) break;
						pw = EncryptionUtils.sha512(pw);
						String name = self.inputText(sc,  "이름을 입력해주세요 : ");
						if(name.equals("0")) break;
						String contact = self.inputText(sc, "연락처를 입력해주세요 : ");
						if(contact.equals("0")) break;
						int result = mDao.insert(new MembersDTO(id, pw, name, contact, null));
						if(result > 0 ) {
							System.out.println("회원가입 성공");
						}else {
							System.out.println("회원가입 실패");
						}
						break;
					}
				}else if(menu == 3) {
					self.printMembers(mDao.selectAll());
				}else {
					System.out.println("메뉴를 다시 확인해주세요.");
				}
			}
			//프로그램 본체
			while(true) {
				while(true) {
					try {
						System.out.println("<< 학생정보 관리 프로그램 >>");
						System.out.println("1. 학생정보 입력");
						System.out.println("2. 학생목록 출력");
						System.out.println("3. 학생정보 수정"); // --학번
						System.out.println("4. 학생정보 삭제"); // --학번
						System.out.println("5. 학생정보 검색"); // --이름
						System.out.println("0. 시스템 종료");
						System.out.print(">> ");
						menu = Integer.parseInt(sc.nextLine());
						break;
					}catch(Exception e) {
						System.out.println("메뉴 번호는 숫자 형식으로 입력해주셔야 합니다.");
						e.printStackTrace();
					}
				}
				if(menu == 0) {
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
				}else if(menu == 1) {
					String name = self.inputText(sc, "신규 등록할 학생 이름 : ");
					int kor = self.inputNumber(sc, name + " 학생의 국어 점수 : ");
					int eng = self.inputNumber(sc, name + " 학생의 영어 점수 : ");
					int math = self.inputNumber(sc, name + " 학생의 수학 점수 : ");
					StudentDTO dto = new StudentDTO(0, name, kor, eng, math);
					if(sDao.insert(dto) > 0) {
						System.out.println("입력 성공");
					}else {
						System.out.println("입력 실패");
					}
				}else if(menu == 2) {
					self.printStudents(sDao.selectAll());
				}else if(menu == 3) {
					int id = self.inputNumber(sc, "수정할 학생의 학번 : ");
					if(!sDao.isIdExist(id)) {
						System.out.println("해당 학번의 학생이 없습니다.");
						continue;
					}
					String name = self.inputText(sc, "수정할 학생 이름 : ");
					int kor = self.inputNumber(sc, name + " 학생의 국어 점수 : ");
					int eng = self.inputNumber(sc, name + " 학생의 영어 점수 : ");
					int math = self.inputNumber(sc, name + " 학생의 수학 점수 : ");
					StudentDTO dto = new StudentDTO(id, name, kor, eng, math);
					if(sDao.updateByID(dto) > 0) {
						System.out.println("수정 성공");
					}else {
						System.out.println("수정 실패");
					}
				}else if(menu == 4) {
					int id = self.inputNumber(sc, "삭제할 학생의 학번 : ");
					if(!sDao.isIdExist(id)) {
						System.out.println("해당 학번의 학생이 없습니다.");
						continue;
					}
					if(sDao.deleteByID(id) > 0) {
						System.out.println("삭제 성공");
					}else {
						System.out.println("삭제 실패");
					}
				}else if(menu == 5) {
					String name = self.inputText(sc, "검색 할 학생 이름 키워드 : ");
					ArrayList<StudentDTO> result = sDao.searchByName(name);
					if(result.isEmpty()) {
						System.out.println("찾은 정보가 없습니다.");
					}else {
						self.printStudents(result);
					}
				}else {
					System.out.println("없는 메뉴 번호입니다.");
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
				System.out.println("숫자 형식으로 입력해 주셔야 합니다");
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
			System.out.println("출력할 정보가 없습니다.");
		}else {
			System.out.println("학번\t이름\t국어\t영어\t수학\t평균");
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
			System.out.println("출력할 정보가 없습니다.");
		}else {
//			System.out.println("아이디\t암호화비번\t이름\t연락처\t계정생성 날짜");
//			for(MembersDTO dto : list) {
//				System.out.println(dto.getId() + "\t" +
//									dto.getPw() + "\t" +
//									dto.getName() + "\t" +
//									dto.getContact() + "\t" +
//									dto.getReg_date());
//			}
			
			System.out.println("아이디\t계정생성_날짜");
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd(E) HH:mm:ss.SSS");
			for(MembersDTO dto : list) {
				//String formedDate = sdf.format(dto.getReg_date());
				System.out.println(dto.getId() + "\t" + dto.getFormedDate() + "\t");
			}
		}
	}

}

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
			System.out.println("라이브러리 탐색 실패");
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
			//로그인 파트
			while(true) {
				String infoText = "<< menu >>" + System.lineSeparator()
									+"1. 로그인" + System.lineSeparator()
									+"2. 회원가입" + System.lineSeparator()
									+"0. 종료" + System.lineSeparator()
									+">>";
				menu = self.inputNumber(sc, infoText);
				if(menu == 0) {
					System.out.println("시스템을 종료합니다.");
					System.exit(0);
				}else if(menu == 1) {
					System.out.println("<< 로그인 >>");
					String id = self.inputText(sc, "ID를 입력해주세요 : ");
					String pw = self.inputText(sc, "PW를 입력해주세요 : ");
					pw = EncryptionUtils.sha256(pw);
					if(dao_member.isLogin(id, pw)) {
						//curName = dao_member.getNameById(id);
						curID = id;
						System.out.println(id + "님 환영합니다.");
						break;
					}else {
						System.out.println("로그인 실패. ID 또는 PW를 다시 확인해주세요.");
					}
				}else if(menu == 2) {
					while(true) {
						System.out.println("<< 회원 가입 >>"); //아이디, 패스워드, 이름, 연락처
						System.out.println("회원가입 중 종료를 원하면 언제든지 0을 입력해주세요.");
						String id = self.inputText(sc, "ID를 입력해주세요 : ");
						if(id.equals("0")) break;
						if(dao_member.isIdExist(id)) {
							System.out.println("이미 사용중인 아이디입니다.");
							continue;
						}
						String pw = self.inputText(sc, "PW를 입력해주세요 : ");
						if(pw.equals("0"))break;
						pw = EncryptionUtils.sha256(pw);
						String name = self.inputText(sc, "이름을 입력해주세요 : ");
						if(name.equals("0")) break;
						String contact = self.inputText(sc, "연락처를 입력해주세요 : ");
						if(contact.equals("0")) break;
						if(0 < dao_member.insert(new MembersDTO(id,pw,name,contact,null))) {
							System.out.println("회원가입 성공");
						}else {
							System.out.println("회원가입 실패");
						}
						break;
					}
				}else {
					System.out.println("메뉴를 다시 확인해주세요.");
				}
			}
			
			//Message 파트
			while(true) {
				String infoText = "<< 경량 게시판 프로그램 >>" + System.lineSeparator()
									+"1. 신규 메시지 등록" + System.lineSeparator() //작성자, 메시지 직접 입력
									+"2. 메시지 목록 출력" + System.lineSeparator()
									+"3. 메시지 삭제" + System.lineSeparator() // seq로 삭제
									+"4. 메시지 수정" + System.lineSeparator() //seq로 수정하되, writer 및 message 만 수정가능
																				//(임의 & 옵션 2)message만 수정. writer 는 현재 id
									+"5. 메시지 검색" + System.lineSeparator() //writer 또는 message에 검색어를 포함하는 대상
									+"6. 내 정보보기" + System.lineSeparator()
									+"0. 종료" + System.lineSeparator()
									+">>";
				menu = self.inputNumber(sc, infoText);
				if(menu == 0) {
					System.out.println("시스템을 종료합니다.");
					System.exit(0);
				}else if(menu == 1) { // 신규 메시지 등록 // 작성자, 메시지 입력
					String message = self.inputText(sc, "등록할 메시지 : ");
					long date = self.inputDate(sc, self);
					if(0 < dao_message.insert(new MessagesDTO(0, curID, message, new Timestamp(date)))) {
						System.out.println("입력 성공");
					}else {
						System.out.println("입력 실패");
					}
				}else if(menu == 2) { // 메시지 목록 출력
					self.printMessages(dao_message.selectAll(), dao_member);
				}else if(menu == 3) { // 메시지 삭제 // seq
					int seq = self.inputNumber(sc, "삭제할 게시글의 등록번호 : ");
					if(!dao_message.getWriterBySeq(seq).equals(curID)) {
						System.out.println("다른 작성자의 게시글입니다. 삭제할 권한이 없습니다.");
						continue;
					}
					if(0 < dao_message.deleteBySeq(seq)) {
						System.out.println("삭제에 성공했습니다.");
					}else {
						System.out.println("삭제에 실패했습니다.");
					}
				}else if(menu == 4) { // 메시지 수정 // seq로 수정하되 ~ // 임의로 message만 수정. 단, 같은 작성자일 경우.
					int seq = self.inputNumber(sc, "수정할 게시글의 등록번호");
					if(!dao_message.getWriterBySeq(seq).equals(curID)) {
						System.out.println("다른 작성자의 게시글입니다. 수정할 권한이 없습니다.");
						continue;
					}
					String newMessage = self.inputText(sc, "덮어 쓸 메시지 : ");
					long date = self.inputDate(sc, self);
					if(0 < dao_message.updateBySeq(seq, newMessage, new Timestamp(date))) {
						System.out.println("수정 성공");
					}else {
						System.out.println("수정 실패");
					}
				}else if(menu == 5) { // 메시지 검색 // writer 또는 message에 검색어를 포함
					String keyword = self.inputText(sc, "검색할 키워드 : ");
					self.printMessages(dao_message.search(keyword), dao_member);
				}else if(menu == 6) { // 내 정보보기
					System.out.println("<< 계정 정보 >>");
					self.printMembers(dao_member.searchById(curID));
					System.out.println("<< 게시글 정보 >>");
					self.printMessages(dao_message.searchById(curID), dao_member);
				}else {
					System.out.println("메뉴를 다시 확인해주세요.");
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
				System.out.println("숫자 형식으로 입력해 주셔야 합니다.");
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
			System.out.println("출력할 정보가 없습니다.");
		}else {
			System.out.println("등록번호\t작성자\t메시지\t작성시간");
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
			System.out.println("출력할 정보가 없습니다.");
		}else {
			System.out.println("ID\t이름\t연락처\t가입날짜"); //pw는 일부러 뻄.
			for(MembersDTO dto : list) {
				System.out.println(dto.getId() + "\t"
									+ dto.getName() + "\t"
									+ dto.getContact() + "\t"
									+ dto.getFormedDate());
			}
		}
	}
	
	private long inputDate(Scanner sc, Main self) throws Exception{
		System.out.println("날짜 입력을 시작하겠습니다.");
		System.out.println("날짜 입력을 중단하려면 -1을 입력하세요. 대신 현재 시간이 입력됩니다.");
		int year = 0;
		while(true) {
			 year = self.inputNumber(sc, "연도(ex - 2020) : ");
			if(year == -1) {return System.currentTimeMillis();}
			else if(year > 0) {break;}
			else {System.out.println("연도 범위를 벗어났습니다.");}
		}

		int month = 0;
		while(true) {
			month = self.inputNumber(sc, "월(ex - 12) : ");
			if(month == -1) {return System.currentTimeMillis();}
			else if(month > 0 && month <= 12){break;}
			else {System.out.println("월 범위를 벗어났습니다.");}
		}
		int day = 0;
		while(true) {
			day = self.inputNumber(sc, "일(ex - 31) : ");
			if(day == -1) {return System.currentTimeMillis();}
			else if(day > 0 && day <= 31) {break;}
			else {System.out.println("일 범위를 벗어났습니다.");}
		}
		int hour = 0;
		while(true) {
			hour = self.inputNumber(sc, "시(ex - 23) : ");
			if(hour == -1) {return System.currentTimeMillis();}
			else if(hour >= 0 && hour < 24) {break;}
			else {System.out.println("시 범위를 벗어났습니다.");}
		}
		int minute = 0;
		while(true) {
			minute = self.inputNumber(sc, "분(ex - 59) : ");
			if(minute == -1) {return System.currentTimeMillis();}
			else if(minute >= 0 && minute < 60) {break;}
			else {System.out.println("분 범위를 벗어났습니다.");}
		}
		int second = 0;
		while(true) {
			second = self.inputNumber(sc, "초(ex - 59) : ");
			if(second == -1) {return System.currentTimeMillis();}
			else if(second >= 0 && second < 60) {break;}
			else {System.out.println("초 범위를 벗어났습니다.");}
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

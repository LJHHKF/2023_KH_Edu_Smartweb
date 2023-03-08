package main;

import java.util.Scanner;

import classes.Gold;
import classes.Member;
import classes.Ruby;
import classes.Silver;
import manager.Manager;

public class Main {
	public static void main(String[] args) {
		// 상속
		// 다형성
		// 추상화
		// Collection

		//먼저 상속.
		//회원관리 시스템
		//등급 클래스 설계 {Silver}
		//고유 ID, 이름, 포인트	
		//Getter / Setter / Constructor / Default Constructor / 정보은닉
		
		//완성된 코드의 문제점 3가지
		//1. 코드 중복도 (유지 보수 문제점) -> 해결법: 상속 (클래스간의 Is-A 관계)
		//		공통 요소 이름: Member, tier 등.
		//2. 코드 결합도 (유지 보수 문제점) -> 해결법: 다형성
		//3. 저장소 이슈 -> 해결법: Collection
		
		Scanner sc = new Scanner(System.in);
		Manager manager = new Manager();
		
		
		while(true) {
			System.out.println("== 회원 관리 시스템 ==");
			System.out.println("1. 신규 회원 등록");
			System.out.println("2. 회원 정보 출력");
			System.out.println("0. 종료");
			System.out.print(">>");
			int menu = 0;
			while(true)
			{
				try {
					menu = Integer.parseInt(sc.nextLine());
					break;
				}catch(Exception e) {
					System.out.println("숫자 형식을 입력해주셔야 합니다.");
					e.printStackTrace();
				}
			}
			
			switch(menu) {
			case 0:
				System.out.println("시스템을 종료합니다.");
				System.exit(0);
				break;
			case 1:
				manager.insert(new Silver(1001, "Tom", 1000));
				manager.insert(new Silver(1002, "Jane", 2000));
				manager.insert(new Silver(1003, "Mike", 3000));
				manager.insert(new Gold(1004, "Susan", 4000));
				manager.insert(new Ruby(1005, "Jack", 5000));
				break;
			case 2:
				System.out.println("ID\tName\tPoint\tBonus");
				for(Member curMember : manager.getMembers()) {
					System.out.println(curMember.getId() + "\t" +
										curMember.getName() + "\t" +
										curMember.getPoint() + "\t" +
										curMember.getBonus());
				}
				break;
				default :
					System.out.println("메뉴를 다시 확인해주세요.");
			}
		}
		
		
		
		
		
	}
}

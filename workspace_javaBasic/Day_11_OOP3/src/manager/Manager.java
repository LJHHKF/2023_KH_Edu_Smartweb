package manager;

import java.util.ArrayList;

import classes.Member;

public class Manager {
	private ArrayList<Member> members = new ArrayList<>(); 	
	
									//추상 클래스라 new할 수 없으나,
									//이 경우 만들어진 것은 담을 변수의 배열을 만든 것임.
									//즉, 여기서는 클래스 인스턴스가 만들어진 게 아님.
	//private int curIndex = 0;
	
	public void insert(Member newMember) {
		members.add(newMember);
	}
	
//	public int getCurIndex() {
//		return members.size();
//	}
	
	public ArrayList<Member> getMembers() {
		return members;
	}
}

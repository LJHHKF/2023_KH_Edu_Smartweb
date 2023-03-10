package test06;

public class Main {
	public static void main(String[] args) {
		
		ShopMember mem = new TestDAO().searchUser("TOM");
		System.out.println(mem.getMemberId());
		System.out.println(mem.getMemberPw());
		System.out.println(mem.getMemberName());
		System.out.println(mem.getMemberAge());
		System.out.println(mem.getPhone());
		System.out.println(mem.getAddr());
	}
}

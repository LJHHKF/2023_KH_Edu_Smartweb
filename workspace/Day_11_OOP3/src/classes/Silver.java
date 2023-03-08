package classes;

public class Silver extends Member{
	
	//public Silver() {super();}

	public Silver(int id, String name, double point) {
		super(id, name, point);
	}
	
	public double getBonus() {
		return this.getPoint() * 0.02;
	}
	
	//부모클래스로부터 상속받은 메서드를 무시하고 동일한 프로토타입을 다시 정의하면
	//상속받은 메서드를 덮어씌우는 효과를 가진다.
	//오버라이드.
}

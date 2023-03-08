package classes;

public abstract class Member {
	private int id;
	private double point;
	private String name;
	
	//public Member() {};
	public Member(int id, String name, double point) {
		this.id = id;
		this.name = name;
		this.point = point;
	}

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public double getPoint() {return point;}
	public void setPoint(double point) {this.point = point;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;};
	
	abstract public double getBonus();
}

package classes;

public class Ruby extends Member{
	public Ruby(int id, String name, double point) {
		super(id, name, point);
	}
	
	public double getBonus() {
		return this.getPoint() * 0.05;
	}
}

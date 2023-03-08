package classes;

public class Gold extends Member{
	
	//public Gold() {super();}

	public Gold(int id, String name, double point) {
		super(id, name, point);
	}
	
	public double getBonus() {
		return this.getPoint() * 0.04;
	}	
}

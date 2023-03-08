package exam_02;

public class CafeMenu {
	//private static int id_count = 1001;
	private int id;
	private int price;
	private String name;
	
	public CafeMenu() {}
	public CafeMenu(int id, String name, int price) {
		//this.id = id_count++;
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public int getId() {
		return id;
	}
//	public void setId(int id) {
//		this.id = id;
//	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

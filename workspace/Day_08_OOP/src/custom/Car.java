package custom;

public class Car {
	
	//this : 자기 참조 변수
	//클래스를 바탕으로 인스턴스가 생성되면, 클래스 내부에서 인스턴스 주소를 알 수 있게
	//자바에 의해 미리 준비된(자바가 스스로 세팅해 놓는) 멤버 필드 
	
	private String model;
	private String color;
	private int price;
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
}

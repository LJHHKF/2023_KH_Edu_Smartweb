package custom;

//클래스를 더 좋게 만드는 방법
//정보은닉 - 접근제한자 [ public, protected, package, private ]
//외부에 노출 될 필요가 없는 정보를 private 키워드로 가려서
//클래스 설계자의 의도대로 안정적인 클래스 운용이 가능하게 만드는 방식
//클래스 설계자에게 안정성을 제공할 수 있음.
//사용자의 편의성에도 영향을 미침.

public class TV {
	
	//설계도에서 속성을 저장하는 변수 - 멤버변수(Member Field)
	String brand;
	private int weight;
	private int price;
	private int channel;
	

	//Member Method
	void powerON() {}
	void powerOFF() {}
	
	//그 외
	//Constructor
	//Nested Class
	
	//private 조작 예시
	public void setChannel(int cha) {
		if(cha > 0) {
			channel = cha;
		}
	}	
	public int getChannel() {
		return channel;
	}
	
	//오른쪽 클릭 -> Source -> Getter/Setter 자동 제작 보조.
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}

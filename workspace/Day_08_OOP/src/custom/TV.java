package custom;

//Ŭ������ �� ���� ����� ���
//�������� - ���������� [ public, protected, package, private ]
//�ܺο� ���� �� �ʿ䰡 ���� ������ private Ű����� ������
//Ŭ���� �������� �ǵ���� �������� Ŭ���� ����� �����ϰ� ����� ���
//Ŭ���� �����ڿ��� �������� ������ �� ����.
//������� ���Ǽ����� ������ ��ħ.

public class TV {
	
	//���赵���� �Ӽ��� �����ϴ� ���� - �������(Member Field)
	String brand;
	private int weight;
	private int price;
	private int channel;
	

	//Member Method
	void powerON() {}
	void powerOFF() {}
	
	//�� ��
	//Constructor
	//Nested Class
	
	//private ���� ����
	public void setChannel(int cha) {
		if(cha > 0) {
			channel = cha;
		}
	}	
	public int getChannel() {
		return channel;
	}
	
	//������ Ŭ�� -> Source -> Getter/Setter �ڵ� ���� ����.
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

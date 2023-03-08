package exam_03;

public class Contact {
	private String name, phone, email;

	public Contact() {};
	public Contact(String name, String phone, String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void myPrint() {
		System.out.println(this.name + " / " + this.phone + " / " + this.email);
	}
	
	
}

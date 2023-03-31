package 복습;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TeamDTO {
	//id �� ������ ���
	private int id;
	private String name;
	private int age;
	private String contact;
	private Timestamp birthday;
	
	public TeamDTO(int id, String name, int age, String contact, Timestamp birthday) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.contact = contact;
		this.birthday = birthday;
	}
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}
	public String getContact() {return contact;}
	public void setContact(String contact) {this.contact = contact;}
	public Timestamp getBirthday() {return birthday;}
	public void setBirthday(Timestamp birthday) {this.birthday = birthday;}
	public String getFormedDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yy년 mm월 dd일");
		return sdf.format(this.birthday);
	}
}

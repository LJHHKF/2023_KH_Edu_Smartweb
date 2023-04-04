package dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ContactsDTO {
	private int id;
	private String name;
	private String contact;
	private Timestamp birthday;
	public ContactsDTO() {
		super();
	}
	public ContactsDTO(int id, String name, String contact, Timestamp birthday) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.birthday = birthday;
	}
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getContact() {return contact;}
	public void setContact(String contact) {this.contact = contact;}
	public Timestamp getBirthday() {return birthday;}
	public void setBirthday(Timestamp birthday) {this.birthday = birthday;}
	public String getFormedBirthday() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년mm월dd일");
		return dateFormat.format(this.birthday);
	}
}

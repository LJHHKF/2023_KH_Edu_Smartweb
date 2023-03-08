package quiz_02;

import java.util.ArrayList;

public class Manager {
	private ArrayList<Contact> contactList = new ArrayList<>();
	
	public void addContact(Contact newContact) {
		contactList.add(newContact);
	}
	
	public ArrayList<Contact> getContactList(){
		return contactList;
	}
}

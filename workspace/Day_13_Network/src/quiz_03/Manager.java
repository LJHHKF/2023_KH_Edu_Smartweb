package quiz_03;

import java.util.ArrayList;

public class Manager {
	private ArrayList<Contact> contacts = new ArrayList<>();
	
	public Manager() {};
	
	public ArrayList<Contact> getContacts() {return contacts;}
	
	public void addContact(Contact newContact) {
		contacts.add(newContact);
	}
	
	public void deleteContact(int index) {
		contacts.remove(index);
	}
	
	
	public ArrayList<Contact> searchMultiContainsToName(String name){
		ArrayList<Contact> result = new ArrayList<>();
		for(Contact c : contacts) {
			if(c.getName().contains(name)) {
				result.add(c);
			}
		}
		return result;
	}
	
	public int searchIndexToID(int id) {
		for(int i = 0; i < contacts.size(); i++) {
			if(contacts.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
}

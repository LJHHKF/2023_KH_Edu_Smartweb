package exam_02;

import java.util.ArrayList;

public class Manager {
	private ArrayList<CafeMenu> menus = new ArrayList<>();
	
	public void addMenu(CafeMenu menu) {
		menus.add(menu);
	}
	
	public void updateMenu(int index, CafeMenu newMenu) {
		menus.set(index, newMenu);
	}
	
	public void deleteMenu(int index) {
		menus.remove(index);
	}
	
	public ArrayList<CafeMenu> getMenus() {
		return menus;
	}
	
	public ArrayList<CafeMenu> searchMultiContainsToName(String name){
		ArrayList<CafeMenu> result = new ArrayList<>();
		for(CafeMenu m : menus) {
			if(m.getName().contains(name)) {
				result.add(m);
			}
		}
		return result;
	}
	
	public int getIndexByName(String name) {
		for(int i = 0; i < menus.size(); i++) {
			if(menus.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
	public int getIndexByID(int id) {
		for(int i = 0; i < menus.size(); i++) {
			if(menus.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
}

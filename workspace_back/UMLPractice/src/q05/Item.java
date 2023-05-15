package q05;

public class Item {
	private String category;
	private String itemCode;
	private String itemName;
	private int itemPrice;
	public Item() {
		super();
	}
	public Item(String category, String itemCode, String itemName, int itemPrice) {
		super();
		this.category = category;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
}

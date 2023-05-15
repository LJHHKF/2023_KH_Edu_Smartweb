package q05;

public class ShopKeeper {
	private Cart cart = new Cart();
	
	public ShopKeeper() {
		super();
	}
	public int makeBillPaper() {
		Item item = null;
		if(cart.getItems().size() > 0) {
			item = cart.getItems().get(0);
		}
		return 0;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public void calculate(Calculator calc) {
		
	}
}

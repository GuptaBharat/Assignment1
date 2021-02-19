package pack;

public class Product {
	private String name;
	private int quantity;
	private int price;
	
	public Product(String name, int qty, int price){
		this.name = name;
		this.quantity = qty;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String toString() {
		return "Item Name = "+this.name+ " Price = "+ price+" Quantity = "+this.quantity;
	}
	
}

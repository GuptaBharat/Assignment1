package pack;

import java.util.ArrayList;
import java.util.Scanner;


public class ShoppingCart {
	private ArrayList<Product> cart = new ArrayList<Product>();
	
	public static void displayItems(ArrayList<Product> productList) {
		System.out.println("Item No.\tItem Name\tItem Price\tQuantity Available");
		System.out.println("-------------------------------------------------------------");
		for(int i = 0; i<productList.size(); i++) {
			System.out.println(i+1 +"\t\t"+ productList.get(i).getName() +"\t\t"+ productList.get(i).getPrice() +"\t\t"+ productList.get(i).getQuantity());
		}
	}
	
	
	public void addItem(Product item, int quantity) {
		if(quantity>item.getQuantity() || quantity<=0 ) {
			System.out.println("Invalid Quantity");
		}else {
			cart.add(new Product(item.getName(), quantity, item.getPrice()));
			int remainingQuantity = item.getQuantity() - quantity;
			item.setQuantity(remainingQuantity);
		}
	}
	
	
	public void displayCart() {
		if(cart.isEmpty()) {
			System.out.println("Your Cart is Empty.");
		}else {
			System.out.println("Item No.\tItem Name\tItem Price\tQuantity Available");
			System.out.println("-------------------------------------------------------------");
			for(int i = 0; i<cart.size(); i++) {
				System.out.println(i+1 +"\t\t"+ cart.get(i).getName() +"\t\t"+ cart.get(i).getPrice() +"\t\t"+ cart.get(i).getQuantity());
			}			
		}
	}
	
	
	public void generateBill() {
		displayCart();
		if(cart.isEmpty()) {
			return;
		}
		int billAmount = 0;
		for(Product item: cart) {
			billAmount += item.getPrice()*item.getQuantity();
		}
		System.out.println("Grand Total = "+ billAmount);
	}
	
	
	public boolean updateCart(ArrayList<Product> productList) {
		displayCart();
		
		if(cart.size()==0) {
			return false;
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please Enter item number : ");
		int itemIndex = sc.nextInt()-1;
		
		if(itemIndex>=cart.size() || itemIndex<0) {
			System.out.println("Invalid item number");
			return false;
		}
		
		int productIndex;
		for(productIndex = 0; productIndex<productList.size(); productIndex++) {
			if(productList.get(productIndex).getName().equals(cart.get(itemIndex).getName())) {
				break;
			}
		}
		int productQuantity = productList.get(productIndex).getQuantity();
		
		System.out.println("Please Enter updated quantity : ");
		int quantity = sc.nextInt();
		int previousQuantity = cart.get(itemIndex).getQuantity();
		if(quantity<0 || quantity>productQuantity) {
			System.out.println("Invalid Quantity or Desired Quantity not available.");
			return false;
		}
		if(quantity==0) {
			cart.remove(itemIndex);
			productList.get(productIndex).setQuantity(productList.get(productIndex).getQuantity()-(quantity-previousQuantity));
			return true;
		}
		cart.get(itemIndex).setQuantity(quantity);
		productList.get(productIndex).setQuantity(productList.get(productIndex).getQuantity()-(quantity-previousQuantity));
		return true;
	}
	
	
	public static void main(String[] args) {
		ArrayList<Product> productList = new ArrayList<Product>();
		productList.add(new Product("Biscuit", 50, 10));
		productList.add(new Product("Orange", 30, 20));
		productList.add(new Product("Apple", 60, 40));

		ShoppingCart userCart = new ShoppingCart();
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("\n             Shopping Menu            ");
			System.out.println("---------------------------------------");
			System.out.println("1. To View Available Items in the Shop");
			System.out.println("2. For Add Item in the Cart");
			System.out.println("3. For View Items in the Cart");
			System.out.println("4. For Update Items in the Cart");
			System.out.println("5. For Generate Bill for the Cart");
			System.out.println("6. For Exit");
			System.out.println("Please Enter your choice : ");
			int userChoice = sc.nextInt();
			
			switch(userChoice) {
				case 1:
					displayItems(productList);
					break;
				
				case 2:
					System.out.println("To Add Item, Enter the Item number: ");
					int itemIndex = sc.nextInt();
					if(itemIndex>0 && itemIndex<=productList.size()) {
						System.out.println("Enter quantity: ");
						int itemQuantity = sc.nextInt();
						userCart.addItem(productList.get(itemIndex-1), itemQuantity);
						break;						
					}else {
						System.out.println("Invalid Item number");
						break;
					}
				
				case 3:
					userCart.displayCart();
					break;
				
				case 4:
					if(userCart.updateCart(productList)) {
						System.out.println("Cart Updated Successfully");
					}else {
						System.out.println("Oops! Cart updation failed");
					}
					break;
				
				case 5:
					userCart.generateBill();
					break;
				
				case 6:
					return;
				
				default:
					System.out.println("Invalid Choice");
			}
		}
	}
}

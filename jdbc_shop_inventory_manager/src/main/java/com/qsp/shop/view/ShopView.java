package com.qsp.shop.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.qsp.shop.controller.ShopController;
import com.qsp.shop.model.Product;

public class ShopView {
//	static to create objects before main method.
	static Scanner myInput = new Scanner(System.in);
	static Product product = new Product();
	static ShopController shopController = new ShopController();

	public static void main(String[] args) {
//	do while for menu driven program.
	do {
		System.out.println("Select One Option\n");
		System.out.println("1.Add Single Product\n2.Add Multiple Products\n3.Remove Product\n4.Fetch Product\n5.Update Product");
		System.out.println();
		System.out.print("Enter Number To Select Option : ");
		int userInput = myInput.nextInt();
//		nextInt() creates buffer. To clear it we use nextLine() to clear that buffer.
		myInput.nextLine();
		
//		Switch for performing selected operation.
		switch (userInput) {
		
//		Exit
		case 0:
			myInput.close();
			System.out.println("---EXITED---");
//			exit(n) is used to terminate all running JVM normally using '0' as arguement. 
			System.exit(0);
			break;
			
//		Add Single Product
		case 1:
			System.out.print("Enter Product Id : ");
			int i_p_id = myInput.nextInt();
//			Clear Buffer
			myInput.nextLine();
			
			System.out.print("Enter Product Name : ");
			String i_p_name = myInput.nextLine();
			
			System.out.print("Enter Product Price : ");
			int i_p_price = myInput.nextInt();
//			Clear Buffer
			myInput.nextLine();
			
			System.out.print("Enter Product Quantity : ");
			int i_p_quantity = myInput.nextInt();
			myInput.nextLine();
//			product availability decided by product quantity.
			boolean i_p_availability = false;
			if (i_p_quantity>0) {
				i_p_availability = true;
			}
			
			if ((shopController.addProduct(i_p_id, i_p_name, i_p_price, i_p_quantity, i_p_availability))!=0) {
				System.out.println("--- Product Added ---");
			}
			else {
				System.out.println("Failed t Add Product...!");
			}
			
			break;
			
//		Add Multiple Product
		case 2:
			ArrayList<Product> products = new ArrayList<Product>();
			Product product = new Product();
			boolean loopFlag = false;

			
			do {
				
				System.out.print("Enter Product Id : ");
				product.setP_id(myInput.nextInt());
				myInput.nextLine();
				
				System.out.print("Enter Product Name : ");
				product.setP_name(myInput.nextLine());;
				
				System.out.println("Enter Product Price : ");
				product.setP_price(myInput.nextInt());
				myInput.nextLine();
				
				System.out.println("Enter Product Quantity : ");
				int quantity = myInput.nextInt();
				product.setP_quantity(quantity);
				
				boolean availability = false;
				if (quantity>0) {
					availability = true;
				}
				product.setP_availability(availability);
				
				products.add(product);
				
				System.out.println("Type '1' to Add More Product\nType '0' to Stop");
				int moreProduct = myInput.nextInt();
				if (moreProduct==1) {
					loopFlag = true;
				}
				
			} while (loopFlag);
			shopController.addMultipleProducts(products);
			
			break;
			
//		Remove Product
		case 3:
			
			System.out.println("Enter Product Id to Remove : ");
			int productToRemove = myInput.nextInt();
			int removeProduct = shopController.removeProduct(productToRemove);
			
			if (removeProduct>0) {
				System.out.println("Product Removed...");
			}
			else {
				System.out.println("Product Id :"+productToRemove+ " not Exists...!");
			}
			break;
			
//		Fetch Product
		case 4:
			
			System.out.println("Enter Product Id : ");
			int productToFind = myInput.nextInt();
			myInput.nextLine();
			
			ResultSet fetchProduct = shopController.fetchProduct(productToFind);
			
			try {
				
				if (fetchProduct.next()) {
					
					System.out.println("--- PRODUCT DETAILS ---");
					System.out.println("Id : "+fetchProduct.getInt(1));
					System.out.println("Name : "+fetchProduct.getString(2));
					System.out.println("Price : "+fetchProduct.getInt(3));
					System.out.println("Quantity : "+fetchProduct.getInt(4));
					if (fetchProduct.getBoolean(5)) {
						System.out.println("Available...:)");
					} else {
						System.out.println("Not Available...!");
					}
					
					
				} else {

					System.out.println("Product with ");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
//		Update Product
		case 5:
			
			System.out.print("Enter Product Id to Update : ");
			int productToUpdate = myInput.nextInt();
			myInput.nextLine();
			ResultSet updateProduct = shopController.fetchProduct(productToUpdate);
			try {
				if (updateProduct.next()) {
					
				} else {
					System.out.println("Product Id : "+productToUpdate+" not Exists...!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		
			
		default:
			System.out.println("--- INVALID SELECTION ---");
			break;
		}

//	While of Menu Driven Block
	} while (true);
	
	
	}

}

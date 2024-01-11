package com.qsp.shop.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.postgresql.Driver;

import com.qsp.shop.model.Product;

public class ShopController {

//	1. Add Product
	public int addProduct(int id, String name, int price, int quantity, boolean availability) {
		Connection connection = null;
		int executeUpdate = 0;
		
		try {
			
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfiguration.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);
			
			String query = "INSERT INTO product VALUES (?,?,?,?,?);";
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			
			prepareStatement.setInt(1, id);
			prepareStatement.setString(2, name);
			prepareStatement.setInt(3, price);
			prepareStatement.setInt(4, quantity);
			prepareStatement.setBoolean(5, availability);
			
			executeUpdate = prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			if (connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return executeUpdate;
	}
	
//	2. Add Multiple Products
	public void addMultipleProducts(ArrayList<Product> products) {
		Connection connection = null;
		
		try {
			
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfiguration.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);
			
			String query = "INSERT INTO product VALUES (?,?,?,?,?);";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			for (Product product : products) {
				
				preparedStatement.setInt(1, product.getP_id());
				preparedStatement.setString(2, product.getP_name());
				preparedStatement.setInt(3, product.getP_price());
				preparedStatement.setInt(4, product.getP_quantity());
				preparedStatement.setBoolean(5, product.isP_availability()); 
				
				preparedStatement.addBatch();
			}
			
			preparedStatement.executeBatch();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			if (connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
//	3.Remove Product
	public int removeProduct(int id) {
		Connection connection = null;
		int executeUpdate = 0;
		
		try {
			
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfiguration.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);
			
			PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM product WHERE p_id = ?");
			prepareStatement.setInt(1, id);
			
			executeUpdate = prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
		return executeUpdate;
	}
	
	
//	4.Fetch Product
	public ResultSet fetchProduct(int id) {
		
		Connection connection = null;
		ResultSet resultSet = null;
	
		try {
			
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfiguration.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);
			
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM product WHERE p_id = ?");
			prepareStatement.setInt(1, id);
			
			resultSet = prepareStatement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			if (connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
		return resultSet;
	}
	
}

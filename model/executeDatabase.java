/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.DatabaseConnection;

public class executeDatabase {

	private Statement stmt = null;
	private ResultSet rs = null;
	private String query;
	private int result;

	private ResultSet execute(String query) {
		try {
			Connection conn = DatabaseConnection.getconnection();
			stmt = (Statement) conn.createStatement();

			// execute the select query
			rs = stmt.executeQuery(query);

			// stmt.close();
		} catch (SQLException e) {
			System.err.println("Query can not be executed!!");
			e.printStackTrace();
		}

		// Returning the result of executed query at desired location
		return rs;
	}// execute

	private int executeUpdate(String query) {
		try {
			Connection conn = DatabaseConnection.getconnection();
			stmt = (Statement) conn.createStatement();

			// execute the insert,update,delete query
			result = stmt.executeUpdate(query);

			// stmt.close();
		} catch (SQLException e) {
			System.err.println("Update Query can not be executed!!");
			e.printStackTrace();
		}

		// Returning the result of executed query at desired location
		return result;
	}// executeUpdate

	// check if user credentails are correct or not
	public ResultSet getUser(String username, String password) {

		query = "SELECT userid,name,usertype,address FROM user WHERE username = '" + username.toLowerCase()
				+ "' AND password = '" + password + "'";
		
		rs = execute(query);

		return rs;
	}// getUser

	// check if username is available or not
	public ResultSet checkUser(String username) {

		query = "SELECT userid FROM user WHERE username = '" + username.toLowerCase() + "'";
		rs = execute(query);

		return rs;
	}// checkUser

	// add new user to the user list
	public int createUser(String name, String username, String password, String address, int usertype) {

		query = "INSERT INTO user(name,username,password,usertype,address) VALUES(\"" + name + "\",\""
				+ username.toLowerCase() + "\",\"" + password + "\"," + usertype + ",\"" + address + "\")";
		
		synchronized(this) {
			result = executeUpdate(query);
		}
		
		return result;
	}// createUser

	// get all product details from the product list
	public ResultSet getAllProducts(int usertype) {

		if (usertype == 1)
			query = "SELECT * FROM product";
		else if (usertype == 0)
			query = "SELECT * FROM product";

		synchronized(this) {
			rs = execute(query);
		}
		
		return rs;

	}// getAllProducts

	// add new product to the product list
	public int addProduct(int productid, String name, String description, String type, Double prize, int quantity) {

		query = "INSERT INTO product(productid,name,description,type,prize,quantity_available) VALUES(" + productid
				+ ",\"" + name + "\",\"" + description + "\",\"" + type + "\"," + prize + "," + quantity + ")";

		synchronized(this) {
			result = executeUpdate(query);
		}
		
		return result;
	}// addProduct

	// get product quantity for the desired product id
	public ResultSet checkProduct(int productid) {

		query = "SELECT productid,quantity_available FROM product WHERE productid = " + productid;
		
		synchronized(this) {
			rs = execute(query);
		}
		
		return rs;
	}// checkProduct

	// delete the product from the product list
	public int deleteProduct(int productid) {

		query = "DELETE FROM product WHERE productid = " + productid;
		
		synchronized(this) {
			result = executeUpdate(query);
		}
		
		return result;
	}// deleteProduct

	// update the product details
	public int updateProduct(int productid, int update_detail, String info) {

		
			if (update_detail == 1) {
				query = "UPDATE product SET description = \"" + info + "\" WHERE productid = " + productid;
			} else if (update_detail == 2) {
				query = "UPDATE product SET prize = " + Double.parseDouble(info) + " WHERE productid = " + productid;
			} else if (update_detail == 3) {
				query = "UPDATE product SET quantity_available = " + Integer.parseInt(info) + " WHERE productid = "
						+ productid;
			}
			
		synchronized(this) {
			result = executeUpdate(query);
		}
		
		return result;
	}// updateProduct

	// delete the customer from the user list
	public int deleteCustomer(int userid) {

		query = "DELETE FROM user WHERE userid = " + userid;
		
		synchronized(this) {
			result = executeUpdate(query);
		}
		
		return result;
	}// deleteCustomer

	// add product to the customers shopping cart
	public int addProductCart(int userid, int productid, int quantity) {
		query = "INSERT INTO shoppingcart(userid, productid, quantityorder, amount, ispurchased) SELECT " + userid + ","
				+ productid + "," + quantity + "," + quantity + "*prize , 0 FROM product WHERE productid = "
				+ productid;

		synchronized(this) {
			result = executeUpdate(query);
		}
		
		return result;
	}// addProductCart

	// check if product is already added in customer's shopping cart
	public ResultSet checkProductCart(int userid, int productid) {

		query = "SELECT userID,productid,quantityorder FROM shoppingcart Where productid = " + productid
				+ " AND userid = " + userid + " AND ispurchased = 0";
		rs = execute(query);

		return rs;
	}// checkProductCart

	// update the quantity of product if product is already added in customer's
	// cart
	public int updateProductCart(int userid, int productid, int quantity) {

		query = "UPDATE shoppingcart A, (SELECT productid, prize FROM product WHERE productid = " + productid
				+ " ) B SET A.quantityorder = " + quantity + ", A.amount = B.prize * " + quantity
				+ " WHERE A.productid = B.productid AND A.productid = " + productid + " AND A.userid = " + userid
				+ " AND A.ispurchased = 0";
		
		synchronized(this) {
			result = executeUpdate(query);
		}
		
		return result;
	}// updateProductCart

	// get all the product which are in the customer's shopping cart
	public ResultSet getShoppingCart(int userid) {

		query = "SELECT A.shid, A.userid, A.productid, B.name, A.quantityorder, A.amount  FROM shoppingcart AS A, product AS B Where A.productid = B.productid AND A.userid = "
				+ userid + " AND ispurchased = 0";
		rs = execute(query);

		return rs;
	}// getShoppingCart

	// update the quantity if product is purchased
	public int purchaseProduct(int productid, int quantity) {

		query = "UPDATE product SET quantity_available = quantity_available - " + quantity + " WHERE productid = "
				+ productid;
		
		synchronized(this) {
			result = executeUpdate(query);
		}
		
		return result;
	}// purchaseProduct

	// mark if product is purchased
	public int insertPurchase(int shid) {

		query = "UPDATE shoppingcart SET ispurchased = 1 WHERE shid = " + shid;
		
		synchronized(this) {
			result = executeUpdate(query);
		}
		
		return result;
	}// insertPurchase

}// class executeDatabase

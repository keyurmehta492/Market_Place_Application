/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import server.Session;
import server.itemList;

public class AdminModel {

	private List<itemList> items;
	private executeDatabase exedb;
	private ResultSet rs = null;

	private String[] user_details = new String[4];
	private String[] prod_details = new String[6];
	private int productid, quantity, update_detail, userID;
	private String name, description, type;
	private Double prize;

	private int i = 0, result;

	public AdminModel() {
		exedb = new executeDatabase();
	}

	// for Admin user to browse the product
	public List<itemList> adminBrowse(Session session) {
		System.out.println("Admin wants to browse the products!!");
		items = new ArrayList<itemList>();
		try {
			items.add(0, new itemList("browse"));
			i = 1;

			// get all the products and add it in the list one by one
			rs = exedb.getAllProducts(1);

			while (rs.next()) {

				productid = rs.getInt("productid");
				name = rs.getString("name");
				description = rs.getString("description");
				type = rs.getString("type");
				prize = rs.getDouble("prize");
				quantity = rs.getInt("quantity_available");

				// Add product details to the list
				items.add(i++, new itemList(productid, name, description, type, prize, quantity));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return items;
	}// adminBrowse

	// for Admin user to add the product
	public synchronized List<itemList> adminAdd(Session session, String info) {
		System.out.println("Admin wants to add the products!!");
		items = new ArrayList<itemList>();

		// Separate the product details in different variables
		prod_details = info.split(",");

		productid = Integer.parseInt(prod_details[0]);
		name = prod_details[1];
		description = prod_details[2];
		type = prod_details[3];
		prize = Double.parseDouble(prod_details[4]);
		quantity = Integer.parseInt(prod_details[5]);

		System.out.println("" + productid + "," + name + "," + description + "," + type + "," + prize + "," + quantity);

		// check if product id is already present in the list
		rs = exedb.checkProduct(productid);

		try {
			// if product id is not used then add new product
			if (!rs.next()) {
				System.out.println(productid + " productId available!! New Product can be added.");
				result = exedb.addProduct(productid, name, description, type, prize, quantity);

				// if product is added successfully then add success message in
				// the list
				if (result != 0) {
					System.out.println("New product with id: " + productid + " added successfully!!");
					items.add(0, new itemList("1"));
				}

				// if product is not added then add failure message in the list
				else {
					System.out.println("New product with id: " + productid + " can not be added!!");
					items.add(0, new itemList("0"));
				}

				// if product id is already used then add appropriate message in
				// the list
			} else {
				System.out.println("Product ID: " + productid + " is unavailable!!");
				items.add(0, new itemList("-1"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return items;
	}// adminAdd

	// for Admin user to update the product
	public List<itemList> adminUpdate(Session session, String info) {
		System.out.println("Admin wants to update the products!!");

		items = new ArrayList<itemList>();
		prod_details = info.split(",");
		productid = Integer.parseInt(prod_details[0]);
		update_detail = Integer.parseInt(prod_details[1]);
		description = prod_details[2];

		// check if product id is already present in the list
		rs = exedb.checkProduct(productid);

		try {
			// if product id is present then update the product detail
			if (rs.next()) {
				System.out.println(prod_details[0] + " productId available!! Product details can be Updated.");
				result = exedb.updateProduct(productid, update_detail, description);

				// if product is updated successfully then add appropriate
				// message in the list
				if (result != 0) {
					System.out.println("Detail of the Product id: " + productid + " updated successfully!!");
					items.add(0, new itemList("1"));
				}

				// if product is not updated then add appropriate message in the
				// list
				else {
					System.out.println("Detail of the Product id: " + productid + " can not be updated!!");
					items.add(0, new itemList("0"));
				}

				// if product id is not present then add appropriate message in
				// the list
			} else {
				System.out.println("Product ID: " + productid + " is unavailable!!");
				items.add(0, new itemList("-1"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return items;
	}// adminUpdate

	// for Admin user to delete the product
	public List<itemList> adminDelete(Session session, String info) {
		System.out.println("Admin wants to delete the products!!");
		items = new ArrayList<itemList>();

		// check if product id is already present in the list
		rs = exedb.checkProduct(Integer.parseInt(info));

		try {
			// if product id is present then delete the product
			if (rs.next()) {
				productid = rs.getInt("productid");
				System.out.println(productid + " productId available!! Product can be deleted.");
				result = exedb.deleteProduct(productid);

				// if product is deleted successfully then add appropriate
				// message in the list
				if (result != 0) {
					System.out.println("Product with id: " + productid + " deleted successfully!!");
					items.add(0, new itemList("1"));
				}

				// if product is not deleted then add appropriate message in the
				// list
				else {
					System.out.println("Product with id: " + productid + " can not be deleted!!");
					items.add(0, new itemList("0"));
				}

				// if product id is not present then add appropriate message in
				// the list
			} else {
				System.out.println("Product ID: " + info + " is unavailable!!");
				items.add(0, new itemList("-1"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return items;
	}// adminDelete

	public List<itemList> adminAddAdmin(Session session, String info) {
		System.out.println("Admin wants to add new Administrator!!");
		System.out.println(info);
		user_details = info.split(",");

		items = new ArrayList<itemList>();

		// check if username is already present in the list
		rs = exedb.checkUser(user_details[1]);

		try {
			// if username is available then add new admin
			if (!rs.next()) {
				System.out.println(user_details[1] + " username available!! Registration process started.");
				result = exedb.createUser(user_details[0], user_details[1], user_details[2], user_details[3], 1);

				// if admin is added successfully then add appropriate message
				// in the list
				if (result != 0) {
					System.out.println("New Admin " + user_details[0] + " is registered successfully!!");
					items.add(0, new itemList("1"));
				}

				// if admin is not added then add appropriate message in the
				// list
				else {
					System.out.println("New Admin " + user_details[0] + " can not be registered!!");
					items.add(0, new itemList("0"));

				}

				// if username is not available then add appropriate message in
				// the list
			} else {
				System.out.println("Username: " + user_details[1] + " is unavailable!!");
				items.add(0, new itemList("-1"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return items;
	}// adminAddAdmin

	public List<itemList> adminAddCustomer(Session session, String info) {
		System.out.println("Admin wants to add new Customer!!");
		System.out.println(info);
		user_details = info.split(",");

		items = new ArrayList<itemList>();

		// check if username is already present in the list
		rs = exedb.checkUser(user_details[1]);

		try {
			// if username is available then add new customer
			if (!rs.next()) {
				System.out.println(user_details[1] + " username available!! Registration process started.");
				result = exedb.createUser(user_details[0], user_details[1], user_details[2], user_details[3], 0);

				// if customer is added successfully then add appropriate
				// message in the list
				if (result != 0) {
					System.out.println("New Customer " + user_details[0] + " is registered successfully!!");
					items.add(0, new itemList("1"));
				}

				// if customer is not added then add appropriate message in the
				// list
				else {
					System.out.println("New Customer " + user_details[0] + " can not be registered!!");
					items.add(0, new itemList("0"));

				}

				// if username is not available then add appropriate message in
				// the list
			} else {
				System.out.println("Username: " + user_details[1] + " is unavailable!!");
				items.add(0, new itemList("-1"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return items;

	}// adminAddCustomer

	public List<itemList> adminRemoveCustomer(Session session, String info) {
		System.out.println("Admin wants to delete the Customer user!!");
		items = new ArrayList<itemList>();

		// check if username is already present in the list
		rs = exedb.checkUser(info);

		try {
			// if username is present then delete the customer
			if (rs.next()) {
				userID = rs.getInt("userid");
				System.out.println(info + " username present!! Customer user can be deleted.");
				result = exedb.deleteCustomer(userID);

				// if customer is deleted successfully then add appropriate
				// message in the list
				if (result != 0) {
					System.out.println("Customer user with username: " + info + " deleted successfully!!");
					items.add(0, new itemList("1"));
				}

				// if customer is not deleted successfully then add appropriate
				// message in the list
				else {
					System.out.println("Customer user with username: " + info + " can not be deleted!!");
					items.add(0, new itemList("0"));
				}

			}

			// if username is not present then add appropriate message in the
			// list
			else {
				System.out.println("Custoemr user: " + info + " is not present!!");
				items.add(0, new itemList("-1"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return items;

	}// adminRemoveCustomer

} // class AdminModel

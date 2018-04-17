/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import server.Session;
import server.itemList;

public class CustomerModel {

	private executeDatabase exedb;
	private ResultSet rs = null;
	private ResultSet rs2 = null;

	private String[] purchase = new String[2];
	private List<itemList> items;
	private List<String> purchaseOrder;
	private int i = 0, result, result2, flag = 0;

	private int productid, quantity, quantity_ava, userid, shid;
	private String name, description, type;
	private Double prize, amount;

	public CustomerModel() {
		exedb = new executeDatabase();
	}

	// customer user to browses the products
	public List<itemList> custBrowse(Session session) {

		System.out.println("Customer wants to browse the products!!");
		items = new ArrayList<itemList>();
		try {
			items.add(0, new itemList("browse"));
			i = 1;

			// get all the product details whose quantity is not 0
			rs = exedb.getAllProducts(0);

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
	}// custBrowse

	// customer user to shopping cart
	public List<itemList> custShopping(Session session, String info) {
		System.out.println("Customer wants to add the product to his shopping cart!!");
		items = new ArrayList<itemList>();
		userid = session.getUserid();
		flag = 0;

		try {
			// Separate the product details in different variables
			purchase = info.split(",");
			productid = Integer.parseInt(purchase[0]);
			quantity = Integer.parseInt(purchase[1]);

			System.out.println("CustomerID: " + userid + " wants to add a product id: " + productid + " with quantity: "
					+ quantity + " to his cart.");

			// check if product id is present in the product list
			rs = exedb.checkProduct(productid);

			// check if product is already added in the user's shopping cart
			rs2 = exedb.checkProductCart(userid, productid);

			// check if product id is present in the product list
			if (rs.next()) {
				// get the available product quantity
				quantity_ava = rs.getInt("quantity_available");

				// add product to shopping cart if ordered quantity is less than
				// the available quantity
				if (quantity <= quantity_ava && !rs2.next()) {
					System.out.println(
							"Product can be added to the cart as ordered quantity is less than the available quantity!!");
					result = exedb.addProductCart(userid, productid, quantity);

					// if product added to the shopping cart then add
					// appropriate message in the list
					if (result != 0) {
						System.out.println("Product successfully added to the customer's shopping cart!!");
						items.add(0, new itemList("1"));
					}

					// if product not added in the shopping cart then add
					// appropriate message in the list
					else {
						System.out.println("Product NOT added to the customer's shopping cart!!");
						items.add(0, new itemList("0"));
					}

				}
				// User entered quantity is greater than the available quantity
				// then response user that product can not be added to the cart.
				else if (quantity > quantity_ava) {
					System.out.println(
							"Product can NOT be added to the cart as ordered quantity is greater than the available quantity!!");
					items.add(0, new itemList("-2"));
				}

				// if product is already added in the user's shopping cart then
				// update the quantity
				else {
					if (!rs2.isBeforeFirst())
						rs2.beforeFirst();

					if (quantity <= quantity_ava && rs2.next()) {

						System.out.println(
								"Product quantity can be updated in the cart as its already present in cart!!");
						result = exedb.updateProductCart(userid, productid, quantity);

						// if product's quantity is updated in the shopping cart
						// then add appropriate message in the list
						if (result != 0) {
							System.out.println("Product quantity is updated successfully in the shopping cart!!");
							items.add(0, new itemList("2"));
						}

						// if product not added in the shopping cart then add
						// appropriate message in the list
						else {
							System.out.println("Product NOT added to the customer's shopping cart!!");
							items.add(0, new itemList("0"));
						}
					}
				}
			}

			// if product id is not present in the product list
			else {
				System.out.println("Product ID: " + productid + " is unavailable!!");
				items.add(0, new itemList("-1"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return items;
	}// custShopping

	// customer user to browses the products
	public List<itemList> custViewShoppingCart(Session session, String info) {

		System.out.println("Customer wants to view shopping cart!!");
		items = new ArrayList<itemList>();
		userid = session.getUserid();

		try {
			items.add(0, new itemList("viewShoppingCart"));
			i = 1;

			// get all the products from customers shopping cart
			rs = exedb.getShoppingCart(userid);

			while (rs.next()) {

				productid = rs.getInt("productid");
				name = rs.getString("name");
				amount = rs.getDouble("amount");
				quantity = rs.getInt("quantityorder");

				// Add product details to the list
				items.add(i++, new itemList(productid, name, quantity, amount));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return items;
	}// custShopping

	// customer user to purchase product from cart
	public List<itemList> custPurchase(Session session, String info) {
		System.out.println("Customer wants to Purchase the product(s) from his shopping cart!!");
		items = new ArrayList<itemList>();
		purchaseOrder = new ArrayList<String>();
		userid = session.getUserid();
		i = 1;
		flag = 0;

		try {

			// get all the products from customers shopping cart
			rs = exedb.getShoppingCart(userid);

			while (rs.next()) {

				shid = rs.getInt("shid");
				productid = rs.getInt("productid");
				amount = rs.getDouble("amount");
				quantity = rs.getInt("quantityorder");

				// get the available product quantity
				rs2 = exedb.checkProduct(productid);
				rs2.next();
				quantity_ava = rs2.getInt("quantity_available");

				// Purchase the product if ordered quantity is less than the
				// available quantity
				if (quantity <= quantity_ava) {
					System.out.println("Product: " + productid
							+ " can be purchased ordered quantity is less than the available quantity!!");
					result = exedb.purchaseProduct(productid, quantity);

					// if product purchased then add appropriate message in the
					// list
					if (result != 0) {
						System.out.println("Order placed for Product: " + productid + " with quantity: " + quantity);
						result2 = exedb.insertPurchase(shid);
						purchaseOrder.add("Order placed for Product id: " + productid + " with quantity: " + quantity);

					}

					// if product can not be purchased then add appropriate
					// message in the list
					else {
						System.out.println("Order can not be placed for Product: " + productid);
						purchaseOrder.add(
								"Order can NOT be placed for Product id: " + productid + " with quantity: " + quantity);

						flag = 1;
					}

				}

				// product can not be purchased if ordered quantity is greater
				// than the available quantity
				else {
					System.out.println("Product id: " + productid
							+ " can NOT be purchased as ordered quantity is greater than the available quantity!!");
					purchaseOrder.add("Product id: " + productid
							+ " can NOT be purchased as ordered quantity is greater than the available quantity!! Kindly recheck the quantity.");

					flag = 1;
				}

			} // while

			// not all products are purchased
			if (flag == 1) {
				items.add(0, new itemList("Partial Order is placed!!"));
				for (int counter = 0; counter < purchaseOrder.size(); counter++) {
					items.add(new itemList(purchaseOrder.get(counter)));
				}

			}

			// all products are purchased
			else if (flag == 0) {
				items.add(0, new itemList("Complete Order is placed and will be delivered to your door step!!"));
				for (int counter = 0; counter < purchaseOrder.size(); counter++) {
					items.add(new itemList(purchaseOrder.get(counter)));

				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return items;
	}// custPurchase

} // class CustomerModel

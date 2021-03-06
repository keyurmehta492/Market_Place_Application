/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import interfaces.IAdminController;
import interfaces.ICustomerController;
import interfaces.IUserController;
import server.Session;
import server.itemList;

public class RmiClient {

	// Server string at rpc01 machine
	private String userStr = "//in-csci-rrpc01.cs.iupui.edu:2089/userController";
	private String adminStr = "//in-csci-rrpc01.cs.iupui.edu:2089/adminController";
	private String custStr = "//in-csci-rrpc01.cs.iupui.edu:2089/customerController";
	private int result;

	private IUserController userController;
	private IAdminController adminController;
	private ICustomerController custController;
	private Session session = null;
	private List<itemList> items;

	public RmiClient() {
		try {

			// lookup to connect to the server
			userController = (IUserController) Naming.lookup(userStr);
			adminController = (IAdminController) Naming.lookup(adminStr);
			custController = (ICustomerController) Naming.lookup(custStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// send the user credentials to authenticate
	public Session sendRequest(String username, String password) {
		try {
			session = userController.userCheck(username, password);

		} catch (Exception e) {
			System.out.println("Something went wrong in login connection...");
			e.printStackTrace();
		}

		return session;
	}// sendRequest

	public int sendSignUpRequest(String name, String username, String password, String address) {
		try {
			result = userController.userRegister(name, username, password, address);

		} catch (Exception e) {
			System.out.println("Something went wrong in register connection...");
			e.printStackTrace();
		}

		return result;

	}// sendSignUpRequest

	// send admin commands to perform the operation related to Admin user
	public List<itemList> sendAdminRequest(String command, Session session, String info) {

		try {
			switch (command) {

			// send request to browse product for admin user
			case "browse":
				items = adminController.adminBrowseProd(session);
				break;

			// send request to add product for admin user
			case "add":
				items = adminController.adminAddProd(session, info);
				break;

			// send request to update product for admin user
			case "update":
				items = adminController.adminUpdateProd(session, info);
				break;

			// send request to delete product for admin user
			case "delete":
				items = adminController.adminDeleteProd(session, info);
				break;

			// send request for Admin user to add new Administrator
			case "addadmin":
				items = adminController.adminAddAdmin(session, info);
				break;

			// send request for Admin user to add new Customer
			case "addcustomer":
				items = adminController.adminAddCustomer(session, info);
				break;

			// send request for Admin user to remove a Customer
			case "removecustomer":
				items = adminController.adminRemoveCustomer(session, info);
				break;
			}// switch

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return items;
	} // sendAdminRequest

	// send customer commands to perform the operation related to Customer user
	public List<itemList> sendCustomerRequest(String command, Session session, String info) {

		try {
			switch (command) {

			// send request to browse product for customer user
			case "browse":
				items = custController.custBrowseProd(session);
				break;

			// send request to add products in shopping cart for customer user
			case "shoppingCart":
				items = custController.custShoppingCart(session, info);
				break;

			// send request to check shopping cart for customer user
			case "viewShoppingCart":
				items = custController.custViewShoppingCart(session, info);
				break;
				
			// send request to purchase product for customer user
			case "purchaseProduct":
				items = custController.custPurchaseProd(session, info);
				break;

			}// switch

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return items;
	}// sendCustomerRequest

} // class RmiClient

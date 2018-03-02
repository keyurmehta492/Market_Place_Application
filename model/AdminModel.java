/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package model;

import server.Session;

public class AdminModel {

	//for Admin user to browse the product
	public Session adminBrowse(Session session) {
		System.out.println("Admin wants to browse the products!!" );
		return session;
	}

	//for Admin user to add the product
	public Session adminAdd(Session session) {
		System.out.println("Admin wants to add the products!!" );
		return session;
	}
	
	//for Admin user to update the product
	public Session adminUpdate(Session session) {
		System.out.println("Admin wants to update the products!!" );
		return session;
	}
	
	//for Admin user to delete the product
	public Session adminDelete(Session session) {
		System.out.println("Admin wants to delete the products!!" );
		return session;
	}

} // class AdminModel

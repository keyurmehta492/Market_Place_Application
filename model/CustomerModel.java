/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package model;

import server.Session;

public class CustomerModel {

	//customer user to browses the products
	public Session custBrowse(Session session) {
		System.out.println("Customer wants to browse the products!!" );
		return session;
	}
	
	//customer user to shopping cart
	public Session custShopping(Session session) {
		System.out.println("Customer wants to see shopping cart!!" );
		return session;
	}
	
} //class CustomerModel

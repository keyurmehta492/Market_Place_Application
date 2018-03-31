/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package model;

import server.Session;

public class UserModel {
	
	Session session = null;
	
	//Constructor which will create Session object for the user
	public UserModel() {
		session = new Session();
	}
	
	// Ryan: Should you be checking Strings or the Session?
	// FIXED: As per my design, I have to check the strings, as my session is created only after the user is authenticated. 
	//        And once user is authenticated on server side, it will create session object of the user which will have user role in it.
	//        And then this session is returned to client.
	
	
	//Check user credentials are valid and set user role based on the authentication
	public synchronized Session checkAuthentication(String username, String password) {

		System.out.println("User "+ username + " attempts for login!!" );
		
		//Credentials of user are checked for admin type of user
		if(username.equals("admin") && password.equals("admin")) {
			System.out.println("Credential of admin user:  "+ username + " are valid. Allow access!!" );
			
			//Set the session parameters for the user
			session.setUserName(username);
			session.setUserRole("ADMIN");
			session.setIsAuth(1);
			return session;
		}
		
		//Credentials of user are checked for customer type of user
		else if(username.equals("john") && password.equals("john123")) {
			System.out.println("Credential of customer user:  "+ username + " are valid. Allow access!!" );
			
			//Set the session parameters for the user
			session.setUserName(username);
			session.setUserRole("CUSTOMER");
			session.setIsAuth(1);
			return session;
		}
		
		//If user is not authenticated, then block the access 
		else {
			System.out.println("Credential of user:  "+ username + " are invalid. Block access!!" );
			
			//set the session parameter for blocking the access to the user
			session.setUserName(username);
			session.setUserRole("UNAUTHORIZED");
			session.setIsAuth(0);
			return session;
		}
	} //checkAuthentication
	
	//to register new user
	public synchronized int register() {
		System.out.println("New User wants to register!!");
		return 0;
	} //register
	
} //class Login

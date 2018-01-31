/* Assignment 1
 *  
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

public class UserModel {
	
	//Check user credentials are valid and its type
	public int check(String username, String password) {
		System.out.println("User "+ username + " attempts for login!!" );
		//admin user
		if(username.equals("admin") && password.equals("admin")) {
			System.out.println("Credential of admin user:  "+ username + " are valid. Allow access!!" );
			return 1;
		}
		//customer user
		else if(username.equals("john") && password.equals("john123")) {
			System.out.println("Credential of customer user:  "+ username + " are valid. Allow access!!" );
			return 2;
		}
		else {
			System.out.println("Credential of user:  "+ username + " are invalid. Block access!!" );
			return 0;
		}
	} //check
	
	//to register new user
	public int register() {
		System.out.println("New User wants to register!!");
		return 0;
	} //register
	
} //class Login

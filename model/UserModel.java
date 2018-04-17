/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import server.Session;

public class UserModel {

	private Session session = null;
	private executeDatabase exedb;
	private ResultSet rs = null;
	private int userType, userid;
	private int result;
	private String name;

	// Constructor which will create Session object for the user
	public UserModel() {
		session = new Session();
		exedb = new executeDatabase();
	}

	// Ryan: Should you be checking Strings or the Session?
	// FIXED: As per my design, I have to check the strings, as my session is
	// created only after the user is authenticated.
	// And once user is authenticated on server side, it will create session
	// object of the user which will have user role in it.
	// And then this session is returned to client.

	// Check user credentials are valid and set user role based on the
	// authentication
	public Session checkAuthentication(String username, String password) {

		System.out.println("User " + username + " attempts for login!!");

		rs = exedb.getUser(username, password);

		try {
			if (rs.next()) {
				userType = rs.getInt("usertype");
				name = rs.getString("name");
				userid = rs.getInt("userid");

				if (userType == 1) {
					System.out.println("Credential of admin user:  " + username + " are valid. Allow access!!");

					// Set the session parameters for the user
					session.setUserName(name);
					session.setUserRole("ADMIN");
					session.setIsAuth(1);
					session.setUserid(userid);
				}

				// Credentials of user are checked for customer type of user
				else if (userType == 0) {
					System.out.println("Credential of customer user:  " + username + " are valid. Allow access!!");

					// Set the session parameters for the user
					session.setUserName(name);
					session.setUserRole("CUSTOMER");
					session.setIsAuth(1);
					session.setUserid(userid);

				}
			}
			// If user is not authenticated, then block the access
			else {
				System.out.println("Credential of user:  " + username + " are invalid. Block access!!");

				// set the session parameter for blocking the access to the user
				session.setUserName(username);
				session.setUserRole("UNAUTHORIZED");
				session.setIsAuth(0);
				session.setUserid(-1);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return session;
	} // checkAuthentication

	// to register new user
	public int register(String name, String username, String password, String address) {
		System.out.println("New User wants to register!!");

		// check if username is available or not
		rs = exedb.checkUser(username);
		try {
			// register only if username is available
			if (!rs.next()) {
				System.out.println(username + " username available!! Registration process started.");
				result = exedb.createUser(name, username, password, address, 0);

				// if customer is registered successfully then add appropriate
				// message in the list
				if (result != 0) {
					System.out.println("New User " + name + " is registered successfully!!");
					return 1;
				}

				// if customer is not registered then add appropriate message in
				// the list
				else {
					System.out.println("New User " + name + " can not be registered!!");
					return 0;
				}

			}

			// if username is not available then add appropriate message
			else {
				System.out.println("Username: " + username + " is unavailable!!");
				return -1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	} // register

} // class Login

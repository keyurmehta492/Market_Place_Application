/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package server;

import java.io.Serializable;

public class Session implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3747056833221799300L;
	private String userName = null;
	private String userRole = null;
	private int isAuth = 0;
	private int userid;

	public Session() {

	}

	// set the username in session object
	public void setUserName(String userName) {
		this.userName = userName;
	}

	// set the role of the user in session object
	public void setUserRole(String userRole) {
		this.userRole = userRole;

	}

	// set whether user is authenticated or not in session object
	public void setIsAuth(int isAuth) {
		this.isAuth = isAuth;
	}

	// get the session object
	public Session getUser() {
		return this;
	}

	// get the username from session object
	public String getUserName() {
		return this.userName;
	}

	// get the role of the user from session object
	public String getUserRole() {
		return this.userRole;
	}

	// get the information whether user is authenticated or not from session
	// object
	public int getIsAuth() {
		return this.isAuth;
	}

	// get the userid from session object
	public int getUserid() {
		return userid;
	}

	// set user id in session object
	public void setUserid(int userid) {
		this.userid = userid;
	}

}// Session

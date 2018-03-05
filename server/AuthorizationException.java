/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */


package server;

public class AuthorizationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6683198638802313262L;

	//Runtime exception for role based access
	public AuthorizationException(String methodName) {
		super("Invalid Authorization - Access Denined to " + methodName + " operation!");
	}

}// class AuthorizationException

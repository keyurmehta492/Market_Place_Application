/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */


package server;

public class AuthorizationException extends RuntimeException {

	//Runtime exception for role based access
	public AuthorizationException(String methodName) {
		super("Invalid Authorization - Access Denined to " + methodName + " operation!");
	}

}// class AuthorizationException

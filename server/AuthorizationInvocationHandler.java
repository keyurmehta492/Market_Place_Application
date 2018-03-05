/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */


package server;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;

import java.lang.reflect.Method;

import interfaces.RequiresRole;

public class AuthorizationInvocationHandler implements InvocationHandler, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5116188650598444603L;
	private Object objectImpl;

	public AuthorizationInvocationHandler(Object impl) {
		this.objectImpl = impl;
	}
	
	//To check the user has valid role to access the desired operation
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		//check if annotation is present or not for the requested operation using reflection Pattern
		if (method.isAnnotationPresent(RequiresRole.class)) {
			RequiresRole requirerole = method.getAnnotation(RequiresRole.class);
			Session session = (Session) args[0];
			
					
			//Check if user has specific role to access the method
			if (session.getUser().getUserRole().equals(requirerole.value())) {
				
				return method.invoke(objectImpl, args);
			} 
			
			//If user doesn't have role, throw exception
			else {
				//throw new AuthorizationException(method.getName());
				throw new AuthorizationException(method.getName());
			}
		} 
		
		//If role is not specified on operation (annotation is not present), then invoke a method
		else {
			System.out.println("Sepcific role not required. Allowing Access!!");
			return method.invoke(objectImpl, args);
		}
		
	} //invoke

}//class AuthorizationInvocationHandler

/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package interfaces;

import java.util.List;

import server.Session;
import server.itemList;

public interface ICommand {
	
	//Command interface method to execute the commands
	public List<itemList> execute(String info);
		
} //interface ICommand

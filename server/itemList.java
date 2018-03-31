/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package server;

import java.io.Serializable;

public class itemList implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4821499269799817456L;
	
	int itemID;
	String itemName;
	String itemDesc;
	String itemType;
	int itemQuantity;
	Double itemPrize;
	String message;
	
	public itemList(int itemID, String itemName, String itemDesc, String itemType, int itemQuantity, Double itemPrize) {
		
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.itemType = itemType;
		this.itemQuantity = itemQuantity;
		this.itemPrize = itemPrize;
	}
	
	
	public itemList(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	public int getItemID() {
		return itemID;
	}
	public String getItemName() {
		return itemName;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public String getItemType() {
		return itemType;
	}
	public int getItemQuantity() {
		return itemQuantity;
	}
	public Double getItemPrize() {
		return itemPrize;
	}

}

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
	
	//list of type itemList will be created which is used for storing all product details
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
	
	//get message regarding operation is successful or not
	public String getMessage() {
		return message;
	}
	
	//get the itemID for the product
	public int getItemID() {
		return itemID;
	}
	
	//get the itemName for the product
	public String getItemName() {
		return itemName;
	}
	
	//get the itemDesc for the product
	public String getItemDesc() {
		return itemDesc;
	}
	
	//get the itemType for the product
	public String getItemType() {
		return itemType;
	}
	
	//get the itemQuantity for the product
	public int getItemQuantity() {
		return itemQuantity;
	}
	
	//get the itemPize for the product
	public Double getItemPrize() {
		return itemPrize;
	}

} //class itemList

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

	private int itemID;
	private String itemName;
	private String itemDesc;
	private String itemType;
	private int itemQuantity;
	private Double itemPrize;
	private String message;

	// list of type itemList will be created which is used for storing all
	// product details
	public itemList(int itemID, String itemName, String itemDesc, String itemType, Double itemPrize, int itemQuantity) {

		this.itemID = itemID;
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.itemType = itemType;
		this.itemPrize = itemPrize;
		this.itemQuantity = itemQuantity;

	}

	// list of type itemList will be created which is used for storing all
	// shopping cart details
	public itemList(int productid, String name, int Quantity, Double amount) {

		this.itemID = productid;
		this.itemName = name;
		this.itemPrize = amount;
		this.itemQuantity = Quantity;

	}

	//Set the message for operation/result type
	public itemList(String message) {
		this.message = message;
	}

	// get message regarding operation is successful or not
	public String getMessage() {
		return this.message;
	}

	// get the itemID for the product
	public int getItemID() {
		return this.itemID;
	}

	// get the itemName for the product
	public String getItemName() {
		return this.itemName;
	}

	// get the itemDesc for the product
	public String getItemDesc() {
		return itemDesc;
	}

	// get the itemType for the product
	public String getItemType() {
		return itemType;
	}

	// get the itemQuantity for the product
	public int getItemQuantity() {
		return itemQuantity;
	}

	// get the itemPice for the product
	public Double getItemPrize() {
		return itemPrize;
	}

} // class itemList

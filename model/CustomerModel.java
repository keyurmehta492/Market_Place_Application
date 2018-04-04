/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import server.Session;
import server.itemList;

public class CustomerModel {

	File fp=new File("productList.txt"); //file contains all the products of Market place
	String fdata,newData;
	String[] prod_details = new String[6];
	String[] purchase = new String[2];
	List<itemList> items;
	FileReader fr;
	BufferedReader br;
	int i=0,flag = 0,newQuantity;
	
	//customer user to browses the products
	public List<itemList> custBrowse(Session session) {
		
		System.out.println("Customer wants to browse the products!!" );
		items = new ArrayList<itemList>();
		try {
			//Check if given file is present or not
			if(fp.exists()) {
				
					fr = new FileReader(fp);
					br=new BufferedReader(fr);
					fdata = br.readLine();
					items.add(0,new itemList("browse"));
					i=1;
					
					//Read the entire product file line by line
					while(fdata!=null)
					{
						//Separate the product details in different variables
						prod_details = fdata.split(",");
						
						//Add product details to the list
						items.add(i++,new itemList(Integer.parseInt(prod_details[0]),prod_details[1],
								prod_details[2],prod_details[3],Integer.parseInt(prod_details[4]),
								Double.parseDouble(prod_details[5])));
						fdata = br.readLine();
					}
					fr.close();
					br.close();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return items;
	}//custBrowse
	
	//customer user to shopping cart
	public synchronized List<itemList> custShopping(Session session, String info) {
		System.out.println("Customer wants to add the product to his shopping cart!!" );
		items = new ArrayList<itemList>();
		
		try {
			//Separate the product details in different variables
			purchase = info.split(",");
			System.out.println("Customer wants to add a product id: " + purchase[0] +" with quantity: " 
					+ purchase[1] + "to his cart.");
			
			//Check if given file is present or not
			if(fp.exists()) {
				
					fr = new FileReader(fp);
					br=new BufferedReader(fr);
					fdata = br.readLine();
					flag=0;
					
					//Read the entire product file line by line
					while(fdata!=null)
					{
						//Separate the product details in different variables
						prod_details = fdata.split(",");
						
						//Check if product is available in the product list file
						if(Integer.parseInt(purchase[0]) == Integer.parseInt(prod_details[0])) {
							
							//check if the available quantity of the product is greater than then user entered quantity
							//if true add product to the cart
							if (Integer.parseInt(purchase[1]) <= Integer.parseInt(prod_details[4])) {
								System.out.println("Product added to the cart as ordered quantity is less than the available quantity!!");
								items.add(0,new itemList("1"));
								flag=1;
								break;
							}
							
							//User entered quantity is greater than the available quantity
							//then response user that product can not be added to the cart.
							else {
								System.out.println("Product can NOT be added to the cart as ordered quantity is greater than the available quantity!!");
								items.add(0,new itemList("0"));
								flag=1;
								break;
							}
						}
												
						fdata = br.readLine();
											
					}//while
					
					//if product id is not present in the list
					if(flag==0){
						System.out.println("Invalid product ID!!");
						items.add(0,new itemList("-1"));
					}
					fr.close();
					br.close();
					
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return items;
	}//custShopping
	
	//customer user to purchase product from cart
	public synchronized List<itemList> custPurchase(Session session, String info) {
		System.out.println("Customer wants to Purchase a product!!" );
		items = new ArrayList<itemList>();
		
		try {
			//create temp file
			File tempFile = new File("tempfile.txt");
			FileWriter fw = new FileWriter(tempFile);
        	PrintWriter pw = new PrintWriter(fw);
        	
        	//Separate the product details in different variables
			purchase = info.split(",");
			System.out.println("Customer wants to purchase the product id: " + purchase[0] +" with quantity: " 
					+ purchase[1] );
			
			//Check if given file is present or not
			if(fp.exists()) {
				
					fr = new FileReader(fp);
					br=new BufferedReader(fr);
					fdata = br.readLine();
					
					//Read the entire product file line by line
					while(fdata!=null)
					{
						//Separate the product details in different variables
						prod_details = fdata.split(",");
						
						//Check if product is available in the productlist file
						if(Integer.parseInt(purchase[0]) == Integer.parseInt(prod_details[0])) {
							
							//check if the available quantity of the product is greater than then user entered quantity
							//if true update the quantity of the product and write to temp file with new quantity
							if (Integer.parseInt(purchase[1]) <= Integer.parseInt(prod_details[4])) {
								newQuantity = Integer.parseInt(prod_details[4]) - Integer.parseInt(purchase[1]);
								newData = prod_details[0]+","+prod_details[1]+","+prod_details[2]+","+prod_details[3]+","
								+newQuantity+","+prod_details[5];
								System.out.println("Product Purchased by the customer.");
								System.out.println("Quantity of product id: " + purchase[0] + " is updated to " + newQuantity);
								pw.println(newData);
								pw.flush();
								items.add(0,new itemList("1"));
																
							}
							//User entered quantity is greater than the available quantity
							//then response user that product can not be purchased.
							else {
								System.out.println("Product can NOT be purchased as ordered quantity is greater than the available quantity!!");
								pw.println(fdata);
								pw.flush();
								items.add(0,new itemList("0"));
								
							}
						}
						//if different product then write directly to the temp file
						else {
							pw.println(fdata);
							pw.flush();
						}
												
						fdata = br.readLine();
											
					}
					fr.close();
					br.close();
					pw.close();
			       
			        
					// Delete the original file
			        fp.delete();
			            
			        // Rename the new file to the filename the original file had.
			        tempFile.renameTo(fp);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
	}//custPurchase
	
} //class CustomerModel

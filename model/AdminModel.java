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

public class AdminModel {

	List<itemList> items;
	File fp=new File("productList.txt"); //file contains all the products of Market place
	String[] prod_details = new String[6];
	String[] prod_get = new String[6];
	FileReader fr;
	BufferedReader br;
	FileWriter fw;
	PrintWriter pw;
	String fdata,newData;
	int i=0,flag=0;
	
	//for Admin user to browse the product
	public List<itemList> adminBrowse(Session session) {
		System.out.println("Admin wants to browse the products!!" );
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
	}//adminBrowse

	//for Admin user to add the product
	public synchronized List<itemList> adminAdd(Session session,String info) {
		System.out.println("Admin wants to add the products!!" );
		items = new ArrayList<itemList>();
				
		try {
			//Check if given file is present or not
			if(fp.exists()) {
			//Open the file in Append mode
			fw = new FileWriter(fp,true);
			
			fr = new FileReader(fp);
			br=new BufferedReader(fr);
			
			//Separate the product details in different variables
			prod_details = info.split(",");
			newData = prod_details[0]+","+prod_details[1]+","+prod_details[2]+","+
					prod_details[3]+","+prod_details[4]+","+prod_details[5];
			System.out.println(newData);
			
			fdata = br.readLine();
			flag=0;
			
			//Read the entire product file line by line
			while(fdata!=null)
			{
				//Separate the product details in different variables
				prod_get = fdata.split(",");
				
				//Check if product id is already present in the file
				if(Integer.parseInt(prod_details[0]) == Integer.parseInt(prod_get[0])) {
					flag=1;
					break;
				}
				fdata = br.readLine();
			}
			
			//If product id is not available then add product to the list.
			if(flag==0) {
				fw.write(newData+"\n");
				System.out.println("New Product is added to the Product List.");
				items.add(0,new itemList("1"));
				
			}
			//If product id is already available then do not add product to the list and send message.
			else if(flag==1){
				System.out.println("New Product can NOT be added to the Product List as same Product ID is present in the list.");
				items.add(0,new itemList("0"));
			}
			
			fw.flush();
			fw.close();
			fr.close();
			br.close();
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		return items;
	}//adminAdd
	
	//for Admin user to update the product
	public List<itemList> adminUpdate(Session session) {
		System.out.println("Admin wants to update the products!!" );
		return items;
	}//adminUpdate
	
	//for Admin user to delete the product
	public List<itemList> adminDelete(Session session) {
		System.out.println("Admin wants to delete the products!!" );
		return items;
	}//adminDelete

} // class AdminModel

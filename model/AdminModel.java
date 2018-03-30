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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import server.Session;
import server.itemList;

public class AdminModel {

	List<itemList> items;
	File fp=new File("productList.txt");
	String[] temp = new String[6];
	String[] temp2 = new String[6];
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
			
			if(fp.exists()) {
				
					fr = new FileReader(fp);
					br=new BufferedReader(fr);
					fdata = br.readLine();
					items.add(0,new itemList("browse"));
					i=1;
					while(fdata!=null)
					{
						temp = fdata.split(",");
						
						items.add(i++,new itemList(Integer.parseInt(temp[0]),temp[1],
								temp[2],temp[3],Integer.parseInt(temp[4]),Double.parseDouble(temp[5])));
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
	}

	//for Admin user to add the product
	public List<itemList> adminAdd(Session session,String info) {
		System.out.println("Admin wants to add the products!!" );
		items = new ArrayList<itemList>();
				
		try {
			if(fp.exists()) {
			fw = new FileWriter(fp,true);
			
			fr = new FileReader(fp);
			br=new BufferedReader(fr);
			
			temp = info.split(",");
			newData = temp[0]+","+temp[1]+","+temp[2]+","+temp[3]+","+temp[4]+","+temp[5];
			System.out.println(newData);
			fdata = br.readLine();
			flag=0;
			while(fdata!=null)
			{
				temp2 = fdata.split(",");
				if(Integer.parseInt(temp[0]) == Integer.parseInt(temp2[0])) {
					flag=1;
					break;
				}
				fdata = br.readLine();
			}
			
			if(flag==0) {
				fw.write(newData+"\n");
				System.out.println("New Product is added to the Product List.");
				items.add(0,new itemList("1"));
				
			}
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
	}
	
	//for Admin user to update the product
	public List<itemList> adminUpdate(Session session) {
		System.out.println("Admin wants to update the products!!" );
		return items;
	}
	
	//for Admin user to delete the product
	public List<itemList> adminDelete(Session session) {
		System.out.println("Admin wants to delete the products!!" );
		return items;
	}

} // class AdminModel

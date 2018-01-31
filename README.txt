# csci50700_spring2018_marketplace

Assignment 1: Keyur Kirti Mehta

Below mentioned all the files are required to run the marketPlace Application.
MarketPlaceServer.java
User.java
IUserController.java
UserController.java
UserModel.java
Customer.java
ICustomerController.java
CustomerController.java
CustomerModel.java
Administrator.java
IAdminController.java
AdminController.java
AdminModel.java
makefile
policy

makefile will compile all the java files and create respective class files

Below are the steps to execute the application:
0. Login to Tesla
	a. Open putty
	b. enter host name: tesla.cs.iupui.edu 
	c. port: 22
	d. Click open
	e. Enter valid credentials

1. Run the registry on port 2010
	a. Open the console as mentioned in 0 and execute the below command
	b. rmiregistry 2010&

2. Open a console and execute below commands to run the server:
	a. cd %FilePath%
	b. make
	c. java -Djava.security.policy=policy MarketPlaceServer

3. Open a new console and execute below commands to run client:
	a. cd %FilePath%
	b. java -Djava.security.policy=policy User

4. To Stop the registry:
	a. Stop the server by ctrl + c
	b. type 'fg' and enter
	
Credentials:
Once client starts, it will have 2 options:
1. Login: currently credentials are hard-coded in the server which are as follows:
				Username	Password
admin user		admin		admin
Customer User	john		john123

Based on the selected type of user, different options (view) will be displayed. And further if those options are selected, server will prompt respective message showing working of rmi communication between client and server.
 
2. Register: Though this has not been implemented yet, if this option is selected server will show message showing rmi connection is working. 

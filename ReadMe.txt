Assignment 3: Keyur Kirti Mehta

Main class are as follows:
server = MarketPlaceServer.java
client = Client.java

Other Java files are:
view = Administrator.java, Customer.java, User.java
clientController = FrontController.java, Dispatcher.java
rmi = RmiClient.java, RmiServer.java
interfaces = IAdminController.java, ICustomerController.java, IUserController.java, ICommand.java, RequiresRole
serverController = AdminController.java, CustomerController.java, UserController.java
model = AdminModel.java, CustomerModel.java, UserModel.java
command = CommandAdd.java, CommandBrowse.java, CommandCBrowse.java, CommandCPurchaseProd.java, CommandCShoppingCart.java, CommandDelete.java, CommandUpdate.java, CommandInvoker.java
abstractFactory = AbstractFact.java, AbstractView.java, ConcreteAdminFact.java, ConcreteCustFact.java
server= AuthorizationException.java, AuthorizationInvocationHandler.java, Session.java, itemList.java

makefile will compile all the java files and create respective class files

Below are the steps to execute the application:
0. Login to in-csci-rrpc01.cs.iupui.edu as my server is on this machine
	a. Open putty
	b. enter host name: in-csci-rrpc01.cs.iupui.edu
	c. port: 22
	d. Click open
	e. Enter valid credentials

1. Run the registry on port 2011
	a. Open the console as mentioned in 0 and execute the below command
	b. rmiregistry 2011&

2. Open a console and execute below commands to run the server:
	a. cd %FilePath%
	b. make
	c. java -Djava.security.policy=policy server.MarketPlaceServer

3. Open a new console and execute below commands to run client:
   Client can be run on any of the below mentioned 5 machines concurrently:
   in-csci-rrpc02.cs.iupui.edu
   in-csci-rrpc03.cs.iupui.edu
   in-csci-rrpc04.cs.iupui.edu
   in-csci-rrpc05.cs.iupui.edu
   in-csci-rrpc06.cs.iupui.edu
   
	a. cd %FilePath%
	b. java -Djava.security.policy=policy client.Client

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
Before accessing this operation, user role is checked and if role is mismatched then user will be prompted with unauthorized access message.
(As admin and customer has different views and controller, it won't show any authorized access error. But functionality with the message was captured in screenshot and mentioned in report)
 
2. Register: This has not been implemented yet.


Admin functionalities:
1. Browse Product: Admin user can view all the product with its details along with product whose quantity is 0
2. Add Product: Admin can add new product to the product list. It will check if the product id is repeated or not. Same product id can not be entered twice.

Customer functionalities:
1. Browse Product: Customer user can view all the available product with its details. Customer can only see products whose quantity greater than 0.
2. Add product to shopping cart: Customer user can add product to the shopping cart. If the requested quantity is less than or equal to available quantity than can add product to shopping cart. Or else user will be prompted with message.
3. Purchase the product: Once product is added to the cart, user will be asked to purchase it. If confirmation given then product quantity is again checked and if valid then product is purchased and respective quantity is subtracted from the list.

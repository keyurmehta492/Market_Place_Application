Assignment 5: Keyur Kirti Mehta

Main class are as follows:
server = MarketPlaceServer.java
client = Client.java

Other Java files are:
view = Administrator.java, Customer.java, User.java
clientController = FrontController.java, Dispatcher.java
rmi = RmiClient.java, RmiServer.java
interfaces = IAdminController.java, ICustomerController.java, IUserController.java, ICommand.java, RequiresRole.java
serverController = AdminController.java, CustomerController.java, UserController.java
model = AdminModel.java, CustomerModel.java, UserModel.java, executeDatabase.java
command = CommandAdd.java,CommandAddAdmin.java, CommandAddCustomer.java, CommandBrowse.java, CommandCBrowse.java, CommandCPurchaseProd.java, CommandCShoppingCart.java, CommandCViewShoppingCart.java,CommandDelete.java, CommandUpdate.java,CommandRemoveCustomer.java, CommandInvoker.java
abstractFactory = AbstractFact.java, AbstractView.java, ConcreteAdminFact.java, ConcreteCustFact.java
server= AuthorizationException.java, AuthorizationInvocationHandler.java, Session.java, itemList.java, DatabaseConnection.java

makefile will compile all the java files and create respective class files

Below are the steps to execute the application:
0. Login to in-csci-rrpc01.cs.iupui.edu as my server and database is on this machine
	a. Open putty
	b. enter host name: in-csci-rrpc01.cs.iupui.edu
	c. port: 22
	d. Click open
	e. Enter valid credentials

1. Run the registry on port 2089
	a. Open the console as mentioned in 0 and execute the below command
	b. rmiregistry 2089&

2. Open a console and execute below commands to run the server:
	a. cd %FilePath%
	b. make
	c. java -cp ".:mysql-connector.jar" -Djava.security.policy=policy server.MarketPlaceServer

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
1. Login: Below are some standard user credentails. But as per functionality new users can also be created.
				Username	Password
admin user		admin		admin
Customer User	john		john123
				keyur		keyur

Based on the selected type of user, different options (view) will be displayed. And further if those options are selected, server will prompt respective message showing working of rmi communication between client and server. 
Before accessing this operation, user role is checked and if role is mismatched then user will be prompted with unauthorized access message.
(As admin and customer has different views and controller, it won't show any authorized access error. But functionality with the message was captured in screenshot and mentioned in report)
 
2. Register: New customer can register itself. User needs to enter all the mentioned fields and if the username entered is available, then new customer is registered to the system.
			Then he needs to login again in order to access the system.


Admin functionalities:
1. Browse Product: Admin user can view all the product with its details. Admin will get to see the products whose quantity is 0 as well.
2. Add Product: Admin can add new product to the product list. It will check if the product id is repeated or not. Same product id can not be entered twice.
				Admin needs to enter all the require fields in order to add the product.
3. Update product: Admin needs to enter the product id. Then he can choose which details needs to be updated (Description, price, Quantity). Based on the selection and new details
				if product id is present, then the detail will be updated. 
4. Remove product: Admin needs to enter the product id. If product id is present, then the product is deleted from the product list.
				Or else user will be prompted with message.
5. Add Administrator: Admin will add the details of the new administrator, and if username is available then new admin will be created.
				Or else user will be prompted with message. 				
6. Add Customer: Admin will add the details of the new customer, and if username is available then new customer will be created.
				Or else user will be prompted with message.
7. Remove Customer:  Admin will add the username of the customer whose account needs to be deleted, and if username is present then customer will be removed. 
				Or else user will be prompted with message.


Customer functionalities:
1. Browse Product: Customer user can view all the available product with its details. Customer can only see products whose quantity greater than 0.
2. Add product to shopping cart: Customer user can add product to the shopping cart. If the requested quantity is less than or equal to available quantity than can add product to shopping cart. Or else user will be prompted with message.
				If customer tries to enter same product twice then it will added only once, with the latest ordered quantity. Customer can add multiple products in his cart.
3. View shopping cart and Purchase the product: User can view his entire shopping cart. If shopping cart contains any product, then customer will get option to purchase.
				Once customer gives confirmation to purchase, each product in the cart will check again for quantity. Product with valid product will be purchased. and if quantity ordered is more than available, product will remain in the cart and order detail will give details about it.
				Customer can then change the quantity from option 2.
		
		

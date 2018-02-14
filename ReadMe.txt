Assignment 2: Keyur Kirti Mehta

Main class are as follows:
server = MarketPlaceServer.java
client = Client.java

Other Java files are:
view = Administrator.java, Customer.java, User.java
clientController = FrontController.java, Dispatcher.java
rmi = RmiClient.java, RmiServer.java
interfaces = IAdminController.java, ICustomerController.java, IUserController.java, ICommand.java
serverController = AdminController.java, CustomerController.java, UserController.java
model = AdminModel.java, CustomerModel.java, UserModel.java, CommandInvoker.java
command = CommandAdd.java, CommandBrowse.java, CommandCBrowse.java, CommandCShoppingCart.java, CommandDelete.java, CommandUpdate.java
abstractFactory = AbstractFact.java, ConcreteAdminFact.java, ConcreteCustFact.java, FactoryDecider.java

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
	c. java -Djava.security.policy=policy server.MarketPlaceServer

3. Open a new console and execute below commands to run client:
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
 
2. Register: Though this has not been implemented yet.
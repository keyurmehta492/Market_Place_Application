JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
server/MarketPlaceServer.java\
client/Client.java\
server/AuthorizationInvocationHandler.java\
server/AuthorizationException.java\
server/Session.java\
server/itemList.java\
view/Administrator.java\
view/Customer.java\
view/User.java\
clientController/FrontController.java\
clientController/Dispatcher.java\
rmi/RmiClient.java\
rmi/RmiServer.java\
interfaces/IAdminController.java\
interfaces/ICustomerController.java\
interfaces/IUserController.java\
interfaces/ICommand.java\
interfaces/RequiresRole.java\
serverController/AdminController.java\
serverController/CustomerController.java\
serverController/UserController.java\
model/AdminModel.java\
model/CustomerModel.java\
model/UserModel.java\
command/CommandInvoker.java\
command/CommandAdd.java\
command/CommandBrowse.java\
command/CommandCBrowse.java\
command/CommandCShoppingCart.java\
command/CommandCPurchaseProd.java\
command/CommandDelete.java\
command/CommandUpdate.java\
abstractFactory/AbstractFact.java\
abstractFactory/ConcreteAdminFact.java\
abstractFactory/ConcreteCustFact.java\
abstractFactory/AbstractView.java\

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) */*.class
JCC = javac
default: Master.class Client.class

Master.class: MarketPlaceServer.java
	$(JCC) MarketPlaceServer.java

Client.class: User.java
	$(JCC) User.java

clean:
	$(RM) *.class

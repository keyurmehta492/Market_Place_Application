/* Honor Pledge: 
 * 
 * I pledge that I have neither given nor received any help on this assignment.
 * -mehtake 
 */

package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	public static Connection conn = null;

	// Db connection credential
	public static String hostname = "localhost:3306";
	public static String dbName = "mehtake_db";
	public static String url = "jdbc:mysql://" + hostname + "/" + dbName;
	public static String username = "mehtake";
	public static String password = "oodp507";

	public static Connection getconnection() {

		// if connection is not created then only create a new connection
		if (conn == null) {
			try {
				conn = (Connection) DriverManager.getConnection(url, username, password);
				System.out.println("MarketPlace Database connected!!");

			} catch (SQLException e) {

				throw new IllegalStateException("Cannot connect the database!", e);
			}
		}

		// or return already created connection
		return conn;
	}// getconnection

}// class DatabaseConnection

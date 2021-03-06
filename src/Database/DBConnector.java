package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {

	private static final String ms_driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	private static final String URL = "jdbc:mysql://localhost:/vestbjerg?autoReconnect=true&useSSL=false";
	
	
	

	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		Statement stmt = null;
		try {
			// Register JDBC driver
			Class.forName(ms_driver);
		} catch (Exception e) {
			System.out.println(e);
		}
		// Open a connection
//		connection = DriverManager.getConnection(URL_MS);
//		System.out.println("Connected to DB " + connection);
//		stmt = connection.createStatement();
//
//		String sql = "CREATE TABLE CUSTOMER " + 
//					 "(id INT, " + 
//					 "name VARCHAR(255), " + 
//					 "address VARCHAR(255), " + 
//					 "zip VARCHAR(255), " + 
//					 "city VARCHAR(255), " + 
//					 "phone VARCHAR(255), " +
//				     "type VARCHAR(255), " +
//					 "PRIMARY KEY ( id ))";
//
//		stmt.executeUpdate(sql);
//		System.out.println("Created table in given database...");

	}

	public Connection createConnection() throws SQLException {

		Connection connection = DriverManager.getConnection(URL_MS);

		if (connection == null) {
			throw new SQLException("Could not connect to database");
		}

		return connection;
	}
}

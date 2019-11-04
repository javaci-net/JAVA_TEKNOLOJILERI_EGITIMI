package net.javaci.dbsample;

import java.sql.*;

public class JdbcApp2WithPreparedSt {

	// EMBEDDED FILE
	private static final String DB_DERBY_URL_FILE = "jdbc:derby:"
			// + "c:\\MyDB\\demo"
			+ "./.mydb1/" 
			+ ";create=true";
	
	// EMBEDDED IN-MEMORY
	private static final String DB_DERBY_URL_MEMORY = "jdbc:derby:" 
			+ "memory:mydb1" 
			+ ";create=true";
	
	// CONNECT TO SERVER (First start derby db: derbyrun.jar server start)
	private static final String DB_DERBY_URL_SERVER = "jdbc:derby:"
			+ "//localhost:1527/mydb1" 
			+ ";create=true";
	
	private static final String DB_MYSQL_URL_MYSQL = "jdbc:mysql:"
			+ "//localhost:3306/mydb1";
	
	private static final String CURRENT_DB_CONNECTION_STRING = DB_MYSQL_URL_MYSQL;

	public static void main(String[] args) throws Exception {

		Connection dbConn = DriverManager.getConnection(CURRENT_DB_CONNECTION_STRING, "root", "");

		Statement stmt = dbConn.createStatement();

		dropTableIfExists(stmt);
		createTable(stmt);
		insertRows(stmt);
		insertRowsWithPreparedStatement(dbConn.prepareStatement("INSERT INTO students VALUES (?,?)"));
		selectResults(stmt);
	}

	private static void dropTableIfExists(Statement stmt) {
		try {
			stmt.executeUpdate("DROP TABLE students");
		} catch(Exception e) {
			System.out.println("No need to drop table, probably it does not exist.");
		}
	}

	private static int createTable(Statement stmt) throws SQLException {
		return stmt.executeUpdate("CREATE TABLE students (id int primary key, name varchar(30))");
	}

	private static void insertRows(Statement stmt) throws SQLException {
		stmt.executeUpdate("INSERT INTO students VALUES (1,'tom')");
		stmt.executeUpdate("INSERT INTO students VALUES (2,'peter')");
	}
	
	private static void insertRowsWithPreparedStatement(PreparedStatement stmt) throws SQLException {
		stmt.setInt(1, 3);
		stmt.setString(2, "volkan");
		stmt.executeUpdate();
	}

	private static void selectResults(Statement stmt) throws SQLException {
		// query
		ResultSet rs = stmt.executeQuery("SELECT * FROM students");

		// print out query result
		while (rs.next()) {
			System.out.printf("%d\t%s\n", rs.getInt("id"), rs.getString("name"));
		}
	}

}

package net.javaci.dbsample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcApp4WithTrxMng {

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
	
	private static final String CURRENT_DB_CONNECTION_STRING = DB_DERBY_URL_MEMORY;

	public static void main(String[] args) throws Exception {

		Connection dbConn = DriverManager.getConnection(CURRENT_DB_CONNECTION_STRING, "root", "");
		dbConn.setAutoCommit(false); // by default, it is true
		
		Statement stmt = dbConn.createStatement();

		dropTableIfExists(stmt); 
		createTable(stmt); 
		dbConn.commit();
		
		PreparedStatement insertStatement = dbConn.prepareStatement("INSERT INTO students VALUES (?,?)");
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
		do {
			
			System.out.println("enter id");  
			int id=Integer.parseInt(br.readLine());  
			
			System.out.println("enter name");  
			String name=br.readLine();  
			
			try {
				insertRowsWithPreparedStatement(insertStatement, id, name);
			} catch(Exception e) {
				System.out.println("Error executing queries" + e.getMessage());  
			}
				
			// Continue or End ? *******************************************
			System.out.println("Want to add more records y/n");  
			String ans=br.readLine();  
			if(ans.equals("n")){  
				break;  
			}  
			
		} while(true);
		
		// Commit / Rollback ? ****************************************
		System.out.println("commit/rollback");  
		String answer=br.readLine();  
		if(answer.equals("commit")){
			dbConn.commit();  
			System.out.println("record successfully saved");  
		}  
		if(answer.equals("rollback")){  
			dbConn.rollback();  
			System.out.println("record successfully rollbacked");  
		}  
	
		System.out.println("ALL RECORDS : "); 
		selectResults(stmt);
		
		dbConn.commit();
		dbConn.close();	
	}

	private static void insertRowsWithPreparedStatement(PreparedStatement stmt, int id, String name) 
			throws SQLException {
		stmt.setInt(1, id);
		stmt.setString(2, name);
		stmt.executeUpdate();
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

	

	private static void selectResults(Statement stmt) throws SQLException {
		// query
		ResultSet rs = stmt.executeQuery("SELECT * FROM students");

		// print out query result
		while (rs.next()) {
			System.out.printf("%d\t%s\n", rs.getInt("id"), rs.getString("name"));
		}
	}

}

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private static final String username = "root";
	

	private static final String password = "  "; // colocar sua senha entre as aspas " "
	

    private static final String databaese_url = "jdbc:mysql://localhost:3306/     "; // colocar o nome do bd entre as / e "
   
	
    public static Connection createConnectionToMySql() {
    	
        try {
        	
            Class.forName("com.mysql.cj.jdbc.Driver");
           
            System.out.printf("[LOG] Database driver's found.\n");
        } catch (ClassNotFoundException e) {
        	
        	System.out.printf("[ERROR] Database driver's not found. Message:\n%s .\n", e.getMessage());
        }

        try {
        	
            Connection connection = DriverManager.getConnection(databaese_url,username,password);
            
            System.out.println("[LOG] Connected to database.\n");
            
            return connection;
        } catch (SQLException e) {
        	
            System.out.printf("[ERROR] Can't connect to database. Message:\n%s .\n", e.getMessage());
            return null;
        }
    }
}
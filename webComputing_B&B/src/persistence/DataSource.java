package persistence;

import java.sql.Connection;
import java.sql.DriverManager;

class DataSource {
	
	final private String dbURI;// = "jdbc:postgresql://localhost/test;"
	final private String userName;// ="postgres";
	final private String password;// ="postgres";
	
	public DataSource(String dbUri, String user, String pass)
	{
		this.dbURI = dbUri;
		this.userName = user;
		this.password = pass;
	}
	
	
	public Connection getConnection() throws PersistenceException{
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(dbURI, userName, password);
			System.out.println("CONNESSO AL DATABASE, classe DataSource");
			
		} catch (Exception e) {
			System.out.println("connection: " + e.getMessage());
			throw new PersistenceException(e.getMessage());
		}
		return connection;
	}
}

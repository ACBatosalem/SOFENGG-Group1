package cso.dlsu.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.sqlite.JDBC;

import cso.dlsu.bean.Organization;

public class APSConnection {
	private static final String DRIVER = "jdbc:sqlite:";
	private static final String DB = "D:/dlsu-cso/aps.db";
	private static APSConnection instance;
	
	public static APSConnection getInstance () {
		if (instance == null)
			instance = new APSConnection();
		return instance;
	}
	
	private APSConnection () {
		createTables();
	}
	
	public Connection connect () {
		Connection connection = null;
		String url = DRIVER + DB;
		
		try {
			Class.forName(JDBC.class.getName());
			connection = DriverManager.getConnection(url);
			System.out.println("[" + getClass().getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful connection to " + DB + "!");
		} catch (SQLException sqlEx) {
			System.out.println("[" + getClass().getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccessful connection to " + DB + ", Read sql message");
			System.out.println(sqlEx.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("[" + getClass().getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccessful connection to " + DB + ", class JDBC not found!");
		}
		
		return connection;
	}

	private void createTables () {
		Connection connection = connect();
		
		if (connection != null) {
			 String query = "CREATE TABLE IF NOT EXISTS " + Organization.TABLE + "("
		                + Organization.COL_ID 		+ 	" integer PRIMARY KEY AUTOINCREMENT,"
		                + Organization.COL_FULLNAME + 	" text NOT NULL UNIQUE,"
		                + Organization.COL_USERNAME + 	" text NOT NULL UNIQUE," 
		                + Organization.COL_PASSWORD + 	" text"
		                + ");";
			 
			 try {
				PreparedStatement ps = connection.prepareStatement(query);
				
				if (ps.execute())
					System.out.println("[" + getClass().getName() + " | " + LocalDateTime.now() + "]" + 
						" Tables already exists!");
				else
					System.out.println("[" + getClass().getName() + " | " + LocalDateTime.now() + "]" + 
							" Created tables for " + DB + ".");

			} catch (SQLException sqlEx) {
				System.out.println(sqlEx.getMessage());
			}
		} 
	}
}

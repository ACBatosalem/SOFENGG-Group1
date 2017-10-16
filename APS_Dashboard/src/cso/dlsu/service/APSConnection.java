package cso.dlsu.service;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;

import org.sqlite.JDBC;

import cso.dlsu.bean.Organization;

public class APSConnection {
	private static final String DRIVER = "jdbc:sqlite:";
	private static final String DIR = "C:/dlsu-cso/";
	private static final String DB = "aps.db";
	private static APSConnection instance;
	
	public static APSConnection getInstance () {
		if (instance == null)
			instance = new APSConnection();
		return instance;
	}
	
	private APSConnection () {
		(new File(DIR)).mkdirs();
		try {
			createTables();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection connect () {
		Connection connection = null;
		String url = DRIVER + DIR + DB;
		
		try {
			Class.forName(JDBC.class.getName());
			connection = DriverManager.getConnection(url);
			System.out.println("[" + getClass().getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful connection to " + DIR + DB + "!");
		} catch (SQLException sqlEx) {
			System.out.println("[" + getClass().getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccessful connection to " + DIR + DB + ", Read sql message");
			System.out.println(sqlEx.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("[" + getClass().getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccessful connection to " + DIR + DB + ", class JDBC not found!");
		}
		
		return connection;
	}

	private void createTables () throws SQLException {
		Connection connection = connect();
		
		if (connection != null) {
			if(!checkTableExist(connection, Organization.TABLE)) {
				String query = "CREATE TABLE IF NOT EXISTS " + Organization.TABLE + "("
		                + Organization.COL_ID 		+ 	" integer PRIMARY KEY AUTOINCREMENT,"
		                + Organization.COL_FULLNAME + 	" text NOT NULL UNIQUE,"
		                + Organization.COL_USERNAME + 	" text NOT NULL UNIQUE," 
		                + Organization.COL_PASSWORD + 	" text"
		                + ");"; 
				executeCreateTables(connection, query, Organization.TABLE);
				createAccountAPS(connection);
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		
	}
	
	private void createAccountAPS(Connection con) {
		PreparedStatement statement = null;
		String query = "INSERT INTO " + Organization.TABLE + " " +
				"VALUES (?,?,?,?);";
		try {
			statement = con.prepareStatement(query);
			statement.setNull(1, Types.NULL);
			statement.setString(2, "CSO Activity Processing and Screening");
			statement.setString(3, "APS");
			statement.setString(4, "password");
			statement.executeUpdate();
			System.out.println("[" + OrganizationService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful INSERT INTO " + Organization.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + OrganizationService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful INSERT INTO " + Organization.TABLE + ", check SQL message");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void executeCreateTables(Connection con, String query, String tableName) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(query);
			ps.execute();
			System.out.println("[" + getClass().getName() + " | " + LocalDateTime.now() + "]" + 
					" Created " + tableName + "for " + DIR + DB + ".");

		} catch (SQLException sqlEx) {
			System.out.println(sqlEx.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private boolean checkTableExist(Connection con, String tableName) throws SQLException {
		boolean exist = false;
		
		ResultSet tables = con.getMetaData().getTables(null, null, tableName, null);
		if (tables.next()) {
		  exist = true;
		}
		return exist;
	}
}

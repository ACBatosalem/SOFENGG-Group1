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
import cso.dlsu.bean.Document;
import cso.dlsu.bean.SubmissionDetails;
import cso.dlsu.bean.CheckingDetails;
import cso.dlsu.bean.ActivityDetails;
import cso.dlsu.bean.TieUp;
import main.java.GSheetsConnection;

/**
 * @author Batosalem, Angelika
 * @author Eroles, Carlo Miguel
 * @author Respicio, Michael Ryan
 * @author Ticug, Jonal Ray
 * @version 1.0
 */
public class APSConnection {
	private static final String DRIVER = "jdbc:sqlite:";
	private static final String DIR = "C:/dlsu-cso/";
	private static final String DB = "aps.db";
	private static APSConnection instance;
	
	/**
	 * This function is used to get the APSConnection instance.
	 * @return the instance of the APSConnection
	 */
	public static APSConnection getInstance () {
		if (instance == null)
			instance = new APSConnection();
		return instance;
	}
	
	/**
	 * This is the constructor of the APSConnection object.
	 */
	private APSConnection () {
		(new File(DIR)).mkdirs();
		try {
			if(GSheetsConnection.newFile == 1)
				dropTables();
			createTables();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function is used to return the Connection object connected to the database.
	 * @return the connection to the database
	 */
	public Connection connect () {
		Connection connection = null;
		String url = DRIVER + DIR + DB;
		
		try {
			Class.forName(JDBC.class.getName());
			connection = DriverManager.getConnection(url);
		//	System.out.println("[" + getClass().getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful connection to " + DIR + DB + "!");
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
	

	private void dropTables() {
		// TODO Auto-generated method stub
		Connection connection = connect();
		String[] tables = {SubmissionDetails.TABLE,
				CheckingDetails.TABLE, TieUp.TABLE,
				ActivityDetails.TABLE};
		if (connection != null) {
			for(String table: tables) {
				String query = "DROP TABLE IF EXISTS " + table;
				System.out.println("DROP" + table);
				executeQueryForTables(connection, query, table);
			}
		}
	}

	/**
	 * This function is used to create the tables in the database if they do not exist yet.
	 * @throws SQLException
	 */
	private void createTables () throws SQLException {
		Connection connection = connect();
		
		if (connection != null) {
			//Create organizations table
			if(!checkTableExist(connection, Organization.TABLE)) {
				String query = "CREATE TABLE IF NOT EXISTS " + Organization.TABLE + "("
		                + Organization.COL_ID 		+ 	" integer PRIMARY KEY AUTOINCREMENT,"
		                //+ Organization.COL_FULLNAME + 	" text NOT NULL UNIQUE,"
		                + Organization.COL_USERNAME + 	" text NOT NULL UNIQUE," 
		                + Organization.COL_PASSWORD + 	" text"
		                + ");"; 
				executeQueryForTables(connection, query, Organization.TABLE);
				createAccountAPS(connection);
			}
			
			//Create documents table
			if(!checkTableExist(connection, Document.TABLE)) {
				String query = "CREATE TABLE IF NOT EXISTS " + Document.TABLE + "("
		                + Document.COL_ID 	  + 	" integer PRIMARY KEY AUTOINCREMENT,"
		                + Document.COL_ORG_ID + 	" integer NOT NULL,"
		                + Document.COL_TITLE  + 	" text NOT NULL," 
		                + Document.COL_TERM   + 	" text NOT NULL"
		                + ");"; 
				executeQueryForTables(connection, query, Document.TABLE);
			}
			
			//Create activity_details table
			if(!checkTableExist(connection, ActivityDetails.TABLE)) {
				String query = "CREATE TABLE IF NOT EXISTS " + ActivityDetails.TABLE + "("
		                + ActivityDetails.COL_ID 	  + 	" integer PRIMARY KEY AUTOINCREMENT,"
		                + ActivityDetails.COL_DOCU_ID + 	" integer NOT NULL,"
		                + ActivityDetails.COL_NATURE  + 	" text,"
		                + ActivityDetails.COL_TYPE    + 	" text,"
		                + ActivityDetails.COL_VENUE   + 	" text,"
		                + ActivityDetails.COL_DATE	  +		" text,"
		                + ActivityDetails.COL_TIME	  +		" text"
		                + ");"; 
				executeQueryForTables(connection, query, ActivityDetails.TABLE);
			}
			
			//Create submission_details table
			if(!checkTableExist(connection, SubmissionDetails.TABLE)) {
				String query = "CREATE TABLE IF NOT EXISTS " + SubmissionDetails.TABLE + "("
		                + SubmissionDetails.COL_ID  	  	    + 	" integer PRIMARY KEY AUTOINCREMENT,"
		                + SubmissionDetails.COL_ACT_ID 	    + 	" integer NOT NULL,"
		                + SubmissionDetails.COL_DATE_SUBMITTED  + 	" text NOT NULL,"
		                + SubmissionDetails.COL_SUBMISSION_TYPE + 	" text NOT NULL,"
		                + SubmissionDetails.COL_SUBMITTED_BY 	+ 	" text NOT NULL,"
		                + SubmissionDetails.COL_EMAIL_ADDRESS 	+ 	" text NOT NULL,"
		                + SubmissionDetails.COL_CONTACT_NO 		+ 	" text NOT NULL,"
		                + SubmissionDetails.COL_SAS_TYPE 		+ 	" text"
		                + ");"; 
				executeQueryForTables(connection, query, SubmissionDetails.TABLE);
			}
			
			//Create checking_details table
			if(!checkTableExist(connection, CheckingDetails.TABLE)) {
				String query = "CREATE TABLE IF NOT EXISTS " + CheckingDetails.TABLE + "("
		                + CheckingDetails.COL_ID  	  	    + 	" integer PRIMARY KEY AUTOINCREMENT,"
		                + CheckingDetails.COL_SUB_ID	    + 	" integer NOT NULL,"
		                + CheckingDetails.COL_STATUS_ID		+ 	" integer NOT NULL,"
		                + CheckingDetails.COL_CHECKER_NAME	+ 	" text, "
		                + CheckingDetails.COL_DATE_CHECKED	+ 	" text, "
		                + CheckingDetails.COL_REMARKS		+ 	" text"
		                + ");"; 
				executeQueryForTables(connection, query, CheckingDetails.TABLE);
			}
			
			//Create tie_ups table
			if(!checkTableExist(connection, TieUp.TABLE)) {
				String query = "CREATE TABLE IF NOT EXISTS " + TieUp.TABLE + "("
		                + TieUp.COL_DOCU_ID + " integer NOT NULL,"
		                + TieUp.COL_ORG_ID  + " integer NOT NULL,"
		                + "PRIMARY KEY("  + TieUp.COL_DOCU_ID + ", "
		                				  + TieUp.COL_ORG_ID + ")"
		                + ");";
				executeQueryForTables(connection, query, TieUp.TABLE);
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		
	}
	
	/**
	 * This function is used to create and add the APS account in the database if it does not exist yet.
	 * @param con the Connection object connected to the database
	 */
	private void createAccountAPS(Connection con) {
		PreparedStatement statement = null;
		String query = "INSERT INTO " + Organization.TABLE + " " +
				"VALUES (?,?,?);";
		try {
			statement = con.prepareStatement(query);
			statement.setNull(1, Types.NULL);
			//statement.setString(2, "CSO Activity Processing and Screening");
			statement.setString(2, "APS");
			statement.setString(3, "password");
			statement.executeUpdate();
		//	System.out.println("[" + OrganizationService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful INSERT INTO " + Organization.TABLE);
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
	
	/**
	 * This function is used to execute the creation or deletion of tables in the database.
	 * @param con the Connection object connected to the database
	 * @param query the query to create the specified table
	 * @param tableName the name of the table to be created
	 */
	private void executeQueryForTables(Connection con, String query, String tableName) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(query);
			ps.execute();
		//	System.out.println("[" + getClass().getName() + " | " + LocalDateTime.now() + "]" + 
		//			" Created " + tableName + "for " + DIR + DB + ".");

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
	
	/**
	 * This function is used to check whether a table exists in the database.
	 * @param con the Connection object connected to the database
	 * @param tableName the name of the table to be created
	 * @return true if the table exists, false if it does not
	 * @throws SQLException
	 */
	private boolean checkTableExist(Connection con, String tableName) throws SQLException {
		boolean exist = false;
		
		ResultSet tables = con.getMetaData().getTables(null, null, tableName, null);
		if (tables.next()) {
		  exist = true;
		}
		return exist;
	}
}

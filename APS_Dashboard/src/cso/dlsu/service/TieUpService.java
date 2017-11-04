package cso.dlsu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cso.dlsu.bean.TieUp;;

/**
 * @author Batosalem, Angelika
 * @author Eroles, Carlo Miguel
 * @author Respicio, Michael Ryan
 * @author Ticug, Jonal Ray
 * @version 1.0
 */
public class TieUpService {
	public static final APSConnection db = APSConnection.getInstance();
	
	/**
	 * This function is used to convert a ResultSet to a TieUp object.
	 * @param set the ResultSet object returned after query execution
	 * @return the TieUp object created from the result set
	 * @throws SQLException
	 */
	private static TieUp toTieUp (ResultSet set) throws SQLException {
		TieUp tieUp = new TieUp();
		
		tieUp.setDocuID(set.getInt(TieUp.COL_DOCU_ID));
		tieUp.setOrgID(set.getInt(TieUp.COL_ORG_ID));
		return tieUp;
	}
	
	/**
	 * This function is used to add the data of a TieUp object into the database.
	 * @param tieUp the TieUp object whose data will be added into the database
	 * @return true if the data was successfully added into the database, false if not
	 */
	public static boolean addTieUp (TieUp tieUp) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean added = false;
		String query = "INSERT INTO " + TieUp.TABLE + " " +
						"VALUES (?, ?);";
		
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(query);
			
			statement.setInt(1, tieUp.getDocuID());
			statement.setInt(2, tieUp.getOrgID());
					
			statement.executeUpdate();
			added = true;
			System.out.println("[" + TieUpService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful INSERT INTO " + TieUp.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + TieUpService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful INSERT INTO " + TieUp.TABLE + ", check SQL message");
			System.out.println(e.getMessage());
			try {
				connection.rollback();
				System.out.println("Transaction being rolled back.");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (statement != null) {
				try {
					connection.commit();
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return added;
	}
	
	/**
	 * This function is used to get all the data from the TieUp table in the database.
	 * @return a List object of all the data from the TieUp table in the database
	 */
	public static List<TieUp> getAllTieUps() {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + TieUp.TABLE;
		List <TieUp> tieUps = new ArrayList <TieUp> ();
		
		try {
			statement = connection.prepareStatement(query);
			set = statement.executeQuery();
			while (set.next())
				tieUps.add(toTieUp(set));
	
			System.out.println("[" + TieUpService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM " + TieUp.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + TieUpService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + TieUp.TABLE + ", check SQL message");
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			
			if (set != null) {
				try {
					set.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		
		return tieUps;
	}
	
	/**
	 * This function is used to get all the data of tie-ups of a specific document from the TieUp table in the database.
	 * @param docuID the id of the document whose tie-ups are being retrieved from the database
	 * @return a List object of all the data of tie-ups of a a specific document that has an id equal to the one specified
	 * 		   as the parameter
	 */
	public static List<TieUp> getTieUpsForDocument(int docuID) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + TieUp.TABLE + " " +
						"WHERE " + TieUp.COL_DOCU_ID + " = ?";
		List <TieUp> tieUps = new ArrayList <TieUp> ();
		
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, docuID);
			
			set = statement.executeQuery();
			while (set.next())
				tieUps.add(toTieUp(set));
	
			System.out.println("[" + TieUpService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM " + TieUp.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + TieUpService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + TieUp.TABLE + ", check SQL message");
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			
			if (set != null) {
				try {
					set.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		
		return tieUps;
	}
	
	/*public static List<TieUp> getTieUpsForOrg() {
		
	}*/
	
	/**
	 * This function is used to delete a Document object's data from the database.
	 * @param docuID the document id of the TieUp object to be deleted from the database
	 * @param orgID the organization id of the TieUp object to be deleted from the database
	 * @return true if the deletion was successful, false if not
	 */
	public static boolean deleteTieUp(int docuID, int orgID) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean deleted = false;
		String query = "DELETE FROM " + TieUp.TABLE + " " +
						"WHERE " + TieUp.COL_DOCU_ID + " = ? " + 
						"AND " + TieUp.COL_ORG_ID + " = ?";
		
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(query);
			
			statement.setInt(1, docuID);
			statement.setInt(2, orgID);
			
			statement.executeUpdate();
			deleted = true;
			System.out.println("[" + TieUpService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful DELETE FROM " + TieUp.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + TieUpService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful DELETE FROM " + TieUp.TABLE + ", check SQL message");
			System.out.println(e.getMessage());
			try {
				connection.rollback();
				System.out.println("Transaction being rolled back.");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (statement != null) {
				try {
					connection.commit();
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return deleted;
	}
}

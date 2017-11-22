package cso.dlsu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cso.dlsu.bean.CheckingDetails;

/**
 * @author Batosalem, Angelika
 * @author Eroles, Carlo Miguel
 * @author Respicio, Michael Ryan
 * @author Ticug, Jonal Ray
 * @version 1.0
 */
public class CheckingDetailsService {
	public static final APSConnection db = APSConnection.getInstance();
	
	/**
	 * This function is used to convert a ResultSet to a CheckingDetails object.
	 * @param set the ResultSet object returned after query execution
	 * @return the CheckingDetails object created from the result set
	 * @throws SQLException
	 */
	private static CheckingDetails toCheckingDetails (ResultSet set) throws SQLException {
		CheckingDetails check = new CheckingDetails();
		
		check.setId(set.getInt(CheckingDetails.COL_ID));
		check.setSubID(set.getInt(CheckingDetails.COL_SUB_ID));
		check.setStatusID(set.getInt(CheckingDetails.COL_STATUS_ID));
		check.setCheckerName(set.getString(CheckingDetails.COL_CHECKER_NAME));
		check.setDateChecked(set.getString(CheckingDetails.COL_DATE_CHECKED));
		check.setRemarks(set.getString(CheckingDetails.COL_REMARKS));
		return check;
	}
	
	/**
	 * This function is used to add the data of a CheckingDetails object into the database.
	 * @param check the CheckingDetails object whose data will be added into the database
	 * @return true if the data was successfully added into the database, false if not
	 */
	public static boolean addCheckingDetails (CheckingDetails check) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean added = false;
		String query = "INSERT INTO " + CheckingDetails.TEMP_TABLE + " " +
						"VALUES (?, ?, ?, ?, ?, ?);";
		
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(query);
			
			statement.setNull(1, Types.NULL);
			statement.setInt(2, check.getSubID());
			statement.setInt(3, check.getStatusID());
			statement.setString(4, check.getCheckerName());
			statement.setString(5, check.getDateChecked());
			statement.setString(6, check.getRemarks());
			
			statement.executeUpdate();
			added = true;
		//	System.out.println("[" + CheckingDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful INSERT INTO " + CheckingDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + CheckingDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful INSERT INTO " + CheckingDetails.TABLE + ", check SQL message");
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
	 * This function is used to get all the data from the CheckingDetails table in the database.
	 * @return a List object of all the data from the CheckingDetails table in the database
	 */
	public static List<CheckingDetails> getAllCheckingDetails() {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + CheckingDetails.TABLE;
		List <CheckingDetails> checks = new ArrayList <CheckingDetails> ();
		
		try {
			statement = connection.prepareStatement(query);
			set = statement.executeQuery();
			while (set.next())
				checks.add(toCheckingDetails(set));
	
		//	System.out.println("[" + CheckingDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful SELECT FROM " + CheckingDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + CheckingDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + CheckingDetails.TABLE + ", check SQL message");
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
		
		return checks;
	}
	
	/**
	 * This function is used to get the data of a single CheckingDetails object stored in the database.
	 * @param id the id of the CheckingDetails object being retrieved from the database
	 * @return the CheckingDetails object with its id equal to the id specified as the parameter
	 */
	public static CheckingDetails getCheckingDetails(int id) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + CheckingDetails.TABLE + " " + 
						"WHERE " + CheckingDetails.COL_ID + " = ?";
		CheckingDetails check = null;
		
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			set = statement.executeQuery();
			
			while (set.next()) {
				check = toCheckingDetails(set);
			}
		
		//	System.out.println("[" + CheckingDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful SELECT FROM " + CheckingDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + CheckingDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + CheckingDetails.TABLE + ", check SQL message");
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
		
		return check;
	}
	
	/**
	 * This function is used to get the data of a CheckingDetails object, of a specific submission, from the database.
	 * @param sub_id the id of the submission referred by the CheckingDetails object being retrieved from the database
	 * @return the CheckingDetails object with the submission id equal to the one specified as the parameter
	 */
	public static CheckingDetails getCheckingDetailsOfSubmission(int sub_id) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + CheckingDetails.TEMP_TABLE + " " + 
						"WHERE " + CheckingDetails.COL_SUB_ID + " = ?";
		CheckingDetails check = null;
		
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, sub_id);
			
			set = statement.executeQuery();
			
			while (set.next()) {
				check = toCheckingDetails(set);
			}
		
		//	System.out.println("[" + CheckingDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful SELECT FROM " + CheckingDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + CheckingDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + CheckingDetails.TEMP_TABLE + ", check SQL message");
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
		
		return check;
	}
	
	/**
	 * This function is used to determine whether a CheckingDetails object exists for a SubmissionDetails object.
	 * @param sub_id the id of the SubmissionDetails object being checked
	 * @return true if a CheckingDetails object exists for the SubmissionDetails object, false if not
	 */
	public static boolean findSubmissionByID(int sub_id) {
		if (getCheckingDetailsOfSubmission(sub_id) == null)
			return false;
		return true;
	}
	
	/**
	 * This function is used to delete a CheckingDetails object's data from the database.
	 * @param id the id of the CheckingDetails object to be deleted from the database
	 * @return true if the deletion was successful, false if not
	 */
	public static boolean deleteCheckingDetails(int id) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean deleted = false;
		String query = "DELETE FROM " + CheckingDetails.TABLE + " " +
						"WHERE " + CheckingDetails.COL_ID + " = ?";
		
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			statement.executeUpdate();
			deleted = true;
		//	System.out.println("[" + CheckingDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful DELETE FROM " + CheckingDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + CheckingDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful DELETE FROM " + CheckingDetails.TABLE + ", check SQL message");
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

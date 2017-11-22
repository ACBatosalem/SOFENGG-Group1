package cso.dlsu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cso.dlsu.bean.SubmissionDetails;

/**
 * @author Batosalem, Angelika
 * @author Eroles, Carlo Miguel
 * @author Respicio, Michael Ryan
 * @author Ticug, Jonal Ray
 * @version 1.0
 */
public class SubmissionDetailsService {
	public static final APSConnection db = APSConnection.getInstance();
	
	/**
	 * This function is used to convert a ResultSet to a SubmissionDetails object.
	 * @param set the ResultSet object returned after query execution
	 * @return the SubmissionDetails object created from the result set
	 * @throws SQLException
	 */
	private static SubmissionDetails toSubmissionDetails (ResultSet set) throws SQLException {
		SubmissionDetails sub = new SubmissionDetails();
		
		sub.setId(set.getInt(SubmissionDetails.COL_ID));
		sub.setActID(set.getInt(SubmissionDetails.COL_ACT_ID));
		sub.setDateSubmitted(set.getString(SubmissionDetails.COL_DATE_SUBMITTED));
		sub.setSubmissionType(set.getString(SubmissionDetails.COL_SUBMISSION_TYPE));
		sub.setSubmittedBy(set.getString(SubmissionDetails.COL_SUBMITTED_BY));
		sub.setEmailAddress(set.getString(SubmissionDetails.COL_EMAIL_ADDRESS));
		sub.setContactNo(set.getString(SubmissionDetails.COL_CONTACT_NO));
		sub.setSasType(set.getString(SubmissionDetails.COL_SAS_TYPE));
		return sub;
	}
	
	/**
	 * This function is used to add the data of a Organization object into the database.
	 * @param sub the SubmissionDetails object whose data will be added into the database
	 * @return true if the data was successfully added into the database, false if not
	 */
	public static boolean addSubmissionDetails (SubmissionDetails sub) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean added = false;
		String query = "INSERT INTO " + SubmissionDetails.TEMP_TABLE + " " +
						"VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(query);
			
			statement.setNull(1, Types.NULL);
			statement.setInt(2, sub.getActID());
			statement.setString(3, sub.getDateSubmitted());
			statement.setString(4, sub.getSubmissionType());
			statement.setString(5, sub.getSubmittedBy());
			statement.setString(6, sub.getEmailAddress());
			statement.setString(7, sub.getContactNo());
			statement.setString(8, sub.getSasType());
			
			statement.executeUpdate();
			added = true;
		//	System.out.println("[" + SubmissionDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful INSERT INTO " + SubmissionDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + SubmissionDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful INSERT INTO " + SubmissionDetails.TEMP_TABLE + ", check SQL message");
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
	 * This function is used to get all the data from the SubmissionDetails table in the database.
	 * @return a List object of all the data from the Document table in the database
	 */
	public static List<SubmissionDetails> getAllSubmissionDetails() {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + SubmissionDetails.TABLE;
		List <SubmissionDetails> subs = new ArrayList <SubmissionDetails> ();
		
		try {
			statement = connection.prepareStatement(query);
			set = statement.executeQuery();
			while (set.next())
				subs.add(toSubmissionDetails(set));
	
		//	System.out.println("[" + SubmissionDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful SELECT FROM " + SubmissionDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + SubmissionDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + SubmissionDetails.TABLE + ", check SQL message");
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
		
		return subs;
	}
	
	/**
	 * This function is used to get all the data of submissions of a specific document from the SubmissionDetails table in the database.
	 * @param docuID the id of the document whose submission details are being retrieved from the database
	 * @return a List object of all the data of submission details of a a specific document that has an id equal to the one specified
	 * 		   as the parameter
	 */
	public static List<SubmissionDetails> getSubmissionDetailsOfDocument(int docuID) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + SubmissionDetails.TABLE + " " +
						"WHERE " + SubmissionDetails.COL_ACT_ID + " = ?";
		List <SubmissionDetails> subs = new ArrayList <SubmissionDetails> ();
		
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, docuID);
			
			set = statement.executeQuery();
			while (set.next())
				subs.add(toSubmissionDetails(set));
	
		//	System.out.println("[" + SubmissionDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful SELECT FROM " + SubmissionDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + SubmissionDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + SubmissionDetails.TABLE + ", check SQL message");
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
		
		return subs;
	}
	
	/**
	 * This function is used to get the data of a single SubmissionDetails object stored in the database.
	 * @param id the id of the SubmssionDetails object being retrieved from the database
	 * @return the SubmissionDetails object with its id equal to the id specified as the parameter
	 */
	public static SubmissionDetails getSubmissionDetails(int id) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + SubmissionDetails.TABLE + " " + 
						"WHERE " + SubmissionDetails.COL_ID + " = ?";
		SubmissionDetails sub = null;
		
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			set = statement.executeQuery();
			
			while (set.next()) {
				sub = toSubmissionDetails(set);
			}
		
		//	System.out.println("[" + SubmissionDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful SELECT FROM " + SubmissionDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + SubmissionDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + SubmissionDetails.TABLE + ", check SQL message");
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
		
		return sub;
	}
	
	/**
	 * This function is used to get the id of the SubmissionDetails object of specific submission identified by the date and time submitted.
	 * @param dateSubmitted the date and time at which the submission was made
	 * @return the id of the SubmissionDetails object of the submission with the dateSubmitted specified as the parameter
	 */
	public static int getSubmissionIDByDateSubmittedAndActID(String dateSubmitted, int actID) {
		int id = 0;
		
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + SubmissionDetails.TEMP_TABLE + " " + 
						"WHERE " + SubmissionDetails.COL_DATE_SUBMITTED + " = ? AND " + SubmissionDetails.COL_ACT_ID + " = ?";
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, dateSubmitted);
			statement.setInt(2, actID);
			
			set = statement.executeQuery();
			
			if (set.next()) {
				id = set.getInt(SubmissionDetails.COL_ID);
			}
		
		//	System.out.println("[" + SubmissionDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful SELECT FROM " + SubmissionDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + SubmissionDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + SubmissionDetails.TEMP_TABLE + ", check SQL message");
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
		
		return id;
	}
	
	/**
	 * This function is used to get the id of the SubmissionDetails object of specific submission identified by the date and time submitted.
	 * @param dateSubmitted the date and time at which the submission was made
	 * @return the id of the SubmissionDetails object of the submission with the dateSubmitted specified as the parameter
	 */
	public static int getSubmissionIDByDateSubmittedAndSubmittedBy(String dateSubmitted, String user) {
		int id = 0;
		
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + SubmissionDetails.TABLE + " " + 
						"WHERE " + SubmissionDetails.COL_DATE_SUBMITTED + " = ? AND " + SubmissionDetails.COL_SUBMITTED_BY + " = ?";
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, dateSubmitted);
			statement.setString(2, user);
			
			set = statement.executeQuery();
			
			if (set.next()) {
				id = set.getInt(SubmissionDetails.COL_ID);
			}
		
		//	System.out.println("[" + SubmissionDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful SELECT FROM " + SubmissionDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + SubmissionDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + SubmissionDetails.TEMP_TABLE + ", check SQL message");
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
		
		return id;
	}
	
	/**
	 * This function is used to delete a SubmissionDetails object's data from the database.
	 * @param id the id of the SubmissionDetails object to be deleted from the database
	 * @return true if the deletion was successful, false if not
	 */
	public static boolean deleteSubmissionDetails(int id) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean deleted = false;
		String query = "DELETE FROM " + SubmissionDetails.TABLE + " " +
						"WHERE " + SubmissionDetails.COL_ID + " = ?";
		
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			statement.executeUpdate();
			deleted = true;
		//	System.out.println("[" + SubmissionDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful DELETE FROM " + SubmissionDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + SubmissionDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful DELETE FROM " + SubmissionDetails.TABLE + ", check SQL message");
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

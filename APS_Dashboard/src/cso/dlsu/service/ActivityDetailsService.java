package cso.dlsu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cso.dlsu.bean.ActivityDetails;

/**
 * @author Batosalem, Angelika
 * @author Eroles, Carlo Miguel
 * @author Respicio, Michael Ryan
 * @author Ticug, Jonal Ray
 * @version 1.0
 */
public class ActivityDetailsService {
	public static final APSConnection db = APSConnection.getInstance();
	
	/**
	 * This function is used to convert a ResultSet to an ActivityDetails object.
	 * @param set the ResultSet object returned after query execution
	 * @return the ActivityDetails object created from the result set
	 * @throws SQLException
	 */
	private static ActivityDetails toActivityDetails (ResultSet set) throws SQLException {
		ActivityDetails actDet = new ActivityDetails();
		
		actDet.setId(set.getInt(ActivityDetails.COL_ID));
		actDet.setDocuID(set.getInt(ActivityDetails.COL_DOCU_ID));
		actDet.setNature(set.getString(ActivityDetails.COL_NATURE));
		actDet.setType(set.getString(ActivityDetails.COL_TYPE));
		actDet.setVenue(set.getString(ActivityDetails.COL_VENUE));
		actDet.setDate(set.getString(ActivityDetails.COL_DATE));
		actDet.setTime(set.getString(ActivityDetails.COL_TIME));
		return actDet;
	}
	
	public static boolean addActivityDetails (ActivityDetails actDet) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean added = false;
		String query = "INSERT INTO " + ActivityDetails.TABLE + " " +
						"VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(query);
			
			statement.setNull(1, Types.NULL);
			statement.setInt(2, actDet.getDocuID());
			statement.setString(3, actDet.getNature());
			statement.setString(4, actDet.getType());
			statement.setString(5, actDet.getVenue());
			statement.setString(6, actDet.getDate());
			statement.setString(7, actDet.getTime());
			
			statement.executeUpdate();
			added = true;
		//	System.out.println("[" + ActivityDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful INSERT INTO " + ActivityDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + ActivityDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful INSERT INTO " + ActivityDetails.TABLE + ", check SQL message");
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
	 * This function is used to get all the data from the ActivityDetails table in the database.
	 * @return a List object of all the data from the ActivityDetails table in the database
	 */
	public static List<ActivityDetails> getAllActivityDetails() {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + ActivityDetails.TABLE;
		List <ActivityDetails> actDet = new ArrayList <ActivityDetails> ();
		
		try {
			statement = connection.prepareStatement(query);
			set = statement.executeQuery();
			while (set.next())
				actDet.add(toActivityDetails(set));
	
		//	System.out.println("[" + ActivityDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful SELECT FROM " + ActivityDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + ActivityDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + ActivityDetails.TABLE + ", check SQL message");
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
		
		return actDet;
	}
	
	/**
	 * This function is used to get all the data of activity details of a specific document from the Document table in the database.
	 * @param docuID the id of the document whose activity details are being retrieved from the database
	 * @return a List object of all the data of activity details of a a specific document that has an id equal to the one specified
	 * 		   as the parameter
	 */
	public static List<ActivityDetails> getActivityDetailsOfDocument(int docuID) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + ActivityDetails.TABLE +  " " + 
						"WHERE " + ActivityDetails.COL_DOCU_ID + " = ?";
		List <ActivityDetails> actDet = new ArrayList <ActivityDetails> ();
		
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, docuID);
			
			set = statement.executeQuery();
			while (set.next())
				actDet.add(toActivityDetails(set));
	
		//	System.out.println("[" + ActivityDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful SELECT FROM " + ActivityDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + ActivityDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + ActivityDetails.TABLE + ", check SQL message");
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
		
		return actDet;
	}
	
	/**
	 * This function is used to get the data of a single ActivityDetails object stored in the database.
	 * @param id the id of the ActivityDetails object being retrieved from the database
	 * @return the ActivityDetails object with its id equal to the id specified as the parameter
	 */
	public static ActivityDetails getActivityDetailsById(int id) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + ActivityDetails.TABLE + " " + 
						"WHERE " + ActivityDetails.COL_ID + " = ?";
		ActivityDetails actDet = null;
		
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			set = statement.executeQuery();
			
			while (set.next()) {
				actDet = toActivityDetails(set);
			}
		
		//	System.out.println("[" + ActivityDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful SELECT FROM " + ActivityDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + ActivityDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + ActivityDetails.TABLE + ", check SQL message");
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
		
		return actDet;
	}
	
	
	public static ActivityDetails getActivityDetailsByDocuID(int docu_id) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + ActivityDetails.TABLE + " " + 
						"WHERE " + ActivityDetails.COL_DOCU_ID + " = ?";
		ActivityDetails actDet = null;
		
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, docu_id);
			
			set = statement.executeQuery();
			
			while (set.next()) {
				actDet = toActivityDetails(set);
			}
		
		//	System.out.println("[" + ActivityDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful SELECT FROM " + ActivityDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + ActivityDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + ActivityDetails.TABLE + ", check SQL message");
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
		
		return actDet;
	}
	
	/**
	 * This function is used to delete a ActivityDetails object's data from the database.
	 * @param id the id of the ActivityDetails object to be deleted from the database
	 * @return true if the deletion was successful, false if not
	 */
	public static boolean deleteActivityDetails(int id) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean deleted = false;
		String query = "DELETE FROM " + ActivityDetails.TABLE + " " +
						"WHERE " + ActivityDetails.COL_ID + " = ?";
		
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			statement.executeUpdate();
			deleted = true;
		//	System.out.println("[" + ActivityDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful DELETE FROM " + ActivityDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + ActivityDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful DELETE FROM " + ActivityDetails.TABLE + ", check SQL message");
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

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

public class SubmissionDetailsService {
	public static final APSConnection db = APSConnection.getInstance();
	
	private static SubmissionDetails toSubmissionDetails (ResultSet set) throws SQLException {
		SubmissionDetails sub = new SubmissionDetails();
		
		sub.setId(set.getInt(SubmissionDetails.COL_ID));
		sub.setDocuID(set.getInt(SubmissionDetails.COL_DOCU_ID));
		sub.setDateSubmitted(set.getString(SubmissionDetails.COL_DATE_SUBMITTED));
		sub.setSubmissionType(set.getString(SubmissionDetails.COL_SUBMISSION_TYPE));
		sub.setSubmittedBy(set.getString(SubmissionDetails.COL_SUBMITTED_BY));
		sub.setEmailAddress(set.getString(SubmissionDetails.COL_EMAIL_ADDRESS));
		sub.setContactNo(set.getString(SubmissionDetails.COL_CONTACT_NO));
		return sub;
	}
	
	public static boolean addSubmissionDetails (SubmissionDetails sub) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean added = false;
		String query = "INSERT INTO " + SubmissionDetails.TABLE + " " +
						"VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		try {
			statement = connection.prepareStatement(query);
			
			statement.setNull(1, Types.NULL);
			statement.setInt(2, sub.getDocuID());
			statement.setString(3, sub.getDateSubmitted());
			statement.setString(4, sub.getSubmissionType());
			statement.setString(5, sub.getSubmittedBy());
			statement.setString(6, sub.getEmailAddress());
			statement.setString(7, sub.getContactNo());
			
			statement.executeUpdate();
			added = true;
			System.out.println("[" + SubmissionDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful INSERT INTO " + SubmissionDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + SubmissionDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful INSERT INTO " + SubmissionDetails.TABLE + ", check SQL message");
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return added;
	}
	
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
	
			System.out.println("[" + SubmissionDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM " + SubmissionDetails.TABLE);
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
	
	public static List<SubmissionDetails> getSubmissionDetailsOfDocument(int docuID) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + SubmissionDetails.TABLE + " " +
						"WHERE " + SubmissionDetails.COL_DOCU_ID + " = ?";
		List <SubmissionDetails> subs = new ArrayList <SubmissionDetails> ();
		
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, docuID);
			
			set = statement.executeQuery();
			while (set.next())
				subs.add(toSubmissionDetails(set));
	
			System.out.println("[" + SubmissionDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM " + SubmissionDetails.TABLE);
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
		
			System.out.println("[" + SubmissionDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM " + SubmissionDetails.TABLE);
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
	
	public static boolean deleteSubmissionDetails(int id) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean deleted = false;
		String query = "DELETE FROM " + SubmissionDetails.TABLE + " " +
						"WHERE " + SubmissionDetails.COL_ID + " = ?";
		
		try {
			statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			statement.executeUpdate();
			deleted = true;
			System.out.println("[" + SubmissionDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful DELETE FROM " + SubmissionDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + SubmissionDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful DELETE FROM " + SubmissionDetails.TABLE + ", check SQL message");
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return deleted;
	}
}

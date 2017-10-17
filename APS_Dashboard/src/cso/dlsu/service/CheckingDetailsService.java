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

public class CheckingDetailsService {
	public static final APSConnection db = APSConnection.getInstance();
	
	private static CheckingDetails toCheckingDetails (ResultSet set) throws SQLException {
		CheckingDetails check = new CheckingDetails();
		
		check.setId(set.getInt(CheckingDetails.COL_ID));
		check.setSubID(set.getInt(CheckingDetails.COL_SUB_IB));
		check.setStatusID(set.getInt(CheckingDetails.COL_STATUS_ID));
		check.setCheckerName(set.getString(CheckingDetails.COL_CHECKER_NAME));
		check.setDateChecked(set.getString(CheckingDetails.COL_DATE_CHECKED));
		check.setRemarks(set.getString(CheckingDetails.COL_REMARKS));
		return check;
	}
	
	public static boolean addCheckingDetails (CheckingDetails check) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean added = false;
		String query = "INSERT INTO " + CheckingDetails.TABLE + " " +
						"VALUES (?, ?, ?, ?, ?, ?);";
		
		try {
			statement = connection.prepareStatement(query);
			
			statement.setNull(1, Types.NULL);
			statement.setInt(2, check.getSubID());
			statement.setInt(3, check.getStatusID());
			statement.setString(4, check.getCheckerName());
			statement.setString(5, check.getDateChecked());
			statement.setString(6, check.getRemarks());
			
			statement.executeUpdate();
			added = true;
			System.out.println("[" + CheckingDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful INSERT INTO " + CheckingDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + CheckingDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful INSERT INTO " + CheckingDetails.TABLE + ", check SQL message");
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
	
			System.out.println("[" + CheckingDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM " + CheckingDetails.TABLE);
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
		
			System.out.println("[" + CheckingDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM " + CheckingDetails.TABLE);
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
	
	public static CheckingDetails getCheckingDetailsOfSubmission(int sub_id) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + CheckingDetails.TABLE + " " + 
						"WHERE " + CheckingDetails.COL_SUB_IB + " = ?";
		CheckingDetails check = null;
		
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, sub_id);
			
			set = statement.executeQuery();
			
			while (set.next()) {
				check = toCheckingDetails(set);
			}
		
			System.out.println("[" + CheckingDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM " + CheckingDetails.TABLE);
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
	
	public static boolean deleteCheckingDetails(int id) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean deleted = false;
		String query = "DELETE FROM " + CheckingDetails.TABLE + " " +
						"WHERE " + CheckingDetails.COL_ID + " = ?";
		
		try {
			statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			statement.executeUpdate();
			deleted = true;
			System.out.println("[" + CheckingDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful DELETE FROM " + CheckingDetails.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + CheckingDetailsService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful DELETE FROM " + CheckingDetails.TABLE + ", check SQL message");
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

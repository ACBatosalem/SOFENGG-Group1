package cso.dlsu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cso.dlsu.bean.ActivityDate;

public class ActivityDateService {
	public static final APSConnection db = APSConnection.getInstance();
	
	private static ActivityDate toActivityDate (ResultSet set) throws SQLException {
		ActivityDate actDate = new ActivityDate();
		
		actDate.setDocuID(set.getInt(ActivityDate.COL_DOCU_ID));
		actDate.setStartDate(set.getDate(ActivityDate.COL_START_DATE));
		actDate.setEndDate(set.getDate(ActivityDate.COL_END_DATE));
		return actDate;
	}
	
	public static boolean addActivityDate (ActivityDate actDate) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean added = false;
		String query = "INSERT INTO " + ActivityDate.TABLE + " " +
						"VALUES (?, ?, ?);";
		
		try {
			statement = connection.prepareStatement(query);
			
			statement.setInt(1, actDate.getDocuID());
			statement.setDate(2, new java.sql.Date(actDate.getStartDate().getTime()));
			statement.setDate(3, new java.sql.Date(actDate.getEndDate().getTime()));
			
			statement.executeUpdate();
			added = true;
			System.out.println("[" + ActivityDateService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful INSERT INTO " + ActivityDate.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + ActivityDateService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful INSERT INTO " + ActivityDate.TABLE + ", check SQL message");
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
	
	public static List<ActivityDate> getAllActivityDates() {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + ActivityDate.TABLE;
		List <ActivityDate> actDates = new ArrayList <ActivityDate> ();
		
		try {
			statement = connection.prepareStatement(query);
			set = statement.executeQuery();
			while (set.next())
				actDates.add(toActivityDate(set));
	
			System.out.println("[" + ActivityDateService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM " + ActivityDate.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + ActivityDateService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + ActivityDate.TABLE + ", check SQL message");
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
		
		return actDates;
	}
	
	public static List<ActivityDate> getActivityDatesOfDocument(int docuID) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + ActivityDate.TABLE + " " +
						"WHERE " + ActivityDate.COL_DOCU_ID + " = ?";
		List <ActivityDate> actDates = new ArrayList <ActivityDate> ();
		
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, docuID);
			
			set = statement.executeQuery();
			while (set.next())
				actDates.add(toActivityDate(set));
	
			System.out.println("[" + ActivityDateService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM " + ActivityDate.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + ActivityDateService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + ActivityDate.TABLE + ", check SQL message");
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
		
		return actDates;
	}
	
	public static boolean deleteActivityDate(int docuID, Date startDate, Date endDate) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean deleted = false;
		String query = "DELETE FROM " + ActivityDate.TABLE + " " +
						"WHERE " + ActivityDate.COL_DOCU_ID + " = ? " + 
						"AND " + ActivityDate.COL_START_DATE + " = ? " +
						"AND " + ActivityDate.COL_END_DATE + " = ?";
		
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, docuID);
			statement.setDate(2, new java.sql.Date(startDate.getTime()));
			statement.setDate(3, new java.sql.Date(endDate.getTime()));
			
			statement.executeUpdate();
			deleted = true;
			System.out.println("[" + ActivityDateService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful DELETE FROM " + ActivityDate.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + ActivityDateService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful DELETE FROM " + ActivityDate.TABLE + ", check SQL message");
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

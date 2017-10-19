package cso.dlsu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cso.dlsu.bean.CheckingDetails;
import cso.dlsu.bean.DashboardData;
import cso.dlsu.bean.Document;
import cso.dlsu.bean.Organization;
import cso.dlsu.bean.Status;
import cso.dlsu.bean.SubmissionDetails;

/**
 * @author Batosalem, Angelika
 * @author Eroles, Carlo Miguel
 * @author Respicio, Michael Ryan
 * @author Ticug, Jonal Ray
 * @version 1.0
 */
public class DashboardService {
	public static final APSConnection db = APSConnection.getInstance();
	
	/**
	 * This function is used to convert a ResultSet to a DashboardData object.
	 * @param set the ResultSet object returned after query execution
	 * @return the DashboardData object created from the result set
	 * @throws SQLException
	 */
	private static DashboardData toDashboardData (ResultSet set) throws SQLException {
		DashboardData data = new DashboardData();
		
		data.setTimeStamp(set.getString(SubmissionDetails.COL_DATE_SUBMITTED));
		data.setOrgName(set.getString(Organization.COL_USERNAME));
		data.setTitle(set.getString(Document.COL_TITLE));
		data.setDate(set.getString(Document.COL_DATE));
		data.setStatus(Status.toStatus(set.getInt(CheckingDetails.COL_STATUS_ID)).getStatus());
		return data;
	}
	
	/**
	 * This function is used to get all the data required for the dashboard from multiple tables in the database.
	 * @return a List object of all the data from the database required for the dashboard
	 */
	public static List<DashboardData> getAllDashboardData() {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT " + SubmissionDetails.COL_DATE_SUBMITTED + ", " + Organization.COL_USERNAME + ", "
								  + Document.COL_TITLE + ", " + Document.COL_DATE + ", "
								  + CheckingDetails.COL_STATUS_ID + " " +
						"FROM ((" + Document.TABLE + " D INNER JOIN " + Organization.TABLE + " O ON D." + Document.COL_ORG_ID + " = O." + Organization.COL_ID + ") "
								  + " INNER JOIN " + SubmissionDetails.TABLE + " S ON D." + Document.COL_ID + " = S." + SubmissionDetails.COL_DOCU_ID + ") "
								  + " INNER JOIN " + CheckingDetails.TABLE + " C ON S." + SubmissionDetails.COL_ID + " = C." + CheckingDetails.COL_SUB_ID;
				
		List <DashboardData> data = new ArrayList <DashboardData> ();
		try {
			statement = connection.prepareStatement(query);
			set = statement.executeQuery();
			while (set.next())
				data.add(toDashboardData(set));
	
			System.out.println("[" + DashboardService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM multiple tables");
		} catch (SQLException e) {
			System.out.println("[" + DashboardService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM multiple tables, check SQL message");
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
		
		return data;
	}
	
	/**
	 * This function is used to get the organization's data required for the dashboard from multiple tables in the database.
	 * @return a List object of organization's data from the database required for the dashboard
	 */
	public static List<DashboardData> getOrgDashboardData(String userName) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT " + SubmissionDetails.COL_DATE_SUBMITTED + ", " + Organization.COL_USERNAME + ", "
								  + Document.COL_TITLE + ", " + Document.COL_DATE + ", "
								  + CheckingDetails.COL_STATUS_ID + " " +
						"FROM ((" + Document.TABLE + " D INNER JOIN " + Organization.TABLE + " O ON D." + Document.COL_ORG_ID + " = O." + Organization.COL_ID + ") "
								  + " INNER JOIN " + SubmissionDetails.TABLE + " S ON D." + Document.COL_ID + " = S." + SubmissionDetails.COL_DOCU_ID + ") "
								  + " INNER JOIN " + CheckingDetails.TABLE + " C ON S." + SubmissionDetails.COL_ID + " = C." + CheckingDetails.COL_SUB_ID + " " +
						"WHERE " + Organization.COL_USERNAME + " = ?";
				
		List <DashboardData> data = new ArrayList <DashboardData> ();
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, userName);
			set = statement.executeQuery();
			while (set.next())
				data.add(toDashboardData(set));
	
			System.out.println("[" + DashboardService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM multiple tables");
		} catch (SQLException e) {
			System.out.println("[" + DashboardService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM multiple tables, check SQL message");
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
		
		return data;
	}
}

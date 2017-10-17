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

public class DashboardService {
	public static final APSConnection db = APSConnection.getInstance();
	
	private static DashboardData toDashboardData (ResultSet set) throws SQLException {
		DashboardData data = new DashboardData();
		
		data.setTimeStamp(set.getString(SubmissionDetails.COL_DATE_SUBMITTED));
		data.setOrgName(set.getString(Organization.COL_USERNAME));
		data.setTitle(set.getString(Document.COL_TITLE));
		data.setDate(set.getString(Document.COL_DATE));
		data.setStatus(Status.toStatus(set.getInt(CheckingDetails.COL_STATUS_ID)).getStatus());
		return data;
	}
	
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
}

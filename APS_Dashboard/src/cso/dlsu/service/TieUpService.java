package cso.dlsu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cso.dlsu.bean.TieUp;;

public class TieUpService {
	public static final APSConnection db = APSConnection.getInstance();
	
	private static TieUp toTieUp (ResultSet set) throws SQLException {
		TieUp tieUp = new TieUp();
		
		tieUp.setDocuID(set.getInt(TieUp.COL_DOCU_ID));
		tieUp.setOrgID(set.getInt(TieUp.COL_ORG_ID));
		return tieUp;
	}
	
	public static boolean addTieUp (TieUp tieUp) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean added = false;
		String query = "INSERT INTO " + TieUp.TABLE + " " +
						"VALUES (?, ?);";
		
		try {
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
	
	public static boolean deleteTieUp(int docuID, int orgID) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean deleted = false;
		String query = "DELETE FROM " + TieUp.TABLE + " " +
						"WHERE " + TieUp.COL_DOCU_ID + " = ? " + 
						"AND " + TieUp.COL_ORG_ID + " = ?";
		
		try {
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

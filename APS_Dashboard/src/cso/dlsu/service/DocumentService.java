package cso.dlsu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cso.dlsu.bean.Document;

public class DocumentService {
	public static final APSConnection db = APSConnection.getInstance();
	
	private static Document toDocument (ResultSet set) throws SQLException {
		Document document = new Document();
		
		document.setId(set.getInt(Document.COL_ID));
		document.setOrgID(set.getInt(Document.COL_ORG_ID));
		document.setTitle(set.getString(Document.COL_TITLE));
		document.setTerm(set.getInt(Document.COL_TERM));
		document.setNature(set.getString(Document.COL_NATURE));
		document.setType(set.getString(Document.COL_TYPE));
		document.setVenue(set.getString(Document.COL_VENUE));	
		return document;
	}
	
	public static boolean addDocument (Document document) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean added = false;
		String query = "INSERT INTO " + Document.TABLE + " " +
						"VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		try {
			statement = connection.prepareStatement(query);
			
			statement.setNull(1, Types.NULL);
			statement.setInt(2, document.getOrgID());
			statement.setString(3, document.getTitle());
			statement.setInt(4, document.getTerm());
			statement.setString(5, document.getNature());
			statement.setString(6, document.getType());
			statement.setString(7, document.getVenue());
			
			statement.executeUpdate();
			added = true;
			System.out.println("[" + DocumentService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful INSERT INTO " + Document.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + DocumentService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful INSERT INTO " + Document.TABLE + ", check SQL message");
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
	
	public static List<Document> getAllDocuments() {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + Document.TABLE;
		List <Document> documents = new ArrayList <Document> ();
		
		try {
			statement = connection.prepareStatement(query);
			set = statement.executeQuery();
			while (set.next())
				documents.add(toDocument(set));
	
			System.out.println("[" + DocumentService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM " + Document.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + DocumentService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + Document.TABLE + ", check SQL message");
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
		
		return documents;
	}
	
	public static List<Document> getDocumentsOfOrg(int orgID) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + Document.TABLE +  " " + 
						"WHERE " + Document.COL_ORG_ID + " = ?";
		List <Document> documents = new ArrayList <Document> ();
		
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, orgID);
			
			set = statement.executeQuery();
			while (set.next())
				documents.add(toDocument(set));
	
			System.out.println("[" + DocumentService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM " + Document.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + DocumentService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + Document.TABLE + ", check SQL message");
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
		
		return documents;
	}
	
	public static Document getDocument(int id) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + Document.TABLE + " " + 
						"WHERE " + Document.COL_ID + " = ?";
		Document document = null;
		
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			set = statement.executeQuery();
			
			while (set.next()) {
				document = toDocument(set);
			}
		
			System.out.println("[" + DocumentService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM " + Document.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + DocumentService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + Document.TABLE + ", check SQL message");
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
		
		return document;
	}
	
	public static boolean deleteDocument(int id) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean deleted = false;
		String query = "DELETE FROM " + Document.TABLE + " " +
						"WHERE " + Document.COL_ID + " = ?";
		
		try {
			statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			statement.executeUpdate();
			deleted = true;
			System.out.println("[" + DocumentService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful DELETE FROM " + Document.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + DocumentService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful DELETE FROM " + Document.TABLE + ", check SQL message");
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

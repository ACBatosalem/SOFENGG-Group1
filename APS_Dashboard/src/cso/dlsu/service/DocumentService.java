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

/**
 * @author Batosalem, Angelika
 * @author Eroles, Carlo Miguel
 * @author Respicio, Michael Ryan
 * @author Ticug, Jonal Ray
 * @version 1.0
 */
public class DocumentService {
	public static final APSConnection db = APSConnection.getInstance();
	
	/**
	 * This function is used to convert a ResultSet to a Document object.
	 * @param set the ResultSet object returned after query execution
	 * @return the Document object created from the result set
	 * @throws SQLException
	 */
	private static Document toDocument (ResultSet set) throws SQLException {
		Document document = new Document();
		
		document.setId(set.getInt(Document.COL_ID));
		document.setOrgID(set.getInt(Document.COL_ORG_ID));
		document.setTitle(set.getString(Document.COL_TITLE));
		document.setTerm(set.getString(Document.COL_TERM));
		return document;
	}
	
	/**
	 * This function is used to add the data of a Document object into the database.
	 * @param document the Document object whose data will be added into the database
	 * @return true if the data was successfully added into the database, false if not
	 */
	public static boolean addDocument (Document document) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean added = false;
		String query = "INSERT INTO " + Document.TABLE + " " +
						"VALUES (?, ?, ?, ?);";
		
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(query);
			
			statement.setNull(1, Types.NULL);
			statement.setInt(2, document.getOrgID());
			statement.setString(3, document.getTitle());
			statement.setString(4, document.getTerm());
			
			statement.executeUpdate();
			added = true;
		//	System.out.println("[" + DocumentService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful INSERT INTO " + Document.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + DocumentService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful INSERT INTO " + Document.TABLE + ", check SQL message");
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
	 * This function is used to get all the data from the Document table in the database.
	 * @return a List object of all the data from the Document table in the database
	 */
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
	
		//	System.out.println("[" + DocumentService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful SELECT FROM " + Document.TABLE);
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
	
	/**
	 * This function is used to get all the data of documents of a specific organization from the Document table in the database.
	 * @param orgID the id of the organization whose documents are being retrieved from the database
	 * @return a List object of all the data of documents of a a specific organization that has an id equal to the one specified
	 * 		   as the parameter
	 */
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
	
		//	System.out.println("[" + DocumentService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful SELECT FROM " + Document.TABLE);
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
	
	/**
	 * This function is used to get the data of a single Document object stored in the database.
	 * @param id the id of the Document object being retrieved from the database
	 * @return the Document object with its id equal to the id specified as the parameter
	 */
	public static Document getDocumentById(int id) {
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
		
		//	System.out.println("[" + DocumentService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful SELECT FROM " + Document.TABLE);
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
	
	/**
	 * This function is used to get the data of a single Document object with a specific title stored in the database.
	 * @param title the title of the Document object being retrieved from the database
	 * @return the Document object with its title equal to the title specified as the parameter
	 */
	public static Document getDocumentByTitle(String title) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + Document.TABLE + " " + 
						"WHERE " + Document.COL_TITLE + " = ?";
		Document document = null;
		
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, title);
			
			set = statement.executeQuery();
			
			while (set.next()) {
				document = toDocument(set);
			}
		
		//	System.out.println("[" + DocumentService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful SELECT FROM " + Document.TABLE);
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
	
	/**
	 * This function is used to get the data of a single Document object, with a specific title
	 * and submitted by a specific organization, stored in the database.
	 * @param title the title of the Document object being retrieved from the database
	 * @param orgID the orgID of the Document object being retrieved from the database
	 * @return the Document object with its title and orgID equal to the ones specified as parameters
	 */
	public static Document getDocumentByTitleAndOrg(String title, int orgID) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + Document.TABLE + " " + 
						"WHERE " + Document.COL_TITLE + " = ? AND " + Document.COL_ORG_ID + " = ?";
		Document document = null;
		
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, title);
			statement.setInt(2, orgID);
			
			set = statement.executeQuery();
			
			while (set.next()) {
				document = toDocument(set);
			}
		
		//	System.out.println("[" + DocumentService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful SELECT FROM " + Document.TABLE);
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
	
	/**
	 * This function is used to delete a Document object's data from the database.
	 * @param id the id of the Document object to be deleted from the database
	 * @return true if the deletion was successful, false if not
	 */
	public static boolean deleteDocument(int id) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean deleted = false;
		String query = "DELETE FROM " + Document.TABLE + " " +
						"WHERE " + Document.COL_ID + " = ?";
		
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			statement.executeUpdate();
			deleted = true;
		//	System.out.println("[" + DocumentService.class.getName() + " | " + LocalDateTime.now() + "]"
		//			+ " Successful DELETE FROM " + Document.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + DocumentService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful DELETE FROM " + Document.TABLE + ", check SQL message");
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

package cso.dlsu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cso.dlsu.bean.Transaction;

public class TransactionService {
	public static final APSConnection db = APSConnection.getInstance();
	
	private static Transaction toTransaction (ResultSet set) throws SQLException {
		Transaction trans = new Transaction();
		
		trans.setId(set.getInt(Transaction.COL_ID));
		trans.setDocuID(set.getInt(Transaction.COL_DOCU_ID));
		trans.setStatusID(set.getInt(Transaction.COL_STATUS_ID));
		trans.setCheckerName(set.getString(Transaction.COL_CHECKER_NAME));
		trans.setDateSubmitted(set.getDate(Transaction.COL_DATE_SUBMITTED));
		trans.setSubmissionType(set.getString(Transaction.COL_SUBMISSION_TYPE));
		trans.setDateChecked(set.getDate(Transaction.COL_DATE_CHECKED));
		trans.setRemarks(set.getString(Transaction.COL_REMARKS));
		return trans;
	}
	
	public static boolean addTransaction (Transaction trans) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean added = false;
		String query = "INSERT INTO " + Transaction.TABLE + " " +
						"VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		
		try {
			statement = connection.prepareStatement(query);
			
			statement.setNull(1, Types.NULL);
			statement.setInt(2, trans.getDocuID());
			statement.setInt(3, trans.getStatusID());
			statement.setString(4, trans.getCheckerName());
			statement.setDate(5, new java.sql.Date(trans.getDateSubmitted().getTime()));
			statement.setString(6, trans.getSubmissionType());
			if (trans.getDateChecked() != null)
				statement.setDate(7, new java.sql.Date(trans.getDateChecked().getTime()));
			else statement.setDate(7, null);
			statement.setString(8, trans.getRemarks());
			
			statement.executeUpdate();
			added = true;
			System.out.println("[" + TransactionService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful INSERT INTO " + Transaction.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + TransactionService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful INSERT INTO " + Transaction.TABLE + ", check SQL message");
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
	
	public static List<Transaction> getAllTransactions() {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + Transaction.TABLE;
		List <Transaction> transactions = new ArrayList <Transaction> ();
		
		try {
			statement = connection.prepareStatement(query);
			set = statement.executeQuery();
			while (set.next())
				transactions.add(toTransaction(set));
	
			System.out.println("[" + TransactionService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM " + Transaction.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + TransactionService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + Transaction.TABLE + ", check SQL message");
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
		
		return transactions;
	}
	
	public static List<Transaction> getTransactionsOfDocument(int docuID) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + Transaction.TABLE + " " +
						"WHERE " + Transaction.COL_DOCU_ID + " = ?";
		List <Transaction> transactions = new ArrayList <Transaction> ();
		
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, docuID);
			
			set = statement.executeQuery();
			while (set.next())
				transactions.add(toTransaction(set));
	
			System.out.println("[" + TransactionService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM " + Transaction.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + TransactionService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + Transaction.TABLE + ", check SQL message");
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
		
		return transactions;
	}
	
	public static Transaction getTransaction(int id) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + Transaction.TABLE + " " + 
						"WHERE " + Transaction.COL_ID + " = ?";
		Transaction transaction = null;
		
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			set = statement.executeQuery();
			
			while (set.next()) {
				transaction = toTransaction(set);
			}
		
			System.out.println("[" + TransactionService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM " + Transaction.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + TransactionService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + Transaction.TABLE + ", check SQL message");
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
		
		return transaction;
	}
	
	public static boolean deleteTransaction(int id) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean deleted = false;
		String query = "DELETE FROM " + Transaction.TABLE + " " +
						"WHERE " + Transaction.COL_ID + " = ?";
		
		try {
			statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			statement.executeUpdate();
			deleted = true;
			System.out.println("[" + TransactionService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful DELETE FROM " + Transaction.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + TransactionService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful DELETE FROM " + Transaction.TABLE + ", check SQL message");
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

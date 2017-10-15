package cso.dlsu.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cso.dlsu.bean.Organization;

public class OrganizationService {
	public static final APSConnection db = APSConnection.getInstance();
	
	private static Organization toOrganization (ResultSet set) throws SQLException {
		Organization org = new Organization();
		
		org.setId(set.getInt(Organization.COL_ID));
		org.setName(set.getString(Organization.COL_FULLNAME));
		org.setUserName(set.getString(Organization.COL_USERNAME));
		org.setPassword(set.getString(Organization.COL_PASSWORD));
		return org;
	}
	
	public static boolean addOrg (Organization org) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean added = false;
		String query = "INSERT INTO " + Organization.TABLE + " " +
						"VALUES (?, ?, ?, ?);";
		
		try {
			statement = connection.prepareStatement(query);
			
			statement.setNull(1, Types.NULL);
			statement.setString(2, org.getName());
			statement.setString(3, org.getUserName());
			statement.setString(4, org.getPassword());
			
			statement.executeUpdate();
			added = true;
			System.out.println("[" + OrganizationService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful INSERT INTO " + Organization.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + OrganizationService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful INSERT INTO " + Organization.TABLE + ", check SQL message");
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
	
	public static Organization getOrgByUsername(String username) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + Organization.TABLE + " " + 
						"WHERE " + Organization.COL_USERNAME + " = ?";
		Organization organization = null;
		
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			
			set = statement.executeQuery();
			
			while (set.next())
				organization = toOrganization(set);
		
			System.out.println("[" + OrganizationService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM " + Organization.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + OrganizationService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + Organization.TABLE + ", check SQL message");
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println("[" + OrganizationService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + Organization.TABLE + ", null connection");
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
		
		return organization;
	}
	
	public static boolean findOrgByNameOrUserName(String fullname, String username) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + Organization.TABLE + " " + 
						"WHERE " + Organization.COL_USERNAME + " = ? AND " +
						Organization.COL_FULLNAME + " = ?";
		Organization organization = null;
		
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, fullname);
			
			set = statement.executeQuery();
			
			while (set.next())
				organization = toOrganization(set);
		
			System.out.println("[" + OrganizationService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM " + Organization.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + OrganizationService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + Organization.TABLE + ", check SQL message");
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
		
		return organization != null;
	}
	
	public static List<Organization> getAllOrgs() {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + Organization.TABLE;
		List <Organization> organizations = new ArrayList <Organization> ();
		
		try {
			statement = connection.prepareStatement(query);
			set = statement.executeQuery();
			while (set.next())
				organizations.add(toOrganization(set));
	
			System.out.println("[" + OrganizationService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM " + Organization.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + OrganizationService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + Organization.TABLE + ", check SQL message");
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
		
		return organizations;
	}
	
	public static boolean deleteOrg(int id) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean deleted = false;
		String query = "DELETE FROM " + Organization.TABLE + " " +
						"WHERE " + Organization.COL_ID + " = ?";
		
		try {
			statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			statement.executeUpdate();
			deleted = true;
			System.out.println("[" + OrganizationService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful DELETE FROM " + Organization.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + OrganizationService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful DELETE FROM " + Organization.TABLE + ", check SQL message");
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
	
	public static Organization getOrg(int id) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		ResultSet set = null;
		String query = 	"SELECT * " + 
						"FROM " + Organization.TABLE + " " + 
						"WHERE " + Organization.COL_ID + " = ?";
		Organization organization = null;
		
		try {
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			set = statement.executeQuery();
			
			while (set.next()) {
				organization = toOrganization(set);
			}
		
			System.out.println("[" + OrganizationService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful SELECT FROM " + Organization.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + OrganizationService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful SELECT FROM " + Organization.TABLE + ", check SQL message");
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
		
		return organization;
	}
	
	public static boolean updateOrg(int id, Organization newinfo) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean updated= false;
		String query = "UPDATE " + Organization.TABLE + " " +
						"SET " + Organization.COL_FULLNAME + " = ?, " +
						Organization.COL_USERNAME + " = ?, " +
						Organization.COL_PASSWORD + " = ? " + 
						"WHERE " + Organization.COL_ID + " = ?";
		
		try {
			statement = connection.prepareStatement(query);
			
			statement.setString(1, newinfo.getName());
			statement.setString(2, newinfo.getUserName());
			statement.setString(3, newinfo.getPassword());
			statement.setInt(4, id);
			
			statement.executeUpdate();
			updated = true;
			System.out.println("[" + OrganizationService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful UPDATE IN " + Organization.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + OrganizationService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful UPDATE IN " + Organization.TABLE + ", check SQL message");
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
		
		return updated;
	}
	
	public static boolean updatePassword(int id, String newPassword) {
		Connection connection = db.connect();
		PreparedStatement statement = null;
		boolean updated= false;
		String query = "UPDATE " + Organization.TABLE + " " +
						"SET " + Organization.COL_PASSWORD + " = ? " + 
						"WHERE " + Organization.COL_ID + " = ?";
		
		try {
			statement = connection.prepareStatement(query);
			
			statement.setString(1, newPassword);
			statement.setInt(2, id);
			
			statement.executeUpdate();
			updated = true;
			System.out.println("[" + OrganizationService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Successful UPDATE IN " + Organization.TABLE);
		} catch (SQLException e) {
			System.out.println("[" + OrganizationService.class.getName() + " | " + LocalDateTime.now() + "]"
					+ " Unsuccesful UPDATE IN " + Organization.TABLE + ", check SQL message");
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
		
		return updated;
	}
}

/* hibernate version
package cso.dlsu.aps.service;

import javax.persistence.*;
import cso.dlsu.aps.bean.Organization;
import java.util.List;

public class OrganizationService {
	
	public static boolean addOrg (Organization org) {
		boolean added = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			em.persist(org);
			trans.commit();
			added= true;
		}catch(Exception e){
			if(trans!=null){
				trans.rollback();
			}
			e.printStackTrace();
		}
		em.close();
		return added;
	}
	
	public static Organization getOrgByUsername(String username) {
		Organization user = null;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			
			TypedQuery<Organization> query = em.createQuery("SELECT orgs FROM orgs orgs WHERE orgUserName = :username", Organization.class);
			query.setParameter("username", username);
			if (query.getResultList() != null && query.getResultList().size() > 0)
				user = (Organization) query.getResultList().get(0);
			
			trans.commit();
		}catch(Exception e){
			if(trans!=null){
				trans.rollback();
			}
			e.printStackTrace();
		}
		em.close();
		
		return user;
	}
	
	public static boolean findOrgByNameOrUserName(String fullname, String username) {
		boolean found = false;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			
			TypedQuery<Organization> query = 
					em.createQuery("SELECT orgs FROM orgs orgs WHERE orgUserName = :username OR orgFullName = :name"
							, Organization.class);
			query.setParameter("name", fullname);
			query.setParameter("username", username);
			if (query.getResultList() != null && query.getResultList().size() > 0)
				found = true;
			
			trans.commit();
		}catch(Exception e){
			if(trans!=null){
				trans.rollback();
			}
			e.printStackTrace();
		}
		em.close();
		
		return found;
	}
	
	public static List<Organization> getAllOrgs() {
		List<Organization> users = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			
			TypedQuery<Organization> query = em.createQuery("SELECT orgs FROM orgs orgs ORDER BY orgID DESC", 
					Organization.class);
			users = query.getResultList();
			
			trans.commit();
		}catch(Exception e){
			if(trans!=null){
				trans.rollback();
			}
			e.printStackTrace();
		}
		em.close();
		
		return users;
	}
	
	public static boolean deleteOrg(int id)
	{	
		boolean success = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			// select student with given id
			Organization org = em.find(Organization.class, id);
			em.remove(org);
			
			trans.commit();
			success = true;
		} catch (Exception e) {
			if(trans != null)
				trans.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return success;
	}
	
	public static Organization getOrg(int id)
	{
		Organization org = null;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			// select student with given id
			org = em.find(Organization.class, id);
			
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return org;
	}
	
	public static boolean updateOrg(int id, Organization newinfo) {
		boolean updated = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			
			// find the student with given id
			Organization student = em.find(Organization.class, id);
			
			// update the student's info
			student.setName(newinfo.getName());
			student.setUserName(newinfo.getUserName());
			
			trans.commit();
			updated = true;
			
		} catch (Exception e) {
			if(trans != null)
				trans.rollback();
			e.printStackTrace();
		} finally {
			em.close();	
		}
		return updated;
	}
	
	public static boolean updatePassword(int id, String newPassword) {
		boolean success = false;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			
			Organization u = em.find(Organization.class, id);
			u.setPassword(newPassword);
			
			trans.commit();
			
			success = true;
		}catch(Exception e){
			if(trans!=null){
				trans.rollback();
			}
			e.printStackTrace();
		}
		em.close();
		
		return success;
	}
}
*/ 

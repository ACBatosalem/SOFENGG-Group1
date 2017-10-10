package service;

import javax.persistence.*;
import java.util.List;
import bean.Organization;

public class OrganizationService {
	
	public static boolean addOrg(Organization org) {
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

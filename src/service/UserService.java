package service;

import javax.persistence.*;
import java.util.List;
import bean.User;

public class UserService {
	
	/*public static void addUser(User user) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			em.persist(user);
			trans.commit();
		}catch(Exception e){
			if(trans!=null){
				trans.rollback();
			}
			e.printStackTrace();
		}
		em.close();
	}*/
	
	public static User getUserByUsername(String username) {
		User user = null;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			
			TypedQuery<User> query = em.createQuery("SELECT orgs FROM orgs orgs WHERE orgAlias = :username", User.class);
			query.setParameter("username", username);
			if (query.getResultList() != null && query.getResultList().size() > 0)
				user = (User) query.getResultList().get(0);
			
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
	
	public static List<User> getAllUsers() {
		List<User> users = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			
			TypedQuery<User> query = em.createQuery("SELECT orgs FROM orgs orgs", User.class);
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
	
	public static boolean updatePassword(int id, String newPassword) {
		boolean success = false;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqldb");
		EntityManager em = emf.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try{
			trans.begin();
			
			User u = em.find(User.class, id);
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

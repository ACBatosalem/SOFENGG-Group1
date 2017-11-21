package cso.dlsu.bean;

/**
 * @author Batosalem, Angelika
 * @author Eroles, Carlo Miguel
 * @author Respicio, Michael Ryan
 * @author Ticug, Jonal Ray
 * @version 1.0
 */
public class Organization {
	
	private int id;
	private String userName;
	private String password;
	private String email;
	
	public static final String TABLE = "orgs";
	public static final String COL_ID = "org_id";
	//public static final String COL_FULLNAME = "fullname";
	public static final String COL_USERNAME = "username";
	public static final String COL_PASSWORD = "password";
	public static final String COL_EMAIL = "email";
	
	/**
	 * This function is used to get the id of the organization.
	 * @return the id of the organization
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * This function is used to set the id of the organization.
	 * @param id the id of the organization
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/*public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}*/
	
	/**
	 * This function is used to get the username of the organization.
	 * @return the username of the organization
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * This function is used to set the username of the organization.
	 * @param userName the username of the organization
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * This function is used to get the password of the organization.
	 * @return the password of the organization
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * This function is used to set the password of the organization.
	 * @param password the password of the organization
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * This function is used to get the email of the organization.
	 * @return the email of the organization
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * This function is used to set the email of the organization.
	 * @param email the email of the organization
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Organization [id=" + id + ", userName=" + userName + ", password=" + password + ", email=" + email + "]";
	}



	/*@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", userName=" + userName + ", password=" + password + "]";
	}*/
}

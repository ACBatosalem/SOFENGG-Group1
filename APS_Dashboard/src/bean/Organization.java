package bean;

import javax.persistence.*;

@Entity(name="orgs")
public class Organization {
	@Id
	@Column(name="orgID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="orgFullName")
	private String name;
	@Column(name="orgUserName")
	private String userName;
	@Column(name="orgPW")
	private String password;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", userName=" + userName + ", password=" + password + "]";
	}
}

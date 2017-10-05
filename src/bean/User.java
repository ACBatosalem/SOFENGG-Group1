package bean;

import javax.persistence.*;

@Entity(name="members")
public class User {
	@Id
	@Column(name="memID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="memEmail")
	private String email;
	@Column(name="memPW")
	private String password;
	@Column(name="memType")
	private String type;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", type=" + type + "]";
	}
	
	
}

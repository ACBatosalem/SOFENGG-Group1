package cso.dlsu.bean;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Notification {
	private int id;
	private String userName;
	private LocalTime time;
	private String message;
	private boolean read;
	
	
	
	public Notification () {
		read = false;
	}
	
	public Notification(String userName, LocalTime localTime, String message) {
		super();
		this.userName = userName;
		this.time = localTime;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}

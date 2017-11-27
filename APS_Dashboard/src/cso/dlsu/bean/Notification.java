package cso.dlsu.bean;

public class Notification {
	private int id;
	private String userName;
	private long time;
	private String message;
	private boolean read;
	
	public Notification () {
		read = false;
	}
	
	public Notification(String userName, long localTime, String message) {
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

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
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

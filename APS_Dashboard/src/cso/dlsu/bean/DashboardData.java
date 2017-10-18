package cso.dlsu.bean;

public class DashboardData {
	
	private String timeStamp;
	private String orgName;
	private String title;
	private String date;
	private String status;
	
	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getOrgName() {
		return orgName;
	}
	
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DashboardData [timeStamp=" + timeStamp + ", orgName=" + orgName + ", title=" + title + ", date=" + date
				+ ", status=" + status + "]";
	}
}

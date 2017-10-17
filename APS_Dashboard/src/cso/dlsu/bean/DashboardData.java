package cso.dlsu.bean;

import java.util.Date;

public class DashboardData {
	
	private Date timeStamp;
	private String orgName;
	private String title;
	private Date date;
	private String status;
	
	public Date getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(Date timeStamp) {
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
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
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

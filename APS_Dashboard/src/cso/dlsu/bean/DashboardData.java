package cso.dlsu.bean;


/**
 * @author Batosalem, Angelika
 * @author Eroles, Carlo Miguel
 * @author Respicio, Michael Ryan
 * @author Ticug, Jonal Ray
 * @version 1.0
 */
public class DashboardData {
	
	private String timeStamp;
	private String orgName;
	private String title;
	private String date;
	private String status;
	
	/**
	 * This function is used to get the time that the document was submitted.
	 * @return the time that the document was submitted
	 */
	public String getTimeStamp() {
		return timeStamp;
	}
	
	/**
	 * This function is used to set the time that the document was submitted.
	 * @param timeStamp the time that the document was submitted 
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	/**
	 * This function is used to get the name of the organization that made the document submission.
	 * @return the name of the organization that made the document submission
	 */
	public String getOrgName() {
		return orgName;
	}
	
	/**
	 * This function is used to set the name of the organization that made the document submission.
	 * @param orgName the name of the organization that made the document submission
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	/**
	 * This function is used to get the title of the activity indicated in the submitted document.
	 * @return the title of the activity indicated in the submitted document
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * This function is used to set the title of the activity indicated in the submitted document.
	 * @param title the title of the activity indicated in the submitted document
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * This function is used to get the date the activity will take place.
	 * @return the date the activity will take place
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * This function is used to set the date the activity will take place.
	 * @param the date the activity will take place 
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * This function is used to get the current status of the submitted document.
	 * @return the current status of the submitted document
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * This function is used to set the current status of the submitted document.
	 * @param status the current status of the submitted document
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DashboardData [timeStamp=" + timeStamp + ", orgName=" + orgName + ", title=" + title + ", date=" + date
				+ ", status=" + status + "]";
	}
}

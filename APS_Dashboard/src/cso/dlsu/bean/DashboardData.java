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
	private int docuID;
	private int orgID;
	private int actID;
	private int subID;
	private int checkID;
	
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
		this.status = status.toUpperCase();
	}
	
	/**
	 * This function is used to get the document id of the submitted document.
	 * @return the document id of the submitted document
	 */
	public int getDocuID() {
		return docuID;
	}
	
	/**
	 * This function is used to set the document id of the submitted document.
	 * @param docuID the document id of the submitted document
	 */
	public void setDocuID(int docuID) {
		this.docuID = docuID;
	}

	/**
	 * This function is used to get the id of the organization that submitted the submitted document.
	 * @return the id of the organization that submitted the submitted document
	 */
	public int getOrgID() {
		return orgID;
	}
	
	/**
	 * This function is used to set the id of the organization that submitted the submitted document.
	 * @param orgID the id of the organization that submitted the submitted document
	 */
	public void setOrgID(int orgID) {
		this.orgID = orgID;
	}

	/**
	 * This function is used to get the activity id of the submitted document.
	 * @return the activity id of the submitted document
	 */
	public int getActID() {
		return actID;
	}

	/**
	 * This function is used to set the activity id of the submitted document.
	 * @param actID the activity id of the submitted document
	 */
	public void setActID(int actID) {
		this.actID = actID;
	}
	
	/**
	 * This function is used to get the submission id of the submitted document.
	 * @return the submission id of the submitted document
	 */
	public int getSubID() {
		return subID;
	}

	/**
	 * This function is used to set the submission id of the submitted document.
	 * @param subID the submission id of the submitted document
	 */ 
	public void setSubID(int subID) {
		this.subID = subID;
	}

	/**
	 * This function is used to get the checking details id of the submitted document.
	 * @return the checking details id of the submitted document
	 */
	public int getCheckID() {
		return checkID;
	}
	
	/**
	 * This function is used to set the checking details id of the submitted document.
	 * @param checkID the checking details id of the submitted document
	 */
	public void setCheckID(int checkID) {
		this.checkID = checkID;
	}

	/**
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DashboardData [timeStamp=" + timeStamp + ", orgName=" + orgName + ", title=" + title + ", date=" + date
				+ ", status=" + status + ", docuID=" + docuID + ", orgID=" + orgID + ", actID=" + actID + ", subID="
				+ subID + ", checkID=" + checkID + "]";
	}
}

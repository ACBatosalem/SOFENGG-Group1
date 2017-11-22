package cso.dlsu.bean;

/**
 * @author Batosalem, Angelika
 * @author Eroles, Carlo Miguel
 * @author Respicio, Michael Ryan
 * @author Ticug, Jonal Ray
 * @version 1.0
 */
public class CheckingDetails {
	
	private int id;
	private int subID;
	private int statusID;
	private String checkerName;
	private String dateChecked;
	private String remarks;
	
	public static final String TABLE = "checking_details";
	public static final String TEMP_TABLE = "checking_details_temp";
	public static final String COL_ID = "chk_id";
	public static final String COL_SUB_ID = "sub_id";
	public static final String COL_STATUS_ID = "status_id";
	public static final String COL_CHECKER_NAME = "checker_name";
	public static final String COL_DATE_CHECKED = "date_checked";
	public static final String COL_REMARKS = "remarks";
	
	/**
	 * This function is used to get the id of the CheckingDetails object.
	 * @return the id of the CheckingDetails object
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * This function is used to set the id of the CheckingDetails object.
	 * @param id the id of the CheckingDetails object 
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * This function is used to get the submission id of the document the CheckingDetails object refers to.
	 * @return the submission id of the document submission the CheckingDetails object refers to
	 */
	public int getSubID() {
		return subID;
	}
	
	/**
	 * This function is used to set the submission id of the document the CheckingDetails object refers to.
	 * @param subID the submission id of the document submission the CheckingDetails object
	 */
	public void setSubID(int subID) {
		this.subID = subID;
	}
	
	/**
	 * This function is used to get the status id of the CheckingDetails object.
	 * @return the status id of the CheckingDetails object
	 */
	public int getStatusID() {
		return statusID;
	}
	
	/**
	 * This function is used to set the status id of the CheckingDetails object.
	 * @param statusID the status id of the CheckingDetails object
	 */
	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	
	/**
	 * This function is used to get the name of the person who checked the document submission the CheckingDetails object refers to.
	 * @return the name of the person who checked the document submission the CheckingDetails object refers to 
	 */
	public String getCheckerName() {
		return checkerName;
	}
	
	/**
	 * This function is used to set the name of the person who checked the document submission the CheckingDetails object refers to.
	 * @param checkerName the name of the person who checked the document submission the CheckingDetails object refers to 
	 */
	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}
	
	/**
	 * This function is used to get the date the document submission the CheckingDetails object refers to was checked.
	 * @return the date the document submission the CheckingDetails object refers to was checked 
	 */
	public String getDateChecked() {
		return dateChecked;
	}
	
	/**
	 * This function is used to set the date the document submission the CheckingDetails object refers to was checked.
	 * @param dateChecked the date the document submission the CheckingDetails object refers to was checked
	 */
	public void setDateChecked(String dateChecked) {
		this.dateChecked = dateChecked;
	}
	
	/**
	 * This function is used to get the remarks of the checker for the document submitted which the CheckingDetails object refers to.
	 * @return the remarks given by the checker for the document submitted which the CheckingDetails object refers to
	 */
	public String getRemarks() {
		return remarks;
	}
	
	/**
	 * This function is used to set the remarks of the checker for the document submitted which the CheckingDetails object refers to.
	 * @param remarks any remarks the checker has for the document submitted which the CheckingDetails object refers to
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CheckingDetails [id=" + id + ", subID=" + subID + ", statusID=" + statusID + ", checkerName="
				+ checkerName + ", dateChecked=" + dateChecked + ", remarks=" + remarks + "]";
	}
}

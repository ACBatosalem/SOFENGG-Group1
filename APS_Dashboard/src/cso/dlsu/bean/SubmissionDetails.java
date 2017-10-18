package cso.dlsu.bean;

/**
 * @author Batosalem, Angelika
 * @author Eroles, Carlo Miguel
 * @author Respicio, Michael Ryan
 * @author Ticug, Jonal Ray
 * @version 1.0
 */
public class SubmissionDetails {

	private int id;
	private int docuID;
	private String dateSubmitted;
	private String submissionType;
	private String submittedBy;
	private String emailAddress;
	private String contactNo;
	
	public static final String TABLE = "submission_details";
	public static final String COL_ID = "sub_id";
	public static final String COL_DOCU_ID = "docu_id";
	public static final String COL_DATE_SUBMITTED = "date_submitted";
	public static final String COL_SUBMISSION_TYPE = "submssion_type";
	public static final String COL_SUBMITTED_BY = "submitted_by";
	public static final String COL_EMAIL_ADDRESS = "email_address";
	public static final String COL_CONTACT_NO = "contact_no";
	
	/**
	 * This function is used to get the id of the submission.
	 * @return the id of the submission
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * This function is used to set the id of the submission.
	 * @param id the id of the submission
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * This function is used to get the id of the document the submission details are for.
	 * @return the id of the document the submission details are for
	 */
	public int getDocuID() {
		return docuID;
	}
	
	/**
	 * This function is used to set the id of the document the submission details are for.
	 * @param docuID the id of the document the submission details are for
	 */
	public void setDocuID(int docuID) {
		this.docuID = docuID;
	}
	
	/**
	 * This function is used to get the date of submission.
	 * @return the date of submission
	 */
	public String getDateSubmitted() {
		return dateSubmitted;
	}
	
	/**
	 * This function is used to set the date of submission.
	 * @param dateSubmitted the date of submission
	 */
	public void setDateSubmitted(String dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	
	/**
	 * This function is used to get the type of submission.
	 * @return the type of submission
	 */
	public String getSubmissionType() {
		return submissionType;
	}
	
	/**
	 * This function is used to set the type of submission.
	 * @param submissionType the type of submission
	 */
	public void setSubmissionType(String submissionType) {
		this.submissionType = submissionType;
	}
	
	/**
	 * This function is used to get the name of the person who made the submission.
	 * @return the name of the person who made the submission
	 */
	public String getSubmittedBy() {
		return submittedBy;
	}
	
	/**
	 * This function is used to set the name of the person who made the submission.
	 * @param submittedBy the name of the person who made the submission
	 */
	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}
	
	/**
	 * This function is used to get the email address of the person who made the submission.
	 * @return the email address of the person who made the submission
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	
	/**
	 * This function is used to set the email address of the person who made the submission.
	 * @param emailAddress the email address of the person who made the submission
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	/**
	 * This function is used to get the contact number of the person who made the submission.
	 * @return the contact number of the person who made the submission
	 */
	public String getContactNo() {
		return contactNo;
	}
	
	/**
	 * This function is used to set the contact number of the person who made the submission.
	 * @param contactNo the contact number of the person who made the submission
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SubmissionDetails [id=" + id + ", docuID=" + docuID + ", dateSubmitted=" + dateSubmitted
				+ ", submissionType=" + submissionType + ", submittedBy=" + submittedBy + ", emailAddress="
				+ emailAddress + ", contactNo=" + contactNo + "]";
	}
}

package cso.dlsu.bean;

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
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getDocuID() {
		return docuID;
	}
	
	public void setDocuID(int docuID) {
		this.docuID = docuID;
	}
	
	public String getDateSubmitted() {
		return dateSubmitted;
	}
	
	public void setDateSubmitted(String dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	
	public String getSubmissionType() {
		return submissionType;
	}
	
	public void setSubmissionType(String submissionType) {
		this.submissionType = submissionType;
	}
	
	public String getSubmittedBy() {
		return submittedBy;
	}
	
	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getContactNo() {
		return contactNo;
	}
	
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	@Override
	public String toString() {
		return "SubmissionDetails [id=" + id + ", docuID=" + docuID + ", dateSubmitted=" + dateSubmitted
				+ ", submissionType=" + submissionType + ", submittedBy=" + submittedBy + ", emailAddress="
				+ emailAddress + ", contactNo=" + contactNo + "]";
	}
}

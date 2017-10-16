package cso.dlsu.bean;

import java.util.Date;

import javax.persistence.*;

@Entity(name="transactions")
public class Transaction {

	@Id
	@Column(name="trans_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="docu_id")
	private int docuID;
	@Column(name="status_id")
	private int statusID;
	@Column(name="checker_name")
	private String checkerName = null;
	@Column(name="date_submitted")
	private Date dateSubmitted;
	@Column(name="submission_type")
	private String submissionType;
	@Column(name="date_checked")
	private Date dateChecked = null;
	@Column(name="remarks")
	private String remarks = null;
	
	public static final String TABLE = "transactions";
	public static final String COL_ID = "trans_id";
	public static final String COL_DOCU_ID = "docu_id";
	public static final String COL_STATUS_ID = "status_id";
	public static final String COL_CHECKER_NAME = "checker_name";
	public static final String COL_DATE_SUBMITTED = "date_submitted";
	public static final String COL_SUBMISSION_TYPE = "submssion_type";
	public static final String COL_DATE_CHECKED = "date_checked";
	public static final String COL_REMARKS = "remarks";
	
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
	
	public int getStatusID() {
		return statusID;
	}
	
	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	
	public String getCheckerName() {
		return checkerName;
	}
	
	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}
	
	public Date getDateSubmitted() {
		return dateSubmitted;
	}
	
	public void setDateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	
	public String getSubmissionType() {
		return submissionType;
	}
	
	public void setSubmissionType(String submissionType) {
		this.submissionType = submissionType;
	}
	
	public Date getDateChecked() {
		return dateChecked;
	}
	
	public void setDateChecked(Date dateChecked) {
		this.dateChecked = dateChecked;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", docuID=" + docuID + ", statusID=" + statusID + ", checkerName="
				+ checkerName + ", dateSubmitted=" + dateSubmitted + ", submissionType=" + submissionType
				+ ", dateChecked=" + dateChecked + ", remarks=" + remarks + "]";
	}
}

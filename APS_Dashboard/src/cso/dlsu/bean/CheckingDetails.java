package cso.dlsu.bean;

public class CheckingDetails {
	
	private int id;
	private int subID;
	private int statusID;
	private String checkerName;
	private String dateChecked;
	private String remarks;
	
	public static final String TABLE = "checking_details";
	public static final String COL_ID = "chk_id";
	public static final String COL_SUB_IB = "sub_id";
	public static final String COL_STATUS_ID = "status_id";
	public static final String COL_CHECKER_NAME = "checker_name";
	public static final String COL_DATE_CHECKED = "date_checked";
	public static final String COL_REMARKS = "remarls";
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getSubID() {
		return subID;
	}
	
	public void setSubID(int subID) {
		this.subID = subID;
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
	
	public String getDateChecked() {
		return dateChecked;
	}
	
	public void setDateChecked(String dateChecked) {
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
		return "CheckingDetails [id=" + id + ", subID=" + subID + ", statusID=" + statusID + ", checkerName="
				+ checkerName + ", dateChecked=" + dateChecked + ", remarks=" + remarks + "]";
	}
}

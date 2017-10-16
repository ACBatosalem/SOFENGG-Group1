package cso.dlsu.bean;

import java.util.Date;

import javax.persistence.*;

@Entity(name="activity_dates")
public class ActivityDate {
	
	@Id
	@Column(name="docu_id")
	private int docuID;
	@Column(name="start_datetime")
	private Date startDate;
	@Column(name="end_datetime")
	private Date endDate;
	
	public static final String TABLE = "activity_dates";
	public static final String COL_DOCU_ID = "docu_id";
	public static final String COL_START_DATE = "start_datetime";
	public static final String COL_END_DATE = "end_datetime";
	
	public int getDocuID() {
		return docuID;
	}
	
	public void setDocuID(int docuID) {
		this.docuID = docuID;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public String toString() {
		return "ActivityDate [docuID=" + docuID + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
}

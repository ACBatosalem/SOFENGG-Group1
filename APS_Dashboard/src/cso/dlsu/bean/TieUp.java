package cso.dlsu.bean;

import javax.persistence.*;

@Entity(name="tie_ups")
public class TieUp {
	
	@Id
	@Column(name="docu_id")
	private int docuID;
	@Column(name="org_id")
	private int orgID;
	
	public static final String TABLE = "tie_ups";
	public static final String COL_DOCU_ID = "docu_id";
	public static final String COL_ORG_ID = "org_id";
	
	public int getDocuID() {
		return docuID;
	}
	
	public void setDocuID(int docuID) {
		this.docuID = docuID;
	}
	
	public int getOrgID() {
		return orgID;
	}
	
	public void setOrgID(int orgID) {
		this.orgID = orgID;
	}

	@Override
	public String toString() {
		return "TieUp [docuID=" + docuID + ", orgID=" + orgID + "]";
	}
}

package cso.dlsu.bean;

/**
 * @author Batosalem, Angelika
 * @author Eroles, Carlo Miguel
 * @author Respicio, Michael Ryan
 * @author Ticug, Jonal Ray
 * @version 1.0
 */
public class TieUp {
	
	private int docuID;
	private int orgID;
	
	public static final String TABLE = "tie_ups";
	public static final String COL_DOCU_ID = "docu_id";
	public static final String COL_ORG_ID = "org_id";
	
	/**
	 * This function is used to get the id of the document the organization is involved in.
	 * @return the id of the document the organization is involved in
	 */
	public int getDocuID() {
		return docuID;
	}
	
	/**
	 * This function is used to set the id of the document the organization is involved in.
	 * @param docuID the id of the document the organization is involved in
	 */
	public void setDocuID(int docuID) {
		this.docuID = docuID;
	}
	
	/**
	 * This function is used to get the id of the organization that is involved in the document.
	 * @return the id of the organization that is involved in the document
	 */
	public int getOrgID() {
		return orgID;
	}
	
	/**
	 * This function is used to set the id of the organization that is involved in the document.
	 * @param orgID the id of the organization that is involved in the document
	 */
	public void setOrgID(int orgID) {
		this.orgID = orgID;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TieUp [docuID=" + docuID + ", orgID=" + orgID + "]";
	}
}

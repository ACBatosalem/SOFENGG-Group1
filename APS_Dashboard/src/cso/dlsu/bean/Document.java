package cso.dlsu.bean;

/**
 * @author Batosalem, Angelika
 * @author Eroles, Carlo Miguel
 * @author Respicio, Michael Ryan
 * @author Ticug, Jonal Ray
 * @version 1.0
 */
public class Document {
	private int id;
	private int orgID;
	private String title;
	private String term;
	
	
	public static final String TABLE = "documents";
	public static final String COL_ID = "docu_id";
	public static final String COL_ORG_ID = "org_id";
	public static final String COL_TITLE = "title";
	public static final String COL_TERM = "term";
	
	/**
	 * This function is used to get the id of the document.
	 * @return the id of the document
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * This function is used to set the id of the document.
	 * @param id the id of the document
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * This function is used to get the id of the organization that submitted the document.
	 * @return the id of the organization that submitted the document
	 */
	public int getOrgID() {
		return orgID;
	}
	
	/**
	 * This function is used to set the id of the organization that submitted the document.
	 * @param orgID the id of the organization that submitted the document
	 */
	public void setOrgID(int orgID) {
		this.orgID = orgID;
	}
	
	/**
	 * This function is used to get the title of the activity the document is for.
	 * @return the title of the activity the document is for
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * This function is used to set the title of the activity the document is for.
	 * @param title the title of the activity the document is for
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * This function is used to get the term which the activity will take place.
	 * @return the term which the activity will take place
	 */
	public String getTerm() {
		return term;
	}
	
	/**
	 * This function is used to set the term which the activity will take place.
	 * @param term the term which the activity will take place
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Document [id=" + id + ", orgID=" + orgID + ", title=" + title + ", term=" + term + "]";
	}
}

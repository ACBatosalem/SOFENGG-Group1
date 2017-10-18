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
	private int term;
	private String nature = null;
	private String type = null;
	private String venue = null;
	private String date;
	private String time;
	
	public static final String TABLE = "documents";
	public static final String COL_ID = "docu_id";
	public static final String COL_ORG_ID = "org_id";
	public static final String COL_TITLE = "title";
	public static final String COL_TERM = "term";
	public static final String COL_NATURE = "nature";
	public static final String COL_TYPE = "type";
	public static final String COL_VENUE = "venue";
	public static final String COL_DATE = "date";
	public static final String COL_TIME = "time";
	
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
	public int getTerm() {
		return term;
	}
	
	/**
	 * This function is used to set the term which the activity will take place.
	 * @param term the term which the activity will take place
	 */
	public void setTerm(int term) {
		this.term = term;
	}
	
	/**
	 * This function is used to get the nature of the activity.
	 * @return the nature of the activity
	 */
	public String getNature() {
		return nature;
	}
	
	/**
	 * This function is used to set the nature of the activity.
	 * @param nature the nature of the activity
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}
	
	/**
	 * This function is used to get the type of the activity. 
	 * @return the type of the activity
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * This function is used to set the type of the activity. 
	 * @param type the type of activity
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * This function is used to get the venue in which the activity will take place.
	 * @return the venue in which the activity will take place
	 */
	public String getVenue() {
		return venue;
	}
	
	/**
	 * This function is used to set the venue in which the activity will take place.
	 * @param venue the venue in which the activity will take place
	 */
	public void setVenue(String venue) {
		this.venue = venue;
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
	 * @param date the activity will take place
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * This function is used to get the time the activity will take place.
	 * @return the time the activity will take place
	 */
	public String getTime() {
		return time;
	}

	/**
	 * This function is used to set the time the activity will take place.
	 * @param time the time the activity will take place
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Document [id=" + id + ", orgID=" + orgID + ", title=" + title + ", term=" + term + ", nature=" + nature
				+ ", type=" + type + ", venue=" + venue + ", date=" + date + ", time=" + time + "]";
	}
}

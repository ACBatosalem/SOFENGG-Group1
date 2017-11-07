package cso.dlsu.bean;

/**
 * @author Batosalem, Angelika
 * @author Eroles, Carlo Miguel
 * @author Respicio, Michael Ryan
 * @author Ticug, Jonal Ray
 * @version 1.0
 */
public class ActivityDetails {
	private int id;
	private int docuID;
	private String nature = null;
	private String type = null;
	private String venue = null;
	private String date;
	private String time;
	
	public static final String TABLE = "activity_details";
	public static final String COL_ID = "id";
	public static final String COL_DOCU_ID = "docu_id";
	public static final String COL_NATURE = "nature";
	public static final String COL_TYPE = "type";
	public static final String COL_VENUE = "venue";
	public static final String COL_DATE = "date";
	public static final String COL_TIME = "time";
	
	/**
	 * This function is used to get the id of the activity details.
	 * @return the id of the activity details
	 */
	public int getId() {
		return id;
	}

	/**
	 * This function is used to set the id of the activity details.
	 * @param id the id of the activity details.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * This function is used to get the id of the document that the activity details are for.
	 * @return the id of the document that the activity details are for
	 */
	public int getDocuID() {
		return docuID;
	}
	
	/**
	 * This function is used to get the id of the document that the activity details are for.
	 * @param docuID the id of the document that the activity details are for
	 */
	public void setDocuID(int docuID) {
		this.docuID = docuID;
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
		return "ActivityDetails [nature=" + nature + ", type=" + type + ", venue=" + venue + ", date=" + date
				+ ", time=" + time + "]";
	}
}

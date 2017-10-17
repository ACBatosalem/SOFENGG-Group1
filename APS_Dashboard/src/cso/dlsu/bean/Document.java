package cso.dlsu.bean;

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
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getOrgID() {
		return orgID;
	}
	
	public void setOrgID(int orgID) {
		this.orgID = orgID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getTerm() {
		return term;
	}
	
	public void setTerm(int term) {
		this.term = term;
	}
	
	public String getNature() {
		return nature;
	}
	
	public void setNature(String nature) {
		this.nature = nature;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getVenue() {
		return venue;
	}
	
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Document [id=" + id + ", orgID=" + orgID + ", title=" + title + ", term=" + term + ", nature=" + nature
				+ ", type=" + type + ", venue=" + venue + ", date=" + date + ", time=" + time + "]";
	}
}

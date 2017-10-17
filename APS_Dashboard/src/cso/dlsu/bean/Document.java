package cso.dlsu.bean;

import javax.persistence.*;

@Entity(name="documents")
public class Document {
	@Id
	@Column(name="docu_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="org_id")
	private int orgID;
	@Column(name="title")
	private String title;
	@Column(name="term")
	private int term;
	@Column(name="nature")
	private String nature = null;
	@Column(name="type")
	private String type = null;
	@Column(name="venue")
	private String venue = null;
	
	public static final String TABLE = "documents";
	public static final String COL_ID = "docu_id";
	public static final String COL_ORG_ID = "org_id";
	public static final String COL_TITLE = "title";
	public static final String COL_TERM = "term";
	public static final String COL_NATURE = "nature";
	public static final String COL_TYPE = "type";
	public static final String COL_VENUE = "venue";
	
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

	@Override
	public String toString() {
		return "Document [id=" + id + ", orgID=" + orgID + ", title=" + title + ", term=" + term + ", nature=" + nature
				+ ", type=" + type + ", venue=" + venue + "]";
	}
}

package cso.dlsu.bean;

public enum Status {
	NONE(0),
	PENDING(1),
	EARLY_APPROVED(2);
	
	private final int statusID;
	 
	private Status(int id) {
		statusID = id;
	}
	
	public static Status toStatus(int i){
		switch (i) {
			case 1 : return PENDING;
			case 2 : return EARLY_APPROVED;
			default: return NONE;
		}
	}
	
	public String getStatus() {
		switch (statusID) {
			case 1 : return "Pending";
			case 2 : return "Early Approved";
			default: return "-";
		}
	}
	
	
		
}

package cso.dlsu.bean;

/**
 * @author Batosalem, Angelika
 * @author Eroles, Carlo Miguel
 * @author Respicio, Michael Ryan
 * @author Ticug, Jonal Ray
 * @version 1.0
 */
public enum Status {
	NONE(0),
	PENDING(1),
	EARLY_APPROVED(2);
	
	private final int statusID;
	
	/**
	 * This function is used to instantiate the Status enums.
	 * @param id the status id
	 */
	private Status(int id) {
		statusID = id;
	}
	
	/**
	 * This function is used to get the Status object from the given status id.
	 * @param i the integer representing the status id
	 * @return the Status object which the status id is assigned to
	 */
	public static Status toStatus(int i){
		switch (i) {
			case 1 : return PENDING;
			case 2 : return EARLY_APPROVED;
			default: return NONE;
		}
	}
	
	/**
	 * This function is used to get the String representation of the status object.
	 * @return the String representation of the Status object
	 */
	public String getStatus() {
		switch (statusID) {
			case 1 : return "Pending";
			case 2 : return "Early Approved";
			default: return "-";
		}
	}
	
	
		
}

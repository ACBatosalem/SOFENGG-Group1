package cso.dlsu.service;

import cso.dlsu.bean.ActivityDetails;
import cso.dlsu.bean.CheckingDetails;
import cso.dlsu.bean.Document;
import cso.dlsu.bean.Notification;
import cso.dlsu.bean.Status;
import cso.dlsu.bean.SubmissionDetails;

public class CreateData {
	public static void createDocument(String[] attributes) {
		// TODO Auto-generated method stub
		attributes[2] = attributes[2].toUpperCase();
		int orgID = OrganizationService.getOrgByUsername(attributes[2]).getId();
		try {
			
			Document docu = DocumentService.getDocumentByTitleAndOrg(attributes[5], orgID);
			//System.out.println(orgID);
			if(docu == null) {
				//create document
				Document document = new Document();
				//System.out.println(attributes[2]);
				
				document.setOrgID(orgID);
				document.setTitle(attributes[5]);
				document.setTerm(attributes[1]);
				
				DocumentService.addDocument(document);

			} 
			createActivity(attributes, docu.getId(), orgID);
		} catch(Exception e){
			System.out.println("Error");
		}
		/*else if(attributes.length >= 20) {
			if (attributes[19].equals("In Case of Change")
				   || attributes[19].equals("Activity Not in GOSM")) {
				createActivity(attributes);
			} else {
				createSubmission(attributes);
			}
		} else {
			createSubmission(attributes);
		}*/
		
	}
	
	private static void createActivity(String[] attributes, int docuID, int orgID) {
		//if(!attributes[4].equals("Pended")) {
		ActivityDetails actDet = new ActivityDetails();
		if(attributes[6].equals("One-day Activity") || attributes[6].equals("Multiple Dates")) {
			actDet.setDocuID(docuID);
			actDet.setNature(attributes[12]);
			actDet.setType(attributes[13]);
			actDet.setVenue(attributes[15]);
			actDet.setDate(attributes[11]);
			actDet.setTime(attributes[14]);
		} else {

			actDet.setDocuID(docuID);
			actDet.setNature(attributes[7]);
			actDet.setType(attributes[8]);
			actDet.setVenue(attributes[10]);
			actDet.setDate(attributes[6]);
			actDet.setTime(attributes[9]);
		}
			
			ActivityDetailsService.addActivityDetails(actDet);
		//}
		
		createSubmission(attributes, docuID, orgID);
	}
	
	private static void createSubmission(String[] attributes, int docuID, int orgID) {
		// TODO Auto-generated method stub
		int actID = ActivityDetailsService.getActivityDetailsByDocuID(docuID).getId();
		int submissionID = SubmissionDetailsService.getSubmissionIDByDateSubmittedAndActID(toDateTime(attributes[0]), actID);
		//System.out.println("docuID: " + docuID);
		if(submissionID == 0){
			//create submission details
			SubmissionDetails subDet = new SubmissionDetails();
			subDet.setActID(actID);
			subDet.setSubmittedBy(attributes[16]);
			subDet.setContactNo(attributes[17]);
			subDet.setEmailAddress(attributes[18]);
			subDet.setDateSubmitted(toDateTime(attributes[0]));
			subDet.setSubmissionType(attributes[4]);
			
			if(subDet.getSubmissionType().equalsIgnoreCase("Special Approval Slip"))
				subDet.setSasType(attributes[19]);
			else subDet.setSasType("-");
			SubmissionDetailsService.addSubmissionDetails(subDet);
			if(attributes.length >= 21) {
				submissionID = SubmissionDetailsService.getSubmissionIDByDateSubmittedAndActID(toDateTime(attributes[0]), actID);
				createCheckingDetails(attributes, submissionID);
			}
		} else {
			if(attributes.length >= 21) {
				submissionID = SubmissionDetailsService.getSubmissionIDByDateSubmittedAndActID(toDateTime(attributes[0]), actID);
				createCheckingDetails(attributes, submissionID);
			}
		}
	
	}
	private static String toDateTime(String dateSubmitted) {
		// TODO Auto-generated method stub
		String[] extracted = dateSubmitted.split(" ");
		
		String[] date = extracted[0].split("/");
		
		if(date[0].length() == 1)
			date[0] = "0" + date[0];
		if(date[1].length() == 1)
			date[1] = "0" + date[1];
		
		return date[2] + "-" + date[0] + "-" + date[1] + " " + extracted[1];
	}

	private static void createCheckingDetails(String[] attributes, int submissionID) {
		// TODO Auto-generated method stub
		if(!CheckingDetailsService.findSubmissionByID(submissionID) 
				&& !attributes[20].equals("")) {
			//create checking details
			//System.out.println(submissionID+"");
			CheckingDetails checkDet = new CheckingDetails();
			try{
				checkDet.setCheckerName(attributes[21]);
			} catch(Exception e){
				checkDet.setCheckerName("N/A");
			}

			try{
				checkDet.setDateChecked(attributes[22]);
			} catch(Exception e){
				checkDet.setDateChecked("N/A");
			}
			
			String remark = "";
			
			if(attributes.length >= 24){
				checkDet.setRemarks(attributes[23]);
				remark = attributes[23];
			}
			else {
				checkDet.setRemarks("N/A");
				remark = "N/A";
			}
			
			checkDet.setStatusID(Status.getStatusByName(attributes[20].toUpperCase()));
			checkDet.setSubID(submissionID);
			
			CheckingDetailsService.addCheckingDetails(checkDet);
			int subID = SubmissionDetailsService.getSubmissionIDByDateSubmittedAndSubmittedBy(toDateTime(attributes[0]), 
					attributes[16]);
			CheckingDetails cd = CheckingDetailsService.getCheckingDetailsOfSubmission(subID);
			if(cd == null || 
					(Status.getStatusByName(attributes[20].toUpperCase()) != cd.getStatusID()) ||
					!cd.getRemarks().equals(remark)) {
				Notification n = new Notification(attributes[2], 
						System.currentTimeMillis(), 
						attributes[5] + " - " + attributes[20].toUpperCase());
				NotificationService.notify(n);
			} 
		}
	}
}

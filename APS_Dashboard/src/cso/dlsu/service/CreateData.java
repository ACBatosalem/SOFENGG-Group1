package cso.dlsu.service;

import cso.dlsu.bean.ActivityDetails;
import cso.dlsu.bean.CheckingDetails;
import cso.dlsu.bean.Document;
import cso.dlsu.bean.Status;
import cso.dlsu.bean.SubmissionDetails;

public class CreateData {
	public static void createDocument(String[] attributes) {
		// TODO Auto-generated method stub
		if(DocumentService.getDocumentByTitle(attributes[5]) == null) {
			//create document
			Document document = new Document();
			System.out.println(attributes[2]);
			document.setOrgID(OrganizationService.getOrgByUsername(attributes[2]).getId());
			document.setTitle(attributes[5]);
			document.setTerm(Integer.parseInt(attributes[1].split(" ")[1]));
			
			DocumentService.addDocument(document);
			createActivity(attributes);
		} else if (attributes[19].equals("In Case of Change")
				   || attributes[19].equals("Activity Not in GOSM")) {
			createActivity(attributes);
		} else {
			createSubmission(attributes);
		}
		
	}
	
	private static void createActivity(String[] attributes) {
		ActivityDetails actDet = new ActivityDetails();
		
		actDet.setDocuID(DocumentService.getDocumentByTitle(attributes[5]).getId());
		actDet.setNature(attributes[12]);
		actDet.setType(attributes[13]);
		actDet.setVenue(attributes[15]);
		actDet.setDate(attributes[11]);
		actDet.setTime(attributes[14]);
		
		ActivityDetailsService.addActivityDetails(actDet);
		
		createSubmission(attributes);
	}
	
	private static void createSubmission(String[] attributes) {
		// TODO Auto-generated method stub
		int submissionID = SubmissionDetailsService.getSubmissionIDByDateSubmitted(attributes[0]);
		int docuID = DocumentService.getDocumentByTitle(attributes[5]).getId();
		if(submissionID == 0){
			//create submission details
			SubmissionDetails subDet = new SubmissionDetails();
			subDet.setActID(ActivityDetailsService.getActivityDetailsByDocuID(docuID).getId());
			subDet.setSubmittedBy(attributes[16]);
			subDet.setContactNo(attributes[17]);
			subDet.setEmailAddress(attributes[18]);
			subDet.setDateSubmitted(attributes[0]);
			subDet.setSubmissionType(attributes[4]);
			
			if(subDet.getSubmissionType().equalsIgnoreCase("Special Approval Slip"))
				subDet.setSasType(attributes[19]);
			
			SubmissionDetailsService.addSubmissionDetails(subDet);
			submissionID = SubmissionDetailsService.getSubmissionIDByDateSubmitted(attributes[0]);
			createCheckingDetails(attributes, submissionID);
		} else {
			submissionID = SubmissionDetailsService.getSubmissionIDByDateSubmitted(attributes[0]);
			createCheckingDetails(attributes, submissionID);
		}
	
	}
	private static void createCheckingDetails(String[] attributes, int submissionID) {
		// TODO Auto-generated method stub
		if(!CheckingDetailsService.findSubmissionByID(submissionID) 
				&& !attributes[20].equals("")) {
			//create checking details
			System.out.println(submissionID+"");
			CheckingDetails checkDet = new CheckingDetails();
			checkDet.setCheckerName(attributes[21]);
			checkDet.setDateChecked(attributes[22]);
			
			if(attributes.length >= 24)
				checkDet.setRemarks(attributes[23]);
			
			checkDet.setStatusID(Status.getStatusByName(attributes[20].toUpperCase()));
			checkDet.setSubID(submissionID);
			
			CheckingDetailsService.addCheckingDetails(checkDet);
		
		}
	}
}

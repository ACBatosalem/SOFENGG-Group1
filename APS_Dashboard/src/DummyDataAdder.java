import java.util.Random;
import java.util.Date;
import java.util.List;

import cso.dlsu.bean.CheckingDetails;
import cso.dlsu.bean.DashboardData;
import cso.dlsu.bean.Document;
import cso.dlsu.bean.Organization;
import cso.dlsu.bean.TieUp;
import cso.dlsu.bean.SubmissionDetails;
import cso.dlsu.service.CheckingDetailsService;
import cso.dlsu.service.DashboardService;
import cso.dlsu.service.DocumentService;
import cso.dlsu.service.OrganizationService;
import cso.dlsu.service.SubmissionDetailsService;

public class DummyDataAdder {
	public static void main(String[] args) {
		
		//Dummy data
		/*String[][] orgsInfo = {	 {"Association of Computer Engineering Students", "ACCESS"},
								 {"Chemical Engineering Society", "ChEn"},
								 {"Civil Engineering Society", "CES"},
								 {"Electronic and Communications Engineering Society", "ECES"},
								 {"Industrial Management Engineering Society", "IMES"},
								 {"Mechanical Engineering Society", "MES"},
								 {"Society of Manufacturing Engineering", "SME"},
								 {"La Salle Computer Society", "LSCS"},
								 {"Union of Students Inspired Towards Education", "UNITED"},
								 {"DLSU Filipino & Chinese Organization", "ENGLICOM"},
								 {"Rotaract Club of DLSU- ROTARACT", "ROTARACT"},
								 {"United International Student Organization", "UNISTO"},
								 {"AIESEC-DLSU", "AIESEC"},
								 {"MOOMEDIA", "MOOMEDIA"},
								 {"Outdoor Club", "OC"},
								 {"Writers’ Guild", "WG"},
								 {"De La Salle University - Environmental Conservation Organization", "ECO"},
								 {"Gakuen Anime Shoshiki", "GAS"},
								 {"Chemistry Society", "ChemSoc"},
								 {"Mathematics Circle", "Math Circle"},
								 {"Physics Society", "PhySoc"},
								 {"Societas Vitae", "SV"},
								 {"AdCreate Society", "AdCreate"},
								 {"Business Management Society", "BMS"},
								 {"Economics Organization", "EconOrg"},
								 {"Junior Entrepreneurs Marketing Association", "JEMA"},
								 {"Junior Philippine Institute of Accountants", "JPIA"},
								 {"Ley La Salle", "LLS"},
								 {"Management of Financial Institutions Association", "MaFIA"},
								 {"Young Entrepreneurs Society", "YES"},
								 {"The Organization for American Studies Students", "AMSTUD"},
								 {"Behavioral Science Society", "BSS"},
								 {"Dalubhasaan ng mga Umuusbong na Mag-aaral ng Araling Filipino", "DANUM"},
								 {"European Studies Association", "ESA"},
								 {"Sociedad de Historia", "SDH"},
								 {"Samahan ng Lasalayanong Pilosopo", "DLSU Pilosopo"},
								 {"Nihon Kenkyuu Kai", "NKK"},
								 {"Political Science Society", "POLISCY"},
								 {"Samahan ng mga Mag-aaral sa Sikolohiya", "SMS"},
								 {"Team Communications", "TEAMCOMM"},
								 {"Kapatiran ng Kabataan para sa Kaunlaran", "KKK"}};
		
		Organization org = new Organization();
		Random randomizer = new Random();
		for (String[] orgInfo : orgsInfo) {
			//org.setName(orgInfo[0]);
			org.setUserName(orgInfo[1]);
			

			String password = "";
			for(int i = 0; i < 6; i++) {
				password += (char) (97 + randomizer.nextInt(26));
			}
			
			org.setPassword(password);
			
			OrganizationService.addOrg(org);
		}
		
		List<Organization> orgs = OrganizationService.getAllOrgs();
		
		for (Organization o : orgs)
			System.out.println(o.toString());*/
		
		/*Document docu = new Document();
		docu.setOrgID(4);
		docu.setTitle("Tituloooo");
		docu.setTerm(1);
		docu.setNature("Father Nature");
		docu.setType("Non-academic");
		docu.setVenue("G213");
		docu.setDate("11/18/2017");
		docu.setTime("12:00 - 18:00");
		DocumentService.addDocument(docu);
		
		SubmissionDetails trans = new SubmissionDetails();
		trans.setDocuID(3);
		trans.setDateSubmitted("10/13/2017");
		trans.setSubmissionType("Initial");
		trans.setSubmittedBy("Submitter guy");
		trans.setEmailAddress("submitter@gmail.com");
		trans.setContactNo("09171234567");
		SubmissionDetailsService.addSubmissionDetails(trans);
		
		CheckingDetails check = new CheckingDetails();
		check.setSubID(3);
		check.setStatusID(1);
		check.setCheckerName("Checker guy");
		check.setDateChecked("10/17/2017");
		check.setRemarks("Great great great!");
		CheckingDetailsService.addCheckingDetails(check);*/
		
		/*List<Document> docus = DocumentService.getAllDocuments();
		
		for (Document document : docus)
			System.out.println(document.toString());
		
		List<SubmissionDetails> transs = SubmissionDetailsService.getAllSubmissionDetails();
		
		for (SubmissionDetails transaction : transs)
			System.out.println(transaction.toString());
		
		List<CheckingDetails> checks = CheckingDetailsService.getAllCheckingDetails();
		
		for (CheckingDetails che : checks)
			System.out.println(che.toString());
		
		/*List<DashboardData> data = DashboardService.getAllDashboardData();
		for (DashboardData d : data)
			System.out.println(d.toString());*/
		
	}
}

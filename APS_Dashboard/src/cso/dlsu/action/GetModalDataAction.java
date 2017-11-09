package cso.dlsu.action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cso.dlsu.bean.ActivityDetails;
import cso.dlsu.bean.CheckingDetails;
import cso.dlsu.bean.Document;
import cso.dlsu.bean.Organization;
import cso.dlsu.bean.SubmissionDetails;
import cso.dlsu.service.ActivityDetailsService;
import cso.dlsu.service.CheckingDetailsService;
import cso.dlsu.service.DocumentService;
import cso.dlsu.service.OrganizationService;
import cso.dlsu.service.SubmissionDetailsService;

public class GetModalDataAction implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int docuID = Integer.parseInt(request.getParameter("docuID"));
		int orgID = Integer.parseInt(request.getParameter("orgID"));
		int actID = Integer.parseInt(request.getParameter("actID"));
		int subID = Integer.parseInt(request.getParameter("subID"));
		int checkID = Integer.parseInt(request.getParameter("checkID"));
		
		Gson gson = new Gson();
		
		Document d = DocumentService.getDocumentById(docuID);
		Organization o = OrganizationService.getOrg(orgID);
		ActivityDetails a = ActivityDetailsService.getActivityDetailsById(actID);
		SubmissionDetails s = SubmissionDetailsService.getSubmissionDetails(subID);
		CheckingDetails c = CheckingDetailsService.getCheckingDetails(checkID);
		
		response.setContentType("application/json");
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("document", d);
		data.put("org", o);
		data.put("act", a);
		data.put("sub", s);
		data.put("check", c);
		
		response.getWriter().println(gson.toJson(data));
		
		response.getWriter().flush();
	}

}

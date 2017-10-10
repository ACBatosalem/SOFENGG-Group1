package cso.dlsu.aps.action.organization;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.aps.action.ActionHandler;
import cso.dlsu.aps.bean.Organization;
import cso.dlsu.aps.service.OrganizationService;

public class AddOrganizationAction implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Random r = new Random();
		String password = "";
		Organization org = new Organization();
		
		if(!OrganizationService.findOrgByNameOrUserName(request.getParameter("name"), 
				request.getParameter("username"))) {
			org.setName(request.getParameter("name"));
			org.setUserName(request.getParameter("username"));
			
			for(int i = 0; i < 6; i++) {
				password += (char) (97 + r.nextInt(26));
			}
			org.setPassword(password);
	
			response.setContentType("text/html;charset=UTF-8");
			if(OrganizationService.addOrg(org)) {
				response.getWriter().write("added");
			} else {
				response.getWriter().write("Organization was not added");
			}
		} else response.getWriter().write("Name or Username is already taken");
	}

}

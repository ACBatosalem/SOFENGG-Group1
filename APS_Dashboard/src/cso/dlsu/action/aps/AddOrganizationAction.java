package cso.dlsu.action.aps;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.action.ActionHandler;
import cso.dlsu.bean.Organization;
import cso.dlsu.service.OrganizationService;

public class AddOrganizationAction implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		if(!OrganizationService.findOrgByNameOrUserName(request.getParameter("name"), 
				request.getParameter("username").toUpperCase())) {
			Random randomizer = new Random();
			String password = "";
			
			for(int i = 0; i < 6; i++) {
				password += (char) (97 + randomizer.nextInt(26));
			}
			
			Organization org = new Organization();
			
			org.setName(request.getParameter("name"));
			org.setUserName(request.getParameter("username").toUpperCase());
			org.setPassword(password);
	
			response.setContentType("text/html;charset=UTF-8");
			if(OrganizationService.addOrg(org))
				response.getWriter().write("added");
			else response.getWriter().write("Organization was not added");	
		} else response.getWriter().write("Name or username is already taken");
	}
}

package cso.dlsu.action.aps;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cso.dlsu.action.ActionHandler;
import cso.dlsu.bean.Organization;
import cso.dlsu.service.OrganizationService;

public class AddOrganizationAction implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		if(OrganizationService.getOrgByUsername(request.getParameter("username").toUpperCase()) == null) {
			Random randomizer = new Random();
			String password = "";
			
			for(int i = 0; i < 6; i++) {
				password += (char) (97 + randomizer.nextInt(26));
			}
			
			Organization org = new Organization();
			
			//org.setName(request.getParameter("name").toUpperCase());
			org.setUserName(request.getParameter("username").toUpperCase());
			org.setPassword(password);
	

			if(OrganizationService.addOrg(org)) {
				Gson gson = new Gson();
				org = OrganizationService.getOrgByUsername(org.getUserName());
				String element = gson.toJson(org);
				
				response.setContentType("application/json");
				response.getWriter().print(element);
				response.getWriter().flush();
			} else response.getWriter().write("Organization was not added");	
		} else response.getWriter().write("Username is already taken");
	}
}

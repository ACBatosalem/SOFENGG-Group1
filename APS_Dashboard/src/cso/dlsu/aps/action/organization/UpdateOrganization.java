package cso.dlsu.aps.action.organization;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.aps.action.ActionHandler;
import cso.dlsu.aps.bean.Organization;
import cso.dlsu.aps.service.OrganizationService;

public class UpdateOrganization implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Organization org = new Organization();
		
		org.setName(request.getParameter("name"));
		org.setUserName(request.getParameter("username"));
		
		response.setContentType("text/html;charset=UTF-8");
		if(!OrganizationService.findOrgByNameOrUserName(request.getParameter("name"), 
														request.getParameter("username"))){
			if(OrganizationService.updateOrg(id, org)) {
				response.getWriter().write("updated");
			} else {
				response.getWriter().write("Organization was not updated");
			}
		} else response.getWriter().write("Name or Username is already taken");
	}

}

package cso.dlsu.action.aps;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.action.ActionHandler;
import cso.dlsu.bean.Organization;
import cso.dlsu.service.OrganizationService;

public class GetAllOrganizationsAction implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Organization> orgs = OrganizationService.getAllOrgs();
		request.setAttribute("orgs", orgs);
		request.getRequestDispatcher("organizations.jsp").forward(request, response);
	}

}

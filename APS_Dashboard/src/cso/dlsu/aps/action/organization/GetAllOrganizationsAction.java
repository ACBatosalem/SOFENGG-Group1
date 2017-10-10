package cso.dlsu.aps.action.organization;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.aps.action.ActionHandler;
import cso.dlsu.aps.bean.Organization;
import cso.dlsu.aps.service.OrganizationService;

public class GetAllOrganizationsAction implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Organization> orgs = OrganizationService.getAllOrgs();
		request.setAttribute("orgs", orgs);
		request.getRequestDispatcher("organization.jsp").forward(request, response);
	}

}

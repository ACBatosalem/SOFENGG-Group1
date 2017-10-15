package cso.dlsu.action.aps;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.action.ActionHandler;
import cso.dlsu.bean.Organization;
import cso.dlsu.service.OrganizationService;

public class GetOrganizationAction implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Organization org = OrganizationService.getOrg(id);
		request.setAttribute("org", org);
		
		request.getRequestDispatcher("updateOrg.jsp").forward(request, response);
	}

}

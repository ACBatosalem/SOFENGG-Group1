package cso.dlsu.aps.action.organization;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.aps.action.ActionHandler;
import cso.dlsu.aps.service.OrganizationService;

public class DeleteOrganizationAction implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		boolean success = OrganizationService.deleteOrg(id);
		response.getWriter().write(String.valueOf(success));
	}

}

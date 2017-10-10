package cso.dlsu.aps.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.aps.service.OrganizationService;

public class CheckUsernameAction implements ActionHandler  {
		
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("user");
		response.setContentType("text/plain");
		response.getWriter().write(String.valueOf(OrganizationService.findOrgByNameOrUserName("", username)));
	}
}

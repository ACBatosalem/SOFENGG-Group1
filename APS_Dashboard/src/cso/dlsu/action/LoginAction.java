package cso.dlsu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cso.dlsu.bean.Organization;
import cso.dlsu.service.OrganizationService;

public class LoginAction implements ActionHandler {

	// TODO Comments and documentations
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("user").toUpperCase();
		String password = request.getParameter("password");
		
		Organization user = OrganizationService.getOrgByUsername(username);
		response.setContentType("text/html;charset=UTF-8");
		
		if (user == null)
			response.getWriter().write("This user is invalid or unauthorized!");
		else if (!user.getPassword().equals(password))
			response.getWriter().write("The password is incorrect.");
		else if (user.getUserName().equals("APS")) {
			response.getWriter().write("aps");
			HttpSession session = request.getSession();
			session.setAttribute("sessionID", user.getId());
			session.setAttribute("user", user);
			session.setAttribute("orgs", OrganizationService.getAllOrgs());
		} else {
			response.getWriter().write("org");
			HttpSession session = request.getSession();
			session.setAttribute("sessionID", user.getId());
			session.setAttribute("user", user);
		}
	}
}

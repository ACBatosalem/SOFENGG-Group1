package cso.dlsu.aps.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cso.dlsu.aps.bean.Organization;
import cso.dlsu.aps.service.OrganizationService;

public class LoginAction implements ActionHandler {

	// TODO Comments and documentations
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		session.setAttribute("sessionun", username);
		
		Organization user = OrganizationService.getOrgByUsername(username);
		response.setContentType("text/html;charset=UTF-8");
		
		if (user == null)
			response.getWriter().write("This user is invalid or unauthorized!");
		else if (!user.getPassword().equals(password))
			response.getWriter().write("The password is incorrect.");
		else if (user.getUserName().equals("CSO"))
			request.getRequestDispatcher("home_aps.jsp").forward(request, response);
		else
			request.getRequestDispatcher("home_org.jsp").forward(request, response);			
	}
}

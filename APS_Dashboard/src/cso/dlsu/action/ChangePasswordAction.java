package cso.dlsu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cso.dlsu.bean.Organization;
import cso.dlsu.service.OrganizationService;

public class ChangePasswordAction implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ((Integer)request.getSession().getAttribute("sessionID") != null) {
			request.getRequestDispatcher("change_password.jsp").forward(request, response);
		} else {
			response.sendRedirect("/APS_Dashboard/home");
		}
	}
}

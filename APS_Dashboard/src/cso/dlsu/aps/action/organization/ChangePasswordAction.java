package cso.dlsu.aps.action.organization;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.aps.action.ActionHandler;
import cso.dlsu.aps.bean.Organization;
import cso.dlsu.aps.service.OrganizationService;

public class ChangePasswordAction implements ActionHandler {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ((String) request.getSession().getAttribute("sessionun") != null) {
		//	if (((String) request.getSession().getAttribute("sessionun")).equals("CSO")) {
				request.getRequestDispatcher("change_password.jsp").forward(request, response);
			//} else {
				//request.getRequestDispatcher("change_password.jsp").forward(request, response);
		//	}
		} else {
			response.sendRedirect("/APS_Dashboard/home");
		}
		
	}
}

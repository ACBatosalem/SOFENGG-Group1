package cso.dlsu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.bean.Organization;
import cso.dlsu.service.OrganizationService;

public class UpdatePasswordAction implements ActionHandler{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = (int) request.getSession().getAttribute("sessionID");
		String oldPW = request.getParameter("old_pw");
		String newPW = request.getParameter("new_pw");
		
		Organization org = OrganizationService.getOrg(id);
		
		if (org.getPassword().equals(oldPW)) {
			OrganizationService.updatePassword(id, newPW);
			response.getWriter().write("Password successfully changed!");
		}
		
		else response.getWriter().write("Input for current password is incorrect!");
	}

}

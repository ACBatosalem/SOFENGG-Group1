package cso.dlsu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.bean.Organization;

public class HomeAction implements ActionHandler {

	//TODO Make comments and documentation
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user") == null)
			request.getRequestDispatcher("loginreg.jsp").forward(request, response);
		else if (((Organization)(request.getSession().getAttribute("user"))).getUserName().equals("APS"))
			response.sendRedirect("homeAPS");
		else response.sendRedirect("homeORG");
	}

}

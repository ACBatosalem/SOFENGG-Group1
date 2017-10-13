package cso.dlsu.aps.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeAction implements ActionHandler {

	//TODO Make comments and documentation
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((String) request.getSession().getAttribute("sessionun") == null)
			request.getRequestDispatcher("loginreg.jsp").forward(request, response);
		else if (((String) request.getSession().getAttribute("sessionun")).equals("CSO"))
			response.sendRedirect("homeAPS");
		else response.sendRedirect("homeORG");
	}

}

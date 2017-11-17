package cso.dlsu.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.service.DashboardService;

public class FilteredSubmissionAction implements ActionHandler{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String filter = request.getParameter("filter");
		if(filter.equals("tt"))
			request.getSession().setAttribute("dashboard_data", DashboardService.getAllDashboardData("none"));
		else if (filter.equals("ft"))
			request.getSession().setAttribute("dashboard_data", DashboardService.getAllDashboardData("non-acads"));
		else if (filter.equals("tf"))
			request.getSession().setAttribute("dashboard_data", DashboardService.getAllDashboardData("acads"));
		
		request.getRequestDispatcher("homeAPS/home_aps.jsp").forward(request, response);
		
	}
	
}

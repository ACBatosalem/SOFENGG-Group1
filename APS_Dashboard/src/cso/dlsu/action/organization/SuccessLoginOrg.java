package cso.dlsu.action.organization;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.action.ActionHandler;
import cso.dlsu.bean.Organization;
import cso.dlsu.service.DashboardService;

public class SuccessLoginOrg implements ActionHandler{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getSession().setAttribute("dashboard_data", DashboardService.getAllDashboardData());
		request.getSession().setAttribute("dashboard_data", 
				DashboardService.getOrgDashboardData(((Organization)request.getSession().getAttribute("user")).getUserName()));
		request.getRequestDispatcher("homeORG/home_org.jsp").forward(request, response);
	}
}

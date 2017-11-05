package cso.dlsu.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.action.*;
import cso.dlsu.action.aps.*;
import cso.dlsu.action.organization.SuccessLoginOrg;

/**
 * Servlet implementation class Controller
 * This controller serves as the main controller for the whole system.
 * It accepts all url patterns and has a hashmap of ActionHandlers 
 * with a key of a String.
 */
@WebServlet(urlPatterns={	"/logout",
							"/login",
							"/home",
							"/homeAPS/getAllOrgs",
							"/homeAPS/addOrg",
							"/homeAPS/deleteOrg",
							"/homeAPS/getOrg",
							"/homeAPS/getStatistics",
							"/homeORG/getStatistics",
							"/homeAPS/changePassword",
							"/homeORG/changePassword",
							"/homeAPS/updatePassword",
							"/homeORG/updatePassword",
							"/homeAPS",
							"/homeORG"})
public class APSController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap <String, ActionHandler> actions;
    
    public APSController() {
        super();
        actions = new HashMap <String, ActionHandler> ();

        actions.put("/home", new HomeAction());
        actions.put("/login", new LoginAction());
        actions.put("/logout", new LogoutAction());

        actions.put("/homeAPS", new SuccessLoginAPS());
        actions.put("/homeAPS/getAllOrgs", new GetAllOrganizationsAction());
        actions.put("/homeAPS/addOrg", new AddOrganizationAction());
        actions.put("/homeAPS/deleteOrg", new DeleteOrganizationAction());
        actions.put("/homeAPS/getOrg", new GetOrganizationAction());
        actions.put("/homeAPS/changePassword", new ChangePasswordAction());
        actions.put("/homeAPS/updatePassword", new UpdatePasswordAction());
        actions.put("/homeAPS/getStatistics", new GetStatisticsAction());
				
        actions.put("/homeORG", new SuccessLoginOrg());
        actions.put("/homeORG/updatePassword", new UpdatePasswordAction());
        actions.put("/homeORG/changePassword", new ChangePasswordAction());
		actions.put("/homeORG/getStatistics", new GetStatisticsAction());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actions.get(request.getServletPath()).execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actions.get(request.getServletPath()).execute(request, response);
	}
}

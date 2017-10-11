package cso.dlsu.aps.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.aps.action.ActionHandler;
import cso.dlsu.aps.action.CheckUsernameAction;
import cso.dlsu.aps.action.HomeAction;
import cso.dlsu.aps.action.LoginAction;
import cso.dlsu.aps.action.LogoutAction;
import cso.dlsu.aps.action.organization.AddOrganizationAction;
import cso.dlsu.aps.action.organization.DeleteOrganizationAction;
import cso.dlsu.aps.action.organization.GetAllOrganizationsAction;
import cso.dlsu.aps.action.organization.GetOrganizationAction;
import cso.dlsu.aps.action.organization.UpdateOrganization;

/**
 * Servlet implementation class Controller
 * This controller serves as the main controller for the whole system.
 * It accepts all url patterns and has a hashmap of ActionHandlers 
 * with a key of a String.
 */
@WebServlet(urlPatterns={	"/logout",
							"/login",
							"/home",
							"/getAllOrgs",
							"/addOrg",
							"/deleteOrg",
							"/getOrg",
							"/updateOrg",
							"/checkUsername"})
public class APSController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap <String, ActionHandler> actions;
    
    public APSController() {
        super();
        actions = new HashMap <String, ActionHandler> ();
        
        actions.put("/logout", new LogoutAction());
        actions.put("/login", new LoginAction());
        actions.put("/home", new HomeAction());
        actions.put("/getAllOrgs", new GetAllOrganizationsAction());
        actions.put("/addOrg", new AddOrganizationAction());
        actions.put("/deleteOrg", new DeleteOrganizationAction());
        actions.put("/getOrg", new GetOrganizationAction());
        actions.put("/updateOrg", new UpdateOrganization());
        actions.put("/checkUsername", new CheckUsernameAction());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actions.get(request.getServletPath()).execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actions.get(request.getServletPath()).execute(request, response);
	}
}

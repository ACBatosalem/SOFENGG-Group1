package cso.dlsu.aps.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.aps.action.*;
import cso.dlsu.aps.action.organization.*;

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
							"/homeAPS/changePassword",
							"/homeORG/changePassword",
							"/homeAPS",
							"/homeORG"})
public class APSController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap <String, ActionHandler> actions;
    
    public APSController() {
        super();
        actions = new HashMap <String, ActionHandler> ();
        
        actions.put("/logout", new LogoutAction());
        actions.put("/login", new LoginAction());
        actions.put("/home", new HomeAction());
        actions.put("/homeAPS/getAllOrgs", new GetAllOrganizationsAction());
        actions.put("/homeAPS/addOrg", new AddOrganizationAction());
        actions.put("/homeAPS/deleteOrg", new DeleteOrganizationAction());
        actions.put("/homeAPS/getOrg", new GetOrganizationAction());
        actions.put("/homeAPS/changePassword", new ChangePasswordAction());
        actions.put("/homeORG/changePassword", new ChangePasswordAction());
        actions.put("/homeAPS", new SuccessLoginAPS());
        actions.put("/homeORG", new SuccessLoginOrg());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actions.get(request.getServletPath()).execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actions.get(request.getServletPath()).execute(request, response);
	}
}

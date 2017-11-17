package cso.dlsu.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cso.dlsu.action.AccountSettingsAction;
import cso.dlsu.action.ActionHandler;
import cso.dlsu.action.FilteredSubmissionAction;
import cso.dlsu.action.GetModalDataAction;
import cso.dlsu.action.GetStatisticsAction;
import cso.dlsu.action.HomeAction;
import cso.dlsu.action.LoginAction;
import cso.dlsu.action.LogoutAction;
import cso.dlsu.action.UpdatePasswordAction;
import cso.dlsu.action.aps.AddOrganizationAction;
import cso.dlsu.action.aps.DeleteOrganizationAction;
import cso.dlsu.action.aps.GetAllOrganizationsAction;
import cso.dlsu.action.aps.GetOrganizationAction;
import cso.dlsu.action.aps.SuccessLoginAPS;
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
							"/getModalData",
							"/homeAPS/getAllOrgs",
							"/homeAPS/addOrg",
							"/homeAPS/deleteOrg",
							"/homeAPS/getOrg",
							"/homeAPS/getStatistics",
							"/homeORG/getStatistics",
							"/homeAPS/accountSettings",
							"/homeORG/accountSettings",
							"/homeAPS/updatePassword",
							"/homeORG/updatePassword",
							"/homeAPS/filtered",
							"/homeORG/filtered",
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
        actions.put("/getModalData", new GetModalDataAction());

        actions.put("/homeAPS", new SuccessLoginAPS());
        actions.put("/homeAPS/getAllOrgs", new GetAllOrganizationsAction());
        actions.put("/homeAPS/addOrg", new AddOrganizationAction());
        actions.put("/homeAPS/deleteOrg", new DeleteOrganizationAction());
        actions.put("/homeAPS/getOrg", new GetOrganizationAction());
        actions.put("/homeAPS/accountSettings", new AccountSettingsAction());
        actions.put("/homeAPS/updatePassword", new UpdatePasswordAction());
        actions.put("/homeAPS/getStatistics", new GetStatisticsAction());
		actions.put("/homeAPS/filtered", new FilteredSubmissionAction());
		
        actions.put("/homeORG", new SuccessLoginOrg());
        actions.put("/homeORG/updatePassword", new UpdatePasswordAction());
        actions.put("/homeORG/accountSettings", new AccountSettingsAction());
		actions.put("/homeORG/getStatistics", new GetStatisticsAction());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actions.get(request.getServletPath()).execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actions.get(request.getServletPath()).execute(request, response);
	}
}

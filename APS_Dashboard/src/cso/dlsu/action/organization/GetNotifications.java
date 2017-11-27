package cso.dlsu.action.organization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cso.dlsu.action.ActionHandler;
import cso.dlsu.bean.Notification;
import cso.dlsu.service.NotificationService;

public class GetNotifications implements ActionHandler{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Notification> data = new ArrayList<>();
		
		if(request.getAttribute("user") != null) {
			String username = request.getAttribute("user").toString();
			data =  NotificationService.getNotifications(username);
		} 
		
		Gson gson = new Gson();
		response.setContentType("application/json");
		response.getWriter().write(gson.toJson(data));
		response.getWriter().flush();
	}

}

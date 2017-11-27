package cso.dlsu.service;

import java.util.ArrayList;
import java.util.List;

import cso.dlsu.bean.Notification;

public class NotificationService {
	private static List <Notification> notifications = new ArrayList<>();
	
	public static void notify (Notification n) {
		notifications.add(n);
	}
	
	public static void read (Notification n) {
		n.setRead(false);
	}
	
	public static List <Notification> getNotifications (String username) {
		List <Notification> notifs = new ArrayList <Notification> ();
		for (int i = 0; i < notifications.size(); i++) {
			if(notifications.get(i).getUserName().equals(username)) {
				notifs.add(notifications.get(i));
			}
		}
		
		return notifs;
	}
}

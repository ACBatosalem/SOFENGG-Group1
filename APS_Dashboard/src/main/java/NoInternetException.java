package main.java;
import java.io.IOException;
import java.time.LocalTime;

import com.google.api.services.sheets.v4.Sheets;

public class NoInternetException extends Exception {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void printStackTrace () {
		System.out.println("[ " + getClass().getName() + " | " + LocalTime.now() + " ] No Internet! Reconnecting...");
	}
	
	public void reconnect () throws IOException {
		Sheets sheetsService = null;
		
		do {
			sheetsService = GSheetsConnection.getSheetsService();
		} while (sheetsService == null);
	}
}

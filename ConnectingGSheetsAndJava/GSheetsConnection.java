import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

public class GSheetsConnection {
	private static Sheets sheetsService;
	
	private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), ".credentials/sheets.googleapis.com-java-quickstart");
	private static FileDataStoreFactory DATA_STORE_FACTORY;
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static HttpTransport HTTP_TRANSPORT;
	private static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS_READONLY);
	static {
	    try {
	        HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
	        DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
	    } catch (Throwable t) {
	        t.printStackTrace();
	        System.exit(1);
	    }
	}
	
    public static Credential authorize() throws IOException {
        InputStream in =
            GSheetsConnection.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        System.out.println("[ " + GSheetsConnection.class.getName() + " | " + LocalTime.now() + " ] " +
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }
    
    public static Sheets getSheetsService() throws IOException {
        Credential credential = authorize();
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
    
    public static synchronized void start (String spreadsheetId, String range, List <LocalTime> times) 
    		throws NoInternetException, UnknownHostException {
    	
    	boolean stop = false;
    	
    	try {
    		// TODO Once implemented use toData (response)
    		generateFile("table.csv", getDataInString(spreadsheetId, range));
		} catch (UnknownHostException u) {
			throw new NoInternetException();
		} catch (IOException e) {
			System.out.println("[ " + GSheetsConnection.class.getName() + " | " + LocalTime.now() + " ] Credentials file not found!");
		}
    	
    	while (!stop) {
    		for (int i = 0; i < times.size(); i++) {
    			if(times.get(i).equals(LocalTime.now()) || times.get(i).isBefore(LocalTime.now())) {
    	    		try {
        	    		// TODO Once implemented use toData (response)
        	    		generateFile("table.csv", getDataInString(spreadsheetId, range));
        				times.remove(i);
        	    		i--;
    	    		} catch (UnknownHostException u) {
    	    			throw new NoInternetException();
    	    		} catch (IOException io) {
    	    			System.out.println("[ " + GSheetsConnection.class.getName() + " | " + LocalTime.now() + " ] Credentials file not found!");
    	    		}
    			}
    		}
    	}	
    }
    
    public static synchronized List <Object> toData (ValueRange response) {
    	// TODO make the response and get the value, make it an object/objects
    	// TODO if data is incomplete, throw incomplete data exception and keep the data
    	
    	return null;
    }
    
    // 
    public static synchronized String getDataInString (String spreadsheetID, String range) throws IOException {
    	String data = "";

		ValueRange response = getSheetsService().spreadsheets().values().get(spreadsheetID, range).execute();
    	List<List<Object>> values = response.getValues();
    	
    	System.out.println("[ " + GSheetsConnection.class.getName() + " | " + LocalTime.now() + " ] Retrieving Data ...");
    	if(values == null || values.size() == 0) {
    		System.out.println("[ " + GSheetsConnection.class.getName() + " | " + LocalTime.now() + " ] No Data Found!");
    	} else {
            for (int i = 0; i < values.size(); i++) {
            	List<Object> row = values.get(i);
				for (int j = 0; j < row.size(); j++) {
				  	if(j == row.size()-1)
				  		data += row.get(j);
				  	else 
				  		data += row.get(j) + ", ";
				}
				
				data += "\n";
			}
    	}
    	
    	return data;
    }
    
    public static final boolean generateFile (String fileName, String message) {
		File file = new File ("gen/ " + fileName);
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(message);
			writer.close();
			return true;
		} catch (IOException io) {
			io.printStackTrace();
			return false;
		}
	}
    
    // Run test cases here
    public static void main(String[] args) throws IOException {

        String spreadsheetId = "1cJ0I-NG-4i-u68YlQmjpOtG1uFfzaKEA-P8EYn7Exs4";
        String range = "Sheet1";
        
        List <LocalTime> times = new ArrayList <LocalTime> ();
        
        times.add(LocalTime.of(8, 0));
        times.add(LocalTime.of(12, 0));
        times.add(LocalTime.of(16, 0));
        
        try {
			start(spreadsheetId, range, times);
		} catch (NoInternetException e) {
			e.reconnect();
		}
       
    }
}
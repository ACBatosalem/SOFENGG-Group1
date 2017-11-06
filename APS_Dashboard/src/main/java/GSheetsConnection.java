package main.java;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import com.google.api.services.sheets.v4.Sheets.Builder;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import cso.dlsu.service.CheckingDetailsService;
import cso.dlsu.service.DocumentService;
import cso.dlsu.service.SubmissionDetailsService;

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
        InputStream in =  GSheetsConnection.class.getClassLoader().getResourceAsStream("client_secret.json");
        
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = null;
        
        try {
        	credential = new AuthorizationCodeInstalledApp(
                    flow, new LocalServerReceiver()).authorize("jonal_ticug@dlsu.edu.ph");
        } catch (Exception e) {
        	e.printStackTrace();
        }
        	
        System.out.println("[ " + GSheetsConnection.class.getName() + " | " + LocalTime.now() + " ] " +
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }
    
    public static Sheets getSheetsService() throws IOException {
        Credential credential = authorize();
        Builder bld = null;
        try {
            bld = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential);
            bld = bld.setApplicationName(APPLICATION_NAME);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        if(bld != null)
        	return bld.build();
        return null;
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
    	
    	processData();
    	
    	/*while (!stop) {
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
    	    			io.printStackTrace();
    	    			System.out.println("[ " + GSheetsConnection.class.getName() + " | " + LocalTime.now() + " ] Credentials file not found!");
    	    		}
    			}
    		}
    	}	*/
    }
    
    private static void processData() {
		// TODO Auto-generated method stub
    	Path pathToFile = Paths.get("C:/dlsu-cso/table.csv");
    	System.out.println(pathToFile);
    	try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split("<>");
                //TODO check if db is empty
                for(int i = 0; i < attributes.length; i++)
                	System.out.println(i+" "+ attributes[i]);
                if (DocumentService.getAllDocuments().size() == 0)
                	createDocument(attributes);
                //else
                	

                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

	}

	private static void createDocument(String[] attributes) {
		// TODO Auto-generated method stub
		if(DocumentService.getDocumentByTitle(attributes[5]) == null
				 || attributes[19].equals("In Case of Change")
				 || attributes[19].equals("Activity Not in GOSM")) {
			//create document
		} else {
			createSubmission(attributes);
		}
		
	}

	private static void createSubmission(String[] attributes) {
		// TODO Auto-generated method stub
		int submissionID = SubmissionDetailsService.getSubmissionIDByDateSubmitted(attributes[0]);
		if(submissionID == 0){
			//create submission details
			
		} else {
			createCheckingDetails(attributes, submissionID);
		}
	
	}
	private static void createCheckingDetails(String[] attributes, int submissionID) {
		// TODO Auto-generated method stub
		if(!CheckingDetailsService.findSubmissionByID(submissionID) 
				&& !attributes[20].equals("")) {
			//create checking details
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
    	sheetsService = getSheetsService();
		ValueRange response = sheetsService.spreadsheets().values().get(spreadsheetID, range).execute();
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
				  		data += row.get(j) + "<> ";
				}
				
				data += "\n";
			}
    	}
    	
    	System.out.println(data);
    	return data;
    }
    
    public static final boolean generateFile (String fileName, String message) {
    	File file = new File ("C:/dlsu-cso/" + fileName);

		
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

        String spreadsheetId = "1zKv-LbZydzzL8YoZIHrDUZQYn12_ctCeqFzmuxFt8uM";
        String range = "Sheet1";
        
        List <LocalTime> times = new ArrayList <LocalTime> ();
        
        times.add(LocalTime.of(8, 0));
        times.add(LocalTime.of(12, 0));
        times.add(LocalTime.of(15, 0));
        times.add(LocalTime.of(16, 0));
        
        try {
			start(spreadsheetId, range, times);
		} catch (NoInternetException e) {
			e.reconnect();
		}
       
    }
}

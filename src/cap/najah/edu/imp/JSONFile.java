package cap.najah.edu.imp;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;

import cap.najah.edu.logger.Logger;

public class JSONFile implements ImportFiles {

	static Logger log;
	private final static String PATHCSV="C:\\DataBaseManager\\json\\json.csv";
	private final static String PATH ="C:\\DataBaseManager\\json";
	  public static String readFileAsString(String file)throws Exception
	    {
	        return new String(Files.readAllBytes(Paths.get(file)));
	    }
	  
	public void JSONFile(Connection database) {
	
	  Scanner keyboard = new Scanner (System.in);
	  System.out.println("Enter the path of JSON file : ");
	  String files = keyboard.nextLine();
    	 String jsonString = null;
		try {
			jsonString = readFileAsString(files);
			
		} catch (Exception e1) {
			System.out.println("wrong JSON file path .");
			log.logError("wrong JSON file path : "+e1.getMessage());
		}
		
	   String jsonArrayString = "{\"fileName\":"+jsonString+"}";

   JSONObject output;
   try {
      output = new JSONObject(jsonArrayString);
      JSONArray docs = output.getJSONArray("fileName");
      File file = new File(PATHCSV);
      String csv = CDL.toString(docs);
      FileUtils.writeStringToFile(file, csv);
      CSVLoader csvconvert = new CSVLoader();
     	csvconvert.CSVLoader(database);
		boolean truncateBeforeLoad = true;
		   System.out.println("Enter table name ");
		   String NameTable = keyboard.nextLine();
		   
		try {
			log.logError("Name"+NameTable);
			csvconvert.loadCSV(PATHCSV,NameTable,truncateBeforeLoad);

		} catch (Exception e) {
			log.logError("csv convert"+csvconvert);

		}
    
   }
   catch(Exception e) {
	   log.logError("unexpected error while write JSON on CSV file "+e.getMessage());
       }
	}
	public void DeleteCSV()
	{
		try  
		{  
		 File directoryPath = new File(PATH);
		      //List of all files and directories
		      File filesList[] = directoryPath.listFiles();
		      String Path;
		      for(File file : filesList) {
		    	  file.delete();
		      }
		 
		}
		catch(Exception e)  
		{  System.out.println("can't found file to remove");
		  log.logError("can't found file to remove"+e.getMessage());
		}

	}
	
	@Override
		public void importsFile(Connection database) {
		    DeleteCSV();
	     	JSONFile(database);
	    
		}
}
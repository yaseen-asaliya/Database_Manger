package cap.najah.edu.imp;

import java.io.*;
import java.sql.*;
import java.util.Date;
import java.util.*;

import org.apache.commons.lang3.StringUtils;

import au.com.bytecode.opencsv.CSVReader;
import cap.najah.edu.creatpro.User;
import cap.najah.edu.infotable.TableInformation;
import cap.najah.edu.logger.Logger;

public class SQLFile extends TableInformation implements ImportFiles {
	Logger log;
    public void SQLFile(Connection connection)
    {
    	System.out.println("Enter the SQL File Path :");
		 Scanner keyboard = new Scanner (System.in);
		 String file = keyboard.nextLine();
    	 String Sql=null;    	
    	try  {
    		BufferedReader bufferedreader = new BufferedReader(new FileReader(file));
    	   
    	    while ((Sql = bufferedreader.readLine()) != null) {
    	    	PreparedStatement ps = connection.prepareStatement(Sql);
    	    	ps.execute(Sql);

    	    }
    	}catch (SQLException e) {
			
    		System.out.println("Unexpected error while execute SQL query");
    		log.logError("Unexpected error while execute SQL query"+e.getMessage());
			
		} catch (FileNotFoundException e) {
			System.out.println("can't found SQL file");
    		log.logError("can't found SQL file"+e.getMessage());
		} catch (IOException e) {
    		log.logError(e.getMessage());
		}
    	
    	
    }
	
	
	@Override
	public void importsFile(Connection database) {

		SQLFile(database);
	}

}

package VersionControl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import cap.najah.edu.infotable.TableInformation;
import cap.najah.edu.logger.Logger;

public class ExportDataBase extends TableInformation implements SnapShot {
	 Logger log;
	 BufferedWriter fileWriter;
	 String NameTable;
	 Connection connection=null;
	 private static String Folder="SnapShot";
	 
	 private static final String PATH ="C:\\Users\\baraa\\Desktop\\DataBaseManager\\"+Folder;
	 private static final int GET_TABLE=3;

	public void ExportDataBase(Connection connection) {
	 try {  
		   this.connection=connection;
		     log.logInfo("Read Schema to ExportDataBase ");

		   DatabaseMetaData metaData = connection.getMetaData();
		   ResultSet resultSet = metaData.getTables(getNameSchema(), null, null, null);
		   while (resultSet.next()) { 
		    	 Export(resultSet.getString(GET_TABLE));
		 }
	   }  
	  
	   catch (Exception e) {  
		   log.logError("can't read database schema"+e.getMessage());
		   System.out.println("can't read database schema");
	   }  
	}

	public void Export(String table)
	{  

		      File file = new File(PATH);
		      boolean bool = file.mkdir();
		 
			  String csvFilePath = file+"Reviews-export.csv";

			try {
				fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
				log.logInfo("fileWriter"+fileWriter);
				
			} catch (IOException e1) {
				log.logError(e1.getMessage());
			}		
		
       String csvFileName = getFileName(table);
        
       try  {
           String sql = "SELECT * FROM "+getNameSchema()+".".concat(table);

           Statement statement = connection.createStatement();
            
           ResultSet result = statement.executeQuery(sql);
            
           fileWriter = new BufferedWriter(new FileWriter(Folder+"\\"+csvFileName));
           log.logInfo("fileWriter csvFileName "+csvFilePath+"\\"+Folder);

            
           int columnCount = writeHeaderLine(result);
            
           while (result.next()) {
               String line = "";
                
               for (int i = 1; i <= columnCount; i++) {
                   Object valueObject = result.getObject(i);
                   String valueString = "";
                    
                   if (valueObject != null) valueString = valueObject.toString();
                    
                   if (valueObject instanceof String) {
                       valueString = "\"" + escapeDoubleQuotes(valueString) + "\"";
                   }
                    
                   line = line.concat(valueString);
                    
                   if (i != columnCount) {
                       line = line.concat(",");
                   }
               }
                
               fileWriter.newLine();
               fileWriter.write(line);            
           }
            
           statement.close();
           fileWriter.close();
            
       } catch (SQLException e) {
           System.out.println("Database error:");
              log.logError("Database Error "+e.getMessage());
           
       } catch (IOException e) {
           System.out.println("File IO error:");
           log.logError("File IO error "+e.getMessage());
       }
        
   }
  
	public void DeleteCSV()
	{
		try  
		{  
		 File directoryPath = new File(PATH);
		      File filesList[] = directoryPath.listFiles();
		      for(File file : filesList) {
		    	  file.delete();
		      }
		 
		}
		catch(Exception e)  
		{  
			System.out.println("Unexpected error while delete CSV file");
     		log.logError("Unexpected error while delete CSV file"+e.getMessage());
		}

	}
 	  
	   
   private String getFileName(String baseName) {
	   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH");    
	   Calendar cal = Calendar.getInstance();
        String dateTimeInfo = dateFormat.format(cal.getTime());
        log.logWarning(" dateTimeInfo "+String.format(dateTimeInfo));
       return baseName.concat(String.format("_%s.csv",dateTimeInfo));
   }
    
   private int writeHeaderLine(ResultSet result) throws SQLException, IOException {
       // write header line containing column names
       ResultSetMetaData metaData = result.getMetaData();
       int numberOfColumns = metaData.getColumnCount();
       String headerLine = "";
        
       // exclude the first column which is the ID field
       for (int i = 1; i <= numberOfColumns; i++) {
           String columnName = metaData.getColumnName(i);
           headerLine = headerLine.concat(columnName).concat(",");
       }
        
       fileWriter.write(headerLine.substring(0, headerLine.length() - 1));
        
       return numberOfColumns;
   }
    
   private String escapeDoubleQuotes(String value) {
       return value.replaceAll("\"", "\"\"");
   }

   


   
@Override
public void Snap(Connection database) {
	DeleteCSV();
	ExportDataBase(database);
}
}

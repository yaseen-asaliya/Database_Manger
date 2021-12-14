package cap.najah.edu.export;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.Calendar;
import java.util.Scanner;

import cap.najah.edu.infotable.TableInformation;
import cap.najah.edu.logger.Logger;

public class ExportFile extends TableInformation{
	 BufferedWriter fileWriter;
	 Logger log;
	public void ExportFile(Connection connection)
	{  
		Scanner scanner= new Scanner(System.in);
		System.out.println("Enter The Name of  the Table to Export it : ");
	    String tableName =scanner.nextLine(); 
		  String csvFilePath = "csv/Reviews-export.csv";
		 
			try {
				fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
				
			} catch (IOException e1) {
				log.logError(e1.getMessage());
				System.out.println(e1.getMessage());
				
			}		
		
       String csvFileName = getFileName(tableName.concat("_Export"));
        
       try  {
           String sql = "SELECT * FROM "+getNameSchema()+".".concat(tableName);

           Statement statement = connection.createStatement();
            
           ResultSet result = statement.executeQuery(sql);
            
           fileWriter = new BufferedWriter(new FileWriter(csvFileName));
            
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
           System.out.println("Datababse error:");
           log.logError("Datababse error : "+e.getMessage());
           
       } catch (IOException e) {
           System.out.println("File IO error:");
           log.logError("File IO error : "+e.getMessage());
           
       }
        
   }

   private String getFileName(String baseName) {
	   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");    
	   Calendar calendar = Calendar.getInstance();
        String dateTimeInfo = dateFormat.format(calendar.getTime());
       return baseName.concat(String.format("_%s.csv", dateTimeInfo));
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
    
 
}

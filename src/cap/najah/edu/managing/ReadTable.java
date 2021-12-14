package cap.najah.edu.managing;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.*;
import java.util.Scanner;

import cap.najah.edu.infotable.TableInformation;
import cap.najah.edu.logger.Logger;


public class ReadTable extends TableInformation implements ManagingDataBase {
	Logger logger;
	public void ReadTable(Connection connection)
	{
		 try {  
			 int countColumn=0;
			 logger.logInfo("Read Table ");
			    DatabaseMetaData metadata = connection.getMetaData();
			    ResultSet resultSet = metadata.getColumns(null, getNameSchema(), getNameTable(),null);
			    while (resultSet.next()) {
			      String name = resultSet.getString("COLUMN_NAME");
			      System.out.print(name +"\t");
			      countColumn++;
			    }
	          
			   String sql = "SELECT * FROM  " +  getNameSchema() + "."+getNameTable()+"";

	            Statement statement = connection.createStatement();
	            ResultSet resultset = statement.executeQuery(sql);
	            while (resultset.next())
	            {	            
	            	System.out.println("\n");
	            	for(int i=1;i<countColumn;i++)
	            	System.out.print(resultset.getString(i)+"\t");
	            }
            	System.out.println("\n");

		   }  
		  
		   catch (Exception e) {  
			  logger.logError("Unexpected error while reading data from table"+e.getMessage());
			  System.out.println("Unexpected error while reading data from table");
		   }  
		 
	}
	
	
	public void Table(Connection connection) {
		Scanner scanner= new Scanner(System.in);
		System.out.println("Enter The Name of Table : ");
	    String NameTable =scanner.nextLine(); 
	    setNameTable(NameTable);
	
		ReadTable(connection);		   

	}
	
	

}

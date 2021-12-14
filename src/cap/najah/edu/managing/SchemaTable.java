package cap.najah.edu.managing;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import cap.najah.edu.infotable.TableInformation;
import cap.najah.edu.logger.Logger;

public class SchemaTable extends TableInformation implements ManagingDataBase {

	Logger logger;
	public void SchemaTable(Connection connection)
	{	
		Scanner scanner= new Scanner(System.in);
		System.out.println("Enter the Name of Table  : ");
	    String NameTable =scanner.nextLine(); 
		 try {  

			    DatabaseMetaData metadata = connection.getMetaData();
			    ResultSet resultSet = metadata.getColumns(null, getNameSchema(), NameTable,null);
			    while (resultSet.next()) {
			      String name = resultSet.getString("COLUMN_NAME");
			      System.out.print(name +"\t");
			    }
            	System.out.println("\n");

		   }  
		  
		   catch (Exception e) {  
			   System.out.println("invalid table name");
			   logger.logError("invalid table name"+e.getMessage());
			   
		   }  
	}
	
	@Override
	public void Table(Connection connection) {
		SchemaTable(connection);
	}

}

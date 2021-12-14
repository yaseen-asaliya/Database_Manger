package cap.najah.edu.managing;

import java.sql.Connection;
import java.util.Scanner;
import java.sql.*;

import cap.najah.edu.infotable.TableInformation;
import cap.najah.edu.logger.Logger;

public class DeleteTable extends TableInformation implements ManagingDataBase {
	
	Logger logger;
	public void DeleteTable(Connection connection)
	{	
		
		Scanner scanner= new Scanner(System.in);
	       System.out.println("Enter the Name of Table to Drop : ");
           String NameTable =scanner.nextLine(); 
           
           try{
  	           Statement stmt = connection.createStatement();
		         logger.logInfo("Drop the table");

			         String sql = "DROP TABLE "+  getNameSchema() + "."+  NameTable;			         
			         stmt.executeUpdate(sql);
			         System.out.println("Table deleted in given database...");   	  
			      } catch (SQLException e) {
				         logger.logError("Unexpected error while drop table "+e.getMessage());
				         System.out.println("Unexpected error while drop table "+e.getMessage());

			      } 
		
	}
	
	
	public void Table(Connection connection) {
		
		DeleteTable(connection);
	}

	
}

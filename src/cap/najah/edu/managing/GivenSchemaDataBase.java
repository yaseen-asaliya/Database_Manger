package cap.najah.edu.managing;

import java.sql.*;

import cap.najah.edu.logger.Logger;


public class GivenSchemaDataBase implements ManagingDataBase{
	Logger logger;
	public void Schema(Connection connection)
	{	
		
		 try {  
			   DatabaseMetaData metaData = connection.getMetaData();
			      ResultSet resultSet = connection.getMetaData().getCatalogs();
			      while (resultSet.next()) {
			        System.out.println("Schema Name - " + resultSet.getString("TABLE_CAT"));
			      }
			      logger.logInfo("Get All Schema in Database");
		   }  
		   catch (Exception e) {  
			   logger.logError("Unexpected error while returning schemas names from database "+e.getMessage());
                 System.out.println("Unexpected error while returning schemas names from database ");
		   }  
	}
	
	@Override
	public void Table(Connection connection) {
		
		Schema(connection);		   
		
	}
}

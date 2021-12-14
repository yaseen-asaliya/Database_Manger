package cap.najah.edu.managing;
import java.util.Scanner;

import cap.najah.edu.infotable.TableInformation;
import cap.najah.edu.logger.Logger;

import java.sql.*;

public class ReadSchema extends TableInformation implements ManagingDataBase {

	Logger logger;
	 private static final int GET_TABLE=3;
	public void ReadSchema(Connection connection)
	{	 
		
		 try {  
			   logger.logInfo("Read Schema DataBase ");
			   DatabaseMetaData metaData = connection.getMetaData();
			   ResultSet resultSet = metaData.getTables(getNameSchema(), null, null, null);
			     while (resultSet.next()) {
			   System.out.println(resultSet.getString(GET_TABLE));
		       	 }

		   }  
		  
		   catch (Exception e) { 
			   System.out.print("Invalid Schema DataBase ");
			   logger.logError("Invalid Schema DataBase "+e.getMessage());
		   }  
		   
	}

	

	@Override
	public void Table(Connection connection) {
		Scanner scanner= new Scanner(System.in);	
		System.out.println("Enter The Name  of Schema : ");  
		String NameSchema= scanner.nextLine();  
	    setNameSchema(NameSchema);
		ReadSchema(connection);	

	}
}

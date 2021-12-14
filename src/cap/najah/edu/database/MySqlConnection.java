package cap.najah.edu.database;
import java.sql.*;  
import java.util.Properties;

import cap.najah.edu.create.CreateConnections;
import cap.najah.edu.exception.ConnectionDataBase;
import cap.najah.edu.exception.ExceptionDataBase;
import cap.najah.edu.logger.Logger;

 
public class MySqlConnection  {
	
	private static MySqlConnection MySql = null;

	Logger logger;
	private String LocalDataBaseURL = "jdbc:mysql://localhost:3306/abed";
	private String user = "root";
	private String password = "1111";

	Connection connectionDataBase = null;
	public Connection getConnection()
	{

		if(MySql==null)
		{
			MySql =  new MySqlConnection();

		try { 
			connectionDataBase = DriverManager.getConnection(LocalDataBaseURL, user, password);
			logger.logInfo("Create Connection to DataBase");
		}
		catch (SQLException e) {
			logger.logError(e.getMessage());
		   }
		
		}

		return connectionDataBase;
	}


}



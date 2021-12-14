package cap.najah.edu.create;

import java.sql.*;
import java.util.Properties;

import cap.najah.edu.logger.Logger;

public class CreateConnections {
	 Logger logger;
	 private static CreateConnections MySql = null;
	 private String user;
	 private String password;
	 private String type;
	 private String locahost;
	 private String portNumber;
	 private String email;
	 
    Connection connections = null;
    
	public  Connection getInstance()
	{
	
		 this.user=InfoDataBase.getUserName();
		 this.password=InfoDataBase.getPassword();
		 this.type=InfoDataBase.getType();
		 this.portNumber=InfoDataBase.getPortNumber();
		 this.locahost=InfoDataBase.getLocahost();
		 this.email=InfoDataBase.getEmail();
	    

		if(MySql==null)
		{
			MySql =  new CreateConnections();

			try {
			    Logger.logInfo("Connection To Database");
			    Properties connectionProps = new Properties();
			    connectionProps.put("user", this.user);
			    connectionProps.put("password", password);
			    if (type.equals("mysql")) {
			    	connections = DriverManager.getConnection(
			                   "jdbc:" + this.type + "://" +
			                   this.locahost +
			                   ":" + this.portNumber + "/",
			                   connectionProps);
				    System.out.println("Connection To Database");

			    }
			    
			}   catch (SQLException e) {
			    Logger.logError("Can't connect to database "+e.getMessage());
                 System.out.println("Can't connect to database"+e.getMessage());
			}

			}
	
		return connections;
		
     }

}

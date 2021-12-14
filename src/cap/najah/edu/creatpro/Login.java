package cap.najah.edu.creatpro;

import java.sql.*;
import java.util.Scanner;

import cap.najah.edu.exception.ExceptionDataBase;
import cap.najah.edu.exception.LoginException;
import cap.najah.edu.logger.Logger;

public class Login {
   	 static Logger log;
	 private static final int COLUMN_EMAIL = 1;
	 private static final int COLUMN_PASSWORD = 2;
	 private static final int COLUMN_PROFILE_TYPE = 5;
	 private static final int FAILED_LOGIN = 4;


	public static int loginApplication(Connection connection) 
	{
		

		Scanner keyboard = new Scanner (System.in);
		System.out.println("Enter your Email : ");
	    String inputEmail = keyboard.nextLine();
		System.out.println("Enter your Password : ");
	    String inputPassword = keyboard.nextLine();
	    
      try {
    	  log.logInfo("Login to Application");
    	  Statement statement=connection.createStatement();  
    	  ResultSet resultSet=statement.executeQuery("select * from test");  

    	  while(resultSet.next())
    	  {
    		  if(resultSet.getString(COLUMN_EMAIL).equals(inputEmail)&&resultSet.getString(COLUMN_PASSWORD).equals(inputPassword)) {
        	  return resultSet.getInt(COLUMN_PROFILE_TYPE);

    		  }
    	  }
    	  return FAILED_LOGIN;
		
	    } 
         catch (Exception e) {
    	  log.logInfo(e.getMessage());

	    }
            
		return FAILED_LOGIN;

	}
	
}

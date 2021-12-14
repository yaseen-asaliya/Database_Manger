package cap.najah.edu.creatpro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import cap.najah.edu.database.MySqlConnection;
import cap.najah.edu.logger.Logger;

public class SignUp {

	static Logger logger;
	private static final int EMAIL_KEY = 1;
    private static final int PASSWORD_KEY = 2;
    private static final int FIRST_NAME_KEY = 3;
    private static final int LAST_NAME_KEY = 4;
    private static final int TYPE_PROFILE_WARNING = 5;
  
	public static void signup() {
		
		  Scanner keyboard = new Scanner (System.in);
		  System.out.println("Enter FirstName : ");
		  String firstName = keyboard.nextLine();
		  System.out.println("Enter LastName : ");
		  String lastName = keyboard.nextLine();
		  System.out.println("Enter Email : ");
		  String email = keyboard.nextLine();
		  System.out.println("Enter Password : ");
		  String password = keyboard.nextLine();
		  System.out.println("Enter Number Type of Profile( Admin 1 Staff 2 Reader 3 : ) ");
		  int Type = keyboard.nextInt();


		User member = new User(firstName, password, email, lastName,Type);
		
	}
	public static void insert(Connection conmysql) 
	{		
		signup();
		try {
			
	    String sql = "insert into test (Email,Password,FirstName,LastName,Profiletype) VALUES (?, ?,? ,?,?)";
	    
		PreparedStatement ps  = conmysql.prepareStatement(sql);
		ps.setString(EMAIL_KEY, User.getEmail());
		ps.setString(PASSWORD_KEY, User.getPassword());
		ps.setString(FIRST_NAME_KEY, User.getFirstName());
		ps.setString(LAST_NAME_KEY, User.getLastName());
		ps.setInt(TYPE_PROFILE_WARNING, User.getType());

		int row = ps.executeUpdate();
	      logger.logInfo("Create Profile");

		
		} catch (SQLException e) {
			e.printStackTrace();
			logger.logError("Can't create Profile : "+e.getMessage());
		}
		
		
	
	}
	
}

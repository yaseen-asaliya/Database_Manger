package cap.najah.edu.create;

import java.sql.*;

public class SaveInformationDataBase extends InfoDataBase {

	
public void insertDataBase(Connection connection) {		
	

	
	String sql = "insert into ts (Email,Localhost,Port,User,Password,Name_DataBase) VALUES (?,?,?,?,?,?)";
	  
	try {
		
		// insert the data
	
	PreparedStatement ps  = connection.prepareStatement(sql);
	ps.setString(1, getEmail());
	ps.setString(2,getLocahost());
	ps.setString(3,getPortNumber());
	ps.setString(4,getPortNumber());
	ps.setString(5, getPassword());
	ps.setString(6,getType());
	ps.executeUpdate();
	
	
	} catch (SQLException e) {
		System.out.print(false);
		e.printStackTrace();
	}
}
}

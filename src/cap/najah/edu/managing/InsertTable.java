package cap.najah.edu.managing;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import cap.najah.edu.creatpro.User;
import cap.najah.edu.infotable.TableInformation;
import cap.najah.edu.logger.Logger;

public class InsertTable extends TableInformation implements ManagingDataBase {
	
	Scanner scanner= new Scanner(System.in);
	private  final 
	String SQL_INSERT = "INSERT INTO "+getNameSchema()+".${table}(${keys}) VALUES(${values})";
     private static final String TABLE_REGEX = "\\$\\{table\\}";
    private static final String KEYS_REGEX = "\\$\\{keys\\}";
    private static final String VALUES_REGEX = "\\$\\{values\\}";

	public void InsertTable(Connection connection) {
		
	
	}
	

	@Override
	public void Table(Connection connection) {
		
		InsertTable(connection);
	}

}

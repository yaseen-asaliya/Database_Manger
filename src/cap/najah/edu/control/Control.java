package cap.najah.edu.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import VersionControl.FactorySnapShot;
import VersionControl.SnapShot;
import cap.najah.edu.create.CreateConnections;
import cap.najah.edu.create.InfoDataBase;
import cap.najah.edu.creatpro.Login;
import cap.najah.edu.creatpro.SignUp;
import cap.najah.edu.database.MySqlConnection;
import cap.najah.edu.export.ExportFile;
import cap.najah.edu.imp.CSVLoader;
import cap.najah.edu.imp.FactroyImportFile;
import cap.najah.edu.imp.ImportFiles;
import cap.najah.edu.logger.Logger;
import cap.najah.edu.managing.ManagerFactory;
import cap.najah.edu.managing.ManagingDataBase;

public class Control {
	static Logger log; 
	public static int Login(Connection connection)
	{
		log.logInfo("Login in Profile");
		  int typeprofile = Login.loginApplication(connection);
			   return typeprofile;
	}
	
	public static void  SignUp(Connection Database) {
		log.logInfo("Create Profile");
	    SignUp.insert(Database);
	}
    
	public static void TypeUser(int type)
	{
	
		switch(type)
		{
		case 1:
			System.out.println("Give All Schema of DataBase '1'");
			System.out.println("Give All Table of Schema '2'");
			System.out.println("Give All Data from Table '3'");
			System.out.println("Give Schema from Table '4'");
			System.out.println("Insert Data to Table '5'");
			System.out.println("Drop Table of DataBase '6'");
			System.out.println("Export File Table '7'");
			System.out.println("Import File to DataBase '8'");
			System.out.println("Take A SnapShoe of DataBase '9'");
			System.out.println("Exit '10'");
			Admin();
			break;
			   
        
		case 2:
			System.out.println("Give All Schema of DataBase '1'");
			System.out.println("Give All Table of Schema '2'");
			System.out.println("Give All Data from Table '3'");
			System.out.println("Give Schema from Table '4'");
			System.out.println("Insert Data to Table '5'");
			System.out.println("Exit '6'");
			System.out.println("Enter The choice :");
			Staff();
			break;

  
		case 3:
			System.out.println("Give All Schema of DataBase '1'");
			System.out.println("Give All Table of Schema '2'");
			System.out.println("Give All Data from Table '3'");
			System.out.println("Give Schema from Table '4'");
			System.out.println("Exit '5'");
			System.out.println("Enter The choice :");
			Reader();
			break;
		 default:

		}
	}
	
	public static void importFile(Connection DataBase)
	{
		FactroyImportFile  Factoryimportfile = new FactroyImportFile();	 
		Scanner keyboard = new Scanner (System.in);
		int input=0;
		System.out.println("Import File CSV to DataBase '1'");
		System.out.println("Import File JSON to DataBase '2'");
		System.out.println("Import File SQL to DataBase '3'");
		log.logInfo("Import File");
		input = keyboard.nextInt();
		switch(input)
		{
		  case 1:
			   ImportFiles CSV = Factoryimportfile.getTypeFile("CSV");
			   CSV.importsFile(DataBase);
			 break;
		  case 2:
		        ImportFiles JSON = Factoryimportfile.getTypeFile("JSON");
		        JSON.importsFile(DataBase);
		    break;
		  case 3:
			    ImportFiles SQL = Factoryimportfile.getTypeFile("SQL");
			    SQL.importsFile(DataBase);
		   break;
			 default:

		}
		
	}
	
	public static void Admin() {
	
		 ManagerFactory  managerFactory = new ManagerFactory();
		 Connection DataBase=InserInfoDataBase();
		 System.out.println("Enter The choice :");
		 Scanner keyboard = new Scanner (System.in);
		 boolean  exit =true;
		 do {
			 System.out.println("Enter The choice :");
			 int choice = keyboard.nextInt();
			 
		 switch(choice) {
		 case 1:	
			 ManagingDataBase Schema = managerFactory.getTable("Schema");
			 Schema.Table(DataBase);
			 break;
		 case 2:	
			 ManagingDataBase ReadSchema = managerFactory.getTable("ReadSchema");
			 ReadSchema.Table(DataBase);
			 break;
		 case 3:	
			 ManagingDataBase ReadTable = managerFactory.getTable("ReadTable");
			 ReadTable.Table(DataBase);
			 break;
		 case 4:	
			 ManagingDataBase SchemaTable = managerFactory.getTable("SchemaTable");
			 SchemaTable.Table(DataBase);
			 break;
		 case 5:	
			 ManagingDataBase InsertTable = managerFactory.getTable("InsertTable");
			 InsertTable.Table(DataBase);
			 break;
		 case 6:	
			 ManagingDataBase DropTable = managerFactory.getTable("DropTable");
			 DropTable.Table(DataBase);
			 break;
		 case 7:	
			  ExportFile exporter = new ExportFile();
		      exporter.ExportFile(DataBase);
			 break;	
		 case 8:	
			 importFile(DataBase);
			 break;	
		 case 9:
			 SnapShot(DataBase);
			 break;
		 case 10:
			 exit=false;
			 break;	 
		 default:
			 
		 }}while(exit);
	}
	
	
	
	private static void SnapShot(Connection dataBase) {
		FactorySnapShot  Factory = new FactorySnapShot();
        System.out.println("Take SnapShot Now  '1'");
        System.out.println("Import SnapShot Now '2'");
        Scanner keyboard = new Scanner (System.in);
		int input = keyboard.nextInt();

        switch(input) {
        
         case 2:
        	  SnapShot Csv = Factory.GetSnapShot("CSV");
			  Csv.Snap(dataBase);
        	 break;
         case 1:
			  SnapShot Export = Factory.GetSnapShot("EXPORT");
			  Export.Snap(dataBase); 
        	 break;
		 default:

        }


	}

	private static void Staff() {
		
		 ManagerFactory  managerFactory = new ManagerFactory();
		 Connection DataBase=InserInfoDataBase(); 
		 Scanner keyboard = new Scanner (System.in);
		 System.out.println("Enter The choice :");			
		 boolean  exit =true;
		 do {
			 System.out.println("Enter The choice :");
			 int choice = keyboard.nextInt();
		 switch(choice) {
		 case 1:	
			 ManagingDataBase Schema = managerFactory.getTable("Schema");
			 Schema.Table(DataBase);
			 break;
		 case 2:	
			 ManagingDataBase ReadSchema = managerFactory.getTable("ReadSchema");
			 ReadSchema.Table(DataBase);
			 break;
		 case 3:	
			 ManagingDataBase ReadTable = managerFactory.getTable("ReadTable");
			 ReadTable.Table(DataBase);
			 break;
		 case 4:	
			 ManagingDataBase SchemaTable = managerFactory.getTable("SchemaTable");
			 SchemaTable.Table(DataBase);
			 break;
		 case 5:	
			 ManagingDataBase InsertTable = managerFactory.getTable("InsertTable");
			 InsertTable.Table(DataBase);
			 break;
		 case 6:
			 exit=false;
		 default:

		 }}while(exit);
		
	}

	
	private static void Reader() {
		
		 ManagerFactory  managerFactory = new ManagerFactory();
		 Connection DataBase=InserInfoDataBase();
		 Scanner keyboard = new Scanner (System.in);
		 System.out.println("Enter The choice :");			
		 boolean  exit =true;
		 do {
			 System.out.println("Enter The choice :");
			 int choice = keyboard.nextInt();
		 switch(choice) {
		 
		 case 1:	
			 ManagingDataBase Schema = managerFactory.getTable("Schema");
			 Schema.Table(DataBase);
			 break;
		 case 2:	
			 ManagingDataBase ReadSchema = managerFactory.getTable("ReadSchema");
			 ReadSchema.Table(DataBase);
			 break;
		 case 3:	
			 ManagingDataBase ReadTable = managerFactory.getTable("ReadTable");
			 ReadTable.Table(DataBase);
			 break;
		 case 4:	
			 ManagingDataBase SchemaTable = managerFactory.getTable("SchemaTable");
			 SchemaTable.Table(DataBase);
			 break;
			 
			 default:

		 }}while(exit);
	}
	
	
	public static Connection InserInfoDataBase() {
	   	 String userName="root";
		 	  String pass="1111";
			  String dbms="mysql";
			  String serverName="localhost";
			  String portNumber="3306";
			  String email="email@email.com";
			  
			  InfoDataBase x 
			= new InfoDataBase(userName,
					pass,
					dbms, 
					serverName,
					 portNumber,email);
			  
			   CreateConnections mysql =new CreateConnections();
			   Connection Any= mysql.getInstance();
			return Any;
			 			
	}
	
	
	
	
    public static void main(String args[]) {		   
		   
		   Logger.logInfo("Welcome Application");
		   boolean bool = true;
		   MySqlConnection Conmysql =new MySqlConnection();
		   Connection mysql= Conmysql.getConnection();
		        
		   
		   
		  int type = 0;
		  do {
			  
			System.out.println("Sign Up : 1");
			System.out.println("Sign In : 2");
			Scanner keyboard = new Scanner (System.in);
			String input = keyboard.nextLine();
			
		  switch(input) {
		    case "1":
		    	SignUp(mysql);
		    	break;
		    case "2":
		       type = Login(mysql);
		       System.out.println(type);
		       TypeUser(type);
		       break;
			 default:
				 }
		  }
		  while(bool);
		  
			  
	   }
}

package cap.najah.edu.managing;

public class ManagerFactory {
 public ManagingDataBase getTable(String shapeType){
		 
		 switch(shapeType) {
		 case  "Schema":
	         return new GivenSchemaDataBase();
		 case "ReadSchema":
			 return new ReadSchema();
		 case "ReadTable":
			 return new ReadTable(); 
		 case "DropTable":
			 return new DeleteTable();
		 case "InsertTable":
			 return new InsertTable();
		 case "SchemaTable":
			 return new SchemaTable();
			  
          default:
		 }
	     
	      return null;
	 }
 }

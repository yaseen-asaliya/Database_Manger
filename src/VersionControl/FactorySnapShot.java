package VersionControl;


public class FactorySnapShot {

	 public SnapShot GetSnapShot(String typeFile){
		  
		 switch(typeFile) {
		 case "CSV":
			 return new CSV();
		 case "EXPORT":
			 return new ExportDataBase();
		 
		 }
		 
		 
		return null;
	 
		 
	 }
}

package cap.najah.edu.imp;


public class FactroyImportFile {
	 public ImportFiles getTypeFile(String typeFile){
		  
		 switch(typeFile) {
		 case "CSV":
			 return new CSVLoader();
		 case "JSON":
			 return new JSONFile();
		 case "SQL":
			 return new SQLFile();
		 
		 }
		 
		 
		return null;
	 
		 
	 }

}

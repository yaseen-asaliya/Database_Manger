package cap.najah.edu.create;

public class InfoDataBase {
	 private static String userName;
	 private static String pass;
	 private static String type;
	 private static String locahost;
	 private static String portNumber;
	 private static String email;
	 
	 public InfoDataBase()
	 {
		 super();
	 }
	 public InfoDataBase(String username,String password,String type,String locahost, String port,String email) {
		 setEmail(email);
		 setUserName(username);
		 setPassword(password);
		 setLocahost(locahost);
		 setType(type);
		 setPortNumber(port);
		 
	 }
	 
	 
	 
	public static String getUserName() {
		return userName;
	}
	public static void setUserName(String userName) {
		InfoDataBase.userName = userName;
	}
	public static String getPassword() {
		return pass;
	}
	public static void setPassword(String pass) {
		InfoDataBase.pass = pass;
	}
	public static String getLocahost() {
		return locahost;
	}
	public static void setLocahost(String locahost) {
		InfoDataBase.locahost = locahost;
	}
	public static String getType() {
		return type;
	}
	public static void setType(String type) {
		InfoDataBase.type = type;
	}
	public static String getPortNumber() {
		return portNumber;
	}
	public static void setPortNumber(String portNumber) {
		InfoDataBase.portNumber = portNumber;
	}
	public static String getEmail() {
		return email;
	}
	public static void setEmail(String email) {
		InfoDataBase.email = email;
	}

}

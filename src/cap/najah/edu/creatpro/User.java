package cap.najah.edu.creatpro;

public class User {
private static String firstName;
private static String password;
private static String email;
private static String lastName;
private static int type;


	
	public User() {
		super();
	}

	public User(String firstName, String password, String email, String lastName,int type) {
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
		setType(type);
	}

	public static String getFirstName() {
		return firstName;
	}

	public static void setFirstName(String firstName) {
		User.firstName = firstName;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		User.password = password;
	}

	public static String getEmail() {
		return email;
	}

	public static void setEmail(String email) {
		User.email = email;
	}

	public static String getLastName() {
		return lastName;
	}

	public static void setLastName(String lastName) {
		User.lastName = lastName;
	}

	public static int getType() {
		return type;
	}

	public static void setType(int type) {
		User.type = type;
	}




}

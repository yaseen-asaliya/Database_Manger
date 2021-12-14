package cap.najah.edu.exception;

public class LoginException extends ExceptionDataBase{

	
	public LoginException(String message) {
		super(message);
	}

	
	@Override
	public String getMessage() {
		String message = super.getMessage();
		return "System can't reach to the database " + message;
		
	}
}



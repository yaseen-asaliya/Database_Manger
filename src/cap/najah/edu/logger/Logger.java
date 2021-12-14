package cap.najah.edu.logger;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class Logger {

	private static final Logger instance = new Logger();

	public  String logname = "Logger";
	protected String env = System.getProperty("user.dir");
	private static File logFile;

	public static Logger getInstance(){
		return instance;
	}

	public static Logger getInstance(String withName){
		instance.logname = withName;
		instance.createLogFile();
		return instance;
	}

	public void createLogFile(){
		File logsFolder = new File(env + '/' + "logs");
		if(!logsFolder.exists()){
			System.err.println("INFO: Creating new logs directory in " + env);
			logsFolder.mkdir();
			
		}

		logname =  logname + ".log";
		Logger.logFile = new File(logsFolder.getName(),logname);
		try{
			if(logFile.createNewFile()){
				System.err.println("INFO: Creating new log file");	
			}
		}catch(IOException e){
			System.err.println("ERROR: Cannot create log file");
			System.exit(1);
		}
	}

	private Logger(){
		if (instance != null){
			throw new IllegalStateException("Cannot instantiate a new singleton instance of log");
		}
		this.createLogFile();
	}

		public static void logInfo(String message){
			try{
				FileWriter out = new FileWriter(Logger.logFile, true);
				out.write(java.time.LocalDateTime.now()  + " [INFO] " + message+"\n");
				out.close();
			}catch(IOException e){
				System.err.println("ERROR: Could not write to INFO file");
			}
		}

		public static void logDebug(String message){
			try{
				FileWriter out = new FileWriter(Logger.logFile, true);
				out.write(java.time.LocalDateTime.now()  + " [Debug] " + message+"\n");
				out.close();
			}catch(IOException e){
				System.err.println("ERROR: Could not write to Debug file");
			}
		}

		public static void logWarning(String message){
			try{
				FileWriter out = new FileWriter(Logger.logFile, true);
				out.write(java.time.LocalDateTime.now()  + " [Warn] " + message+"\n");
				out.close();
			}catch(IOException e){
				System.err.println("ERROR: Could not write to Warn file");
			}
		}

		public static void logError(String message){
			try{
				FileWriter out = new FileWriter(Logger.logFile, true);
				out.write(java.time.LocalDateTime.now()  + " [Error] "+ message+"\n");
				out.close();
			}catch(IOException e){
				System.err.println("ERROR: Could not write to Error file");
			}
		}

	}

	



package cap.najah.edu.imp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
import org.apache.commons.lang3.time.DateUtils;

import org.apache.commons.lang3.StringUtils;

import au.com.bytecode.opencsv.CSVReader;
import cap.najah.edu.infotable.TableInformation;
import cap.najah.edu.logger.Logger;

public class CSVLoader extends TableInformation implements ImportFiles {

	Logger log;
	private  final 
	String SQL_INSERT = "INSERT INTO "+getNameSchema()+".${table}(${keys}) VALUES(${values})";
	private static final String TABLE_REGEX = "\\$\\{table\\}";
	private static final String KEYS_REGEX = "\\$\\{keys\\}";
	private static final String VALUES_REGEX = "\\$\\{values\\}";

	private Connection connection;
	private char seprator;


	public CSVLoader() {
		
	}
	public void CSVLoader(Connection connection) {
		this.connection = connection;
		//Set default separator
		this.seprator = ',';
	}
	
	
	public void loadCSV(String csvFile, String tableName,
			boolean truncateBeforeLoad) throws Exception {

		CSVReader csvReader = null;
		if(null == this.connection) {
			throw new Exception("Not a valid connection.");
		    }
		try
		{
			csvReader = new CSVReader(new FileReader(csvFile), this.seprator);

		} catch (Exception e) {
            System.out.println("Unexpected error while reading csv file");
			log.logError("Unexpected error while reading csv file"+e.getMessage());
			
		}
		
		String[] headerRow = csvReader.readNext();
		log.logInfo("headerRow"+headerRow.length);

		if (null == headerRow) {
			throw new FileNotFoundException(
					"No columns defined in given CSV file." +
					"Please check the CSV file format.");
		}

		String questionmarks = StringUtils.repeat("?,", headerRow.length);
		questionmarks = (String) questionmarks.subSequence(0, questionmarks
				.length() - 1);

		String query = SQL_INSERT.replaceFirst(TABLE_REGEX, tableName);
		System.out.print(query);
		query = query
				.replaceFirst(KEYS_REGEX, StringUtils.join(headerRow, ","));
		query = query.replaceFirst(VALUES_REGEX, questionmarks);


		String[] nextLine;
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		try {
			connection = this.connection;
			connection.setAutoCommit(false);
			preparedstatement = connection.prepareStatement(query);

			final int batchSize = 1000;
			int count = 0;
			Date date = null;
			while ((nextLine = csvReader.readNext()) != null) {

				if (null != nextLine) {
					int index = 1;
					for (String string : nextLine) {
						 date = new Date();
						 preparedstatement.setString(index++, string);
						}
					}
				preparedstatement.addBatch();
				}
				if (++count % batchSize == 0) {
					preparedstatement.executeBatch();
				}
			
			preparedstatement.executeBatch();  // insert remaining records
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
			throw new Exception(
					"Error occured while loading data from file to database."
							+ e.getMessage());
		} finally {
			if (null != preparedstatement)
				preparedstatement.close();
			if (null != connection)
				connection.close();

			csvReader.close();
		}
	}

	public char getSeprator() {
		return seprator;
	}

	public void setSeprator(char seprator) {
		this.seprator = seprator;
	}


	@Override
	public void importsFile(Connection database) {
		CSVLoader(database);
		boolean truncateBeforeLoad = true;
		 System.out.println("Enter CSV File Path :");
		   Scanner keyboard = new Scanner (System.in);
		   String Path = keyboard.nextLine();
		   System.out.println("Enter the name of table : ");
		   String NameTable = keyboard.nextLine();

		   
		try {
			loadCSV(Path,NameTable,truncateBeforeLoad);
		} catch (Exception e) {
			System.out.println("unexpected error while loading data from csv file");
			log.logError("unexpected error while loading data from csv file"+e.getMessage());
		}
		
	}
	
}

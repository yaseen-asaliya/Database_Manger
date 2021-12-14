package VersionControl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import au.com.bytecode.opencsv.CSVReader;
import cap.najah.edu.infotable.TableInformation;
import cap.najah.edu.logger.Logger;

public class CSV extends TableInformation implements SnapShot {
	Logger log;
	private  final 
	String SQL_INSERT = "INSERT INTO "+getNameSchema()+".${table}(${keys}) VALUES(${values})";
	private static final String TABLE_REGEX = "\\$\\{table\\}";
	private static final String KEYS_REGEX = "\\$\\{keys\\}";
	private static final String VALUES_REGEX = "\\$\\{values\\}";

	private Connection connection;
	private char seprator;
	private static final String PATH ="C:\\Users\\baraa\\Desktop\\DataBaseManager\\SnapShot";
	private static final int TABLE_NAME=0; 
	public void ReadFile(Connection database)
	{
	      File directoryPath = new File(PATH);
	      File filesList[] = directoryPath.listFiles();
	      String AbsolutePath;
	      
	      for(File file : filesList) {
	         
	         AbsolutePath=file.getAbsolutePath();
	         String TableName =file.getName();
	  
	         String[] parts = TableName.split("_");
	         String getTableName = parts[TABLE_NAME];
	         
	         CSVLoader(database);
	   	 		  boolean truncateBeforeLoad = true;
	   	 		try {
	   	 			loadCSV(AbsolutePath,getTableName,truncateBeforeLoad);
	   	 		} catch (Exception e) {
	   	 		   System.out.println("unexpected error while loading data from csv file");
				    log.logError("unexpected error while loading data from csv file"+e.getMessage());
	   	 		}
	   	        
	   	      }
	        
	      }



	public CSV() {
		
	}
	public void CSVLoader(Connection connection) {
		this.connection = connection;
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

			if(truncateBeforeLoad) {
				//delete data from table before loading csv
				connection.createStatement().execute("DELETE FROM " + tableName);
			}
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
			
				preparedstatement.executeBatch(); // insert remaining records
			connection.commit();
		} 
		catch (Exception e) {
			connection.rollback();
			throw new Exception("Error occured while loading data from file to database."+ e.getMessage());
		} 
		finally {
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
	public void Snap(Connection database) {
	
		ReadFile(database);
		
	}

}

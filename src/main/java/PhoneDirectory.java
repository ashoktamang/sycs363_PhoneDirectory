package main.java;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class PhoneDirectory {
	private String name;
	private String number;

	private String getPath(){
		Properties phoneDirectory = new Properties();
		InputStream input = null;
		
		try {
//			File file = new File("")
			input = new FileInputStream("src/main/java/phone.properties");
			phoneDirectory.load(input);
			System.out.println(phoneDirectory.getProperty("path"));
			return phoneDirectory.getProperty("path");
		} catch(IOException error) {
			error.printStackTrace();
			return "File not found";
		}
	}
	public void addEntry(String name, String number){
		String directoryPath = getPath();
		
		File fileName= new File(directoryPath);
		try{
			FileWriter fileWriter  = new FileWriter(fileName,true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			String new_Line=name;
			new_Line=new_Line.concat(" "+number);
			bufferedWriter.write(new_Line);
			bufferedWriter.newLine();	
			bufferedWriter.close();
			
		}
		catch(IOException ex){
			System.out.println("Unable to open file : "+ex);
		}	
		
		System.out.println(name);
		System.out.println(number);
		String path = getPath();
		System.out.println(path);
	}
	public void deleteEntry(String name) {
	}
	public String getNumber(String name) {
		return null;
	}
	public void changeEntry(String name, String number) {
	}
}

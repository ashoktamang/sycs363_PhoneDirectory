package main.java;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.io.InputStream;
import java.io.IOException;

public class PhoneDirectory {
	private String name;
	private String number;
	
	/**
	 * Returns the path of phone directory file.
	 * The method access the phone.properties file which contains
	 * 'path' as key and 'directory path' as value.
	 * 
	 * @return the path of the phone directory file.
	 */
	private String getPath(){
		Properties phoneDirectory = new Properties();
		InputStream input = null;
		
		try {
			input = new FileInputStream("src/main/resources/phone.properties");
			phoneDirectory.load(input);
			System.out.println(phoneDirectory.getProperty("path"));
			return phoneDirectory.getProperty("path");
		} catch(IOException error) {
			error.printStackTrace();
			return "File not found";
		}
	}
	
	/**
	 * Adds new contacts to the phone directory file.
	 * 
	 * @param name Name of the new contact to be added to the phone directory file
	 * @param number Number of the new contact to added with the new contact's name.
	 * @return 
	 */
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
		
	}
	
	/**
	 * Deletes the name and number of the given contact.
	 * 
	 * @param name Name of the contact to be deleted from the phone directory path
	 */
	public void deleteEntry(String name) {
		String directoryPath = getPath();
		
		File fileName= new File(directoryPath);
		String line=null;
		
		String number=getNumber(name);
		String new_name = name.concat(" "+number);
		List<String> list = new ArrayList<String>();
		/*
		 * A list of strings is declared. The values that are not equal to the name are added to the list.
		 */
		int count=0;
		/*
		 * The variable count is used to see if the name entered is found or not.
		 * If the value of count dosn't change, the user is notified that the number is not found
		 */
		try{
			FileReader fileReader = new FileReader(fileName);			
			BufferedReader bufferedReader = new BufferedReader(fileReader);			
			String line_to_delete=new_name;
			while((line=bufferedReader.readLine())!=null){
				if(line.equals(line_to_delete)){
					count++;
					continue;
				}
				else{
					list.add(line);
				}
			}
			bufferedReader.close();
			if(count==0){
				System.out.println("ERROR! " +name + " is not in the Directory");
				return;
			}
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			/*
			 * Writing all the components of the list to the file.
			 */
			for(int i=0;i<list.size();i++){
				bufferedWriter.write(list.get(i));
				bufferedWriter.newLine();
			}
			
			bufferedWriter.close();
			System.out.println(name + " is deleted.");
			
		}
		catch(FileNotFoundException ex){
			System.out.println("Unable to oepn file");
		}
		catch(IOException ex){
			System.out.println("File not opened.");
		}
		
	}

	/**
	 * Returns the number of the contact given the contact's name
	 * @param name
	 * @return
	 */
	public String getNumber(String name) {
		Scanner x;
		int count=0;
		/*
		 * The variable count is used to see if the name entered is found or not.
		 * If the value of count dosn't change, the user is notified that the number is not found
		 */
		String a="",b="";
		/*
		 * The file is read string by string.
		 * When the name is found the number is entered.
		 */
		try{
			x=new Scanner(new File("file.txt"));			
			while(x.hasNext()){
				a = x.next();
				if(a.equals(name)){
					count++;
					b= x.next();
					break;
				}
			}
			
		}
		catch(Exception e){
			System.out.println("File not found");
		}
		if(count==0){
			System.out.println("ERROR! " + name + " is not in Directory");
			return "NULL";
		}
		else{
			return b;
		}
		
	}

	public void changeEntry(String name, String number) {
		File fileName = new File("file.txt");
		String line=null;		
		String new_name = name.concat(" "+number);
		/*
		 * The number for the corresponding name is stored in number
		 * by calling function getNumber().
		 * The name and number are concatinated to match the pattern as present in the file.
		 */
		int count=0;
		List<String> list = new ArrayList<String>();
		/*
		 * When the matched line is found, the new name and number entered
		 * by the user is stored in place of the line in the Directory.
		 */
		try{
			FileReader fileReader = new FileReader(fileName);			
			BufferedReader bufferedReader = new BufferedReader(fileReader);	
			
			while((line=bufferedReader.readLine())!=null){
				if(line.equals(new_name)){
					count++;
					Scanner in =new Scanner (System.in);
					System.out.println("Enter the new name to be entered : ");
					String new__name;
					new__name=in.nextLine();
					System.out.println("Enter the new number to be entered : ");
					String new_number;
					new_number=in.nextLine();
					new__name=new__name.concat(" "+new_number);
					list.add(new__name);
				}
				else{
					list.add(line);
					
				}
			}
			bufferedReader.close();
			if(count==0){
				System.out.println("ERROR!!! The items not found in Directory");
			}
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			/*
			 * All contents of the list are not written to the file.
			 */
			for(int i=0;i<list.size();i++){
				bufferedWriter.write(list.get(i));
				bufferedWriter.newLine();
			}
			
			bufferedWriter.close();
			
		}
		catch(FileNotFoundException ex){
			System.out.println("Unable to oepn file");
		}
		catch(IOException ex){
			System.out.println("File not opened.");
		}
	}
}

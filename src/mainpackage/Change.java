package mainpackage;

import java.io.*;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Change {
	public static void choose_field() throws FileNotFoundException, IOException {
		Scanner input = new Scanner(System.in);
		int exit = 0;
		int answer;
		//we will loop until user wants to exit the application
		do {//according to user's input i go to the correct method
			System.out.println("Do you want to edit a contact based on the name or Phone number?");
			System.out.println("Give '1', '2' or '0' to return to main menu.");	
			try {
				answer = input.nextInt();
			} catch (Exception e) {
				answer = 0;
			}
			if(answer == 1) 
				nameSearch();				
			else if(answer == 2)
				numberSearch();
							
		}while(answer != exit);
	}
	
	public static void nameSearch() throws IOException, FileNotFoundException{
		Scanner input= new Scanner(System.in);
		String f1,f2;
		System.out.println("Give Name: ");
		f1 = input.nextLine();
		System.out.println("Give Surname: ");
		f2 = input.nextLine();
		File file = new File(System.getProperty("user.dir")+"/src/contacts.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
                String currentLine;
		boolean first = false;
                boolean found = false;
		String[] fields = new String[0];
		List<String> lines = new ArrayList<String>();
                
		while((currentLine = reader.readLine()) != null && !found) {
			if(!first) {
				fields = currentLine.split(",");
				first = true;
			}
			else {// only if both of the user's inputs (name and surname) match a contact then i add this contact's info to an array
				String[] info=currentLine.split(",");
				if(info[0].equals(f1) && info[1].equals(f2)) {
					System.out.println("----There is a contact for the information you gave----");
					for (int i = 0; i < fields.length; i++ ) {
						System.out.println(fields[i] +": "+ info[i]);					
					}
					lines.add(currentLine);
                                        
                                        found = true;
				}
			}
		}
                
                if(!found) {
                    System.out.println("----Name and Surname must be valid----");
                }
                
		System.out.println("-------------------");
		reader.close();
		for(Object str:lines){//for every contact that i found that is a match
			infoCheck(str.toString(), fields);
		} 
		choose_field();
	}
	
	public static void numberSearch() throws IOException, FileNotFoundException{
		Scanner input= new Scanner(System.in);
		int f1 = -1;
		int f2 = -1;
		boolean valid;
		List<String> lines = new ArrayList<String>();
                
		System.out.println("Give Phone number: ");
		do {
			valid = true;
			try {
			    f1 = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {
			    //e.printStackTrace();
				valid = false;
			}
		}while(valid == false);
                
		System.out.println("Give Mobile number: ");
		do {
			valid = true;
			try {
			    f2 = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {
			    //e.printStackTrace();
				valid = false;
			}
		}while(valid == false);
                
		File file = new File(System.getProperty("user.dir")+"/src/contacts.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String currentLine;
		boolean first = false;
                boolean found = false;
		String[] fields = new String[0];
                
		if(f1 == -1 && f2 == -1) {
			System.out.println("-------------------");
			System.out.println("You gave wrong information.");
		}
		else {
			while((currentLine = reader.readLine()) !=null) {
				if(!first) {
					fields = currentLine.split(",");
					first = true;
				}
				else {
					String[] info=currentLine.split(",");
					if(f1 != -1 && f2 != -1) {
						if(info[2].equals(String.valueOf(f1)) && info[3].equals(String.valueOf(f2))) {
							System.out.println("----There is a contact for the information you gave----");
							for (int i = 0; i < fields.length; i++ ) {
								System.out.println(fields[i] +": "+ info[i]);					
							}
							lines.add(currentLine);
                                                        
                                                        found = true;
						}
					}					
				}			
			}
		}
		
                if(!found) {
                    System.out.println("----Phone and Mobile numbers must be valid----");
                }
                
		System.out.println("-------------------");
		reader.close();
		for(Object str:lines){
			infoCheck(str.toString(), fields);
		} 
		choose_field();
	}
        
        public static boolean isValueEmpty(String name, String surname, String email,
            String street, String town, int phone, int mobile, int streetNumber,
            int zipCode) {
        
            return ("".equals(name) || "".equals(surname) || "".equals(email) ||
                    "".equals(street) || "".equals(town) || phone == -1 || mobile == -1 ||
                    streetNumber == -1 || zipCode == -1);
        }
	
	public static void infoCheck(String line, String[] fields) throws IOException, FileNotFoundException{
		//in this method user gives the contact's new info
		Scanner input = new Scanner(System.in);
		String currentLine1;
		String name = "";
		String surname = "";
		String email = "";
		String street = "";
		String town = "";
		int phone = -1;
		int mobile = -1;
		int streetNumber = -1;
		int zipCode = -1;
		String str = "";
		boolean duplicate, valid;
                
		System.out.println("----Edit Information----");
		String[] info = line.split(",");
		System.out.println("Change the information " + fields[0] +": "+ info[0] + ", to:");
		name = input.nextLine();
		System.out.println("Change the information " + fields[1] +": "+ info[1] + ", to:");
		surname = input.nextLine();
                
                do {
			duplicate = false;
			valid = true;
			System.out.println("Change the information " + fields[2] +": "+ info[2] + ", to:");
			try {
				phone = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {
				//e.printStackTrace();
				valid = false;
			}
		}while (duplicate == true || valid == false);
                
		do {
			duplicate = false;
			valid = true;
			System.out.println("Change the information " + fields[3] +": "+ info[3] + ", to:");
			try {
				mobile = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {
				//e.printStackTrace();
				valid = false;
			}
		}while (duplicate == true || valid == false);
                
		do {
			duplicate = false;
			System.out.println("Change the information " + fields[4] +": "+ info[4] + ", to:");
			email = input.nextLine();
		}while (duplicate == true);
                
		System.out.println("Change the information " + fields[5] +": "+ info[5] + ", to:");
		street = input.nextLine();
                
		System.out.println("Change the information " + fields[6] +": "+ info[6] + ", to:");
		try {
			streetNumber = Integer.parseInt(input.nextLine());
		} catch (NumberFormatException e) {
			//e.printStackTrace();
		}
                
		System.out.println("Change the information " + fields[7] +": "+ info[7] + ", to:");
		town = input.nextLine();
                
		System.out.println("Change the information " + fields[8] +": "+ info[8] + ", to:");
		try {
			zipCode = Integer.parseInt(input.nextLine());
		} catch (NumberFormatException e) {
			//e.printStackTrace();
		}
                
		str = name + "," + surname + "," + String.valueOf(phone) + "," +
                        String.valueOf(mobile) + "," + email + "," + street + "," +
                        String.valueOf(streetNumber) + "," + town + "," + String.valueOf(zipCode);
                
		if(isValueEmpty(name, surname, email, street, town, phone, mobile, 
                    streetNumber, zipCode)) {//if any of the variables is not valid or has a value assigned to it
			System.out.println("You gave wrong information, information change wasn't successful.");
		} else {//else i call this method and pass the string of the new info i built			
			contactChange(line, str);
		}			
	}

	public static void contactChange(String line, String str)  throws IOException, FileNotFoundException{
		File file1 = new File(System.getProperty("user.dir")+"/src/contacts.txt");
		BufferedReader reader1 = new BufferedReader(new FileReader(file1));	
		String currentLine1;
		boolean first = false;
		boolean duplicate = false;
		String[] fields = new String[0];
		String[] info1 = new String[0];
		String[] info2 = new String[0];
		info1 = str.split(",");
		File file2 = new File(System.getProperty("user.dir")+"/src/contactstemp.txt");//i create a temporary file to save the changes
		BufferedWriter writer = new BufferedWriter(new FileWriter(file2));
		
                while((currentLine1 = reader1.readLine()) !=null) {
			if(!first) {
				fields = currentLine1.split(",");
				writer.write(currentLine1 + "\n");
				first = true;
			}
			else if(currentLine1.equals(line)){//if the current line in the reader is the one we want to change									
				writer.write(str + "\n");//i write the new info instead of the old
			}
			else{//for the rest of the lines nothing changes
				info2 = currentLine1.split(",");
				if (info2[2].equals(info1[2])) {
					System.out.println("Mobile number must be unique among the contacts.");
					duplicate = true;
				}
				
                                if(info2[3].equals(info1[3])) {
					System.out.println("Phone number must be unique among the contacts.");
					duplicate = true;
				}
				
                                if(info2[4].equals(info1[4])) {
					System.out.println("E-mail must be unique among the contacts.");
					duplicate = true;
				}
				writer.write(currentLine1 + "\n");
			}
		}
		reader1.close();
		writer.close();
		if (duplicate) {//if we found that the new info are not unique the changed wont be saved
			System.out.println("Contact change did not complete.");
			file2.delete();//we delete the temporary file
		}
		else {//if everything is ok
			System.out.println("Contact change is completed.");
			file1.delete();//we delete the original file
			file2.renameTo(file1);//we rename the temporary file to the original file's name
		}			
	}
		

}

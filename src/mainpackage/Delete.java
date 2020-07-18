package mainpackage;

import java.io.*;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Delete {
	public static void choose_field() throws FileNotFoundException, IOException {
		
		int exit = 0;
		int answer;
		//we will loop until user wants to exit the application
		do {//according to user's input i go to the correct method
                        deleteMenu();
			answer = choose();
                        if(answer == 1) 
				name_search();				
			else if(answer == 2)
				number_search();
							
		}while(answer != exit);
	}
	
	public static void name_search() throws IOException, FileNotFoundException{
		Scanner input= new Scanner(System.in);
		String name,surname;
		System.out.println("Give Name: ");
		name = input.nextLine();
		System.out.println("Give Surname: ");
		surname = input.nextLine();
		File file = new File(System.getProperty("user.dir")+"/src/contacts.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String currentLine;
		boolean first = false;
		String[] fields = new String[0];
		List<String> lines = new ArrayList<String>();
		while((currentLine = reader.readLine()) !=null) {
			if(!first) {
				fields = currentLine.split(",");
				first = true;
			}
			else {// only if both of the user's inputs (name and surname) match a contact then i add this contact's info to an array
				String[] info=currentLine.split(",");
				if(info[0].equals(name) && info[1].equals(surname)) {
					rightInformation(fields, info);
					//contact_change(currentLine);
					lines.add(currentLine);
				}
				else if(info[0].equals(name) && !info[1].equals(surname)) {
					halfRightInformation(fields, info, "name");
				}
				else if(!info[0].equals(name) && info[1].equals(surname)) {
					halfRightInformation(fields, info, "surname");
				}
				
			}
		}
		System.out.println("-------------------");
		reader.close();
		for(Object str:lines){//for every contatc that i found that is a match
			contact_delete(str.toString());
		} 
		choose_field();
	}
	
	public static void number_search() throws IOException, FileNotFoundException{
		Scanner input= new Scanner(System.in);
		int phone = -1;
		int mobile = -1;
		boolean valid;
		System.out.println("Give Phone number: ");
		do {
			valid = true;
			try {
			    phone = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {
			    //e.printStackTrace();
				valid = false;
			}
		}while(valid == false);
		System.out.println("Give Mobile number: ");
		do {
			valid = true;
			try {
			    mobile = Integer.parseInt(input.nextLine());
			} catch (NumberFormatException e) {
			    //e.printStackTrace();
				valid = false;
			}
		}while(valid == false);
		File file = new File(System.getProperty("user.dir")+"/src/contacts.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String currentLine;
		boolean first = false;
		String[] fields = new String[0];
		if(phone == -1 && mobile == -1) {
                    wrongInformation();
		}
		else {
			while((currentLine = reader.readLine()) !=null) {
				if(!first) {
					fields = currentLine.split(",");
					first = true;
				}
				else {
					String[] info=currentLine.split(",");
					if(phone == -1 && mobile != -1) {
						if(info[3].equals(String.valueOf(mobile))) {
                                                    halfRightInformation(fields, info, "mobile number");
						}
					}
					else if(phone != -1 && mobile == -1) {
						if(info[2].equals(String.valueOf(phone))) {
                                                    halfRightInformation(fields, info, "phone number");
						}
					}
					else if (phone != -1 && mobile != -1) {
						if(info[2].equals(String.valueOf(phone)) && info[3].equals(String.valueOf(mobile))) {
                                                        rightInformation(fields, info);
							contact_delete(currentLine);
						}
						else if(info[2].equals(String.valueOf(phone)) && !info[3].equals(String.valueOf(mobile))) {
                                                        halfRightInformation(fields, info, "phone number");
						}
						else if(!info[2].equals(String.valueOf(phone)) && info[3].equals(String.valueOf(mobile))) {
                                                    halfRightInformation(fields, info, "mobile number");
						}
					}					
				}			
			}
		}
		
		System.out.println("-------------------");
		reader.close();
		choose_field();
	}
	
	public static void contact_delete(String line)  throws IOException, FileNotFoundException{
		File file1 = new File(System.getProperty("user.dir")+"/src/contacts.txt");
		BufferedReader reader1 = new BufferedReader(new FileReader(file1));	
		String currentLine1;
		boolean first = false;
		String[] fields = new String[0];
		File file2 = new File(System.getProperty("user.dir")+"/src/contactstemp.txt");//i create a temporary file to save the changes
		BufferedWriter writer = new BufferedWriter(new FileWriter(file2));
		while((currentLine1 = reader1.readLine()) !=null) {
			if(!first) {
				fields = currentLine1.split(",");
				writer.write(currentLine1 + "\n");
				first = true;
			}
			else if(!currentLine1.equals(line)){//if the current line in the reader is not the one we want to delete we write it to the temp file	
				writer.write(currentLine1 + "\n");
			}
		}
		reader1.close();
		writer.close();
		file1.delete();//we delete the original file
		file2.renameTo(file1);//we rename the temporary file to the original file's name
		System.out.println("Information was valid, deletion completed successfully");
	}	
	
        public static void deleteMenu(){
            
            System.out.println("Delete contact based on");
            System.out.println("1. Name");	
            System.out.println("2. Phone Number");
            System.out.println("0. Back to menu");
        }
	
        public static int choose(){
            int answer;
            Scanner input = new Scanner(System.in);
            
            System.out.print("Pilih : ");
            try {//we handle the input of the user
		answer = input.nextInt();
            } catch (NumberFormatException e) {
                //e.printStackTrace();
		answer = 0;
            }
            return answer;
        }
        
        public static void printContact(String[] fields, String[] info){
            for (int i = 0; i < fields.length; i++ ) {
		System.out.println(fields[i] +": "+ info[i]);					
            }
        }
        
        public static void rightInformation(String[] fields, String[] info){
            System.out.println("----There is a contact for the information you gave----");
            printContact(fields, info);
        }
        
        public static void halfRightInformation(String[] fields, String[] info, String rightInfo){
            System.out.println("----There is a contact for the " + rightInfo + " you gave----");
            printContact(fields, info);
            System.out.println("----Information must be valid----");
        }
        public static void wrongInformation(){
            System.out.println("-------------------");
            System.out.println("You gave wrong information.");
        }
}

package mainpackage;

import java.util.*;
import java.util.Arrays;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Add extends Main{
    public static int readPhoneNumber(File file) throws FileNotFoundException, IOException {
        int phone = -1;
        boolean duplicate, valid;
        String currentLine;
        Scanner input= new Scanner(System.in);
        
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        do {
            duplicate = false;
            valid = true;
            
            System.out.println("Give Phone: ");
            try {
                phone = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                valid = false;
                System.out.println("Phone must be number.");
            }
            
            while((currentLine = reader.readLine()) != null) {//check for duplicate
		String[] words1 = currentLine.split(",");
		if(words1[2].equals(String.valueOf(phone))) {
                    duplicate = true;
                    System.out.println("Phone must be unique among the contacts.");
		}
            }
            
            reader = new BufferedReader(new FileReader(file));
	}while (duplicate == true || valid == false);
		
        return phone;
    }
    
    public static int readMobileNumber(File file) throws FileNotFoundException, IOException {
        int mobile = -1;
        boolean duplicate, valid;
        String currentLine;
        Scanner input= new Scanner(System.in);
        
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        do {
            duplicate = false;
            valid = true;
            System.out.println("Give Mobile phone: ");
            
            try {
                mobile = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Mobile phone must be number.");
                valid = false;
               }
            
            while((currentLine = reader.readLine()) != null) {//check for duplicate
		String[] words1=currentLine.split(",");
                
		if(words1[3].equals(String.valueOf(mobile))) {
                    duplicate=true;
                    System.out.println("Mobile Phone must be unique among the contacts.");
		}
            }
            
            reader = new BufferedReader(new FileReader(file));
	}while (duplicate == true || valid == false);
        
        return mobile;
    }
    
    public static String readEmail(File file) throws FileNotFoundException, IOException {
        String email;
        boolean duplicate, valid;
        String currentLine;
        Scanner input= new Scanner(System.in);
        
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        do {
            duplicate = false;
            System.out.println("Give E-mail: ");
            email = input.nextLine();
            
            while((currentLine = reader.readLine()) != null) {//check for duplicate
		String[] words1=currentLine.split(",");
                
		if(words1[4].equals(email)) {
                    duplicate = true;
                    System.out.println("E-mail must be unique among the contacts.");
		}
            }
	
            reader = new BufferedReader(new FileReader(file));
	}while (duplicate == true);
        
        return email;
    }
    
    public static int readStreetNumber(File file) throws FileNotFoundException {
        int streetNumber = -1;
        boolean duplicate, valid;
        String currentLine;
        Scanner input= new Scanner(System.in);
        
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        System.out.println("Give Street number: ");
	do {
            valid = true;
            try {
                streetNumber = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Street number must be a number.");
                valid = false;
            }	
        }while(valid == false);
        
        return streetNumber;
    }
    
    public static int readZipCode(File file) throws FileNotFoundException {
        int zipCode = -1;
        boolean duplicate, valid;
        String currentLine;
        Scanner input= new Scanner(System.in);
        
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        System.out.println("Give Street: ");
	do {
            valid = true;
            try {
                zipCode = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Zip code must be a number.");
                valid = false;
            }	
        }while(valid == false);
        
        return zipCode;
    }
    
    public static boolean isValueEmpty(String name, String surname, String email,
            String street, String town, int phone, int mobile, int streetNumber,
            int zipCode) {
        
        return ("".equals(name) || "".equals(surname) || "".equals(email) ||
                "".equals(street) || "".equals(town) || phone == -1 || mobile == -1 ||
                streetNumber == -1 || zipCode == -1);
    }
    
    public static void addContact() throws IOException, FileNotFoundException{
        String owner = "user.dir";
        String dir = "/src/contacts.txt";
        File file = new File(System.getProperty(owner)+dir);
        BufferedReader reader1 = new BufferedReader(new FileReader(file));
        OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(System.getProperty(owner)+dir, true),
                "UTF-8");
        BufferedWriter writer1 = new BufferedWriter(writer);
        Scanner input= new Scanner(System.in);
	
        boolean duplicate, valid;
	String currentLine1;
        String
            name = "",
            surname = "",//I initialize the variablesto avoid errors
            email = "",
            street = "",
            town = "";
            
        int 
            phone = -1,
            mobile = -1,
            streetNumber = -1,
            zipCode = -1;
            
        String str;
	
        System.out.println("Give Name: ");
	name = input.nextLine();
        
	System.out.println("Give Surname: ");
	surname = input.nextLine();		
		
        phone = readPhoneNumber(file);
	mobile = readMobileNumber(file);
        email = readEmail(file);
        
	System.out.println("Give Street: ");
	street = input.nextLine();
        
        streetNumber = readStreetNumber(file);
        
	System.out.println("Give town: ");
	town = input.nextLine();
        
        zipCode = readZipCode(file);
        
	if(isValueEmpty(name, surname, email, street, town, phone, mobile, 
                streetNumber, zipCode)) {//i check that all variables have a valid attribute assigned
            System.out.println("You gave false inputs, adding new contact wasn't successful: ");
	
        } else {//if everything is correct i build a string
            str = name + "," + surname + "," + String.valueOf(phone) + "," + 
                    String.valueOf(mobile) + "," + email + "," + street + "," +
                    String.valueOf(streetNumber) + "," + town + "," + String.valueOf(zipCode);
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));//with these code I add a line at the bottom of the file
            out.println(str);
            out.close();
	}
	
        writer.close(); 
	reader1.close(); 
    }

}

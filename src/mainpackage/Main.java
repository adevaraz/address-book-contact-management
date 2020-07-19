package mainpackage;

import java.util.Arrays;
import java.util.Scanner;

import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.charset.Charset;

public class Main {
	public static void main(String[] args) throws IOException {
		ContactRepository contactRepo = new ContactRepository();
		BlockedContactRepository blockContactRepo = new BlockedContactRepository();
		ContactAppService service = new ContactAppService(contactRepo, blockContactRepo);
		Display display = new Display(service);
//		List<Contact> contacts = new ArrayList<>();
		List<RegularContact> regContacts = new ArrayList<>();
		List<BlockContact> blockContacts = new ArrayList<>();
		
		int exit = 0;
		int answer;
		//we will loop until user wants to exit the application
		do {
            display.displayMenu();
			answer = choose();
			switch(answer)
                        {
                            case 1:
                                display.displayAllContactAsc();
                                break;
                            case 2:
                            	// TODO read data from user
                                Scanner input= new Scanner(System.in);
                                System.out.println("Give name, and surname");
                                Name name = new Name(input.nextLine(), input.nextLine());
                                System.out.println("Give street, street number, town, zipcode");
                                Address address = new Address(input.nextLine(), input.nextInt(), input.nextLine(), input.nextInt());
                                System.out.println("Give email");
                                String email = input.nextLine();
                                System.out.println("Give phone number and mobile number");
                                Number number = new Number(input.nextLine(), input.nextLine());
                            	service.contactRepo.addContact(regContacts, new RegularContact(name, address, email, number));
                            	
                                break;
                            case 3:
                            	// TODO display submenu search
                            	// switch case search
                            	// case 1 : service.searchByName(name);
                            	// case 2 : service.searchByNumber(number); 
                                break;
                            case 4:
                            	// TODO display submenu change/update isinya minta input nama/number
                            	// switch case idem search
                            	// search
                            	// update
                                break;
                            case 5:
                            	// TODO display minta input nama/number
                            	// search (must) by number
                            	// remove (pake remove yg ada di service)
                                break;
                            case 0:
                                break;
                                
                            default:                    
                                System.out.println("Invalid request");
                                break;
                        }

		}while(answer != exit);
		System.out.println("Application terminating...");
	}
        
        
    public static int choose(){
        int answer;
        Scanner input = new Scanner(System.in);
            
        System.out.print("Pilih : ");
        try {//we handle the input of the user
        	answer = input.nextInt();
        } catch (NumberFormatException e) {
        	answer = -1;
        }
            return answer;
    }
}
=======
package mainpackage;

import java.util.Arrays;
import java.util.Scanner;

import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.charset.Charset;

public class Main {
	public static void main(String[] args) throws IOException {
		ContactRepository contactRepo = new ContactRepository();
		BlockedContactRepository blockContactRepo = new BlockedContactRepository();
		ContactAppService service = new ContactAppService(contactRepo, blockContactRepo);
		Display display = new Display(service);
//		List<Contact> contacts = new ArrayList<>();
		List<RegularContact> regContacts = new ArrayList<>();
		List<BlockContact> blockContacts = new ArrayList<>();
		
		int exit = 0;
		int answer;
		//we will loop until user wants to exit the application
		do {
            display.displayMenu();
			answer = choose();
			switch(answer)
                        {
                            case 1:
                                display.displayAllContactAsc();
                                break;
                            case 2:
                            	// TODO read data from user
                            	// service.contactRepo.addContact(regContacts, new RegularContact());
                                break;
                            case 3:
                            	// TODO display submenu search
                            	// switch case search
                            	// case 1 : service.searchByName(name);
                            	// case 2 : service.searchByNumber(number); 
                                break;
                            case 4:
                            	// TODO display submenu change/update; input nama/number
                            	// switch case idem search
                            	// search
                            	// update
                                break;
                            case 5:
                                // TODO display input nama/number
                            	// search (must) by number
                            	// remove
                                Number number = inputNumber();
                            	service.searchByNumber(number);
                                
                                break;
                            case 0:
                                break;
                                
                            default:                    
                                System.out.println("Invalid request");
                                break;
                        }

		}while(answer != exit);
		System.out.println("Application terminating...");
	}
        
        
    public static int choose(){
        int answer;
        Scanner input = new Scanner(System.in);
            
        System.out.print("Pilih : ");
        try {//we handle the input of the user
        	answer = input.nextInt();
        } catch (NumberFormatException e) {
        	answer = -1;
        }
            return answer;
    }
    
    public static Name inputName(){
        Name name = null;
        Scanner input = new Scanner(System.in);
        
        System.out.print("First name : ");
        name.firstName = input.nextLine();
        System.out.print("Surname : ");
        name.surName = input.nextLine();
        return name;
    }
    
    public static Number inputNumber(){
        Number number = null;
        Scanner input = new Scanner(System.in);
        
        System.out.print("Mobile number : ");
        number.mobile = input.nextLine();
        System.out.print("Phone Number : ");
        number.phone = input.nextLine();
        
        return number;
    }
}

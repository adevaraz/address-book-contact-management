package mainpackage;

import java.util.Arrays;
import java.util.Scanner;

import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.charset.Charset;

public class Main {
	public static void main(String[] args) throws IOException {
		
		int exit = 0;
		int answer;
		//we will loop until user wants to exit the application
		do {
                        displayMenu();
			answer = choose();
			switch(answer)
                        {
                            case 1:
                                Print.show_contacts();
                                break;
                            case 2:
                                Add.add_contact();
                                break;
                            case 3:
                                Search.choose_field();
                                break;
                            case 4:
                                Change.choose_field();
                                break;
                            case 5:
                                Delete.choose_field();
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
        
        public static void displayMenu(){
            
            System.out.println("==Menu==");
            System.out.println("1. Print Contacts");
            System.out.println("2. Add Contact");
            System.out.println("3. Search Contact");
            System.out.println("4. Edit Contact");
            System.out.println("5. Delete Contact");
            System.out.println("0. Exit");
        }
        
        public static int choose(){
            int answer;
            Scanner input = new Scanner(System.in);
            
            System.out.print("Pilih : ");
            try {//we handle the input of the user
		answer = input.nextInt();
            } catch (NumberFormatException e) {
                //e.printStackTrace();
		answer = -1;
            }
            return answer;
        }
}

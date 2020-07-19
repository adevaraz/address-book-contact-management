package mainpackage;

import java.util.Arrays;
import java.util.Scanner;

import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.charset.Charset;

public class Main {
	public static void main(String[] args) throws IOException {
		Display display;
		int exit = 0;
		int answer;
		//we will loop until user wants to exit the application
		do {
            display.displayMenu();
			answer = choose();
			switch(answer)
                        {
                            case 1:
                                Print.showContacts();
                                break;
                            case 2:
                                Add.addContact();
                                break;
                            case 3:
                                Search.chooseField();
                                break;
                            case 4:
                                Change.chooseField();
                                break;
                            case 5:
                                Delete.chooseField();
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
                //e.printStackTrace();
        	answer = -1;
        }
            return answer;
    }
}

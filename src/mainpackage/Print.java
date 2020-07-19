package mainpackage;


import java.io.*;
import java.io.IOException;


public class Print extends Main{
    public static void showContacts() throws IOException, FileNotFoundException{
	File file = new File(System.getProperty("user.dir")+"/src/contacts.txt");//we get the cantact file 
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine;
            boolean first = false;
            String[] fields = new String[0];
            
            while((currentLine = reader.readLine()) !=null) {//for each line in txt file
                if(!first) {//if it is the first line the line is the fields and we save them into an array
                    fields = currentLine.split(",");
                    first = true;
                }
                else {//for the rest lines we print the information
                    String[] info=currentLine.split(",");
                    System.out.println("-------------------");
                    printInfo(currentLine, fields , info);
                }
            }
            System.out.println("-------------------");
        }
    }
    
    public static void printInfo(String currentLine, String[] fields, String[] info){     
	for (int i = 0; i < fields.length; i++ ) {
            System.out.println(fields[i] +": "+ info[i]);					
	}
    }
    
}

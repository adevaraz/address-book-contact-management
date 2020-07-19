package mainpackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContactRepository {
	String
		dir = "user.dir",
		fileName = "regular_contact.txt",
		specificFile = "src/" + fileName;
	File file;
    
    public ContactRepository() throws FileNotFoundException, UnsupportedEncodingException {
    	File file = new File(System.getProperty(dir) + specificFile);
    }
    
    boolean isExist(List<RegularContact> contactList, RegularContact contact) {
		boolean exist = false;
		Iterator<RegularContact> contactIter = contactList.iterator();
		
		while(contactIter.hasNext() && !exist) {
			RegularContact tmp = contactIter.next();

			if(tmp.getNumber().getPhone() == contact.getNumber().getPhone()) {
				exist = true;
			}
		}
		
		return exist;
	}
    
    public void addContact(List<RegularContact> contactList, RegularContact contact) throws IOException {
		
		if(!isExist(contactList, contact)) {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			out.println(contact.toString());
            out.close();
		}
    }
    
	public List<RegularContact> readAllContacts() throws FileNotFoundException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		List<RegularContact> tmpContactList = new ArrayList<>();
		String currentLine;
		boolean firstLine = true;
		
		try {
			while((currentLine = reader.readLine()) != null) {
				if(firstLine) {
					firstLine = false;
				} else {
					String[] data = currentLine.split(",");
					
					//TODO Create contact
//					Contact tmpContact = new RegularContact(data[0], data[1]);
//					tmpContactList.add(tmpContact);
				}
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return tmpContactList;
	}
	
	public void updateFile(String line, String nData) throws IOException {
		File tmpFile = new File(System.getProperty(dir) + "/src/contactstemp.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tmpFile));
		String currentLine;
		boolean firstLine = true;
		
		try {
			while((currentLine = reader.readLine()) != null) {
				if(firstLine) {
					writer.write(currentLine);
					firstLine = false;
				} else if(currentLine.equals(line)) {
					writer.write(nData + "\n");
				}
			}
			
			reader.close();
			writer.close();
			
			file.delete();
			tmpFile.renameTo(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addFavorite(List<RegularContact> contactList, RegularContact contact) throws IOException {
		
		if(isExist(contactList, contact)) {
			// Replace the changed contact
			int i = contactList.indexOf(contact);
			contact.setFavorite(true);
			contactList.add(i, contact);
			
			updateFile(contactList.get(i).toString(), contact.toString());
		}
	}
	
	public void addUrgent(List<RegularContact> contactList, RegularContact contact) throws IOException {
		if(isExist(contactList, contact)) {
			// Replace the changed contact
			int i = contactList.indexOf(contact);
			contact.setUrgent(true);
			contactList.add(i, contact);
			
			updateFile(contactList.get(i).toString(), contact.toString());
		}
	}
	
	public void undoFavorite(List<RegularContact> contactList, RegularContact contact) throws IOException {
		if(contact.isFavorite()) {
			// Replace the changed contact
			int i = contactList.indexOf(contact);
			contact.setFavorite(false);
			contactList.add(i, contact);
			
			updateFile(contactList.toString(), contact.toString());
		}
	}
	
	public void undoUrgent(List<RegularContact> contactList, RegularContact contact) throws IOException {
		if(contact.isUrgent()) {
			// Replace the changed contact
			int i = contactList.indexOf(contact);
			contact.setUrgent(false);;
			contactList.add(i, contact);
			
			updateFile(contactList.toString(), contact.toString());
		}
	}
	
	public void removeContact(List<RegularContact> contactList, RegularContact contact) throws IOException {
		File tmpFile = new File(System.getProperty(dir) + "/src/contactstemp.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tmpFile));
		String 
			currentLine,
			line = contact.toString();
		boolean firstLine = true;
		
		if(isExist(contactList, contact)) {
			contactList.remove(contact);
			
			while((currentLine = reader.readLine()) != null) {
				if(firstLine) {
					writer.write(currentLine);
					firstLine = false;
				} else if(!currentLine.equals(line)) {
					writer.write(currentLine + "\n");
				}
			}
		}
		
		reader.close();
		writer.close();
	}
}

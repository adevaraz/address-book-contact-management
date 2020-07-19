<<<<<<< HEAD
package mainpackage;

public class BlockedContactRepository {
        
    public void displayBlockedContact(String[] fields, String info){
        
    }
}
=======
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

public class BlockedContactRepository {
	String
		dir = "user.dir",
		fileName = "blocked_contact.txt",
		specificFile = "src/" + fileName;
	File file;

	public BlockedContactRepository() throws FileNotFoundException, UnsupportedEncodingException {
		File file = new File(System.getProperty(dir) + specificFile);
	}
	
	boolean isExist(List<BlockContact> contactList, BlockContact contact) {
		boolean exist = false;
		Iterator<BlockContact> contactIter = contactList.iterator();
		
		while(contactIter.hasNext() && !exist) {
			BlockContact tmp = contactIter.next();

			if(tmp.getNumber().getPhone() == contact.getNumber().getPhone()) {
				exist = true;
			}
		}
		
		return exist;
	}
	
	public void addBlockedContact(List<BlockContact> contactList, BlockContact contact) throws IOException {
		if(!isExist(contactList, contact)) {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			out.println(contact.toString());
            out.close();
		}
	}
	
	public void removeBlockedContact(List<BlockContact> contactList, BlockContact contact) throws IOException {
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
	
	public List<BlockContact> readBlockedContact() throws FileNotFoundException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		List<BlockContact> tmpContactList = new ArrayList<>();
		String currentLine;
		boolean firstLine = true;
		
		try {
			while((currentLine = reader.readLine()) != null) {
				if(firstLine) {
					firstLine = false;
				} else {
					String[] data = currentLine.split(",");
					
					//TODO Create contact
//					Contact tmpContact = new BlockContact(data[0], data[1]);
//					tmpContactList.add(tmpContact);
				}
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return tmpContactList;
	}
}
>>>>>>> implementing method for blocked contact

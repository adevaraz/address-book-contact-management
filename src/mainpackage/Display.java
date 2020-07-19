package mainpackage;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

public class Display {
	ContactAppService service;
	
	public Display(ContactAppService service) {
		this.service = service;
	}

	public void displayMenu() {
		System.out.println("==Menu==");
        System.out.println("1. Print Contacts");
        System.out.println("2. Add Contact");
        System.out.println("3. Search Contact");
        System.out.println("4. Edit Contact");
        System.out.println("5. Delete Contact");
        System.out.println("0. Exit");
	}
	
	public void displayAllContactAsc() {
		List<Contact> contacts = service.ascendingContact();
		Iterator<Contact> contactsIter = contacts.iterator();
		
		while(contactsIter.hasNext()) {
			contactDetails(contactsIter.next());
		}
	}
	
	public void displayAllContactDesc() {
		List<Contact> contacts = service.descendingContact();
		Iterator<Contact> contactsIter = contacts.iterator();
		
		while(contactsIter.hasNext()) {
			contactDetails(contactsIter.next());
		}
	}
	
	public void contactDetails(Contact contact) {
		System.out.println("Name : " + contact.getName().getFirstName());
		System.out.println("Surname : " + contact.getName().getSurName());
		System.out.println("Address : " + contact.getAddress().getStreet() + " " + contact.getAddress().getStreetNum() +
				", " + contact.getAddress().getTown() + ", " + contact.getAddress().getZipCode());
		System.out.println("Email : " + contact.getEmail());
		System.out.println("Number");
		System.out.println("Phone : " + contact.getNumber().getPhone());
		System.out.println("Mobile : " + contact.getNumber().getMobile());
	}
	
	public void displayAllFavorite(String[] fields, String[] info) {
		List<RegularContact> favContact = service.getFavoriteContact();
		
		for (int i = 0; i < favContact.size(); i++ ) {
            System.out.println(fields[i] +": "+ info[i]);					
		}
	}
	
	public void displayAllUrgent(String[] fields, String[] info) {
		List<RegularContact> urgentContact = service.getUrgentContact();
		
		for (int i = 0; i < urgentContact.size(); i++ ) {
            System.out.println(fields[i] +": "+ info[i]);					
		}
	}
    
//	public void displayAllBlocked(String[] fields, String[] info) throws FileNotFoundException {
//            List<BlockContact> blockedContact = service.getBlockedContact();
//		
//            for (int i = 0; i < blockedContact.size(); i++ ) {
//                System.out.println(fields[i] +": "+ info[i]);					
//            }
//	}
}
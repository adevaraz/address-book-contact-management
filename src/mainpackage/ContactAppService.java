package mainpackage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ContactAppService {
	ContactRepository contactRepo;
	BlockedContactRepository blockedContactRepo;
	List<RegularContact> regContactList;
	List<BlockContact> blockContactList;
	
	public ContactAppService(ContactRepository contactRepo, BlockedContactRepository blockedContactRepo) throws FileNotFoundException {
		
		this.contactRepo = contactRepo;
		this.blockedContactRepo = blockedContactRepo;
//		this.regContactList = contactRepo.readAllContacts();
//		this.blockContactList = blockedContactRepo.readBlockedContact();
	}
	
	public List<Contact> ascendingContact() {
		List<Contact> contactList = new ArrayList<Contact>();
		Iterator<RegularContact> regIterator = regContactList.iterator();
		Iterator<BlockContact> blockIterator = blockContactList.iterator();
		
		while(regIterator.hasNext()) {
			contactList.add(regIterator.next());
		}
		
		while(blockIterator.hasNext()) {
			contactList.add(regIterator.next());
		}
		
		Collections.sort(contactList, new Comparator<Contact>() {
			@Override
			public int compare(Contact c1, Contact c2) {
				if(c1.getName().getFirstName() != c2.getName().getFirstName()) {
					return c1.getName().getFirstName().compareTo(c2.getName().getFirstName());
				}
				return c1.getName().getSurName().compareTo(c2.getName().getSurName());
			}
		});
		
		return contactList;
	}
	
	public List<Contact> descendingContact() {
		List<Contact> contactList = new ArrayList<Contact>();
		
		contactList = ascendingContact();
		Collections.reverse(contactList);
		
		return contactList;
	}
	
	public List<RegularContact> getFavoriteContact() {
		List<RegularContact> tmpList = new ArrayList<>();
		Iterator<RegularContact> iter = regContactList.iterator();
		
		while(iter.hasNext()) {
			RegularContact tmp = iter.next();
					
			if(tmp.isFavorite()) {
				tmpList.add(tmp);
			}
		}
		
		return tmpList;
	}
	
	public List<RegularContact> getUrgentContact() {
		List<RegularContact> tmpList = new ArrayList<>();
		Iterator<RegularContact> iter = regContactList.iterator();
		
		while(iter.hasNext()) {
			RegularContact tmp = iter.next();
					
			if(tmp.isUrgent()) {
				tmpList.add(tmp);
			}
		}
		
		return tmpList;
	}
        
	public Contact searchByName(Name name) {
		Contact tmpContact = null;
		List<Contact> contactList = ascendingContact();
		Iterator<Contact> contactIter = contactList.iterator();
		boolean found = false;
		
		while(contactIter.hasNext() && !found) {
			if(contactIter.next().getName().equals(name)) {
				found = true;
			}
		}
		
		return tmpContact;
	}
	
	public Contact searchByNumber(Number number) {
		Contact tmpContact = null;
		List<Contact> contactList = ascendingContact();
		Iterator<Contact> contactIter = contactList.iterator();
		boolean found = false;
		
		while(contactIter.hasNext() && !found) {
			if(contactIter.next().getNumber().equals(number)) {
				found = true;
			}
		}
		
		return tmpContact;
	}
        
//    public List<BlockContact> getBlockedContact() throws FileNotFoundException {
//    	return blockedContactRepo.readBlockedContact();
//	}
	
}

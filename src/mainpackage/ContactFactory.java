package mainpackage;

public abstract class ContactFactory {
	public abstract Contact createContact(Name name, Address address, String email, Number number);
}

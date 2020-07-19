package mainpackage;

public class ConcreteRegularFactory extends ContactFactory {
	
	@Override
	public Contact createContact(Name name, Address address, String email, Number number) {
		return new RegularContact(name, address, email, number);
	}
	
}

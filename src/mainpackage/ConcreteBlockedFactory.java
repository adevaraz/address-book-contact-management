package mainpackage;

public class ConcreteBlockedFactory extends ContactFactory {

    @Override
    public Contact createContact(Name name, Address address, String email, Number number) {
        return new BlockContact(name, address, email, number); //To change body of generated methods, choose Tools | Templates.
    }
}

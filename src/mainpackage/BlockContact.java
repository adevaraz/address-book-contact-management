package mainpackage;

public class BlockContact implements Contact {

    private Name name;
    private Address address;
    private String email;
    private Number number;
        
    BlockContact() {

    }
    
    BlockContact(Name name, Address address, String email, Number number){
        this.name = name;
        this.address = address;
        this.email = email;
        this.number = number;
    }

    /**
     * @return the name
     */
    public Name getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the number
     */
    public Number getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(Number number) {
        this.number = number;
    }
            
        
    @Override
    public BlockContact getContact() {
        return this;
    }

    @Override
    public void setContact(Contact contact) {
        
    }
}

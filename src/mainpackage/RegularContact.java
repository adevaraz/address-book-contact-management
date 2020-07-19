package mainpackage;

public class RegularContact implements Contact {
	private Name name;
	private Address address;
	private String email;
	private Number number;
	private boolean favorite;
	private boolean urgent;
	
	public RegularContact(Name name, Address address, String email, Number number) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.number = number;
		this.urgent = false;
		this.favorite = false;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Number getNumber() {
		return number;
	}

	public void setNumber(Number number) {
		this.number = number;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	@Override
	public RegularContact getContact() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void setContact(Contact regContact) {
		
	}
}

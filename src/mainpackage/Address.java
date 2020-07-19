package mainpackage;

public class Address {
	String street;
	int streetNum;
	String town;
	int zipCode;
	
	public Address(String street, int streetNum, String town, int zipCode) {
		this.street = street;
		this.streetNum = streetNum;
		this.town = town;
		this.zipCode = zipCode;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public int getStreetNum() {
		return streetNum;
	}
	
	public void setStreetNum(int streetNum) {
		this.streetNum = streetNum;
	}
	
	public String getTown() {
		return town;
	}
	
	public void setTown(String town) {
		this.town = town;
	}
	
	public int getZipCode() {
		return zipCode;
	}
	
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	
}

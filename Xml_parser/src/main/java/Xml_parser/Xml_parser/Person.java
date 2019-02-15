package Xml_parser.Xml_parser;

public class Person {
	
	public Person(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;

	}

	private String firstname;
	private String lastname;
	private PhoneNumber phone;
	private PhoneNumber fax;
	
	public void setPhone(PhoneNumber phoneNumber) {
		this.phone = phoneNumber;

	}

	public void setFax(PhoneNumber phoneNumber) {
		this.fax = phoneNumber;		
	}
}

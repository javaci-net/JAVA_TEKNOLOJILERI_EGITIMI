package application;

public class Customer {
	
	
	private String mail;
	private String phoneNumber;
	private String name;
	
	Customer(String name, String mail, String phoneNumber) {
		this.name = name;
		this.mail = mail;
		this.phoneNumber = phoneNumber;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	
	@Override
	public String toString() {
		String s = "Customer Name: " + getName() + "\nEmail: " + getMail() + "\nPhone Number: " + getPhoneNumber();
		return s;
	}
	
	

	
	
}

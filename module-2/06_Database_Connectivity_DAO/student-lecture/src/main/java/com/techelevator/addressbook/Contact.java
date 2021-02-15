package com.techelevator.addressbook;

public class Contact {
	
	private int contactId;
	private String firstname;
	private String lastname;
	
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}	

	
	
}

package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class BankCustomer {
	
	public BankCustomer() {}
	
	public BankCustomer(String name, String address, String phoneNumber, List <Accountable>accounts) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.accounts = accounts;
	}
	
	private String name;
	private String address;
	private String phoneNumber;
	private List <Accountable> accounts;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public List<Accountable>  getAccounts() {
		
		return accounts;
	}

	
	
	public void addAccount(Accountable newAccount) {
		if (getAddress() != null) {
			List<Accountable> accounts = new ArrayList<Accountable>();
				for(Accountable account : accounts) {
					accounts.add(account);
			}
		}
	}
	
	
}

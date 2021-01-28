package com.techelevator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankCustomer {
	
	public BankCustomer() {}
	
//	public BankCustomer(String name, String address, String phoneNumber, List <Accountable>accounts) {
//		this.name = name;
//		this.address = address;
//		this.phoneNumber = phoneNumber;
//		this.accounts = accounts;
//	}
	
	private String name;
	private String address;
	private String phoneNumber;
	private List <Accountable> accounts = new ArrayList <Accountable>();
	
	
	
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
	
	//private int account;
	

	
	public boolean isVip() {
		// for loop the accounts List to add each together.
		// if sum is >= 25000, return true.
		int total = 0;
		for (Accountable account : accounts) {
			//total;
		}
		
		
		
		
//		for (Accountable account : accounts) {
//			total += account;
//		}
		
		return true;
	}

	
	
	public void addAccount(Accountable newAccount) {
		accounts.add(newAccount);
	}
}	
		



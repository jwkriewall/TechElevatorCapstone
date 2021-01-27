package com.techelevator;

public class CreditCardAccount implements Accountable {
	
	public CreditCardAccount(String accountHolder, String accountNumber){
		this.accountHolder = accountHolder;
		this.accountNumber = accountNumber;
		
	}
	
	public String getAccountHolder(){
		return this.accountHolder;
	}
	
	public String getAccountNumber() {
		return this.accountNumber;
	}
	
	
	String accountHolder;
	String accountNumber;
	private int debt;
	
	
	
	public int getDebt() {
		return debt;
	}
	
	@Override
	public int getBalance() {
		return -(debt);
	}
	
	
	public void pay(int amountToPay) {
		debt = debt - amountToPay;
	}
	public void charge(int amountToCharge) {
		debt = debt + amountToCharge;
	}
	
}

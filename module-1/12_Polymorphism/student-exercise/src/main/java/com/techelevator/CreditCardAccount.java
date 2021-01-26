package com.techelevator;

public class CreditCardAccount implements Accountable {
	
	public CreditCardAccount(String accountHolder, String accountNumber){
		this.accountHolder = accountHolder;
		this.accountNumber = accountNumber;
		//this.debt = debt;
	}

	String accountHolder;
	String accountNumber;
	int debt;
	
	public void pay(int amountToPay) {
		debt = Accountable.getBalance(accountHolder, accountNumber) + amountToPay;
	}
	public void charge(int amountToCharge) {
		debt = Accountable.getBalance(accountHolder, accountNumber) - amountToCharge;
	}
}

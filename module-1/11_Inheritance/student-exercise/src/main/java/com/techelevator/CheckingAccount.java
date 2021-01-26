package com.techelevator;

public class CheckingAccount extends BankAccount {
	
	
	public CheckingAccount(String accountHolderName, String accountNumber) {
		super(accountHolderName, accountNumber);
	}
	public CheckingAccount(String accountHolderName, String accountNumber, int balance) {
		super(accountHolderName, accountNumber, balance);
		
		
	}
	
	// if balance is < 0 && > -100.00, subtract 10 
	// unless initial withdraw returns -100, then return fail, same balance
	
	@Override
	public int withdraw(int amountToWithdraw) {
		
		int newBalance = this.getBalance() - amountToWithdraw;
		
		int penalty = 10;
		
		
		if (newBalance <= -100.00) {
			return this.getBalance();
		}
		else if (newBalance < 0.00 && newBalance > -100.00) {
			super.withdraw((amountToWithdraw + penalty));
			return (newBalance - penalty);
		}
		else {
			super.withdraw(amountToWithdraw);
			return newBalance;
		}
		
	}
}

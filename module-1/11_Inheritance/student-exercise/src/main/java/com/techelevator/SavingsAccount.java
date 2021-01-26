package com.techelevator;

public class SavingsAccount extends BankAccount{
	
	public SavingsAccount(String AccountName, String AccountNumber) {
		super(AccountName, AccountNumber);
	}
	public SavingsAccount(String AccountName, String AccountNumber, int balance) {
		super(AccountName, AccountNumber, balance);
	}
	
	@Override
	public int withdraw(int amountToWithdraw) {
		// if current balance is less than 150 when withdrawal is made, 2 dollar surcharge
		// if withdrawal is requested for more than current balance,
		// withdrawal fails and balance remains the same. NO FEES
		
		int newBalance = this.getBalance() - amountToWithdraw;
		int finalBalance = 0;
		int penalty = 2;
			
		if (newBalance < 150 && newBalance > 0) {
			finalBalance = super.withdraw(amountToWithdraw + penalty);
			return finalBalance;
		}
		if (newBalance < 0) {
			finalBalance = this.getBalance();
			return finalBalance;
		}
		if (newBalance >= 150) {
			finalBalance = super.withdraw(amountToWithdraw);
			return finalBalance;
		}
		
		return finalBalance;
	}
	// code is not interacting with parent class in way I imagined. 
	// Both examples seem to add final balance instead of updating them.
	
}

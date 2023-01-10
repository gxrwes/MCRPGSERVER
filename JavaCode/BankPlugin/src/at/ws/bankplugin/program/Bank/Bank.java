package at.ws.bankplugin.program.Bank;

import java.util.ArrayList;

import java.io.IOException;


public class Bank {
	// Main holder, a bank holds the accounts
	ArrayList<Account> accounts;
	
	private static Bank bank;
	
	public Bank()
	{
		accounts = new ArrayList<Account>();
	}
	
	// Singleton Instance
	public static Bank getInstance() {
		if(bank == null)
		{
			bank = new Bank();
		}
		return bank;
	}
	
	public int getNumberOfAccounts()
	{
		return accounts.size();
	}
	
	public void addAccount(Account a)
	{
		if(!this.contains(a))
		{
			this.accounts.add(a);
			System.out.println("Bank Adding account:" + a.getName());
		}else
		{
			System.out.println("Account Already Active");
		}
	}
	
	public void listAccounts()
	{
		System.out.println("Listing all Accounts in Detail");
		int i = 0;
		for (Account a : accounts)
		{
			System.out.println(i + ": "+ a.getName() + " : " + a.getAccountValue() +"- Overdrawlimit: " + a.getOverdrawLimit());
		}
	}
	public boolean contains(Account a)
	{
		for(Account ac : accounts)
		{
			//System.out.println("Comparing " + ac.getName() + " to " + a.getName());
			if(a.getName().equals(ac.getName()))
			{
				//System.out.println("->true");
				return true;
			}
		}
		return false;
	}
	public ArrayList<Account> getAccounts(){ return accounts; }
	
	

}

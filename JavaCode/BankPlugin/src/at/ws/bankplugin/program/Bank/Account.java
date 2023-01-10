package at.ws.bankplugin.program.Bank;

public class Account {
	
	private String accountName;
	
	private float overdrawLimit;
	private float accountValue;
	
	private boolean isLocked;
	
	//  Constructors and Overloads
	public Account(String accountname)
	{
		this.accountName = accountname;
		this.overdrawLimit = 0;
		this.accountValue = 0;
		this.isLocked = false;
		System.out.println("Account Added ADD <" + accountname + "> overdrawlimit: " + overdrawLimit + " value:" + accountValue);
	}
	public Account(String accountname, float overdrawlimit)
	{
		this.accountName = accountname;
		this.overdrawLimit = overdrawlimit;
		this.accountValue = 0;
		this.isLocked = false;
		System.out.println("Account Added ADD <" + accountname + "> overdrawlimit: " + overdrawlimit + " value:" + accountValue);
	}
	public Account(String accountname, float overdrawlimit,float accountvalue)
	{
		this.accountName = accountname;
		this.overdrawLimit = overdrawlimit;
		this.accountValue = accountvalue;
		this.isLocked = false;
		System.out.println("Account Added ADD <" + accountname + "> overdrawlimit: " + overdrawlimit + " value:" + accountvalue);
	}
	
	// Getters and Setters
	public String getName() { return accountName; }
	public float getOverdrawLimit() { return overdrawLimit; }
	public float getAccountValue() { return accountValue; }
	public boolean islocked() { return isLocked; }
	public void setLocked( boolean b) { this.isLocked = b;	}
	
	// Functionality
	
	// use this only as admin, otherwise only called in transfer in order to not 'create' money
	public void transferAdd(float value)
	{
		System.out.println("Transaction ADD <" + value + "> to account: " + accountName);
		accountValue += value;
	}
	
	// Main transfer function from 'this' to reciever
	public void transfer(float value, Account reciever)
	{
		System.out.println("Transaction <" + value + "> from account: " + accountName + " to account:" + reciever.getName());
		accountValue -= value;
		reciever.transferAdd(value);
				
	}
	
	

}

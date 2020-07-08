package account;

public interface Account {
	
	public double getBalance();
	//returns the current balance
	
	public int getCredit();
	//returns the granted credit 
	//if none granted, returns 0
	
	public double deposit(double amount);
	//returns the new, increased balance
	//if negative parameter, returns the balance unchanged
	
	public double withdraw(double amount);
	//returns the amount made available
	//if negative parameter/not covered, returns 0

}

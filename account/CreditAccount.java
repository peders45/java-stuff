package account;

public class CreditAccount implements Account {

	private double balance;
	private int creditLimit;
	private int fees = 0;

	public CreditAccount(int creditLimit) {
		if (creditLimit < 0) {
			throw new IllegalArgumentException("Credit limit cannot be negative: " + creditLimit);
		}
		this.balance = 0;
		this.creditLimit = creditLimit;
	}
	
	public double getBalance() {
		return balance;
	}

	public int getCredit() {
		return creditLimit;
	}

	public double deposit(double amount) {
		if (amount > 0) {
			this.balance += amount;
		}
		return balance;
	}
	
	// charge 50 in fees whenever credit is used
	public double withdraw(double amount) {
		if (amount > 0 && amount <= (balance + creditLimit - 50)) {
			this.balance -= amount;
			if (balance < 0) {
				this.balance -= 50;
				this.fees += 50;
			}
			return amount;
		}
		else {
			return 0; 
		}
	}
	
	public int getFees() {
		return fees;
	}
	
	public String toString() {
		return String.format("[CreditAccount balance=%f creditLimit=%f fees=%f", balance, creditLimit, fees);
	}
	
}

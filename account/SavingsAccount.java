package account;

public class SavingsAccount implements Account {
	
	private double balance;
	private double interestRate;
	
	public SavingsAccount(double balance, double interestRate) {
		checkNotNegative(balance, "Balance");
		this.balance = balance;
		setInterestRate(interestRate);
	}
	
	public SavingsAccount() {
		this.balance = 0;
		this.interestRate = 0;
	}

	public double getBalance() {
		return balance;
	}

	public int getCredit() {
		return 0;
	}
	
	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		checkNotNegative(interestRate, "Interest rate");
		this.interestRate = interestRate;
	}

	public double deposit(double amount) {
		if (amount > 0) {
			this.balance += amount;
		}
		return balance;
	}

	public double withdraw(double amount) {
		if (amount > 0 && amount <= balance) {
			this.balance -= amount;
			return amount;
		}
		else {
			return 0;
		}
	}
	
	private void checkNotNegative(double value, String valueName) {
		if (value < 0) {
			throw new IllegalArgumentException(valueName + " cannot be negative: " + value);
		}
	}
	
	public String toString() {
		return String.format("[SavingsAccount balance=%f interestRate=%f", balance, interestRate);
	}
	
}

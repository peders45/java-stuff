package account;

import java.util.ArrayList;
import java.util.List;

public class AccountOverview {
	
	private List<Account> accounts = new ArrayList<Account>();
	
	public AccountOverview() {
		accounts.clear();
	}
	
	public int getAccountCount() {
		return accounts.size();
	}
	
	public Account getAccount(int index) {
		if (index < 0) {
			throw new IllegalArgumentException("Account index cannot be negative: " + index);
		}
		else if (index > accounts.size() -1) {
			throw new IllegalArgumentException("Account index is too high: " + index);
		}
		return accounts.get(index);
	}
	
	public void addAccount(Account newAccount) {
		if (!(accounts.contains(newAccount))) {
			accounts.add(newAccount);
		}
	}
	
	public double getTotalBalance() {
		double totalBalance = 0;
		for (Account account : accounts) {
			totalBalance += account.getBalance();
		}
		return totalBalance;
	}
	
	public int getTotalCredit() {
		int totalCredit = 0;
		for (Account account : accounts) {
			totalCredit += account.getCredit();
		}
		return totalCredit;	
	}
	
	public int getTotalFees() {
		int totalFees = 0;
		for (Account account : accounts) {
			if (account instanceof CreditAccount) {
				CreditAccount newAccount = (CreditAccount)account;
				totalFees += (newAccount).getFees();
			}
		}
		return totalFees;	
	}
	
}

package bank;

import java.util.Comparator;

public class BankAccountComparator implements Comparator<BankAccount> {
	public int compare(BankAccount first, BankAccount second) {
		return first.getAccountId().compareTo(second.getAccountId());
	}
}

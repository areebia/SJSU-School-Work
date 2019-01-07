package bank;

import java.util.ArrayList;
import java.util.Collections;

public class BankAccountTester {
	
	public static void main(String[] args)
    {
        ArrayList<BankAccount> accounts = new ArrayList<>();
        accounts.add(new BankAccount(500.00, "Areeb"));
        accounts.add(new BankAccount(10000.00, "Aidan"));
        accounts.add(new BankAccount(1.00, "Sammi"));
        accounts.add(new BankAccount(4456.00, "Angus"));
        accounts.add(new BankAccount(3400.00, "Ruchika"));
        accounts.add(new BankAccount(9765.00, "Nishank"));
        accounts.add(new BankAccount(12345.67, "Justin"));
        accounts.add(new BankAccount(6657562.00, "Mom"));

        BankAccountComparator comp = new BankAccountComparator();
        Collections.sort(accounts, comp);
        for (BankAccount b: accounts)
        {
            System.out.printf("%-18s%s%n",b.getAccountId(), b.getBalance());              
        }

    }
}

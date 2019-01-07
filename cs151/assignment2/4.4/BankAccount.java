package bank;


/**
 * A bank account has a balance that can be changed by 
 * deposits and withdrawals
 *  base class obtained from my time in CS 46a
 *  modified with a comparable
 */
public class BankAccount
{  
    private double balance;
    private String accountId;
   

    /**
     * Constructs a bank account with a given balance.
     * @param initialBalance the initial balance
     * @param id the id for this account
     */
    public BankAccount(double initialBalance, String id)
    {   
        balance = initialBalance;
        accountId = id;
    }

    /**
     * Gets the id for this account
     * @returns the id for this account
     */
    public String getAccountId()
    {
        return accountId;
    }

    /**
       Deposits money into the bank account.
       @param amount the amount to deposit
     */
    public void deposit(double amount)
    {  
        balance = balance + amount;
    }

    /**
       Withdraws money from the bank account.
       @param amount the amount to withdraw
     */
    public void withdraw(double amount)
    {   
        balance = balance - amount;
    }

    /**
       Gets the current balance of the bank account.
       @return the current balance
     */
    public double getBalance()
    {   
        return balance;
    }

}
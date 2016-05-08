package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<Account> accounts;
    public static final String AMOUNT_MUST_BE_AVAILABLE_FOR_TRANSFER = "Amount must be available for transfer";

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    //Sagar Replaced String concat with StringBuffer
    public String getStatement() {

        StringBuffer statement = new StringBuffer("Statement for ");

        statement.append(name).append("\n");
        double total = 0.0;
        for (Account a : accounts) {

            statement.append("\n").append(statementForAccount(a)).append("\n");
            total += a.sumTransactions();
        }

        statement.append("\nTotal In All Accounts ").append(toDollars(total));
        return statement.toString();
    }

    //Sagar Replaced String concat with StringBuffer
    private String statementForAccount(Account a) {

        StringBuffer s = new StringBuffer("");

        switch (a.getAccountType()) {
            case Account.CHECKING:
                s.append("Checking Account\n");
                break;
            case Account.SAVINGS:
                s.append("Savings Account\n");
                break;
            case Account.MAXI_SAVINGS:
                s.append("Maxi Savings Account\n");
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
            s.append("  ").append(t.amount < 0 ? "withdrawal" : "deposit").append(" ").append(toDollars(t.amount)).append("\n");
            total += t.amount;
        }
        s.append("Total ").append(toDollars(total));
        return s.toString();
    }

    private String toDollars(double d) {
        return String.format("$%,.2f", abs(d));
    }

    //Sagar new feature

    /**
     * Method to transfer between accounts.
     * verifies The amount is available for the transfer.
     *
     * @param fromAccount
     * @param toAccount
     * @param amount
     */
    public void transferFunds(Account fromAccount, Account toAccount, double amount) {

        if (fromAccount.sumTransactions() < amount) {
            throw new IllegalArgumentException(AMOUNT_MUST_BE_AVAILABLE_FOR_TRANSFER);
        } else {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
        }
    }

}

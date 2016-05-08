package com.abc;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;
    public static final String AMOUNT_MUST_BE_GREATER_THAN_ZERO = "amount must be greater than zero";

    private final int accountType;
    public List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(AMOUNT_MUST_BE_GREATER_THAN_ZERO);
        } else {
            transactions.add(new Transaction(amount));
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(AMOUNT_MUST_BE_GREATER_THAN_ZERO);
        } else {
            transactions.add(new Transaction(-amount));
        }
    }

    public double interestEarned() {
        double amount = sumTransactions();
        switch (accountType) {
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + (amount - 1000) * 0.002;
//            case SUPER_SAVINGS:
//                if (amount <= 4000)
//                    return 20;
            /* Code commented to implement Sagar's code case MAXI_SAVINGS:
                if (amount <= 1000)
                    return amount * 0.02;
                if (amount <= 2000)
                    return 20 + (amount - 1000) * 0.05;
                return 70 + (amount - 2000) * 0.1;
            */
                //Sagar
            case MAXI_SAVINGS:
                if (duration())
                    return amount * 0.05;
                else return amount * 0.001;
            default:
                return amount * 0.001;
        }
    }

    public double sumTransactions() {
        return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t : transactions)
            amount += t.amount;
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

//Sagar New method

    /**
     * This method calculates whether todays date is 10 days after the last withdrawal to apply 5% interest.
     * It assumes if there are no withdrawals at all the interest rate is 1%
     * It returns true if the condition to apply 5% interest is satisfied
     *@return boolean
     */
    private boolean duration() {

        boolean maxInterest = false;
        ZonedDateTime today = ZonedDateTime.now();
        ZonedDateTime recent = today;

        for (Transaction t : transactions) {
            recent = t.transactionDate;
            if (t.amount < 0) {
                recent = t.transactionDate.plusDays(9);
            }
        }
        if (today.isAfter(recent)) maxInterest = true;

        return maxInterest;
    }

}

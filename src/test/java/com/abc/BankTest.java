package com.abc;

import org.junit.Test;

import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_accounnt() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }


    //Sagar Changed the assertEquals expected interest value to match the new requirement
    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0);

        assertEquals(3.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    //Sagar changed the method getFirstCustomer() and added the new test
    //Below test whether the method retrieves the first customer of the bank or not
    @Test
    public void firstCustomer() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING));
        bank.addCustomer(john);
        Customer bill = new Customer("Bill");
        john.openAccount(new Account(Account.SAVINGS));
        bank.addCustomer(bill);

        assertEquals("John", bank.getFirstCustomer());
    }


    //Sagar In order to run this test change the remove the final identifier from the Transaction class so that the transaction date can be manipulated

    @Test
    public void maxi_savings_account_maxInterest() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
        Transaction t = new Transaction(500.0);
        t.transactionDate = ZonedDateTime.parse("2016-04-07T16:50:33.680-04:00[America/New_York]");
        System.out.println(t.transactionDate);
        Transaction t1 = new Transaction(-100.0);
        t1.transactionDate = ZonedDateTime.parse("2016-04-15T16:50:33.680-04:00[America/New_York]");
        System.out.println(t1.transactionDate);
        checkingAccount.transactions.add(t);
        checkingAccount.transactions.add(t1);

        assertEquals(20.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}

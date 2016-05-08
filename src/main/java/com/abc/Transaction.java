package com.abc;

import java.time.ZonedDateTime;

public class Transaction {
    public final double amount;


    //Sagar Removed private identifier for testing

    public ZonedDateTime transactionDate;


    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }

}

package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers;


    public Bank() {
        customers = new ArrayList<Customer>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }


    //Sagar used StringBuilder
    public String customerSummary() {

        StringBuffer summary = new StringBuffer("Customer Summary");

        for (Customer c : customers)
            summary.append("\n - ").append(c.getName()).append(" (").append(format(c.getNumberOfAccounts(), "account")).append(")");
        return summary.toString();
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end

    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }


    public double totalInterestPaid() {
        double total = 0;
        for (Customer c : customers)
            total += c.totalInterestEarned();
        return total;
    }

    //Sagar It will always return error
    //Sagar Removed the line where it says the customer is null
    //Sagar Wrote a test for it
    public String getFirstCustomer() {
        try {
            //customers = null;
            return customers.get(0).getName();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }


}

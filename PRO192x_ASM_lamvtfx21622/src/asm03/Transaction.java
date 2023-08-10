package asm03;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Transaction implements Serializable {
    private String id;
    private String accountNumber;
    private double amount;
    private String dateTime;

    public Transaction() {
        this.id = String.valueOf(UUID.randomUUID());
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}

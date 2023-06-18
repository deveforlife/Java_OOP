package asm02;

import asm03.Transaction;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Account {
    private String accountNumber;
    private double balance;
    private List<Transaction> transactions;
    public Account() {
        this.transactions = new ArrayList<>();
    }

    @Override
    public String toString() {
        return getAccountNumber() + "    |                               " +
                formatCurrency(getBalance()) + "\n";
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Boolean isPremium() {
        if (this.balance >= 10000000) {
            return true;
        } else {
            return false;
        }
    }

    //định dạng tiền tệ
    protected String formatCurrency (double currency){
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(vietnam);
        return currencyVN.format(currency);
    }

    //lấy thời gian local
    protected String getDateTime(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern ("dd/MM/yyyy HH:mm:ss");
        LocalDateTime timeNow = LocalDateTime.now();
        return dateTimeFormatter.format(timeNow);
    }

    protected List<Transaction> getTransactions() {
        return transactions;
    }

    //add transaction vào list
    protected void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

}

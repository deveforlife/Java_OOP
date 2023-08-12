package asm02;

import asm03.Transaction;
import asm04.AccountDao;
import asm04.TransactionDao;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Account implements Serializable {
    private String accountNumber;
    private double balance;
    private List<Transaction> transactions;

    //----ASM4
    protected String customerID;
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

    public Boolean isPremium() {
        if (this.balance >= 10000000) {
            return true;
        } else {
            return false;
        }
    }


    //----------asm4----------
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    //lay transaction
    public List<Transaction> getTransactions(String accountNumber) {
        List<Transaction> list = new ArrayList<>(TransactionDao.list());

        List<Transaction> transWithAccNumber = list
                .stream()
                .filter(c -> c.getAccountNumber().equals(accountNumber))
                .collect(Collectors.toList());

        return transWithAccNumber;
    }

}

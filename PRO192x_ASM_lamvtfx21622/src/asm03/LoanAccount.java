package asm03;

import asm02.Account;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LoanAccount extends Account implements Withdraw, Report{
    private String dateTime;
    private double fee;
    public LoanAccount() {

    }

    @Override
    public String toString() {
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(vietnam);
        String soDu = currencyVN.format(getBalance());
        return getAccountNumber() + "    |        LOAN  |                " + soDu + "\n";
    }

    @Override
    public void log(double amount) {
        System.out.println("+-------------+----------------------------------------+-------------+");
        System.out.printf("%30s%n", "BIEN LAI GIAO DICH LOAN");
        System.out.printf("NGAY G/D: %28s%n", dateTime);
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %31s%n", getAccountNumber());
        System.out.printf("SO TIEN: %29s%n", formatCurrency(amount));
        System.out.printf("SO DU: %31s%n", formatCurrency(getBalance()));
        System.out.printf("PHI + VAT: %27s%n", formatCurrency(fee));
    }

    // nộp tiền
    @Override
    public boolean deposit(double amount) {
        double newBalance = 0;
        if (amount > 0 && amount % 1000 == 0){
            newBalance = getBalance() + amount;
            setBalance(newBalance);
            dateTime = getDateTime();
            return true;
        }
        else {
            System.out.println("Số tiền nộp không hợp lệ!");
            return false;
        }
    }

    // rút tiền
    @Override
    public boolean withdraw(double amount) {
        double newBalance = 0;
        if (isAccepted(amount)){
            fee = (getTransactionFee()*amount);
            newBalance = getBalance() - amount - fee;
            setBalance(newBalance);
            dateTime = getDateTime();
            return true;
        }
        return false;
    }

    @Override
    public boolean isAccepted(double amount) {
        if (amount < 50000) System.out.println("Số tiền cần rút phải lớn hơn hoặc bằng 50000");
        else if (amount % 10000 != 0) System.out.println("Số tiền cần rút phải là bội số của 10000");
        else if (amount > getBalance() - 50000)
            System.out.println("Tài khoản không đủ số dư, vui lòng kiểm tra lại");
        else {
            return true;
        }
        return false;
    }

    private float getTransactionFee(){
        if (isPremium()) return 0.01f;
        else return 0.05f;
    }

    //Lưu lịch sử rút tiền
    public void saveTransaction(String accNum, double amount){
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accNum);
        transaction.setAmount(amount);
        transaction.setDateTime(dateTime);
        addTransaction(transaction);
    }

    //show lịch sử giao dịch
    public void showTransactionHistory(){
        System.out.println("Lịch sử giao dịch:");
        if (getTransactions().size() <= 0) {
            System.out.println("Không có lịch sử giao dịch.");
        }
        else {
            for (int i = getTransactions().size()-1; i >= 0; i--){
                System.out.printf("[GD] %5s",getAccountNumber());
                System.out.printf(" | %15s",formatCurrency(getTransactions().get(i).getAmount()));
                System.out.printf(" | %25s%n",getTransactions().get(i).getDateTime());
            }
        }
    }
}


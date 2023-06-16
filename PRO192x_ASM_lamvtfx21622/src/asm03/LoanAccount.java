package asm03;

import asm02.Account;

import java.text.NumberFormat;
import java.util.Locale;

public class LoanAccount extends Account implements Withdraw, Report{

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
        System.out.printf("NGAY G/D: %28s%n", "GETDATETIME");
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %31s%n", getAccountNumber());
        System.out.printf("SO TIEN: %29s%n", formatCurrency(amount));
        System.out.printf("SO DU: %31s%n", formatCurrency(getBalance()));
        System.out.printf("PHI + VAT: %27s%n", formatCurrency(getTransactionFee()*amount));
    }

    @Override
    public boolean withdraw(double amount) {
        double newBalance = 0;
        if (isAccepted(amount)){
            newBalance = getBalance() - amount - (getTransactionFee()*amount);
            setBalance(newBalance);
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
}


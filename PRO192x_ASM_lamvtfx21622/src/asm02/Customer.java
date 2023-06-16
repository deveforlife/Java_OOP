package asm02;

import java.text.NumberFormat;
import java.util.*;

public class Customer extends User {
    private List<Account> accounts;

    private static Scanner sc = new Scanner(System.in);

    public Customer() {
        this.accounts = new ArrayList<>();
    }

    public void displayInformation() {
        //chuyển đổi kiêu tiền tệ
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(vietnam);
        String soDu = currencyVN.format(getBalance());

        System.out.println("+-------------+----------------------------------------+-------------+");
        System.out.println(getCustomerId() + "  |   " + getName().toUpperCase() + "  |  " + isPremium() + "   |  " + soDu);
        for (int i = 0; i < accounts.size(); i++) {
            System.out.print((i + 1) + ".  " + accounts.get(i).toString());
        }
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    private double getBalance() {
        double totalBalance = 0;
        for (int i = 0; i < accounts.size(); i++) {
            totalBalance = totalBalance + accounts.get(i).getBalance();
        }
        return totalBalance;
    }

    //nhập số dư
    public boolean inputamount(double amount) {
        if (amount >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void addAccount(Account newAcount, double amount) {
        newAcount.setBalance(amount);
        accounts.add(newAcount);
    }

    public String isPremium() {
        String classCustom = "STANDARD";
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).isPremium()) {
                classCustom = "PREMIUM";
            }
        }
        return classCustom;
    }
}

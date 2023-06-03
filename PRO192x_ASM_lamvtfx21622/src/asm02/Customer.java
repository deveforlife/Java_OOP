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

    public void addAccount(Account newAcount) {
        double accountBalance = -1;

        // Kiểm tra số dư được nhập vào có đúng hay không?
        do {
            try {
                System.out.print("Nhập số dư: ");
                accountBalance = sc.nextDouble();
                if (accountBalance >= 0) {
                    newAcount.setBalance(accountBalance);
                } else {
                    System.out.println("Dữ liệu không đúng. Vui lòng nhập lại!");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Dữ liệu không đúng. Vui lòng nhập lại!");
                sc.nextLine();
                accountBalance = -1;
            }
        } while (accountBalance < 0);
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

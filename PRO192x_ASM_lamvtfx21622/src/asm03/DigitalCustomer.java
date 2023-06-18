package asm03;

import asm02.Account;
import asm02.Customer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DigitalCustomer extends Customer {
    private static Scanner sc = new Scanner(System.in);

    public DigitalCustomer() {
        super();
    }

    public void withdraw(String numAcc, double amount){
        for (Account account : getAccounts()){
            if (account.getAccountNumber().equals(numAcc)){
                if (account instanceof SavingsAccount){
                    if (((SavingsAccount)account).withdraw(amount)){
                        ((SavingsAccount)account).saveTransaction(numAcc, amount);
                        System.out.println("Giao dịch thành công");
                        ((SavingsAccount)account).log(amount);
                    }
                }
                else {
                    if (((LoanAccount)account).withdraw(amount)){
                        ((LoanAccount)account).saveTransaction(numAcc, amount);
                        System.out.println("Giao dịch thành công");
                        ((LoanAccount)account).log(amount);
                    }
                }
            }
        }
    }

    //tra cứu lịch sử
    public void showHistory(String numAcc){
        for (Account account : getAccounts()){
            if (account.getAccountNumber().equals(numAcc)){
                if (account instanceof SavingsAccount){
                    ((SavingsAccount)account).showTransactionHistory();
                }
                else {
                    ((LoanAccount)account).showTransactionHistory();
                }
            }
        }
    }

    // Add account savings
    public void addAccSavings(String accNum){
        SavingsAccount savingsAccount = new SavingsAccount();
        if (!isAccExists(accNum)) {
            savingsAccount.setAccountNumber(accNum);
            try {
                System.out.print("Nhập số dư: ");
                double amount = sc.nextDouble();
                sc.nextLine();
                if(inputamount(amount)){
                    addAccount(savingsAccount, amount);
                    System.out.println("Tạo tài khoản thành công!");
                }
                else System.out.println("Vui lòng nhập số dư lớn hơn hoặc bằng 0");
            }
            catch (InputMismatchException ime){
                System.out.println("Vui lòng nhập ký tự số");
                sc.nextLine();
            }
        }
        else System.out.println("Tài khoản "+accNum+" đã tồn tại trong hệ thống.");
    }

    // Add account loan
    public void addAccLoan(String accNum){
        LoanAccount loanAccount = new LoanAccount();
        if (!isAccExists(accNum)) {
            loanAccount.setAccountNumber(accNum);
            try {
                System.out.print("Nhập số dư: ");
                double amount = sc.nextDouble();
                sc.nextLine();
                if(inputamount(amount) && amount <= 100000000){
                    addAccount(loanAccount, amount);
                    System.out.println("Tạo tài khoản thành công!");
                }
                else System.out.println("Vui lòng nhập số dư lớn hơn hoặc bằng 0 " +
                        "và nhỏ hơn hoặc bằng 100 triệu");
            }
            catch (InputMismatchException ime){
                System.out.println("Vui lòng nhập ký tự số");
                sc.nextLine();
            }
        }
        else System.out.println("Tài khoản "+accNum+" đã tồn tại trong hệ thống.");
    }

    public boolean isAccExists(String accNum){
        for (Account account : getAccounts()) {
            if (account.getAccountNumber().equals(accNum)){
                return true;
            }
        }
        return false;
    }
}

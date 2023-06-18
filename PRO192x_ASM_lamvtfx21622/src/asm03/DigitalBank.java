package asm03;

import asm02.Account;
import asm02.Bank;
import asm02.Customer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DigitalBank extends Bank {

    private Scanner sc = new Scanner(System.in);

    public DigitalBank() {

    }

    //((DigitalCustomer)newCustomer).withdraw() ép kiểu
    public void addCustomer(String customer_name, String customerID) {
        DigitalCustomer newCustomer = new DigitalCustomer();
        newCustomer.setName(customer_name);
        newCustomer.setCustomerId(customerID);
        addCustomer(newCustomer);
    }

    public void addAccSavings(String customerID, String accNum) {
        for (Customer customer : getCustomers()) {
            if (customer.getCustomerId().equals(customerID)) {
                ((DigitalCustomer) customer).addAccSavings(accNum);
            }
        }
    }

    public void addAccLoan(String customerID, String accNum) {
        for (Customer customer : getCustomers()) {
            if (customer.getCustomerId().equals(customerID)) {
                ((DigitalCustomer) customer).addAccLoan(accNum);
            }
        }
    }

    public void getCustomerById(String customerID) {
        int index = findIndexOfCusId(customerID);
        if (isCustomerExisted(customerID)) {
            System.out.println("Kết quả");
            getCustomers().get(index).displayInformation();
        } else System.out.println("Số CCCD không tồn tại. Vui lòng thử lại!");
    }

    //rút tiền
    public void withdraw(String customerID, String accNum) {
        int index = findIndexOfCusId(customerID);
        DigitalCustomer digitalCustomer = (DigitalCustomer) getCustomers().get(index);
        try {
            if (digitalCustomer.isAccExists(accNum)) {
                System.out.print("Nhập số tiền cần rút: ");
                double amount = sc.nextDouble();
                sc.nextLine();
                digitalCustomer.withdraw(accNum, amount);
            } else System.out.println("Không tìm thấy tài khoản " + accNum + " trong hệ thống.");
        } catch (InputMismatchException ime) {
            System.out.println("Số tiền không đúng, vui lòng thử lại!");
        }
    }

    //tra cứu lịch sử
    public void showHistory(String customerID) {
        int index = findIndexOfCusId(customerID);
        DigitalCustomer digitalCustomer = (DigitalCustomer) getCustomers().get(index);
        digitalCustomer.displayInformation();
        System.out.print("Nhập số tài khoản cần tra cứu: ");
        String accNum = sc.nextLine();
        if (digitalCustomer.isAccExists(accNum)) {
            digitalCustomer.showHistory(accNum);
        } else System.out.println("Không tìm thấy tài khoản " + accNum + " trong hệ thống.");
    }
}

package asm03;

import asm02.Account;
import asm02.Bank;
import asm02.Customer;

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

    public void addAccSavings(String customerID, String accNum){
        for (Customer customer : getCustomers()){
            if (customer.getCustomerId().equals(customerID)){
                ((DigitalCustomer)customer).addAccSavings(accNum);
            }
        }
    }

    public void addAccLoan(String customerID, String accNum){
        for (Customer customer : getCustomers()){
            if (customer.getCustomerId().equals(customerID)){
                ((DigitalCustomer)customer).addAccLoan(accNum);
            }
        }
    }
    public void getCustomerById(String customerID){
        int index = findIndexOfCusId(customerID);
        if (isCustomerExisted(customerID)){
            System.out.println("Kết quả");
            getCustomers().get(index).displayInformation();
        }
        else System.out.println("Số CCCD không tồn tại. Vui lòng thử lại!");
    }

    public void withdraw(String customerID, String accNum){
        int index = findIndexOfCusId(customerID);
        DigitalCustomer digitalCustomer = (DigitalCustomer) getCustomers().get(index);
        if (digitalCustomer.isAccExists(accNum)){
            System.out.print("Nhập số tiền cần rút: ");
            double amount = sc.nextDouble();
            digitalCustomer.withdraw(accNum,amount);
        }
        else System.out.println("Không tìm thấy tài khoản "+accNum+" trong hệ thống.");

    }

//    //Tìm số tài khoản nhập từ bàn phím
//    public int findAccNum(String accNum, String customerID){
//        int index = super.findIndexOfCusId(customerID);
//        int check = -1;
//        for (int i = 0; i < super.getCustomers().get(index).getAccounts().size();i++){
//            if(super.getCustomers().get(index).
//                    getAccounts().get(i).getAccountNumber().equals(accNum)){
//                check = i;
//                break;
//            }
//        }
//        return check;
//    }

//    public String getTypeAcc(int indexAcc, String customerID){
//        int indexCus = super.findIndexOfCusId(customerID);
//        return super.getCustomers().get(indexCus).
//                getAccounts().get(indexAcc).getTypeAccount();
//    }
}

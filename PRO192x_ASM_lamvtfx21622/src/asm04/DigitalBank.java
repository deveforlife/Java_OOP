package asm04;

import asm02.Account;
import asm02.Customer;
import asm03.DigitalCustomer;
import asm03.SavingsAccount;
import asm03.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DigitalBank extends asm03.DigitalBank {
    private static Scanner scanner = new Scanner(System.in);
    private List<Customer> customersList = new ArrayList<>(CustomerDao.list());
    private List<Account> accountList = new ArrayList<>(AccountDao.list());
    private List<Transaction> transactionList = new ArrayList<>(TransactionDao.list());

    // hiển thị khách hàng
    public void showCustomers() {
        List<Customer> list = new ArrayList<>(CustomerDao.list());
        if (list.size() == 0){
            System.out.println("Không có khách hàng nào trong danh sách");
        }
        else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println("STT " + (i + 1) + ":");
                list.get(i).displayInformation04();
            }
            System.out.println("----------- Hết danh sách -----------");
        }
    }

    public void addCustomer(String filePath){
        // get custom từ file data
        List<Customer> getCusFromFile = new ArrayList<>(CustomerDao.list());

        // lấy dữ liệu từ file đổ vào list
        List<Customer> newListCus = new ArrayList<>();

        try (BufferedReader file = new BufferedReader(new FileReader(filePath))) {
            String line;
            while (true){
                line = file.readLine();
                if (line == null) {
                    break;
                }
                String getString[] = line.split(",");
                Customer customer = new Customer();
                customer.setCustomerId(getString[0]);
                customer.setName(getString[1]);

                newListCus.add(customer);

            }
        }catch (Exception e){

        }

        //kiểm tra list, gộp list
        if (getCusFromFile.size() == 0) {
            getCusFromFile.addAll(newListCus);
        }
        else {
            for (int i = 0; i < newListCus.size();i++){
                boolean flag = false;
                for (int j = 0; j < getCusFromFile.size();j++){
                    if (newListCus.get(i).getCustomerId().equals(getCusFromFile.get(j).getCustomerId())){
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    System.out.println("Số CCCD "+newListCus.get(i).getCustomerId()+" đã tồn tại.");
                }
                else {
                    getCusFromFile.add(newListCus.get(i));
                    System.out.println("Thêm khách hàng \""+newListCus.get(i).getName()+"\" thành công");
                }
            }
        }

        //lưu lại xuống file
        try {
            CustomerDao.save(getCusFromFile);
            System.out.println("Lưu dữ liệu thành công.");
        }catch (Exception e){

        }
    }

    //thêm tài khoản atm
    public void creatAccountAtm(){


        String inputCCCD = typeCCCD();

        if (getCustomerById(customersList,inputCCCD) != null){
            getCustomerById(customersList,inputCCCD).displayInformation04();

            System.out.print("Nhập số tài khoản ATM muốn tạo: ");
            String newNumberAccount = typeAccountNumber();

            if (newNumberAccount.matches("^[0-9]{6,6}$")) {
                SavingsAccount newAccount = new SavingsAccount();
                newAccount.setAccountNumber(newNumberAccount);
                if (accountList.size() == 0) {
                    newAccount.setBalance(0);
                    newAccount.setCustomerID(inputCCCD);
                    System.out.println("Tạo tài khoản thành công");
                    accountList.add(newAccount);
                    try {
                        AccountDao.save(accountList);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if (!isAccountExisted(accountList,newAccount)){
                    newAccount.setBalance(0);
                    newAccount.setCustomerID(inputCCCD);
                    System.out.println("Tạo tài khoản thành công");
                    accountList.add(newAccount);
                    try {
                        AccountDao.save(accountList);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else System.out.println("Số tài khoản này đã tồn tại.");
            } else System.out.println("Dữ liệu nhập vào không đúng, vui lòng thử lại!");
        }
        else System.out.println("Khách hàng không tồn tại.");
    }

    //tạo giao dịch

    public void createTransaction(String type){
        //- Nhập cccd
        Account account = new Account();
        String inputCCCD = typeCCCD();
        if (getCustomerById(customersList,inputCCCD) != null) {
            getCustomerById(customersList, inputCCCD).displayInformation04();

            List<Account> listAccountByCCCD = getCustomerById(customersList, inputCCCD)
                    .getAccounts(inputCCCD);

            System.out.print("Nhập số tài khoản: ");
            String numberAccount = typeAccountNumber();
            if (listAccountByCCCD.size() == 0){
                System.out.println("Khách hàng chưa có tài khoản.");
            }
            else {
                for (int i = 0; i < listAccountByCCCD.size();i++){
                    if (listAccountByCCCD.get(i).getAccountNumber().equals(numberAccount)){
                        account = listAccountByCCCD.get(i);
                        break;
                    }
                    else account = null;
                }
            }
            if (account != null){
                System.out.print("Nhập số tiền: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                switch (type){
                    case "deposit":
                        ((SavingsAccount) account).deposit(amount);
                        ((SavingsAccount) account).log(amount);
                        AccountDao.update(account);
                        ((SavingsAccount) account).saveTransaction(numberAccount,amount,type,TransactionDao.list());
                        break;
                    case "withdraw":
                        ((SavingsAccount) account).withdraw(amount);
                        ((SavingsAccount) account).log(amount);
                        AccountDao.update(account);
                        ((SavingsAccount) account).saveTransaction(numberAccount,amount,type,TransactionDao.list());
                        break;
                    case "transfer":
                        break;
                    default:
                }
            }
            else System.out.println("Không tìm thấy số tài khoản "+numberAccount);
        }
        else System.out.println("Khách hàng không tồn tại.");
    }

    // hiển thị lịch sử giao dịch
    public void showHistory() {
        // Nhập cccd
        String inputCCCD = typeCCCD();
        if (isCustomerIdExisted(customersList,inputCCCD)){
            getCustomerById(customersList,inputCCCD).displayInformation04();

            // Nhập stk cần show lịch sử
            System.out.print("Nhập số tài khoản: ");
            String accountNumber = typeAccountNumber();
            for (Account account : accountList){
                if (account.getAccountNumber().equals(accountNumber)){
                    ((SavingsAccount) account).showTransactionHistory(account.getTransactions(accountNumber));
                }
                else System.out.println("Số tài khoản không tồn tại.");
            }
        }
        else System.out.println("Không tồn tại khách hàng có CCCD "+inputCCCD);


    }

    //kiểm tra customer
    private boolean isCustomerExisted(List<Customer> customers, Customer newCustomer){
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(newCustomer.getCustomerId())){
                return true;
            }
        }
        return false;
    }

    //kiểm tra account
    private boolean isAccountExisted(List<Account> accounts, Account newAccount){
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(newAccount.getAccountNumber())){
                return true;
            }
        }
        return false;
    }

    //kiểm tra customer = cccd
    private boolean isCustomerIdExisted(List<Customer> customers, String cccd){
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(cccd)){
                return true;
            }
        }
        return false;
    }

    private Customer getCustomerById(List<Customer> customers, String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)){
                return customer;
            }
        }
        return null;
    }

    private String typeCCCD(){
        System.out.print("Nhập số CCCD của bạn: ");
        String inputCCCD = scanner.nextLine();
        return inputCCCD;
    }

    private String typeAccountNumber(){
        String accountNumber = scanner.nextLine();
        return accountNumber;
    }
}

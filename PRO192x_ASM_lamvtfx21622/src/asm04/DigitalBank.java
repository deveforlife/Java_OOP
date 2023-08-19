package asm04;

import asm02.Account;
import asm02.Customer;
import asm03.DigitalCustomer;
import asm03.SavingsAccount;
import asm03.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
                getString[1] = getString[1].replaceAll("\\s\\s+", " ").trim();
                if (getString[0].matches("^[0-9]{12}$") && !getString[1].isBlank()
                        && getString[1].contains(" ") && getString[1].matches("^[a-zA-Z ]{0,30}$")){
                    Customer customer = new Customer();
                    customer.setCustomerId(getString[0]);
                    customer.setName(getString[1]);
                    newListCus.add(customer);
                }
            }
        }catch (Exception e){

        }

        //kiểm tra list, gộp list
        if (getCusFromFile.size() == 0) {

            // lấy các object duy nhất
            List<Customer> uniqueCus = newListCus.stream()
                    .filter(distinctByKey(Customer::getCustomerId))
                    .collect(Collectors.toList());

            getCusFromFile.addAll(uniqueCus);
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
        Account sourceAccount = new Account();
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
                        sourceAccount = listAccountByCCCD.get(i);
                        break;
                    }
                    else sourceAccount = null;
                }
            }
            if (sourceAccount != null){
                System.out.print("Nhập số tiền: ");
                double amount = scanner.nextDouble();
                scanner.nextLine(); // taại sao khi nhập string thì không giữ enter
                switch (type){
                    case "deposit":
                        if (((SavingsAccount) sourceAccount).deposit(amount)){
                            ((SavingsAccount) sourceAccount).log(amount);
                            AccountDao.update(sourceAccount);
                            ((SavingsAccount) sourceAccount).saveTransaction(numberAccount,amount,type,TransactionDao.list());
                        }
                        else System.out.println("Số tiền nộp không hợp lệ!");

                        break;
                    case "withdraw":
                        if (((SavingsAccount) sourceAccount).withdraw(amount)){
                            ((SavingsAccount) sourceAccount).log(amount);
                            AccountDao.update(sourceAccount);
                            ((SavingsAccount) sourceAccount).saveTransaction(numberAccount,-amount,type,TransactionDao.list());
                        }
                        break;
                    case "transfer":
                        Account receivedAccount = new Account();
                        System.out.print("Nhập số tài khoản nhận tiền: ");
                        String receivedAccountNumber = typeAccountNumber();
                        if (isAccountExisted(receivedAccountNumber)){
                            showCustombyAccountNumber(receivedAccountNumber);
                            System.out.print("Xác nhận chuyển tiền (Y/N): ");
                            String comfirm = scanner.nextLine();
                            while (true) {
                                if (comfirm.toUpperCase().equals("Y")){
                                    //trừ tiền trong tài khoản nguồn + update số dư
                                    if (((SavingsAccount) sourceAccount).withdraw(amount)){
                                        ((SavingsAccount) sourceAccount).log(amount,type,receivedAccountNumber);
                                        AccountDao.update(sourceAccount);
                                        ((SavingsAccount) sourceAccount).saveTransaction(numberAccount,-amount,type,TransactionDao.list());
                                    }

                                    //Cộng tiền tài khoản nhận
                                    for (int i = 0; i < accountList.size();i++){
                                        if (accountList.get(i).getAccountNumber().equals(receivedAccountNumber)){
                                            receivedAccount = accountList.get(i);
                                            break;
                                        }
                                    }
                                    if (((SavingsAccount) receivedAccount).deposit(amount)) {
                                        AccountDao.update(receivedAccount);
                                        ((SavingsAccount) receivedAccount).
                                                saveTransaction(receivedAccountNumber,amount,type,TransactionDao.list());
                                    }
                                    break;
                                }
                                else if (comfirm.toUpperCase().equals("N")) {
                                    System.out.println("Giao dịch thất bại.");
                                    break;
                                }
                                else System.out.println("Xác nhận thất bại, vui lòng nhập Y (YES) hoặc N (NO).");
                            }
                        }
                        else System.out.println("Số tài khoản nhận không đúng, vui lòng kiểm tra lại");
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
            if (!isAccountExisted(accountNumber))
                System.out.println("Số tài khoản không tồn tại.");
            else {
                for (Account account : accountList){
                    if (account.getAccountNumber().equals(accountNumber)){
                        ((SavingsAccount) account).
                                showTransactionHistory(account.getTransactions(accountNumber));
                    }
                }
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
    private boolean isAccountExisted(String accountNumber){
        for (Account account : accountList) {
            if (account.getAccountNumber().equals(accountNumber)){
                return true;
            }
        }
        return false;
    }

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

    //show ra thông tin người nhận - và số tài khoản đã nhập
    private void showCustombyAccountNumber(String accountNumber){
        for (Account account : accountList){
            if (account.getAccountNumber().equals(accountNumber)){
                System.out.println("--------- THÔNG TIN NGUƯỜI NHẬN ---------");
                System.out.println("CHỦ TÀI KHOẢN: "+getCustomerById(customersList,account.getCustomerID()).getName().toUpperCase());
                System.out.println("SỐ TÀI KHOẢN: "+accountNumber);
                System.out.println("-----------------------------------------");
                break;
            }
        }
    }

    // viết lại hàm predicate để lấy các object duy nhất
    public <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor){
        Set<T> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add((T) keyExtractor.apply(t));
    }
}

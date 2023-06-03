package asm02;

import asm01.EnterCccd;

import java.util.*;

public class Bank {
    private String id;
    private List<Customer> customers;

    private static Scanner sc = new Scanner(System.in);

    public Bank() {
        this.id = String.valueOf(UUID.randomUUID());
        this.customers = new ArrayList<>();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    //Phương thức nhập dữ liệu khách hàng từ bàn phím
    public Customer insertInfo() {
        Customer newCustomer = new Customer();

        //Nhập tên khách hàng từ bàn phím
        int countSpace = 0;
        while (countSpace == 0) {
            System.out.print("Nhập họ và tên khách hàng (không dấu): ");
            String inputName = sc.nextLine();
            inputName = inputName.replaceAll("\\s\\s+", " ").trim();
            for (int i = 0; i < inputName.length(); i++) {
                if (inputName.charAt(i) == ' ') {
                    countSpace++;
                }
            }

            //regex "^[\\p{L} ]{0,30}$" -> dùng cho tất cả các ngôn ngữ bao gồm cả tiếng việt
            //regex "^[a-zA-Z ]{0,30}$" -> dùng cho ngôn ngữ english bao gồm dấu cách
            // Kiểm tra fullname
            if (countSpace != 0 && inputName.matches("^[a-zA-Z ]{0,30}$")) {
                newCustomer.setName(inputName);
                break;
            } else {
                System.out.println("Dữ liệu không đúng, vui lòng nhập lại!");
                countSpace = 0;
            }
        }

        //Nhập CCCD của khách hàng từ bàn phím
        EnterCccd insertID = new EnterCccd();
        newCustomer.setCustomerId(insertID.nhapCanCuoc());
        return newCustomer;
    }

    public void addCustomer(Customer newCustomer) {
        if (customers.size() == 0) {
            customers.add(newCustomer);
            System.out.println("Thêm khách hàng thành công");
        } else {
            //System.out.println("Cần kiểm tra ID");
            if (!isCustomerExisted(newCustomer.getCustomerId())) {
                customers.add(newCustomer);
                System.out.println("Thêm khách hàng thành công");
            } else {
                System.out.println("Khách hàng đã tồn tại!");
            }
        }
    }

    public void addAccount(Account newAccount, String customerId) {
        boolean checkAccountNumber = false;
        int indexOfCus = findIndexOfCusId(customerId);
        // kiểm tra stk đã tồn tại hay chưa
        for (int i = 0; i < customers.size(); i++) {
            for (int j = 0; j < customers.get(i).getAccounts().size(); j++) {
                if (newAccount.getAccountNumber().equals
                        (customers.get(i).getAccounts().get(j).getAccountNumber())) {
                    checkAccountNumber = true;
                }
            }
        }
        if (checkAccountNumber) {
            System.out.println("Số tài khoản " + newAccount.getAccountNumber() + " đã tồn tại! " +
                    "Vui lòng chọn số tài khoản khác");
        } else {
            customers.get(indexOfCus).addAccount(newAccount);
        }
    }

    public boolean isCustomerExisted(String customerId) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId().equals(customerId)) {
                return true;
            }
        }
        return false;
    }

    public int findIndexOfCusId(String customerId) {
        int check = -1;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId().equals(customerId)) {
                check = i;
            }
        }
        return check;
    }

    // Tìm kiếm tên khách hàng
    public ArrayList searchCustomerByName(String keyword) {
        ArrayList<Integer> result = new ArrayList<>();
        String checkKeyword;
        for (int i = 0; i < customers.size(); i++) {
            checkKeyword = customers.get(i).getName().substring(customers.get(i).getName().length()
                    - keyword.length());
            if (checkKeyword.toUpperCase().equals(keyword.toUpperCase())) {
                result.add(i);
            }
        }
        return result;
    }

    public void searchByCusID(String keyword) {
        if (isCustomerExisted(keyword)) {
            System.out.println("Kết quả:");
            int index = findIndexOfCusId(keyword);
            customers.get(index).displayInformation();
        } else {
            System.out.println("Không tìm thấy khách hàng có số CCCD "
                    + keyword + " trong hệ thống!");
        }
    }
}

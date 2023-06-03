package asm02;

import asm01.AuthenRobot;
import asm01.CheckAndResult;
import asm01.EnterCccd;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAs2 {
    private Scanner sc = new Scanner(System.in);
    private static final Bank bank = new Bank();

    private EnterCccd enterCccd = new EnterCccd();

    public void showMenu() {
        System.out.println("+-------------+----------------------------------------+-------------+");
        System.out.println("|   NGAN HANG SO    |   fx21622@v2.0.0                               |");
        mainMenu();
    }

    private void mainMenu() {
        int selection;
        do {
            System.out.println("+-------------+----------------------------------------+-------------+");
            System.out.println("|   1. Thêm khách hàng                                               |");
            System.out.println("|   2. Thêm tài khoản khách hàng                                     |");
            System.out.println("|   3. Hiển thị danh sách khách hàng                                 |");
            System.out.println("|   4. Tìm khách hàng theo số CCCD                                   |");
            System.out.println("|   5. Tìm theo tên khách hàng                                       |");
            System.out.println("|   0. Thoát                                                         |");
            System.out.println("+-------------+----------------------------------------+-------------+");
            System.out.print("Chọn chức năng: ");
            try {
                selection = sc.nextInt();
                switch (selection) {
                    case 1:
                        System.out.println("Chức năng 1: Thêm khách hàng");
                        bank.addCustomer(bank.insertInfo());
                        break;
                    case 2:
                        System.out.println("Chức năng 2: Thêm tài khoản khách hàng");
                        sc.nextLine();
                        String inputCustomerID;

                        // Kiểm tra số CCCD nhập có tồn tại trong hệ thống hay không?
                        // Nếu tồn tại -> thoát while và thực hiện tiếp chương trình
                        do {
                            inputCustomerID = enterCccd.nhapCanCuoc();
                            if (!bank.isCustomerExisted(inputCustomerID)) {
                                System.out.println("Số CCCD không tồn tại. Vui lòng thử lại!");
                            }
                        } while (!bank.isCustomerExisted(inputCustomerID));

                        // Nhập thông tin STK, số dư
                        Account newAccount = new Account();
                        String accountNumber;
                        do {
                            System.out.print("Tạo số tài khoản ngân hàng: ");
                            accountNumber = sc.nextLine();
                            if (accountNumber.matches("^[0-9]+$")) {
                                newAccount.setAccountNumber(accountNumber);
                            } else {
                                System.out.println("Dữ liệu nhập vào không đúng, vui lòng thử lại!");
                            }
                        } while (!accountNumber.matches("^[0-9]+$"));

                        //Khởi tạo phương thức addAccount
                        bank.addAccount(newAccount, inputCustomerID);
                        break;
                    case 3:
                        System.out.println("Chức năng 3: Danh sách khách hàng");
                        for (int i = 0; i < bank.getCustomers().size(); i++) {
                            System.out.println("STT " + (i + 1) + ":");
                            bank.getCustomers().get(i).displayInformation();
                        }
                        break;
                    case 4:
                        System.out.println("Chức năng 4: Tìm khách hàng theo số CCCD");
                        String findCusID = enterCccd.nhapCanCuoc();
                        bank.searchByCusID(findCusID);
                        break;
                    case 5:
                        System.out.println("Chức năng 5: Tìm khách hàng theo tên");
                        sc.nextLine();
                        System.out.print("Nhập tên khách hàng cần tìm (không dấu): ");
                        String searchName = sc.nextLine();
                        searchName = searchName.replaceAll("\\s\\s+"," ").trim();
                        ArrayList<Integer> indexResult = bank.searchCustomerByName(searchName);

                        // Lưu index của tất cả Customer có tên trùng với dữ liệu
                        if (indexResult.size() > 0) {
                            System.out.println("Kết quả:");
                            for (int i = 0; i < indexResult.size(); i++) {
                                bank.getCustomers().get(i).displayInformation();
                            }
                        }
                        else {
                            System.out.println("Không tìm thấy khách hàng có tên \""+searchName+"\"");
                        }
                        break;
                    case 0:
                        System.out.println("Xin chào và hẹn gặp lại");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Chức năng không tồn tại, vui lòng thử lại!");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Bạn nhập sai thông tin, vui lòng thử lại!");
                sc.nextLine();
                selection = -1;
            }
        } while (selection != 0);
    }
}

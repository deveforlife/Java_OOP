package asm03;

import asm01.EnterCccd;
import asm02.Account;
import asm02.Bank;
import asm02.Customer;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAs3 {
    private Scanner sc = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String customer_ID = "001215000001";
    private static final String customer_name = "Hoang Lan";

    public void showMenu() {
        activeBank.addCustomer(customer_name, customer_ID);
        System.out.println("+-------------+----------------------------------------+-------------+");
        System.out.println("|   NGAN HANG SO    |   fx21622@v3.0.0                               |");
        mainMenu();
    }

    private void mainMenu() {
        int selection;
        do {
            System.out.println("+-------------+----------------------------------------+-------------+");
            System.out.println("|   1. Thông tin khách hàng                                          |");
            System.out.println("|   2. Thêm tài khoản ATM                                            |");
            System.out.println("|   3. Thêm tài khoản tín dụng                                       |");
            System.out.println("|   4. Rút tiền                                                      |");
            System.out.println("|   5. Lịch sử giao dịch                                             |");
            System.out.println("|   0. Thoát                                                         |");
            System.out.println("+-------------+----------------------------------------+-------------+");
            System.out.print("Chọn chức năng: ");
            try {
                selection = sc.nextInt();
                String accNum;
                switch (selection) {
                    case 1:
                        System.out.println("Chức năng 1: Thông tin khách hàng");
                        String inputCCCD = new EnterCccd().nhapCanCuoc();
                        activeBank.getCustomerById(inputCCCD);
                        break;
                    case 2:
                        System.out.println("Chức năng 2: Thêm tài khoản ATM");
                        sc.nextLine();
                        do {
                            System.out.print("Tạo số tài khoản ATM: ");
                            accNum = sc.nextLine();
                            if (accNum.matches("^[0-9]{6,6}$")) {
                                break;
                            } else System.out.println("Dữ liệu nhập vào không đúng, vui lòng thử lại!");
                        } while (!accNum.matches("^[0-9]{6,6}$"));
                        activeBank.addAccSavings(customer_ID, accNum);
                        break;
                    case 3:
                        System.out.println("Chức năng 3: Thêm tài khoản tín dụng");
                        sc.nextLine();
                        do {
                            System.out.print("Tạo số tài khoản ATM: ");
                            accNum = sc.nextLine();
                            if (accNum.matches("^[0-9]{6,6}$")) {
                                break;
                            } else System.out.println("Dữ liệu nhập vào không đúng, vui lòng thử lại!");
                        } while (!accNum.matches("^[0-9]{6,6}$"));
                        activeBank.addAccLoan(customer_ID, accNum);
                        break;
                    case 4:
                        System.out.println("Chức năng 4: Rút tiền");
                        sc.nextLine();
                        System.out.print("Nhập số tài khoản cần rút tiền: ");
                        accNum = sc.nextLine();
                        if (accNum.matches("^[0-9]{6,6}$")) {
                            activeBank.withdraw(customer_ID, accNum);
                        } else System.out.println("Dữ liệu nhập vào không đúng, vui lòng thử lại!");
                        break;
                    case 5:
                        System.out.println("Chức năng 5: Lịch sử giao dịch");
                        activeBank.showHistory(customer_ID);
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

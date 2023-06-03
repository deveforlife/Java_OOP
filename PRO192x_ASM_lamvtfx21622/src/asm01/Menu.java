package asm01;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private Scanner sc = new Scanner(System.in);
    public void showMenu() {
        System.out.println("+-------------+----------------------------------------+-------------+");
        System.out.println("|   NGAN HANG SO    |   fx21622@v1.0.0                               |");
        mainMenu();
    }

    private void mainMenu() {
        int selection;
        do {
            System.out.println("+-------------+----------------------------------------+-------------+");
            System.out.println("|   1. Nhập CCCD                                                     |");
            System.out.println("|   0. Thoát                                                         |");
            System.out.println("+-------------+----------------------------------------+-------------+");
            System.out.print("Chọn chức năng: ");
            try {
                selection = sc.nextInt();
                switch (selection) {
                    case 1:
                        // Nhập mã xác thực
                        AuthenRobot art = new AuthenRobot();
                        String securCode = art.generateSecurityCode();
                        System.out.println("Mã xác thực: " + securCode);
                        boolean check = false;
                        while (!check){
                            check = art.verifySecurCode(securCode);
                        }
                        EnterCccd chucNang1 = new EnterCccd();
                        String soCccd = chucNang1.nhapCanCuoc();
                        if (soCccd.equals("No")) {
                            selection = 0;
                            System.out.println("Xin chào và hẹn gặp lại");
                        }
                        else {
                            subMenu1(soCccd);
                        }
                        break;
                    case 0:
                        System.out.println("Xin chào và hẹn gặp lại");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Chức năng không tồn tại, vui lòng thử lại!");
                }
            }
            catch (InputMismatchException ime) {
                System.out.println("Bạn nhập sai thông tin, vui lòng thử lại!");
                sc.next();
                selection = -1;
            }
        } while (selection != 0);
    }

    public void subMenu1 (String soCccd) {
        CheckAndResult car = new CheckAndResult();
        int selection = 0;
        do {
            System.out.println("CCCD đã nhập là: " + soCccd);
            System.out.println("|       1. Kiểm tra nơi sinh                                         |");
            System.out.println("|       2. Kiểm tra tuổi, giới tính                                  |");
            System.out.println("|       3. Kiểm tra số ngẫu nhiên                                    |");
            System.out.println("|       0. Quay lại danh mục trước                                   |");
            System.out.print("Chọn chức năng: ");
            try {
                selection = sc.nextInt();
                switch (selection) {
                    case 1:
                        System.out.println("+-------------+----------------------------------------+-------------+");
                        car.placeOfBirth(soCccd);
                        System.out.println("+-------------+----------------------------------------+-------------+");
                        break;
                    case 2:
                        System.out.println("+-------------+----------------------------------------+-------------+");
                        car.getGioitinh(soCccd);
                        car.calAge(soCccd);
                        System.out.println("+-------------+----------------------------------------+-------------+");
                        break;
                    case 3:
                        System.out.println("+-------------+----------------------------------------+-------------+");
                        car.soNgauNhien(soCccd);
                        System.out.println("+-------------+----------------------------------------+-------------+");
                        break;
                    case 0:
                        mainMenu();
                        break;
                    default:
                        System.out.println("Chức năng không tồn tại, vui lòng thử lại!");
                }
            }
            catch (InputMismatchException ime) {
                System.out.println("Bạn nhập sai thông tin, vui lòng thử lại!");
            }
        } while (selection != 0);
    }
}

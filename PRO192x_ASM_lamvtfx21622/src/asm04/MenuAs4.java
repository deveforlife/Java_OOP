package asm04;

import asm01.EnterCccd;
import asm02.User;
import asm03.DigitalBank;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuAs4 {
    private Scanner sc = new Scanner(System.in);

    public void showMenu() {
        System.out.println("+-------------+----------------------------------------+-------------+");
        System.out.println("|   NGAN HANG SO    |   fx21622@v4.0.0                               |");
        mainMenu();
    }

    private void mainMenu() {
        int selection;
        do {
            System.out.println("+-------------+----------------------------------------+-------------+");
            System.out.println("|   1. Xem danh sách khách hàng                                      |");
            System.out.println("|   2. Nhập danh sách khách hàng                                     |");
            System.out.println("|   3. Thêm tài khoản ATM                                            |");
            System.out.println("|   4. Chuyển tiền                                                   |");
            System.out.println("|   5. Rút tiền                                                      |");
            System.out.println("|   6. Nộp tiền                                                      |");
            System.out.println("|   7. Tra cứu lịch sử giao dịch                                     |");
            System.out.println("|   0. Thoát                                                         |");
            System.out.println("+-------------+----------------------------------------+-------------+");
            System.out.print("Chọn chức năng: ");
            try {
                selection = sc.nextInt();
                String accNum;
                switch (selection) {
                    case 1:
                        System.out.println("Chức năng 1: Xem danh sách khách hàng");

                        break;
                    case 2:
                        List<User04> users = new ArrayList<>();
                        System.out.println("Chức năng 2: Nhập danh sách khách hàng");
                        System.out.println("Nhập đường dẫn (folder\\file.txt): ");
                        sc.nextLine();
                        String path = sc.nextLine();
                        path = path.replace("\\", "\\\\");
                        TextFileService tfs = new TextFileService();
                        users = tfs.readFile(path);

                        for (User04 user : users){
                            System.out.println(user.getName());
                            System.out.println(user.getCustomerId());
                        }


                        break;
                    case 3:
                        System.out.println("Chức năng 3: Thêm tài khoản ATM");

                        break;
                    case 4:
                        System.out.println("Chức năng 4: Chuyển tiền");

                        break;
                    case 5:
                        System.out.println("Chức năng 5: Rút tiền");

                        break;
                    case 6:
                        System.out.println("Chức năng 6: Nộp tiền");

                        break;
                    case 7:
                        System.out.println("Chức năng 7: Tra cứu lịch sử giao dịch");

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

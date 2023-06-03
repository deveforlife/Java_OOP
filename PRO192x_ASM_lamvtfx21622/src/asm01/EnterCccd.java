package asm01;

import java.util.Scanner;

public class EnterCccd {

    public String nhapCanCuoc () {
        Scanner sc = new Scanner(System.in);
        String soCccd = new String();
        boolean flag = true;

        while (flag) {
            try {
                System.out.print("Nhập số CCCD: ");
                soCccd = sc.nextLine();

                if (soCccd.equals("No")) {
                    flag = false;
                    return "No";
                }
                else if (soCccd.matches("^[0-9]{12}$")) {
                        flag = false;
                }
                else {
                    System.out.println("CCCD không hợp lệ, hãy nhập lại");
                }
            }
            catch (NumberFormatException nfe) {
                System.out.println("CCCD không hợp lệ, hãy nhập lại");
            }
        }
        return soCccd;
    }
 }

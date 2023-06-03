package asm01;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class AuthenRobot {
    public String generateSecurityCode() {
        int length = 6;
        String finalString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        char[] password = new char[length];
        for (int i = 0; i < length; i++) {
            password[i] = finalString.charAt(random.nextInt(finalString.length()));
        }
        String securCode = String.valueOf(password);
        return securCode;
    }

    public boolean verifySecurCode(String securCode) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã xác thực: ");
        String textInput = sc.next();
        boolean check = textInput.equals(securCode);
        if (check) {
            System.out.println("Xác thực thành công!");
            return true;
        } else {
            System.out.println("Mã xác thực không đúng, vui lòng thử lại.");
            return false;
        }
    }
}

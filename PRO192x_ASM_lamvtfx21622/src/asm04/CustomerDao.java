package asm04;

import asm02.Customer;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class CustomerDao extends Customer implements Serializable {

    private final static String FILE_PATH = "D:\\BOOK\\JAVA\\Java_OOP\\PRO192x_ASM_lamvtfx21622\\" +
            "src\\store\\customers.dat";

    public CustomerDao() {

    }

    public static void save(List<Customer> customers) throws IOException {
        TextFileService.writeFile(FILE_PATH, customers);
    }

    public static List<Customer> list(){
        return TextFileService.readFile(FILE_PATH);
    }


}

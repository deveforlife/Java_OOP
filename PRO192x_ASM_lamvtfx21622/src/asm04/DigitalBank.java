package asm04;

import asm02.Customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DigitalBank extends asm03.DigitalBank {
    public void addCustomer(String filePath){
        // lấy dữ liệu từ file đổ vào list
        List<Customer> newListCus = new ArrayList<>();
        Customer customer = new Customer();

        try (BufferedReader file = new BufferedReader(new FileReader(filePath))) {
            String line;
            while (true){
                line = file.readLine();
                if (line == null) {
                    break;
                }
                String getString[] = line.split(",");
                customer.setCustomerId(getString[0]);
                customer.setName(getString[1]);
                newListCus.add(customer);
            }
        }catch (Exception e){

        }

        //---------------------
    }

}

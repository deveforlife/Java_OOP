package asm04;

import asm02.Customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DigitalBank extends asm03.DigitalBank {

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
                Customer customer = new Customer();
                customer.setCustomerId(getString[0]);
                customer.setName(getString[1]);

                newListCus.add(customer);

            }
        }catch (Exception e){

        }

        //kiểm tra list, gộp list
        if (getCusFromFile.size() == 0) {
            getCusFromFile.addAll(newListCus);
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

}

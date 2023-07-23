package asm04;

import asm02.Customer;
import asm02.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextFileService {
    private static String pathSave = "\\src\\store\\customers.dat";

    // ghi dữ liệu xuống file
    public void writeFile(List<CustomerDao> users) {
        try {
            FileOutputStream fos = new FileOutputStream(pathSave);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (User user : users) {
                oos.writeObject(user);
            }
            oos.close();
            fos.close();
        }
        catch (Exception e){

        }
    }

    // Đọc dữ liệu file
    public List<CustomerDao> readFile(String path) {
        List<CustomerDao> listCustomer = new ArrayList<>();
        CustomerDao customerDao = new CustomerDao();
        try {
            File file = new File(path);
            String line = null;
            if (!checkPath(path)) {
                System.out.println("Vui lòng thử lại!");
            }
            else {
                BufferedReader br = Files.newBufferedReader(file.toPath());
                while (checkFileStructure(path)) {
                    line = br.readLine();
                    if (line == null) {
                        break;
                    } else {
                        String array[] = line.split(",");
                        String cccd = array[0];
                        String name = array[1].replaceAll("\\s\\s+", " ").trim();
                        customerDao.setName(name);
                        customerDao.setCustomerId(cccd);
                        listCustomer.add(customerDao);
                    }
                }
            }
        } catch (Exception e) {

        }
        return listCustomer;
    }

    //kiểm tra path
    private boolean checkPath(String path) {
        boolean status =true;
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("File không tồn tại.");
            status = false;
        }
        else if (!file.isFile()) {
            System.out.println("Đường dẫn không đúng!");
            status = false;
        }
        else {
            System.out.println("Cảnh báo: File không có dữ liệu !!!");
            status = false;
        }
        return status;
    }

    // kiểm tra cấu trúc file ds
    private boolean checkFileStructure(String path) {
        boolean status = true;
        try {
            File file = new File(path);
            BufferedReader br = Files.newBufferedReader(file.toPath());
            String line = null;
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                } else {
                    String array[] = line.split(",");
                    String cccd = array[0];
                    String name = array[1].replaceAll("\\s\\s+", " ").trim();
                    int countSpace = 0;
                    for (int i = 0; i < name.length(); i++) {
                        if (name.charAt(i) == ' ') {
                            countSpace++;
                        }
                    }
                    if (!cccd.matches("^[0-9]{12}$") || countSpace == 0
                            || !name.matches("^[a-zA-Z ]{0,30}$")){
                        status = false;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            status = false;
        }
        return status;
    }
}

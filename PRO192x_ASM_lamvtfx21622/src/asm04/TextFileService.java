package asm04;

import asm02.Customer;
import asm02.User;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextFileService {


    // ghi dữ liệu xuống file
    public static <T> void writeFile(String filepath, List<T> objects) {
        try (ObjectOutputStream file = new ObjectOutputStream(new BufferedOutputStream
                (new FileOutputStream(filepath)))) {
            for (T object : objects) {
                file.writeObject(object);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Đọc dữ liệu object file
    public static <T> List<T> readFile(String filepath) {
        List<T> objects = new ArrayList<>();
        try (ObjectInputStream file = new ObjectInputStream(new BufferedInputStream
                (new FileInputStream(filepath)))) {
            boolean eof = false;
            while (!eof) {
                try {
                    T object = (T) file.readObject();
                    objects.add(object);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (IOException io) {
            System.out.println("IO Exception " + io.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException " + e.getMessage());
        }
        return objects;
    }

    //Kiểm tra filepath
    public static boolean checkFilePath(String filepath){
        File file = new File(filepath);
        if(!file.isFile()){
            // là file return true
            System.out.println("File không tồn tại, vui lòng thử lại!");
            return false;
        }
        else if (file.length() == 0) {
            //file not empty -> có data -> return true
            System.out.println("File không có dữ liệu, vui lòng thử lại");
            return false;
        }
        return true;
    }
}

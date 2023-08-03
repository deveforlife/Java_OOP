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
        try (ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(filepath))) {
            for (T object : objects) {
                file.writeObject(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Đọc dữ liệu text file
    public static <T> List<T> readFile(String filepath) {
        List<T> objects = new ArrayList<>();
        try (ObjectInputStream file = new ObjectInputStream(new FileInputStream(filepath))) {
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



}

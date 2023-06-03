package asm01;


import Data.IdentData;
import java.time.LocalDate;

public class CheckAndResult {
    public void placeOfBirth (String soCccd) {
        String idCityNumber = soCccd.substring(0,3);
        IdentData datas =new IdentData();
        System.out.println("Nơi sinh: "+datas.getCityNameByIdCityNumber(idCityNumber));
    }

    public void getGioitinh (String soCccd) {
        String gioiTinh = soCccd.substring(3,4);
        int convGioiTinh = Integer.parseInt(gioiTinh);
        if (convGioiTinh % 2 == 0) {
            System.out.println("Giới tính: Nam");
        }
        else {
            System.out.println("Giới tính: Nữ");
        }
    }

    public void calAge (String soCccd) {
        String last2ofnamSinh = soCccd.substring(4,6);
        String gioiTinh = soCccd.substring(3,4);
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int namSinh = 0;

        switch (Integer.parseInt(gioiTinh)) {
            case 0: case 1:
                namSinh = Integer.parseInt("19" + last2ofnamSinh);
                break;
            case 2:  case 3:
                namSinh = Integer.parseInt("20" + last2ofnamSinh);
                break;
            case 4: case 5:
                namSinh = Integer.parseInt("21" + last2ofnamSinh);
                break;
            case 6: case 7:
                namSinh = Integer.parseInt("22" + last2ofnamSinh);
                break;
            case 8:  case 9:
                namSinh = Integer.parseInt("23" + last2ofnamSinh);
                break;
            default:
                break;
        }
        if (year > namSinh) {
            int tuoi = year - namSinh;
            if (tuoi < 122)
            {
                System.out.println("Tuổi: " + tuoi);
            }
            else {
                System.out.println("Người nhiều tuổi nhất thế giới đạt 122 tuổi,\n " +
                        "bạn hãy đăng ký kỷ lục thế giới ngay ^_^");
            }
        }
        else {
            System.out.println("Năm sinh không hợp lệ, vui lòng kiểm tra lại");
        }
    }

    public void soNgauNhien (String soCccd) {
        String soNgauNhien = soCccd.substring(6);
        System.out.println("Số ngẫu nhiên: "+soNgauNhien);
    }
}

package Data;

import java.util.ArrayList;
import java.util.List;

public class IdentData {
    private List<String> idCityNumberArr;
    private List<String> cityNameArr;

    public IdentData() {
        this.idCityNumberArr = new ArrayList<>();
        this.cityNameArr = new ArrayList<>();
        // Dữ liệu CCCD
        cityNameArr.add("Hà Nội"); idCityNumberArr.add("001");
        cityNameArr.add("Hà Giang"); idCityNumberArr.add("002");
        cityNameArr.add("Cao Bằng"); idCityNumberArr.add("004");
        cityNameArr.add("Bắc Kạn"); idCityNumberArr.add("006");
        cityNameArr.add("Tuyên Quang"); idCityNumberArr.add("008");
        cityNameArr.add("Lào Cai"); idCityNumberArr.add("010");
        cityNameArr.add("Điện Biên"); idCityNumberArr.add("011");
        cityNameArr.add("Lai Châu"); idCityNumberArr.add("012");
        cityNameArr.add("Sơn La"); idCityNumberArr.add("014");
        cityNameArr.add("Yên Bái"); idCityNumberArr.add("015");
        cityNameArr.add("Hòa Bình"); idCityNumberArr.add("017");
        cityNameArr.add("Thái Nguyên"); idCityNumberArr.add("019");
        cityNameArr.add("Lạng Sơn"); idCityNumberArr.add("020");
        cityNameArr.add("Quảng Ninh"); idCityNumberArr.add("022");
        cityNameArr.add("Bắc Giang"); idCityNumberArr.add("024");
        cityNameArr.add("Phú Thọ"); idCityNumberArr.add("025");
        cityNameArr.add("Vĩnh Phúc"); idCityNumberArr.add("026");
        cityNameArr.add("Bắc Ninh"); idCityNumberArr.add("027");
        cityNameArr.add("Hải Dương"); idCityNumberArr.add("030");
        cityNameArr.add("Hải Phòng"); idCityNumberArr.add("031");
        cityNameArr.add("Hưng Yên"); idCityNumberArr.add("033");
        cityNameArr.add("Thái Bình"); idCityNumberArr.add("034");
        cityNameArr.add("Hà Nam"); idCityNumberArr.add("035");
        cityNameArr.add("Nam Định"); idCityNumberArr.add("036");
        cityNameArr.add("Ninh Bình"); idCityNumberArr.add("037");
        cityNameArr.add("Thanh Hóa"); idCityNumberArr.add("038");
        cityNameArr.add("Nghệ An"); idCityNumberArr.add("040");
        cityNameArr.add("Hà Tĩnh"); idCityNumberArr.add("042");
        cityNameArr.add("Quảng Bình"); idCityNumberArr.add("044");
        cityNameArr.add("Quảng Trị"); idCityNumberArr.add("045");
        cityNameArr.add("Thừa Thiên Huế"); idCityNumberArr.add("046");
        cityNameArr.add("Đà Nẵng"); idCityNumberArr.add("048");
        cityNameArr.add("Quảng Nam"); idCityNumberArr.add("049");
        cityNameArr.add("Quảng Ngãi"); idCityNumberArr.add("051");
        cityNameArr.add("Bình Định"); idCityNumberArr.add("052");
        cityNameArr.add("Phú Yên"); idCityNumberArr.add("054");
        cityNameArr.add("Khánh Hòa"); idCityNumberArr.add("056");
        cityNameArr.add("Ninh Thuận"); idCityNumberArr.add("058");
        cityNameArr.add("Bình Thuận"); idCityNumberArr.add("060");
        cityNameArr.add("Kon Tum"); idCityNumberArr.add("062");
        cityNameArr.add("Gia Lai"); idCityNumberArr.add("064");
        cityNameArr.add("Đắk Lắk"); idCityNumberArr.add("066");
        cityNameArr.add("Đắk Nông"); idCityNumberArr.add("067");
        cityNameArr.add("Lâm Đồng"); idCityNumberArr.add("068");
        cityNameArr.add("Bình Phước"); idCityNumberArr.add("070");
        cityNameArr.add("Tây Ninh"); idCityNumberArr.add("072");
        cityNameArr.add("Bình Dương"); idCityNumberArr.add("074");
        cityNameArr.add("Đồng Nai"); idCityNumberArr.add("075");
        cityNameArr.add("Bà Rịa - Vũng Tàu"); idCityNumberArr.add("077");
        cityNameArr.add("Hồ Chí Minh"); idCityNumberArr.add("079");
        cityNameArr.add("Long An"); idCityNumberArr.add("080");
        cityNameArr.add("Tiền Giang"); idCityNumberArr.add("082");
        cityNameArr.add("Bến Tre"); idCityNumberArr.add("083");
        cityNameArr.add("Trà Vinh"); idCityNumberArr.add("084");
        cityNameArr.add("Vĩnh Long"); idCityNumberArr.add("086");
        cityNameArr.add("Đồng Tháp"); idCityNumberArr.add("087");
        cityNameArr.add("An Giang"); idCityNumberArr.add("089");
        cityNameArr.add("Kiên Giang"); idCityNumberArr.add("091");
        cityNameArr.add("Cần Thơ"); idCityNumberArr.add("092");
        cityNameArr.add("Hậu Giang"); idCityNumberArr.add("093");
        cityNameArr.add("Sóc Trăng"); idCityNumberArr.add("094");
        cityNameArr.add("Bạc Liêu"); idCityNumberArr.add("095");
        cityNameArr.add("Cà Mau"); idCityNumberArr.add("096");
    }

    public String getCityNameByIdCityNumber(String idCityNumber) {
        try {
            return this.cityNameArr.get(this.idCityNumberArr.indexOf(idCityNumber));
        }
        catch (IndexOutOfBoundsException ioe) {
            return "Không tìm thấy Tỉnh / Thành Phố phù hợp với dữ liệu!";
        }
    }
}

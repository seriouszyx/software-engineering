package presentation.controller.impl.hotel;

import businessLogic.impl.HotelBlServiceImpl;
import businessLogic.service.HotelBlService;
import common.ResultMessage;
import presentation.controller.service.hotel.HotelInfoViewControllerService;
import vo.HotelVo;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public class HotelInfoViewControllerImpl implements HotelInfoViewControllerService {
    private static HotelInfoViewControllerService INSTANCE = new HotelInfoViewControllerImpl();
    private String hotelId;
    private HotelBlService hotelBlService;
    private HotelVo hotel;

    private HotelInfoViewControllerImpl(){
        hotelBlService = HotelBlServiceImpl.getInstance();
    }

    public static HotelInfoViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
        hotel = hotelBlService.getHotelVo(hotelId);
    }

    @Override
    public String getHotelName() {
        return hotel.getHotelName();
    }

    @Override
    public String getHotelAddress() {
        return hotel.getHotelPosition();
    }

    @Override
    public String getHotelDiscription() {
        return hotel.getHotelInfo();
    }

    @Override
    public String getHotelPoint() {
        return String.valueOf(hotel.getPoStrings());
    }

    @Override
    public String getHotelStar() {
        return String.valueOf(hotel.getStars());
    }

    @Override
    public String getHotelPhone() {
        return hotel.getHotelTel();
    }

    @Override
    public void setHotelName(String hotelName) {
        hotel.setHotelName(hotelName);
    }

    @Override
    public void setHotelAddress(String hotelAddress) {
        hotel.setHotelPosition(hotelAddress);
    }

    @Override
    public void setHotelDiscription(String hotelDiscription) {
        hotel.setHotelInfo(hotelDiscription);
    }

    @Override
    public void setHotelPhone(String hotelPhone) {
        hotel.setHotelTel(hotelPhone);
    }

    @Override
    public void setHotelStar(String hotelStar) {
        hotel.setStars(Integer.valueOf(hotelStar));
    }

    @Override
    public ResultMessage updateInfo() {
        return hotelBlService.updateBasicInfo(hotel);
    }
}

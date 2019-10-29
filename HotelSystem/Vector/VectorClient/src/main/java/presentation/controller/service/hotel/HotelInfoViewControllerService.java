package presentation.controller.service.hotel;

import common.ResultMessage;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public interface HotelInfoViewControllerService {

    void setHotelId(String hotelId);

    String getHotelName();

    String getHotelAddress();

    String getHotelDiscription();

    String getHotelPoint();

    String getHotelStar();

    String getHotelPhone();

    void setHotelName(String hotelName);

    void setHotelAddress(String hotelAddress);

    void setHotelDiscription(String hotelDiscription);

    void setHotelPhone(String hotelPhone);

    void setHotelStar(String hotelStar);

    ResultMessage updateInfo();
}

package presentation.controller.service.hotel;

import common.ResultMessage;
import common.RoomType;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public interface HotelRoomViewControllerService {

    ResultMessage initializeRoom(RoomType type, int number, int price);

    ResultMessage checkoutRoom(RoomType type, int number);

    ResultMessage checkinRoom(RoomType type, int number);

    void setHotelId(String hotelId);

}

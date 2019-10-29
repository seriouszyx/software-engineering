package presentation.controller.service.member;

import common.RoomType;

import java.util.List;

/**
 * @author Molloh
 * @version 2017/1/1
 * @description 客户查看酒店信息界面的controller
 */
public interface MemberHotelInfoViewControllerService {

    void setHotelId(String hotelId);

    //hotel信息的get/set方法

    String getHotelName();

    String getHotelStar();

    String getHotelPoint();

    String getHotelDiscription();

    String getHotelRoomPrice(RoomType T);

    String getRoomNum(RoomType T);

    List<String> getComment();

}

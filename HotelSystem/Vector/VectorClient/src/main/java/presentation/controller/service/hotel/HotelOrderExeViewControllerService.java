package presentation.controller.service.hotel;

import common.OrderCondition;
import common.ResultMessage;
import common.RoomType;

/**
 * @author Molloh
 * @version 2016/12/25
 * @description
 */
public interface HotelOrderExeViewControllerService {

    void setOrderId(String orderId);

    ResultMessage delayOrder();

    ResultMessage revokeOrder();

    ResultMessage checkIn();

    OrderCondition getOrderCondition();

    String getMemberId();

    String getMemberName();

    String getCreateTime();

    String getCheckInTime();

    String getCheckOutTime();

    String getHotelName();

    String getHotelId();

    RoomType getRoomType();

    int getNumOfRoom();

    int getNumOfGuest();

    boolean getChildExist();

    int getOriginalPrice();

    double getDiscount();

    double getDiscountedPrice();

}

package presentation.controller.service.member;

import common.OrderCondition;
import common.ResultMessage;
import common.RoomType;

import java.util.Date;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description Member Order信息控制器接口
 */
public interface MemberOrderInfoViewControllerService {

    void setOrderId(String orderId);

    ResultMessage deleteOrder();

    //Order get方法

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

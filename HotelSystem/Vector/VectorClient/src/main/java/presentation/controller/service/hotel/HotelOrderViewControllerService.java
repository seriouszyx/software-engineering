package presentation.controller.service.hotel;

import common.OrderCondition;
import vo.OrderVo;

import java.util.List;

/**
 * @author Molloh
 * @version 2016/12/25
 * @description
 */
public interface HotelOrderViewControllerService {

    void setMemberId(String memberId);

    //所有订单
    List<OrderVo> getAllOrders();

    //订单状态筛选订单
    List<OrderVo> getOrdersInConditionByHotel(OrderCondition condition);

    OrderVo findOrder(String orderId);

}

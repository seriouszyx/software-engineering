package presentation.controller.impl.hotel;

import businessLogic.impl.OrderBlServiceImpl;
import businessLogic.service.OrderBlService;
import common.OrderCondition;
import presentation.controller.service.hotel.HotelOrderViewControllerService;
import vo.OrderVo;

import java.util.List;

/**
 * @author Molloh
 * @version 2016/12/25
 * @description
 */
public class HotelOrderViewControllerImpl implements HotelOrderViewControllerService {
    private static HotelOrderViewControllerService INSTANCE = new HotelOrderViewControllerImpl();

    private String hotelId;
    private OrderBlService order;

    private HotelOrderViewControllerImpl() {
        order = OrderBlServiceImpl.getInstance();
    }

    public static HotelOrderViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public void setMemberId(String hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public List<OrderVo> getAllOrders() {
        return order.getAllOrdersByHotel(hotelId);
    }

    @Override
    public List<OrderVo> getOrdersInConditionByHotel(OrderCondition condition) {
        return order.getOrdersInConditionByHotel(hotelId, condition);
    }

    @Override
    public OrderVo findOrder(String orderId) {
        return order.getOrder(orderId);
    }
}

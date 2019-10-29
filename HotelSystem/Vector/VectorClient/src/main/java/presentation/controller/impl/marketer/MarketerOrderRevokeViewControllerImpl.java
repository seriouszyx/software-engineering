package presentation.controller.impl.marketer;

import businessLogic.impl.OrderBlServiceImpl;
import businessLogic.service.OrderBlService;
import common.OrderCondition;
import common.ResultMessage;
import common.RoomType;
import presentation.controller.service.marketer.MarketerOrderRevokeViewControllerService;
import vo.OrderVo;

import java.text.SimpleDateFormat;

/**
 * @author Molloh
 * @version 2016/12/25
 * @description
 */
public class MarketerOrderRevokeViewControllerImpl implements MarketerOrderRevokeViewControllerService {
    private static MarketerOrderRevokeViewControllerService INSTANCE = new MarketerOrderRevokeViewControllerImpl();

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private OrderBlService order;
    private OrderVo orderVo;

    private String orderId;

    private MarketerOrderRevokeViewControllerImpl() {
        order = OrderBlServiceImpl.getInstance();
    }

    public static MarketerOrderRevokeViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public void setOrderId(String orderId) {
        this.orderId = orderId;
        orderVo = order.getOrder(orderId);
    }

    @Override
    public ResultMessage revoke(double d) {
        return order.revoke(orderId, d);
    }

    @Override
    public String getOrderCondition() {
        return String.valueOf(orderVo.getCondition());
    }

    @Override
    public String getMemberId() {
        return orderVo.getMemberId();
    }

    @Override
    public String getMemberName() {
        return orderVo.getMemberName();
    }

    @Override
    public String getCreateTime() {
        return formatter.format(orderVo.getCreateTime());
    }

    @Override
    public String getCheckInTime() {
        return formatter.format(orderVo.getCheckInTime());
    }

    @Override
    public String getCheckOutTime() {
        return formatter.format(orderVo.getCheckOutTime());
    }

    @Override
    public String getHotelName() {
        return orderVo.getHotel();
    }

    @Override
    public String getHotelId() {
        return orderVo.getHotelId();
    }

    @Override
    public RoomType getRoomType() {
        return orderVo.getRoomType();
    }

    @Override
    public int getNumOfRoom() {
        return orderVo.getNumOfRoom();
    }

    @Override
    public int getNumOfGuest() {
        return orderVo.getNumOfGuest();
    }

    @Override
    public boolean getChildExist() {
        return orderVo.getChildExist();
    }

    @Override
    public int getOriginalPrice() {
        return orderVo.getOriginalPrice();
    }

    @Override
    public double getDiscount() {
        return orderVo.getDiscount();
    }

    @Override
    public double getDiscountedPrice() {
        return orderVo.getDiscountedPrice();
    }

    @Override
    public OrderCondition getOrderState() {
        return orderVo.getCondition();
    }
}

package presentation.controller.impl.marketer;

import businessLogic.impl.OrderBlServiceImpl;
import businessLogic.service.OrderBlService;
import presentation.controller.service.marketer.MarketerOrderViewControllerService;
import vo.OrderVo;

import java.util.List;

/**
 * @author Molloh
 * @version 2016/12/25
 * @description
 */
public class MarketerOrderViewControllerImpl implements MarketerOrderViewControllerService {
    private static MarketerOrderViewControllerService INSTANCE = new MarketerOrderViewControllerImpl();

    private OrderBlService order;

    private MarketerOrderViewControllerImpl() {
        order = OrderBlServiceImpl.getInstance();
    }

    public static MarketerOrderViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<OrderVo> getNotExecutedOrderByWebSite() {
        return order.getNotExecutedOrderByWebSite();
    }

    @Override
    public List<OrderVo> getAbnormalByWebSite() {
        return order.getAbnormalByWebSite();
    }

    @Override
    public OrderVo findOrder(String orderId) {
        return order.getOrder(orderId);
    }
}

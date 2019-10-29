package presentation.controller.service.marketer;

import vo.OrderVo;

import java.util.List;

/**
 * @author Molloh
 * @version 2016/12/25
 * @description
 */
public interface MarketerOrderViewControllerService {

    List<OrderVo> getNotExecutedOrderByWebSite();

    List<OrderVo> getAbnormalByWebSite();

    OrderVo findOrder(String orderId);

}

package presentation.controller.service.member;

import common.OrderCondition;
import vo.OrderVo;

import java.util.List;

/**
 * @author Molloh
 * @version 2016/12/25
 * @description 客户订单列表界面控制器接口
 */
public interface MemberOrderViewControllerService {

    void setMemberId(String memberId);

    //所有订单
    List<OrderVo> getAllOrders();

    //订单状态筛选订单
    List<OrderVo> getOrdersInConditionByMember(OrderCondition condition);

}

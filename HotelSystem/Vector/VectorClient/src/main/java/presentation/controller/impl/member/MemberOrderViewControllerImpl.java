package presentation.controller.impl.member;

import businessLogic.impl.OrderBlServiceImpl;
import businessLogic.service.OrderBlService;
import common.OrderCondition;
import presentation.controller.service.member.MemberOrderViewControllerService;
import vo.OrderVo;

import java.util.List;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description 客户订单列表界面控制器接口实现类
 */
public class MemberOrderViewControllerImpl implements MemberOrderViewControllerService {
    private String memberId;
    private static MemberOrderViewControllerService INSTANCE = new MemberOrderViewControllerImpl();
    private OrderBlService order;

    private MemberOrderViewControllerImpl() {
        order = OrderBlServiceImpl.getInstance();
    }

    @Override
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public static MemberOrderViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<OrderVo> getAllOrders() {
        return order.getAllOrdersByMember(memberId);
    }

    @Override
    public List<OrderVo> getOrdersInConditionByMember(OrderCondition condition) {
        return order.getOrdersInConditionByMember(memberId, condition);
    }

}

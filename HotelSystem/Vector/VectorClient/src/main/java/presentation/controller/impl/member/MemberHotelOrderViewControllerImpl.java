package presentation.controller.impl.member;

import businessLogic.impl.OrderBlServiceImpl;
import businessLogic.service.OrderBlService;
import common.ResultMessage;
import common.RoomType;
import presentation.controller.service.member.MemberHotelOrderViewControllerService;

/**
 * @author Molloh
 * @version 2016/12/25
 * @description 提交界面的controller接口实现类
 */
public class MemberHotelOrderViewControllerImpl implements MemberHotelOrderViewControllerService {
    private static MemberHotelOrderViewControllerService INSTANCE = new MemberHotelOrderViewControllerImpl();

    private OrderBlService order;

    private MemberHotelOrderViewControllerImpl() {
        order = OrderBlServiceImpl.getInstance();
    }

    public static MemberHotelOrderViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public ResultMessage submit(String memberId, String planCheckInTimeStr, String hoteId, int numOfDays,
                                RoomType roomType, int numOfRoom, int numOfGuest, boolean childExist) {
        return order.submit(memberId, planCheckInTimeStr, hoteId, numOfDays, roomType, numOfRoom, numOfGuest, childExist);
    }
}

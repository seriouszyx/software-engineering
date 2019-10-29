package presentation.controller.service.member;

import common.ResultMessage;
import common.RoomType;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description 提交界面的controller接口
 */
public interface MemberHotelOrderViewControllerService {
    ResultMessage submit(String memberId, String planCheckInTimeStr, String hoteId, int numOfDays,
                         RoomType roomType, int numOfRoom, int numOfGuest, boolean childExist);

}

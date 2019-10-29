package presentation.controller.service.marketer;

import common.ResultMessage;
import vo.ActivityPromotionVo;
import vo.BirthdayProVo;
import vo.CompanyProVo;
import vo.RoomPromotionVo;

import java.util.List;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public interface MarketerPromotionViewControllerService {

    List<ActivityPromotionVo> getCurrentActStrategy();

    ResultMessage delActivityStrategy(ActivityPromotionVo vo);

    ResultMessage upActivityStrategy(ActivityPromotionVo vo);

    ResultMessage addActivityStrategy(ActivityPromotionVo vo);

    ResultMessage updateCooperationStrategy(CompanyProVo vo);

    ResultMessage updateOrderRoomStrategy(RoomPromotionVo vo);

    ResultMessage upBirthStrategy(BirthdayProVo vo);

}

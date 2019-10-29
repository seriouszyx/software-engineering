package presentation.controller.impl.marketer;

import businessLogic.impl.MarketPromotionBlServiceImpl;
import businessLogic.service.MarketPromotionBlService;
import common.ResultMessage;
import presentation.controller.service.marketer.MarketerPromotionViewControllerService;
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
public class MarketerPromotionViewControllerImpl implements MarketerPromotionViewControllerService {
    private static MarketerPromotionViewControllerService INSTANCE = new MarketerPromotionViewControllerImpl();

    private MarketPromotionBlService promotion;

    private MarketerPromotionViewControllerImpl() {
        promotion = MarketPromotionBlServiceImpl.getInstance();
    }

    public static MarketerPromotionViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<ActivityPromotionVo> getCurrentActStrategy() {
        return promotion.getCurrentActStrategy();
    }

    @Override
    public ResultMessage delActivityStrategy(ActivityPromotionVo vo) {
        return promotion.delActivityStrategy(vo);
    }

    @Override
    public ResultMessage upActivityStrategy(ActivityPromotionVo vo) {
        return null;
    }

    @Override
    public ResultMessage addActivityStrategy(ActivityPromotionVo vo) {
        return null;
    }

    @Override
    public ResultMessage updateCooperationStrategy(CompanyProVo vo) {
        return null;
    }

    @Override
    public ResultMessage updateOrderRoomStrategy(RoomPromotionVo vo) {
        return null;
    }

    @Override
    public ResultMessage upBirthStrategy(BirthdayProVo vo) {
        return null;
    }
}

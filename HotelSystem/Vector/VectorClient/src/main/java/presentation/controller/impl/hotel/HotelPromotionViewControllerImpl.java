package presentation.controller.impl.hotel;

import businessLogic.impl.HotelPromotionBlServiceImpl;
import businessLogic.service.HotelPromotionBlService;
import common.ResultMessage;
import presentation.controller.service.hotel.HotelPromotionViewControllerService;
import vo.ActivityPromotionVo;
import vo.BirthdayProVo;
import vo.CompanyProVo;
import vo.RoomPromotionVo;

import java.util.List;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description PromotionViewControllerService接口的实现类
 */
public class HotelPromotionViewControllerImpl implements HotelPromotionViewControllerService {
    private String hotelId;
    private static HotelPromotionViewControllerService INSTANCE = new HotelPromotionViewControllerImpl();

    private HotelPromotionBlService promotion;

    private HotelPromotionViewControllerImpl() {
        promotion = HotelPromotionBlServiceImpl.getInstance();
    }

    public static HotelPromotionViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public List<ActivityPromotionVo> getCurrentActStrategy() {
        return promotion.getCurrentActStrategy(hotelId);
    }

    @Override
    public ResultMessage delActivityStrategy(ActivityPromotionVo vo) {
        return promotion.delActivityStrategy(hotelId, vo);
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

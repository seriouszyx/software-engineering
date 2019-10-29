package presentation.controller.impl.hotel;

import businessLogic.impl.HotelPromotionBlServiceImpl;
import businessLogic.service.HotelPromotionBlService;
import common.HotelPromotionType;
import common.ResultMessage;
import presentation.common.SingletonItem;
import presentation.controller.service.hotel.HotelProAddViewControllerService;
import vo.ActivityPromotionVo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description Hotel 新增促销策略控制器接口实现类
 */
public class HotelProAddViewControllerImpl implements HotelProAddViewControllerService {
        private static HotelProAddViewControllerService INSTANCE = new HotelProAddViewControllerImpl();

        private HotelPromotionBlService hotelPromotionBlService;

        private ActivityPromotionVo vo;

        private HotelProAddViewControllerImpl() {
            vo = new ActivityPromotionVo(null, null, null, 0);
            hotelPromotionBlService = HotelPromotionBlServiceImpl.getInstance();
        }

        public static HotelProAddViewControllerService getInstance() {
            return INSTANCE;
        }

        @Override
        public void setPromotionType(HotelPromotionType type) {

        }

        @Override
        public void setPromotionName(String name) {
            vo.setPromotionName(name);
        }

        @Override
        public void setStartDate(LocalDate date) {
            vo.setStartDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        @Override
        public void setEndDate(LocalDate date) {
            vo.setEndDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        @Override
        public void setDiscount(String dis) {
            vo.setDiscount(Double.valueOf(dis));
        }

        @Override
        public ResultMessage update() {
            return hotelPromotionBlService.addActivityStrategy(SingletonItem.getInstance().getActivateId(), vo);
        }
}

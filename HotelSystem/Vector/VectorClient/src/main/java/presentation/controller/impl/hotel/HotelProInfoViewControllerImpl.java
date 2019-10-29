package presentation.controller.impl.hotel;

import businessLogic.impl.HotelPromotionBlServiceImpl;
import businessLogic.service.HotelPromotionBlService;
import common.ResultMessage;
import presentation.common.SingletonItem;
import presentation.controller.service.hotel.HotelProInfoViewControllerService;
import vo.ActivityPromotionVo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description
 */
public class HotelProInfoViewControllerImpl implements HotelProInfoViewControllerService {
    private static HotelProInfoViewControllerService INSTANCE = new HotelProInfoViewControllerImpl();
    private HotelPromotionBlService hotelPromotionBlService;
    private ActivityPromotionVo vo;

    private HotelProInfoViewControllerImpl() {
        hotelPromotionBlService = HotelPromotionBlServiceImpl.getInstance();
    }

    public static HotelProInfoViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public void setPromotionVo(ActivityPromotionVo vo) {
        this.vo = vo;
    }

    @Override
    public String getPromotionType() {
        return vo.getPromotionType().name();
    }

    @Override
    public String getPromotionName() {
        return vo.getPromotionName();
    }

    @Override
    public LocalDate getStartDate() {
        return vo.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @Override
    public LocalDate getEndDate() {
        return vo.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @Override
    public String getDiscount() {
        return String.valueOf(vo.getDiscount());
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
        return hotelPromotionBlService.upActivityStrategy(SingletonItem.getInstance().getActivateId(), vo);
    }

    @Override
    public ResultMessage delete() {
        return hotelPromotionBlService.delActivityStrategy(SingletonItem.getInstance().getActivateId(), vo);
    }
}

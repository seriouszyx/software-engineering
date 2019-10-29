package presentation.controller.impl.marketer;

import businessLogic.impl.MarketPromotionBlServiceImpl;
import businessLogic.service.MarketPromotionBlService;
import common.HotelPromotionType;
import common.ResultMessage;
import presentation.controller.service.marketer.MarketerProAddViewControllerService;
import vo.ActivityPromotionVo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description Marketer 新增策略界面控制器实现类
 */
public class MarketerProAddViewControllerImpl implements MarketerProAddViewControllerService {
    private static MarketerProAddViewControllerService INSTANCE = new MarketerProAddViewControllerImpl();

    private MarketPromotionBlService MarketerPromotionBlService;

    private ActivityPromotionVo vo;

    private MarketerProAddViewControllerImpl() {
        vo = new ActivityPromotionVo(null, null, null, 0);
        MarketerPromotionBlService = MarketPromotionBlServiceImpl.getInstance();
    }

    public static MarketerProAddViewControllerService getInstance() {
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
        return MarketerPromotionBlService.addActivityStrategy(vo);
    }
}

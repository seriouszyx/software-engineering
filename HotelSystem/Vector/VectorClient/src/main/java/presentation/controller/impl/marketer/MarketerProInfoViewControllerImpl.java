package presentation.controller.impl.marketer;

import businessLogic.impl.MarketPromotionBlServiceImpl;
import businessLogic.service.MarketPromotionBlService;
import common.ResultMessage;
import presentation.controller.service.marketer.MarketerProInfoViewControllerService;
import vo.ActivityPromotionVo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public class MarketerProInfoViewControllerImpl implements MarketerProInfoViewControllerService {
    private static MarketerProInfoViewControllerService INSTANCE = new MarketerProInfoViewControllerImpl();
    private MarketPromotionBlService promotionBlService;
    private ActivityPromotionVo vo;

    private MarketerProInfoViewControllerImpl() {
        promotionBlService = MarketPromotionBlServiceImpl.getInstance();
    }

    public static MarketerProInfoViewControllerService getInstance() {
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
        return promotionBlService.upActivityStrategy(vo);
    }

    @Override
    public ResultMessage delete() {
        return promotionBlService.delActivityStrategy(vo);
    }
}

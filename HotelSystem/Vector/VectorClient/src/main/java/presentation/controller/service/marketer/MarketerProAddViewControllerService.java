package presentation.controller.service.marketer;

import common.HotelPromotionType;
import common.ResultMessage;

import java.time.LocalDate;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description Marketer 新增策略界面控制器
 */
public interface MarketerProAddViewControllerService {

    void setPromotionType(HotelPromotionType type);

    void setPromotionName(String name);

    void setStartDate(LocalDate date);

    void setEndDate(LocalDate date);

    void setDiscount(String dis);

    ResultMessage update();

}

package presentation.controller.service.hotel;

import common.HotelPromotionType;
import common.ResultMessage;

import java.time.LocalDate;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description Hotel 新增促销策略控制器接口
 */
public interface HotelProAddViewControllerService {

    void setPromotionType(HotelPromotionType type);

    void setPromotionName(String name);

    void setStartDate(LocalDate date);

    void setEndDate(LocalDate date);

    void setDiscount(String dis);

    ResultMessage update();

}

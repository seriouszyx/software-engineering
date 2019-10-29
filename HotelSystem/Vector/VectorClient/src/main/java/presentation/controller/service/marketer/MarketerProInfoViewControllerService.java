package presentation.controller.service.marketer;

import common.ResultMessage;
import vo.ActivityPromotionVo;

import java.time.LocalDate;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public interface MarketerProInfoViewControllerService {

    void setPromotionVo(ActivityPromotionVo vo);

    String getPromotionType();

    String getPromotionName();

    LocalDate getStartDate();

    LocalDate getEndDate();

    String getDiscount();

    void setPromotionName(String name);

    void setStartDate(LocalDate date);

    void setEndDate(LocalDate date);

    void setDiscount(String dis);

    ResultMessage update();

    ResultMessage delete();

}

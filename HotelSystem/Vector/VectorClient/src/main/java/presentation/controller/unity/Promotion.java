package presentation.controller.unity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import vo.ActivityPromotionVo;

import java.util.Date;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description 促销策略类型的property类，用于tableview的数据导入
 */
public class Promotion {
    private final SimpleObjectProperty<ActivityPromotionVo> promotionName;
    private final SimpleObjectProperty<Date> promotionStartDate;
    private final SimpleObjectProperty<Date> promotionEndDate;
    private final SimpleDoubleProperty promotionDiscount;

    public Promotion(ActivityPromotionVo activityPromotionVo, Date promotionStartDate, Date promotionEndDate, double promotionDiscount) {
        this.promotionName = new SimpleObjectProperty<>(activityPromotionVo);
        this.promotionStartDate = new SimpleObjectProperty<>(promotionStartDate);
        this.promotionEndDate = new SimpleObjectProperty<>(promotionEndDate);
        this.promotionDiscount = new SimpleDoubleProperty(promotionDiscount);
    }

    public SimpleObjectProperty<ActivityPromotionVo> promotionNameProperty() {
        return promotionName;
    }

    public SimpleObjectProperty<Date> promotionStartDateProperty() {
        return promotionStartDate;
    }

    public SimpleObjectProperty<Date> promotionEndDateProperty() {
        return promotionEndDate;
    }

    public SimpleDoubleProperty promotionDiscountProperty() {
        return promotionDiscount;
    }
}

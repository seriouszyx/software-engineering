package presentation.view.unity;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import presentation.common.SingletonItem;
import presentation.controller.impl.hotel.HotelPromotionViewControllerImpl;
import presentation.controller.service.hotel.HotelPromotionViewControllerService;
import vo.ActivityPromotionVo;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/25
 * @description
 */
public class PromotionView implements Initializable {
    @FXML
    private TextField proName_field;
    @FXML
    private TextField proDiscount_field;
    @FXML
    private DatePicker proStart_date;
    @FXML
    private DatePicker proEnd_date;
    @FXML
    private Label proType_choice;

    private ActivityPromotionVo promotionVo;
    private HotelPromotionViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = HotelPromotionViewControllerImpl.getInstance();
        controller.setHotelId(SingletonItem.getInstance().getHotelId());
    }

    public void initModify() {
        promotionVo = SingletonItem.getInstance().getActivityPromotionVo();
        proName_field.setText(promotionVo.getPromotionName());
        proType_choice.setText(promotionVo.getPromotionType().name());
        proStart_date.setValue(promotionVo.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        proEnd_date.setValue(promotionVo.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        proDiscount_field.setText(String.valueOf(promotionVo.getDiscount()));

        proName_field.setEditable(false);
        proDiscount_field.setEditable(false);
        proStart_date.setEditable(false);
        proEnd_date.setEditable(false);

    }

    public void initCreate() {
        proName_field.setText("");
        proStart_date.setValue(null);
        proEnd_date.setValue(null);
        proDiscount_field.setText("");
    }

    @FXML
    private void handleEdit() {
        proName_field.setEditable(true);
        proDiscount_field.setEditable(true);
        proStart_date.setEditable(true);
        proEnd_date.setEditable(true);
    }

    @FXML
    private void handleModify() {
        promotionVo.setDiscount(Double.valueOf(proDiscount_field.getText()));
        promotionVo.setStartDate(Date.from(proStart_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        promotionVo.setEndDate(Date.from(proEnd_date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        promotionVo.setPromotionName(proName_field.getText());

        controller.upActivityStrategy(promotionVo);
    }


}

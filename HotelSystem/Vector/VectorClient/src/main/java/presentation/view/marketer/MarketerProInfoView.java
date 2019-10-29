package presentation.view.marketer;

import common.ResultMessage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.impl.marketer.MarketerProInfoViewControllerImpl;
import presentation.controller.service.marketer.MarketerProInfoViewControllerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/7
 * @description
 */
public class MarketerProInfoView implements Initializable {
    @FXML
    private AnchorPane missionPane;

    @FXML
    private Label proType_label;
    @FXML
    private TextField name_field;
    @FXML
    private TextField discount_field;
    @FXML
    private DatePicker start_date;
    @FXML
    private DatePicker end_date;

    private MarketerProInfoViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = MarketerProInfoViewControllerImpl.getInstance();
        controller.setPromotionVo(SingletonItem.getInstance().getActivityPromotionVo());

        proType_label.setText(controller.getPromotionType());
        name_field.setText(controller.getPromotionName());
        discount_field.setText(controller.getDiscount());
        start_date.setValue(controller.getStartDate());
        end_date.setValue(controller.getEndDate());
    }

    @FXML
    private void handleModifyPromotion() {
        controller.setPromotionName(name_field.getText());
        controller.setDiscount(discount_field.getText());
        controller.setStartDate(start_date.getValue());
        controller.setEndDate(end_date.getValue());

        ResultMessage msg = controller.update();
        popUp(msg, "修改促销策略成功！");
        handleCancel();
    }

    @FXML
    private void handleCancel() {
        try {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.MarketerPromotion_View_Path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDelete() {
        controller.delete();
        handleCancel();
    }

    private void popUp(ResultMessage msg, String a) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tips");
        alert.setHeaderText("");
        if(msg == ResultMessage.SUCCEED) {
            alert.setContentText(a);
            alert.showAndWait();
        }else {
            alert.setContentText("失败！");
            alert.showAndWait();
        }
    }
}

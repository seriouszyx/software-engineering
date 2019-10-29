package presentation.view.hotel;

import common.ResultMessage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import presentation.common.ViewFxmlPath;
import presentation.controller.impl.hotel.HotelProAddViewControllerImpl;
import presentation.controller.service.hotel.HotelProAddViewControllerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/7
 * @description
 */
public class HotelProAddView implements Initializable {
    @FXML
    private AnchorPane missionPane;

    //@FXML
    //private ChoiceBox<HotelPromotionType> type_choice;

    @FXML
    private TextField name_field;
    @FXML
    private TextField discount_field;
    @FXML
    private DatePicker start_date;
    @FXML
    private DatePicker end_date;

    private HotelProAddViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = HotelProAddViewControllerImpl.getInstance();

    }

    @FXML
    private void handleConfirm() {
        //controller.setPromotionType(type_choice.getValue());
        controller.setPromotionName(name_field.getText());
        controller.setDiscount(discount_field.getText());
        controller.setStartDate(start_date.getValue());
        controller.setEndDate(end_date.getValue());

        ResultMessage msg = controller.update();
        popUp(msg, "新增促销策略成功！");
        handleCancel();
    }

    @FXML
    private void handleCancel() {
        try {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.HotelPromotion_View_Path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

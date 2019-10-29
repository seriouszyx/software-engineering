package presentation.view.hotel;

import common.ResultMessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import presentation.common.SingletonItem;
import presentation.controller.impl.hotel.HotelInfoViewControllerImpl;
import presentation.controller.service.hotel.HotelInfoViewControllerService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/7
 * @description
 */
public class HotelInfoView implements Initializable {
    @FXML
    private TextField name_field;
    @FXML
    private TextField address_field;
    @FXML
    private TextField phone_field;

    @FXML
    private ComboBox<String> star_combo;

    @FXML
    private Label id_label;
    @FXML
    private Label point_label;

    @FXML
    private TextArea discription_area;

    private HotelInfoViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = HotelInfoViewControllerImpl.getInstance();
        String hotelId = SingletonItem.getInstance().getActivateId();
        controller.setHotelId(hotelId);

        star_combo.getItems().addAll("1", "2", "3", "4", "5");
        discription_area.setPrefRowCount(5);

        name_field.setText(controller.getHotelName());
        address_field.setText(controller.getHotelAddress());
        phone_field.setText(controller.getHotelPhone());
        star_combo.setValue(controller.getHotelStar());
        id_label.setText(hotelId);
        point_label.setText(controller.getHotelPoint());
        discription_area.setText(controller.getHotelDiscription());
    }

    @FXML
    private void handleModify() {
        controller.setHotelName(name_field.getText());
        controller.setHotelAddress(address_field.getText());
        controller.setHotelDiscription(discription_area.getText());
        controller.setHotelPhone(phone_field.getText());
        controller.setHotelStar(star_combo.getValue());

        ResultMessage msg = controller.updateInfo();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tips");
        alert.setHeaderText("");
        if(msg == ResultMessage.SUCCEED) {
            alert.setContentText("修改成功！");
            alert.showAndWait();
        }else {
            alert.setContentText("修改失败！");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleCancelMission() {
        name_field.setText(controller.getHotelName());
        address_field.setText(controller.getHotelAddress());
        phone_field.setText(controller.getHotelPhone());
        star_combo.setValue(controller.getHotelStar());
        discription_area.setText(controller.getHotelDiscription());
    }

}

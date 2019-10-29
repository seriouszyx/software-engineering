package presentation.view.hotel;

import common.ResultMessage;
import common.RoomType;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import presentation.common.SingletonItem;
import presentation.controller.impl.hotel.HotelRoomViewControllerImpl;
import presentation.controller.impl.member.MemberHotelInfoViewControllerImpl;
import presentation.controller.service.hotel.HotelRoomViewControllerService;
import presentation.controller.service.member.MemberHotelInfoViewControllerService;
import presentation.view.unity.StyleUnity;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.SynchronousQueue;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description 酒店更新房间界面
 */
public class HotelRoomView implements Initializable {
    @FXML
    private ChoiceBox<String> mission_choice;
    @FXML
    private ComboBox<RoomType> roomType_combo;
    @FXML
    private TextField num_field;
    @FXML
    private Label price_label;
    @FXML
    private TextField price_field;

    @FXML
    private Label single_label;
    @FXML
    private Label singleNum_label;
    @FXML
    private Label double_label;
    @FXML
    private Label doubleNum_label;
    @FXML
    private Label family_label;
    @FXML
    private Label familyNum_label;

    private HotelRoomViewControllerService helper;
    private MemberHotelInfoViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        helper = HotelRoomViewControllerImpl.getInstance();
        helper.setHotelId(SingletonItem.getInstance().getActivateId());
        controller = MemberHotelInfoViewControllerImpl.getInstance();
        controller.setHotelId(SingletonItem.getInstance().getActivateId());
        initRoom();
        price_field.setVisible(false);
        price_label.setVisible(false);

        StyleUnity.numeric(num_field);
        StyleUnity.numeric(price_field);

        mission_choice.getItems().addAll("更新入住", "更新退房", "设置房间数量");
        roomType_combo.getItems().addAll(RoomType.SINGLE, RoomType.DOUBLE, RoomType.FAMILY);
        mission_choice.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    String command = mission_choice.getItems().get(new_val.intValue());
                    if(command.equals("设置房间数量")){
                        price_field.setVisible(true);
                        price_label.setVisible(true);
                    }else {
                        price_field.setVisible(false);
                        price_label.setVisible(false);
                    }
                }
        );
    }

    private void initRoom() {
        single_label.setText("￥" + controller.getHotelRoomPrice(RoomType.SINGLE));
        double_label.setText("￥" + controller.getHotelRoomPrice(RoomType.DOUBLE));
        family_label.setText("￥" + controller.getHotelRoomPrice(RoomType.FAMILY));

        singleNum_label.setText(controller.getRoomNum(RoomType.SINGLE) + "间");
        doubleNum_label.setText(controller.getRoomNum(RoomType.DOUBLE) + "间");
        familyNum_label.setText(controller.getRoomNum(RoomType.FAMILY) + "间");
    }

    //处理更新房间信息事件
    @FXML
    private void handleUpdate() {
        if(price_label.isVisible() && price_field.isVisible()) {
            RoomType type = roomType_combo.getValue();
            ResultMessage msg;
            if(type != null) {
                msg = helper.initializeRoom(type, Integer.valueOf(num_field.getText()), Integer.valueOf(price_field.getText()));
                popUp(msg);
                initRoom();
            }
        }else {
            String command = mission_choice.getValue();
            ResultMessage msg = ResultMessage.FAIL;
            if(command.equals("更新入住")) {
                msg = helper.checkinRoom(roomType_combo.getValue(), Integer.valueOf(num_field.getText()));
            }else if(command.equals("更新退房")) {
                msg = helper.checkoutRoom(roomType_combo.getValue(), Integer.valueOf(num_field.getText()));
            }
            popUp(msg);
        }

        initRoom();
    }

    @FXML
    private void handleClear() {
        mission_choice.setValue("");
        roomType_combo.setValue(null);
        num_field.clear();

    }

    //弹出窗口
    private void popUp(ResultMessage msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tips");
        alert.setHeaderText("");
        if(msg == ResultMessage.SUCCEED) {
            alert.setContentText("更新成功！");
            alert.showAndWait();
        }else {
            alert.setContentText("更新失败！");
            alert.showAndWait();
        }
    }
}

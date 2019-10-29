package presentation.view.member;

import common.ResultMessage;
import common.RoomType;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.impl.member.MemberHotelInfoViewControllerImpl;
import presentation.controller.impl.member.MemberHotelOrderViewControllerImpl;
import presentation.controller.service.member.MemberHotelInfoViewControllerService;
import presentation.controller.service.member.MemberHotelOrderViewControllerService;
import presentation.view.unity.StyleUnity;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description Member 提交订单界面
 */
public class MemberHotelOrderView implements Initializable {
    @FXML
    private AnchorPane missionPane;

    @FXML
    private TextField roomNum_field;
    @FXML
    private ChoiceBox<RoomType> roomType_choice;
    @FXML
    private TextField peopleNum_field;
    @FXML
    private TextField stayDays_field;

    @FXML
    private DatePicker checkInTime_date;

    @FXML
    private Label price_label;

    @FXML
    private RadioButton hasChild_radio;

    private MemberHotelOrderViewControllerService controller;
    private SimpleIntegerProperty price;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StyleUnity.numeric(peopleNum_field);
        StyleUnity.numeric(stayDays_field);
        StyleUnity.numeric(roomNum_field);
        controller = MemberHotelOrderViewControllerImpl.getInstance();
        MemberHotelInfoViewControllerService tempController = MemberHotelInfoViewControllerImpl.getInstance();
        tempController.setHotelId(SingletonItem.getInstance().getHotelId());

        roomType_choice.getItems().addAll(RoomType.SINGLE, RoomType.DOUBLE, RoomType.FAMILY);
        /*
        int roomNum = 0;
        if(roomNum_field.getText() != "") {
            roomNum = Integer.valueOf(roomNum_field.getText());
        }
        int peopleNum = 0;
        if(peopleNum_field.getText() != null) {
            peopleNum = Integer.valueOf(peopleNum_field.getText());
        }
        int dayNum = 0;
        if(stayDays_field.getText() != null) {
            dayNum = Integer.valueOf(stayDays_field.getText());
        }*/

        /*
        roomType_choice.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    price = new SimpleIntegerProperty(Integer.valueOf(tempController.getHotelRoomPrice(roomType_choice.getItems().get((Integer)new_val))));
                }
        );

        //SimpleIntegerProperty num = new SimpleIntegerProperty(price);
        price_label.textProperty().bind(price.asString());*/
    }

    //处理返回事件
    @FXML
    private void handleCancel() throws IOException {
        missionPane.getChildren().clear();
        missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.MemberHotelInfo_View_Path)));

    }

    //处理订单提交事件
    @FXML
    private void handleSubmit() throws IOException {
        String memberId = SingletonItem.getInstance().getActivateId();
        String hoteId = SingletonItem.getInstance().getHotelId();
        LocalDate localDate = checkInTime_date.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = localDate.format(formatter) + " 00:00:00";
        int numOfDays = Integer.valueOf(stayDays_field.getText());
        RoomType roomType = roomType_choice.getValue();
        int numOfRoom = Integer.valueOf(roomNum_field.getText());
        int numOfGuest = Integer.valueOf(peopleNum_field.getText());
        boolean childExist = hasChild_radio.isSelected();

        ResultMessage msg = controller.submit(memberId, date, hoteId, numOfDays, roomType, numOfRoom, numOfGuest, childExist);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tips");
        alert.setHeaderText("");
        if(msg == ResultMessage.SUCCEED) {
            alert.setContentText("订单已提交！");
            alert.showAndWait();
        }else {
            alert.setContentText("提交失败！");
            alert.showAndWait();
        }
        handleCancel();
    }
}

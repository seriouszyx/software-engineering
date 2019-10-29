package presentation.view.hotel;

import common.OrderCondition;
import common.ResultMessage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.impl.hotel.HotelOrderExeViewControllerImpl;
import presentation.controller.service.hotel.HotelOrderExeViewControllerService;
import presentation.controller.unity.Order;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.awt.SystemColor.info;

/**
 * @author Molloh
 * @version 2016/12/7
 * @description
 */
public class HotelOrderExeView implements Initializable {
    @FXML
    private AnchorPane missionPane;

    @FXML
    private Label orderId_label;
    @FXML
    private Label hotel_label;
    @FXML
    private Label type_label;
    @FXML
    private Label yesOrNo_label;
    @FXML
    private Label start_label;
    @FXML
    private Label end_label;
    @FXML
    private Label last_label;
    @FXML
    private Label people_label;
    @FXML
    private Label price_label;

    @FXML
    private Button revoke_btn;
    @FXML
    private Button exe_btn;
    @FXML
    private Button delay_btn;

    private HotelOrderExeViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = HotelOrderExeViewControllerImpl.getInstance();
        controller.setOrderId(SingletonItem.getInstance().getOrderId());

        delay_btn.setVisible(false);
        revoke_btn.setVisible(false);
        exe_btn.setVisible(false);

        initOrder();
    }

    private void initOrder() {
        price_label.setText("￥" + String.valueOf(controller.getDiscountedPrice()));
        orderId_label.setText(SingletonItem.getInstance().getOrderId());
        hotel_label.setText(controller.getHotelName());
        type_label.setText(controller.getRoomType().name());
        yesOrNo_label.setText(String.valueOf(controller.getChildExist()));
        start_label.setText(controller.getCheckInTime());
        end_label.setText(controller.getCheckOutTime());
        last_label.setText("");
        people_label.setText(String.valueOf(controller.getNumOfGuest()));

        OrderCondition state = controller.getOrderCondition();
        if(state == OrderCondition.ABNORMAL) {
            revoke_btn.setVisible(true);
        }else if(state == OrderCondition.WAITING) {
            exe_btn.setVisible(true);
        }else if(state == OrderCondition.EXECUTING) {
            delay_btn.setVisible(true);
        }
    }

    @FXML
    private void handleRevoke() {
        ResultMessage msg = controller.revokeOrder();
        popUp(msg, "订单已延期！");
    }

    @FXML
    private void handleExe() throws IOException {
        ResultMessage msg = controller.checkIn();
        popUp(msg, "订单已执行！");
        handleCancel();
    }

    @FXML
    private void handleDelay() {
        ResultMessage msg = controller.delayOrder();
        popUp(msg, "订单已退房！");
    }

    @FXML
    private void handleCancel() throws IOException {
        missionPane.getChildren().clear();
        missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.HotelOrder_View_Path)));
    }

    private void popUp(ResultMessage msg, String a) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tips");
        alert.setHeaderText("");
        if(msg == ResultMessage.SUCCEED) {
            alert.setContentText(a);
            initOrder();
            alert.showAndWait();
        }else {
            alert.setContentText("失败！");
            alert.showAndWait();
        }
    }
}

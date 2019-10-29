package presentation.view.member;

import common.OrderCondition;
import common.ResultMessage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.impl.member.MemberOrderInfoViewControllerImpl;
import presentation.controller.service.member.MemberOrderInfoViewControllerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description 客户订单信息界面
 */
public class MemberOrderInfoView implements Initializable {
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
    private Label orderState_label;

    @FXML
    private Button delete_btn;
    @FXML
    private Button comment_btn;

    @FXML
    private AnchorPane missionPane;

    private MemberOrderInfoViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = MemberOrderInfoViewControllerImpl.getInstance();
        controller.setOrderId(SingletonItem.getInstance().getOrderId());

        delete_btn.setVisible(false);
        comment_btn.setVisible(false);

        OrderCondition orderCondition = controller.getOrderCondition();
        if(orderCondition == OrderCondition.WAITING) {
            delete_btn.setVisible(true);
        }else if(orderCondition == OrderCondition.EXECUTED) {
            comment_btn.setVisible(true);
        }

        orderState_label.setText(controller.getOrderCondition().name());
        orderId_label.setText(SingletonItem.getInstance().getOrderId());
        hotel_label.setText(controller.getHotelName());
        type_label.setText(controller.getRoomType().name());
        yesOrNo_label.setText(String.valueOf(controller.getChildExist()));
        start_label.setText(controller.getCheckInTime());
        end_label.setText(controller.getCheckOutTime());
        last_label.setText("");
        people_label.setText(String.valueOf(controller.getNumOfGuest()));
    }

    //处理返回事件
    @FXML
    private void handleReturn() {
        try {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.MemberOrder_View_Path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleComment() {
        //弹出窗口实现评论功能
        try {
            Stage primaryStage = (Stage)orderId_label.getScene().getWindow();

            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ViewFxmlPath.MemberOrderComment_View_Path));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("评价酒店");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            MemberOrderCommentView controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //处理删除订单事件
    @FXML
    private void handleDelete() {
        ResultMessage msg = controller.deleteOrder();
        if(msg == ResultMessage.SUCCEED) {
            handleReturn();
        }
    }

}

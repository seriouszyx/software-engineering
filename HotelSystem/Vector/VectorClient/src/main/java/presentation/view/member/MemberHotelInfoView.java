package presentation.view.member;

import common.RoomType;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.impl.member.MemberHotelInfoViewControllerImpl;
import presentation.controller.service.member.MemberHotelInfoViewControllerService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description Member 查看酒店信息界面
 */
public class MemberHotelInfoView implements Initializable {
    @FXML
    private Button reserve_btn;
    @FXML
    private Button return_btn;

    @FXML
    private Label name_label;
    @FXML
    private Label star_label;
    @FXML
    private Label point_label;
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

    @FXML
    private TextArea description_area;

    @FXML
    private ListView<String> comment_list;

    @FXML
    private AnchorPane missionPane;

    private MemberHotelInfoViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = MemberHotelInfoViewControllerImpl.getInstance();
        controller.setHotelId(SingletonItem.getInstance().getHotelId());

        //初始化酒店信息
        name_label.setText(controller.getHotelName());
        star_label.setText(controller.getHotelStar());
        point_label.setText(controller.getHotelPoint());
        description_area.setText(controller.getHotelDiscription());

        single_label.setText("￥" + controller.getHotelRoomPrice(RoomType.SINGLE));
        double_label.setText("￥" + controller.getHotelRoomPrice(RoomType.DOUBLE));
        family_label.setText("￥" + controller.getHotelRoomPrice(RoomType.FAMILY));

        singleNum_label.setText(controller.getRoomNum(RoomType.SINGLE) + "间");
        doubleNum_label.setText(controller.getRoomNum(RoomType.DOUBLE) + "间");
        familyNum_label.setText(controller.getRoomNum(RoomType.FAMILY) + "间");

        ArrayList<String> list = (ArrayList<String>) controller.getComment();
        if(list.isEmpty()) {
            comment_list.getItems().add("暂无评价");
        }else {
            for(String item : list) {
                comment_list.getItems().add(item);
            }
        }

    }

    //处理返回事件
    @FXML
    private void handleReverseOrReturn(ActionEvent event) throws IOException {
        if(event.getSource() == reserve_btn) {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.MemberHotelOrder_View_Path)));
        }else if(event.getSource() == return_btn) {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.MemberHotelList_View_Path)));
        }
    }
}

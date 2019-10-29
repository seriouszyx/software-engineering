package presentation.view.hotel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.impl.hotel.HotelRootViewControllerImpl;
import presentation.controller.impl.sign.SignViewControllerImpl;
import presentation.controller.service.hotel.HotelRootViewControllerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/3
 * @description
 */
public class HotelRootView implements Initializable{
    @FXML
    private ToggleButton modifyInfo_btn;
    @FXML
    private ToggleButton promotion_btn;
    @FXML
    private ToggleButton order_btn;
    @FXML
    private ToggleButton roomUpdate_btn;

    @FXML
    private Button logo_btn;
    @FXML
    private Button signOut_btn;

    @FXML
    private Label hotelId_label;

    @FXML
    private AnchorPane missionPane;

    private HotelRootViewControllerService controller;

    private String fxmlPath;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = HotelRootViewControllerImpl.getInstance();
        String hotelId = SingletonItem.getInstance().getActivateId();
        controller.setHotelId(hotelId);

        hotelId_label.setText(hotelId);

        ToggleGroup guide = new ToggleGroup();
        modifyInfo_btn.setToggleGroup(guide);
        promotion_btn.setToggleGroup(guide);
        order_btn.setToggleGroup(guide);
        roomUpdate_btn.setToggleGroup(guide);

        setMissionPane(ViewFxmlPath.HotelOrder_View_Path);
    }

    @FXML
    private void handleMissionSwitch(ActionEvent event) throws IOException {
        if(event.getSource() == signOut_btn) {
            fxmlPath = ViewFxmlPath.Sign_View_Path;
            Stage stage = (Stage)signOut_btn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            SignViewControllerImpl.getInstance().signOut();
            stage.show();
        }else if(event.getSource() == modifyInfo_btn) {
            fxmlPath = ViewFxmlPath.HotelInfo_View_Path;
        }else if(event.getSource() == promotion_btn) {
            fxmlPath = ViewFxmlPath.HotelPromotion_View_Path;
        }else if(event.getSource() == order_btn || event.getSource() == logo_btn) {
            fxmlPath = ViewFxmlPath.HotelOrder_View_Path;
        }else if(event.getSource() == roomUpdate_btn) {
            fxmlPath = ViewFxmlPath.HotelRoom_View_Path;
        }

        setMissionPane(fxmlPath);
    }

    private void setMissionPane(String fxmlPath) {
        try {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(fxmlPath)));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    //关闭
    @FXML
    private void handleExit() {
        SignViewControllerImpl.getInstance().signOut();
        System.exit(0);
    }

    //最小化
    @FXML
    private void handleMinimize() {
        Stage stage = (Stage)modifyInfo_btn.getScene().getWindow();
        stage.setIconified(true);
    }
}

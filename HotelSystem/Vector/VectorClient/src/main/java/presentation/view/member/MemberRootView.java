package presentation.view.member;

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
import javafx.stage.Stage;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.impl.sign.SignViewControllerImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/3
 * @description Member Interface的界面根节点
 */
public class MemberRootView implements Initializable {
    @FXML
    private Label memberId_label;

    @FXML
    private ToggleButton modifyInfo_btn;
    @FXML
    private ToggleButton myOrder_btn;
    @FXML
    private Button signOut_btn;
    @FXML
    private ToggleButton home_btn;
    @FXML
    private ToggleButton credit_btn;
    @FXML
    private Button exit_btn;
    @FXML
    private Button logo_btn;

    @FXML
    private AnchorPane missionPane;

    private String fxmlPath;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        String memberId = SingletonItem.getInstance().getActivateId();

        memberId_label.setText(memberId);
        ToggleGroup guide = new ToggleGroup();
        myOrder_btn.setToggleGroup(guide);
        home_btn.setToggleGroup(guide);
        modifyInfo_btn.setToggleGroup(guide);
        credit_btn.setToggleGroup(guide);

        setMissionPane(ViewFxmlPath.MemberHotelList_View_Path);
    }

    //导航设置
    @FXML
    private void handleMissionSwitch(ActionEvent event) throws IOException {
        if(event.getSource() == signOut_btn) {
            SignViewControllerImpl.getInstance().signOut();
            //跳转登录界面
            fxmlPath = ViewFxmlPath.Sign_View_Path;
            Stage stage = (Stage)signOut_btn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else if(event.getSource() == modifyInfo_btn) {
            fxmlPath = ViewFxmlPath.MemberInfo_View_Path;
        }else if(event.getSource() == myOrder_btn) {
            fxmlPath = ViewFxmlPath.MemberOrder_View_Path;
        }else if(event.getSource() == home_btn || event.getSource() == logo_btn) {
            fxmlPath = ViewFxmlPath.MemberHotelList_View_Path;
        }else if(event.getSource() == exit_btn) {
            SignViewControllerImpl.getInstance().signOut();
            System.exit(0);
        }else if(event.getSource() == credit_btn) {
            fxmlPath = ViewFxmlPath.MemberCredit_View_Path;
        }

        if(fxmlPath != null)
            setMissionPane(fxmlPath);
    }

    //load mission pane
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
        Stage stage = (Stage)memberId_label.getScene().getWindow();
        stage.setIconified(true);
    }
}
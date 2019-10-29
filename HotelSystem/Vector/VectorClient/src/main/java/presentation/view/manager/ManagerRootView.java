package presentation.view.manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.impl.sign.SignViewControllerImpl;
import presentation.controller.service.sign.SignViewControllerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description Manager 界面根节点
 */
public class ManagerRootView implements Initializable {
    @FXML
    private Button search_btn;
    @FXML
    private TextField search_field;
    @FXML
    private ToggleButton addUser_btn;
    @FXML
    private Button signOut_btn;

    @FXML
    private Label id_label;

    @FXML
    public BorderPane missionPane;

    private String fxmlPath;
    private SignViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id_label.setText(SingletonItem.getInstance().getActivateId());
        controller = SignViewControllerImpl.getInstance();
    }

    /**
     * 处理界面跳转事件
     */
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
        }else if(event.getSource() == search_btn) {
            SingletonItem.getInstance().setSearchedId(search_field.getText());
            fxmlPath = ViewFxmlPath.ManagerUserEdit_View_Path;
        }else if(event.getSource() == addUser_btn) {
            fxmlPath = ViewFxmlPath.ManagerUserAdd_View_Path;
        }

        setMissionPane(fxmlPath);
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
        Stage stage = (Stage)search_btn.getScene().getWindow();
        stage.setIconified(true);
    }


    private void setMissionPane(String fxmlPath) {
        try {
            missionPane.setCenter(FXMLLoader.load(getClass().getResource(fxmlPath)));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}


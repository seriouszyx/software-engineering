package presentation.view.manager;

import common.AccountType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.impl.manager.ManagerUserControllerImpl;
import presentation.controller.service.manager.ManagerUserControllerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description Manager 添加用户界面
 */
public class ManagerUserAddView implements Initializable {
    @FXML
    private AnchorPane missionPane;
    @FXML
    private ComboBox<String> userType_combo;

    @FXML
    private PasswordField password_field;
    @FXML
    private PasswordField repeat_field;

    @FXML
    private Label alert_label;

    private ManagerUserControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = ManagerUserControllerImpl.getInstance();

        userType_combo.setValue(null);
        userType_combo.getItems().addAll(
                "普通会员","企业会员","酒店","营销人员"
        );
    }

    @FXML
    private void handleAddUser() {
        if(!password_field.getText().equals(repeat_field.getText())) {
            alert_label.setText("MisMatch!");
        }else {
            String userType = userType_combo.getValue();
            AccountType accountType;
            switch (userType) {
                case "普通会员":
                    accountType = AccountType.Member;
                    break;
                case "企业会员":
                    accountType = AccountType.Enterprise;
                    break;
                case "酒店":
                    accountType = AccountType.Hotel;
                    break;
                case "营销人员":
                    accountType = AccountType.Marketer;
                    break;
                default:
                    accountType = AccountType.Fail;
                    break;
            }

            if(accountType != AccountType.Fail) {
                SingletonItem.getInstance().setSearchedId(
                        controller.addUser(password_field.getText(), accountType));
                setMissionPane(ViewFxmlPath.ManagerUserEdit_View_Path);
            } else {
                alert_label.setText("请选择用户类型！");
            }
        }
    }

    private void setMissionPane(String fxmlPath) {
        try {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(fxmlPath)));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

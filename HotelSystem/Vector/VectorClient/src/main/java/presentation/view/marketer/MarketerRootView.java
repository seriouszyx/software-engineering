package presentation.view.marketer;

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
import presentation.controller.impl.sign.SignViewControllerImpl;
import presentation.controller.service.sign.SignViewControllerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/7
 * @description
 */
public class MarketerRootView implements Initializable {
    @FXML
    private ToggleButton credit_btn;
    @FXML
    private ToggleButton promotion_btn;
    @FXML
    private ToggleButton order_btn;
    @FXML
    private Button signOut_btn;
    @FXML
    private Button logo_btn;

    @FXML
    private Label id_label;

    @FXML
    private AnchorPane missionPane;

    private String fxmlPath;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup guide = new ToggleGroup();
        credit_btn.setToggleGroup(guide);
        promotion_btn.setToggleGroup(guide);
        order_btn.setToggleGroup(guide);

        id_label.setText(SingletonItem.getInstance().getActivateId());
        setMissionPane(ViewFxmlPath.MarketerOrder_View_Path);
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
        }else if(event.getSource() == credit_btn) {
            fxmlPath = ViewFxmlPath.MarketerCredit_View_Path;
        }else if(event.getSource() == promotion_btn) {
            fxmlPath = ViewFxmlPath.MarketerPromotion_View_Path;
        }else if(event.getSource() == order_btn || event.getSource() == logo_btn) {
            fxmlPath = ViewFxmlPath.MarketerOrder_View_Path;
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
        Stage stage = (Stage)logo_btn.getScene().getWindow();
        stage.setIconified(true);
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

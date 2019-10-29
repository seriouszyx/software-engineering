package presentation.view.marketer;

import common.ResultMessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import presentation.controller.impl.marketer.MarketerCreditViewControllerImpl;
import presentation.controller.service.marketer.MarketerCreditViewControllerService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/7
 * @description
 */
public class MarketerCreditView implements Initializable {
    @FXML
    private TextField memberId_field;
    @FXML
    private TextField credit_field;

    private MarketerCreditViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = MarketerCreditViewControllerImpl.getInstance();
    }

    @FXML
    private void handleCharge() {
        ResultMessage message = controller.chargeCredit(memberId_field.getText(), Integer.parseInt(credit_field.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tips");
        alert.setHeaderText("");
        if(message == ResultMessage.SUCCEED) {
            alert.setContentText("充值成功！");
            alert.showAndWait();
        }else {
            alert.setContentText("充值失败！");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleCancelCharge() {
        memberId_field.setText(null);
        credit_field.setText(null);
    }

}

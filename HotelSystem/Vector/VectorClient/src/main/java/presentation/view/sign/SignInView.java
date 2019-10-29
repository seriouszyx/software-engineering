package presentation.view.sign;

import common.AccountType;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
 * @version 2016/12/22
 * @description 实现登录注册的界面逻辑
 */
public class SignInView implements Initializable{
    @FXML
    private Button signIn_btn;
    @FXML
    private Button switchSignIn_btn;
    @FXML
    private Button switchSignUp_btn;

    @FXML
    private TextField account_field;
    @FXML
    private PasswordField password_field;

    @FXML
    private TextField userName_field;
    @FXML
    private PasswordField signUpKey_field;
    @FXML
    private PasswordField repeatKey_field;

    @FXML
    private Label alertIn_label;
    @FXML
    private Label alertUp_label;

    @FXML
    private RadioButton normal_radio;
    @FXML
    private RadioButton enterprise_radio;

    @FXML
    private ImageView img;

    @FXML
    private GridPane signIn_pane;
    @FXML
    private GridPane signUp_pane;

    private String fxmlPath;
    private SignViewControllerService controller;
    private boolean isNormalMember;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = SignViewControllerImpl.getInstance();

        //设置logo
        img.setImage(new Image(getClass().getResourceAsStream("/images/Vector.png")));

        //初始默认登录
        signIn_pane.setVisible(true);
        signUp_pane.setVisible(false);

        //会员类别选择初始化
        ToggleGroup member_group = new ToggleGroup();
        normal_radio.setToggleGroup(member_group);
        enterprise_radio.setToggleGroup(member_group);
        member_group.selectedToggleProperty().addListener(
                (ObservableValue<?extends Toggle> ov, Toggle old_Toggle, Toggle new_Toggle) -> {
                    if(member_group.getSelectedToggle() == normal_radio) {
                        isNormalMember = true;
                    }else if(member_group.getSelectedToggle() == enterprise_radio){
                        isNormalMember = false;
                    }
                }
        );

    }

    //登录事件
    @FXML
    private void handleSignIn() throws IOException {
        //检查输入
        if(account_field.getText().equals("")) {
            alertIn_label.setText("请输入账号！");
            return;
        }
        if(password_field.getText().equals("")) {
            alertIn_label.setText("请输入密码！");
            return;
        }

        //处理登录
        AccountType TYPE = controller.signIn(account_field.getText(), password_field.getText());
        if(TYPE != AccountType.Fail) {
            String userId = account_field.getText();
            SingletonItem.getInstance().setActivateId(userId);
        }
        switch (TYPE) {
            case Fail:
                alertIn_label.setText("账号/密码错误！");
                break;
            case Member:
                fxmlPath = ViewFxmlPath.MemberRoot_View_Path;
                break;
            case Hotel:
                fxmlPath = ViewFxmlPath.HotelRoot_View_Path;
                break;
            case Manager:
                fxmlPath = ViewFxmlPath.ManagerRoot_View_Path;
                break;
            case Marketer:
                fxmlPath = ViewFxmlPath.MarketerRoot_View_Path;
                break;
            default:
                break;
        }
        //界面切换
        if(fxmlPath != null) {
            Stage stage = (Stage)signIn_btn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    //注册事件
    @FXML
    private void handleSignUp() throws IOException {
        //检查输入
        if(signUpKey_field.getText().equals("")) {
            alertUp_label.setText("请输入密码！");
            return;
        }
        if(!repeatKey_field.getText().equals(signUpKey_field.getText())) {
            alertUp_label.setText("先后输入密码不一致！");
            return;
        }

        //处理注册
        String memberId = controller.signUp(userName_field.getText(), signUpKey_field.getText(), isNormalMember);
        if(!memberId.equals("FAIL")) {
            signIn_pane.setVisible(true);
            signUp_pane.setVisible(false);
            account_field.setText(memberId);
        }else {
            alertUp_label.setText("注册失败！");
        }
    }

    //登录注册切换
    @FXML
    private void handleSwitchSign(ActionEvent event) {
        if(event.getSource() == switchSignIn_btn) {
            signIn_pane.setVisible(true);
            signUp_pane.setVisible(false);
        }else if(event.getSource() == switchSignUp_btn) {
            signIn_pane.setVisible(false);
            signUp_pane.setVisible(true);
        }
    }

    //关闭
    @FXML
    private void handleExit() {
        System.exit(0);
    }

    //最小化
    @FXML
    private void handleMinimize() {
        Stage stage = (Stage)signIn_btn.getScene().getWindow();
        stage.setIconified(true);
    }
}

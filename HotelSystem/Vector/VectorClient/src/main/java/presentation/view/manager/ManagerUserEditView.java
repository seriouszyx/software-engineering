package presentation.view.manager;

import common.ResultMessage;
import common.Sex;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import presentation.common.SingletonItem;
import presentation.controller.impl.manager.ManagerUserControllerImpl;
import presentation.controller.service.manager.ManagerUserControllerService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description Manager 编辑用户信息界面
 */
public class ManagerUserEditView implements Initializable {
    @FXML
    private AnchorPane missionPane;

    @FXML
    private TextField name_field;
    @FXML
    private Label type_label;
    @FXML
    private Label ID_label;

    //Member
    @FXML
    private GridPane member_grid;

    //普通会员
    @FXML
    private DatePicker birthday_field;

    //企业会员
    @FXML
    private TextField enterprise_field;

    @FXML
    private TextField address_field;
    @FXML
    private TextField phone_field;
    @FXML
    private RadioButton male_radio;
    @FXML
    private RadioButton female_radio;

    @FXML
    private Label VIP_label;
    @FXML
    private Label credit_label;
    @FXML
    private Label member_label;

    //Hotel
    @FXML
    private GridPane hotel_grid;
    @FXML
    private TextArea discription_area;
    @FXML
    private TextField hotelPoint_field;
    @FXML
    private TextField hotelAddress_field;
    @FXML
    private TextField hotelPhone_field;

    @FXML
    private ComboBox<String> star_combo;

    private ManagerUserControllerService controller;

    private Sex sex;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = ManagerUserControllerImpl.getInstance();
        controller.setUserId(SingletonItem.getInstance().getSearchedId());

        star_combo.getItems().addAll("1", "2", "3", "4", "5");
        name_field.setText(controller.getUserName());
        ID_label.setText(SingletonItem.getInstance().getSearchedId());
        initEditable();

        //根据用户类型初始化界面
        char accountType = SingletonItem.getInstance().getSearchedId().charAt(0);
        if(accountType == 'N') {
            VIP_label.setText(controller.getVIPLevel());
            credit_label.setText(controller.getCredit());
            type_label.setText("普通会员");
            member_grid.setVisible(true);
            hotel_grid.setVisible(false);
            member_label.setText("生日：");
            birthday_field.setVisible(true);
            enterprise_field.setVisible(false);
            birthday_field.setValue(controller.getBirthDay());
            initialMember();
        }else if(accountType == 'E') {
            VIP_label.setText("VIP " + controller.getVIPLevel());
            credit_label.setText("信用: " + controller.getCredit());
            type_label.setText("企业会员");
            member_grid.setVisible(true);
            hotel_grid.setVisible(false);
            member_label.setText("企业：");
            enterprise_field.setVisible(true);
            birthday_field.setVisible(true);
            enterprise_field.setText(controller.getEnterPrise());
            initialMember();
        }else if(accountType == 'M') {
            type_label.setText("营销人员");
            member_grid.setVisible(false);
            hotel_grid.setVisible(false);
        }else if(accountType == 'H') {
            type_label.setText("酒店");
            hotel_grid.setVisible(true);
            member_grid.setVisible(false);
            initialHotel();
        }

    }

    private void initEditable () {
        name_field.setEditable(true);
        hotelAddress_field.setEditable(true);
        birthday_field.setEditable(true);
        enterprise_field.setEditable(true);
        address_field.setEditable(true);
        phone_field.setEditable(true);
    }

    //初始化member的信息显示
    private void initialMember() {
        address_field.setText(controller.getAddress());
        phone_field.setText(controller.getPhone());
        credit_label.setText(controller.getCredit());
        VIP_label.setText(controller.getVIPLevel());

        ToggleGroup sex_group = new ToggleGroup();
        male_radio.setToggleGroup(sex_group);
        female_radio.setToggleGroup(sex_group);

        sex_group.selectedToggleProperty().addListener(
                (ObservableValue<?extends Toggle> ov, Toggle old_Toggle, Toggle new_Toggle) -> {
                    if(sex_group.getSelectedToggle() == male_radio) {
                        sex = Sex.MALE;
                    }else if(sex_group.getSelectedToggle() == female_radio) {
                        sex = Sex.FEMALE;
                    }
                }
        );
    }

    //初始化hotel的信息显示
    private void initialHotel() {
        hotelAddress_field.setText(controller.getHotelAddress());
        hotelPhone_field.setText(controller.getHotelPhone());
        star_combo.setValue(controller.getHotelStar());
        discription_area.setText(controller.getHotelDiscription());
    }


    @FXML
    private void handleEditUser() {
        //根据用户类型保存用户信息
        char accountType = SingletonItem.getInstance().getSearchedId().charAt(0);
        if(accountType == 'N') {
            controller.setUserName(name_field.getText());
            controller.setAddress(address_field.getText());
            controller.setPhone(phone_field.getText());
            controller.setSex(sex);

            controller.setBirthDay(birthday_field.getValue());
        }else if(accountType == 'E') {
            controller.setUserName(name_field.getText());
            controller.setAddress(address_field.getText());
            controller.setPhone(phone_field.getText());
            controller.setSex(sex);

            controller.setEnterPrise(enterprise_field.getText());
        }else if(accountType == 'M') {
            controller.setUserName(name_field.getText());
        }else if(accountType == 'H') {
            controller.setHotelPhone(hotelPhone_field.getText());
            controller.setHotelAddress(hotelAddress_field.getText());
            controller.setHotelDiscription(discription_area.getText());
            controller.setHotelPoint(hotelPoint_field.getText());
            controller.setHotelStar(star_combo.getValue());
        }
        ResultMessage msg = controller.updateInfo();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tips");
        alert.setHeaderText("");
        if(msg == ResultMessage.SUCCEED) {
            alert.setContentText("修改成功！");
            alert.showAndWait();
        }else {
            alert.setContentText("失败！");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleDelete() {
        ResultMessage msg = controller.delUser(SingletonItem.getInstance().getSearchedId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tips");
        alert.setHeaderText("");
        if(msg == ResultMessage.SUCCEED) {
            alert.setContentText("账号已删除！");
            alert.showAndWait();
        }else {
            alert.setContentText("删除失败！");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleMissionCancel() {
        missionPane.getChildren().clear();
    }
}

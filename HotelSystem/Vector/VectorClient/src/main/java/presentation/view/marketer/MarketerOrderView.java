package presentation.view.marketer;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.unity.Order;
import presentation.controller.impl.marketer.MarketerOrderViewControllerImpl;
import presentation.controller.service.marketer.MarketerOrderViewControllerService;
import vo.OrderVo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @ author Molloh
 * @ version 2016/11/5
 * @ description Controller for login view
 */
public class MarketerOrderView implements Initializable {
    @FXML
    private TableView<Order> order_list;
    @FXML
    private TableColumn<Order, String> orderId_column;
    @FXML
    private TableColumn<Order, String> orderState_column;
    @FXML
    private TableColumn<Order, Number> orderPrice_column;
    @FXML
    private TableColumn<Order, String> orderHotel_column;
    @FXML
    private TableColumn<Order, Date> orderTime_column;
    @FXML
    private TableColumn<Order, Date> orderExeTime_column;

    @FXML
    private ComboBox<String> orderType_choice;
    @FXML
    private TextField orderId_field;

    @FXML
    private AnchorPane missionPane;

    private MarketerOrderViewControllerService controller;

    private ArrayList<OrderVo> orderList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = MarketerOrderViewControllerImpl.getInstance();

        orderType_choice.getItems().addAll("未执行订单", "异常订单");
        initOrderTypeChoice();
    }

    private void initOrderTypeChoice() {
        orderType_choice.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    String orderSwitch = orderType_choice.getItems().get(new_val.intValue());
                    if(orderSwitch.equals("未执行订单")) {
                        orderList = (ArrayList<OrderVo>) controller.getNotExecutedOrderByWebSite();
                        initTable();
                    }else if(orderSwitch.equals("异常订单")) {
                        orderList = (ArrayList<OrderVo>) controller.getAbnormalByWebSite();
                        initTable();
                    }
                }
        );
    }

    private void initTable() {
        ObservableList<Order> data = FXCollections.observableArrayList();
        for(OrderVo vo : orderList) {
            data.add(new Order(vo.getOrderId(),
                    vo.getCondition().toString(),
                    vo.getDiscountedPrice(),
                    vo.getHotel(),
                    vo.getCreateTime(),
                    vo.getCheckInTime()));
        }
        order_list.setItems(data);

        orderPrice_column.setCellValueFactory(cellData -> cellData.getValue().orderPriceProperty());
        orderHotel_column.setCellValueFactory(cellData -> cellData.getValue().orderHotelProperty());
        orderTime_column.setCellValueFactory(cellData -> cellData.getValue().orderTimeProperty());
        orderExeTime_column.setCellValueFactory(cellData -> cellData.getValue().orderExeTimeProperty());
        orderState_column.setCellValueFactory(cellData -> cellData.getValue().orderStateProperty());
        orderId_column.setCellValueFactory(cellData -> cellData.getValue().orderIdProperty());
        orderId_column.setCellFactory(param -> new MarketerOrderView.OrderBtnCell());
    }

    class OrderBtnCell extends TableCell<Order, String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Button order_btn;
            if(item != null) {
                order_btn = new Button(item);
                setGraphic(order_btn);
                order_btn.setOnAction((ActionEvent event) -> {
                    try {
                        SingletonItem.getInstance().setOrderId(item);
                        missionPane.getChildren().clear();
                        missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.MarketerOrderRevoke_View_Path)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    @FXML
    private void handleSearchOrder() {
        String orderId = orderId_field.getText();
        orderList.clear();
        orderList.add(controller.findOrder(orderId));
        initTable();
    }
}

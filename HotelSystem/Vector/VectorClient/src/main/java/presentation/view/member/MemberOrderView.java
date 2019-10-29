package presentation.view.member;

import common.OrderCondition;
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
import presentation.controller.impl.member.MemberOrderViewControllerImpl;
import presentation.controller.service.member.MemberOrderViewControllerService;
import presentation.controller.unity.Order;
import vo.OrderVo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2017/1/1
 * @description Member 查看Order界面
 */
public class MemberOrderView implements Initializable {
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
    private AnchorPane missionPane;

    private MemberOrderViewControllerService controller;

    private ArrayList<OrderVo> orderList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = MemberOrderViewControllerImpl.getInstance();
        controller.setMemberId(SingletonItem.getInstance().getActivateId());

        orderType_choice.getItems().addAll("全部订单", "未执行订单", "已执行订单", "执行中订单", "待评价订单", "取消的订单", "异常订单");
        orderType_choice.setValue("全部订单");
        orderList = (ArrayList<OrderVo>) controller.getAllOrders();
        initTable(orderList);
        initOrderTypeChoice();
    }

    //订单类别选择监听
    private void initOrderTypeChoice() {
        orderType_choice.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    OrderCondition state = getState(orderType_choice.getItems().get(new_val.intValue()));
                    if(state == OrderCondition.ALL) {
                        initTable(orderList);
                    }else {
                        ArrayList<OrderVo> list = (ArrayList<OrderVo>) controller.getOrdersInConditionByMember(state);
                        initTable(list);
                    }
                }
        );
    }

    //刷新/初始化表格
    private void initTable(ArrayList<OrderVo> orderList) {
        ObservableList<Order> data = FXCollections.observableArrayList();
        //data.add(new Order("1","2", "3", "4", "5", "6"));
        for(OrderVo vo : orderList) {
            data.add(new Order(vo.getOrderId(),
                    vo.getCondition().toString(),
                    vo.getDiscountedPrice(),
                    vo.getHotel(),
                    vo.getCreateTime(),
                    vo.getPlanCheckInTime()));
        }
        order_list.setItems(data);

        orderPrice_column.setCellValueFactory(cellData -> cellData.getValue().orderPriceProperty());
        orderHotel_column.setCellValueFactory(cellData -> cellData.getValue().orderHotelProperty());
        orderTime_column.setCellValueFactory(cellData -> cellData.getValue().orderTimeProperty());
        orderExeTime_column.setCellValueFactory(cellData -> cellData.getValue().orderExeTimeProperty());
        orderState_column.setCellValueFactory(cellData -> cellData.getValue().orderStateProperty());
        orderId_column.setCellValueFactory(cellData -> cellData.getValue().orderIdProperty());
        orderId_column.setCellFactory(param -> new OrderBtnCell());
    }

    //自定义表格组件为按钮
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
                        missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.MemberOrderInfo_View_Path)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    private OrderCondition getState(String state) {
        switch (state) {
            case "全部订单": return OrderCondition.ALL;
            case "未执行订单": return OrderCondition.WAITING;
            case "已执行订单": return OrderCondition.EXECUTED;
            case "执行中订单": return OrderCondition.EXECUTING;
            case "已评价订单": return OrderCondition.FINISHED;
            case "取消的订单": return OrderCondition.CANCELED;
            case "异常订单": return OrderCondition.ABNORMAL;
            default: return null;
        }
    }
}

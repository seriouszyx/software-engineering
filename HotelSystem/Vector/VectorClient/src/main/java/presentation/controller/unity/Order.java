package presentation.controller.unity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

/**
 * @author Molloh
 * @version 2016/12/10
 * @description
 */
public class Order {
    private final SimpleStringProperty orderId;
    private final SimpleStringProperty orderState;
    private final SimpleIntegerProperty orderPrice;
    private final SimpleStringProperty orderHotel;
    private final SimpleObjectProperty<Date> orderTime;
    private final SimpleObjectProperty<Date> orderExeTime;

    public Order(String orderId, String orderState, int orderPrice, String orderHotel, Date orderTime, Date orderExeTime) {
        this.orderId = new SimpleStringProperty(orderId);
        this.orderState = new SimpleStringProperty(orderState);
        this.orderPrice = new SimpleIntegerProperty(orderPrice);
        this.orderHotel = new SimpleStringProperty(orderHotel);
        this.orderTime = new SimpleObjectProperty<>(orderTime);
        this.orderExeTime = new SimpleObjectProperty<>(orderExeTime);
    }

    public SimpleStringProperty orderIdProperty() {
        return orderId;
    }

    public SimpleStringProperty orderStateProperty() {
        return orderState;
    }

    public SimpleIntegerProperty orderPriceProperty() {
        return orderPrice;
    }

    public SimpleStringProperty orderHotelProperty() {
        return orderHotel;
    }

    public SimpleObjectProperty<Date> orderTimeProperty() {
        return orderTime;
    }

    public SimpleObjectProperty<Date> orderExeTimeProperty() {
        return orderExeTime;
    }
}

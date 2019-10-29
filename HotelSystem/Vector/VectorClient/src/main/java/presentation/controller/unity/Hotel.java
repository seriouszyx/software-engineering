package presentation.controller.unity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Molloh
 * @version 2016/12/10
 * @description Hotel的property类
 */
public class Hotel {
    private final SimpleStringProperty hotelNameWithId;
    private final SimpleIntegerProperty hotelStar;
    private final SimpleDoubleProperty hotelPoint;
    private final SimpleStringProperty hotelAddress;
    private final SimpleIntegerProperty hotelPrice;


    public Hotel(String hotelNameWithId, int hotelStar, double hotelPoint, String hotelAddress, int hotelPrice) {
        this.hotelNameWithId = new SimpleStringProperty(hotelNameWithId);
        this.hotelStar = new SimpleIntegerProperty(hotelStar);
        this.hotelPoint = new SimpleDoubleProperty(hotelPoint);
        this.hotelAddress = new SimpleStringProperty(hotelAddress);
        this.hotelPrice = new SimpleIntegerProperty(hotelPrice);
    }

    public String getName() {
        return hotelNameWithId.get();
    }

    public SimpleStringProperty hotelNameProperty() {
        return hotelNameWithId;
    }

    public SimpleIntegerProperty hotelStarProperty() {
        return hotelStar;
    }

    public SimpleDoubleProperty hotelPointProperty() {
        return hotelPoint;
    }

    public SimpleStringProperty hotelAddressProperty() {
        return hotelAddress;
    }

    public SimpleIntegerProperty hotelPriceProperty() {
        return hotelPrice;
    }
}

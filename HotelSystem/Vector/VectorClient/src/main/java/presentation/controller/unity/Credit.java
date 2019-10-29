package presentation.controller.unity;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description 信用类型的property类，用于tableview的数据导入
 */
public class Credit {

    private final SimpleStringProperty creditNow;
    private final SimpleStringProperty reason;
    private final SimpleStringProperty time;
    private final SimpleStringProperty delta;

    public Credit(String creditNow, String reason, String time, String delta) {
        this.creditNow = new SimpleStringProperty(creditNow);
        this.reason = new SimpleStringProperty(reason);
        this.time = new SimpleStringProperty(time);
        this.delta = new SimpleStringProperty(delta);
    }

    public SimpleStringProperty creditNowProperty() {
        return this.creditNow;
    }

    public SimpleStringProperty reasonProperty() {
        return this.reason;
    }

    public SimpleStringProperty timeProperty() {
        return this.time;
    }

    public SimpleStringProperty deltaProperty() {
        return this.delta;
    }
}

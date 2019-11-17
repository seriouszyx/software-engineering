package presentation.Table;

import javafx.beans.property.SimpleStringProperty;

public class CreditTable {
		public final SimpleStringProperty guestID;
		public final SimpleStringProperty orderID;
		public final SimpleStringProperty previousCredit;
		public final SimpleStringProperty afterCredit;
		public final SimpleStringProperty reason;
		public final SimpleStringProperty time;
		
		public CreditTable(String guestID, String orderID, String previousCredit, String afterCredit, String time, String reason) {
			this.guestID = new SimpleStringProperty(guestID);
			this.orderID = new SimpleStringProperty(orderID);
			this.previousCredit = new SimpleStringProperty(previousCredit);
			this.afterCredit = new SimpleStringProperty(afterCredit);
			this.time = new SimpleStringProperty(time);
			this.reason = new SimpleStringProperty(reason);
		}
	}
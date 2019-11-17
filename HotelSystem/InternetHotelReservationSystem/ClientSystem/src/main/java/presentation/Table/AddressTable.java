package presentation.Table;

import javafx.beans.property.SimpleStringProperty;

public class AddressTable {
	public final SimpleStringProperty city;
	public final SimpleStringProperty cycle;
	public final SimpleStringProperty discount;

	public AddressTable(String city, String cycle, String discount) {
		this.city = new SimpleStringProperty(city);
		this.cycle = new SimpleStringProperty(cycle);
		this.discount = new SimpleStringProperty(discount);
	}

	public void setDiscount(String discount) {
		this.discount.set(discount);
	}

	public void setCity(String city) {
		this.city.set(city);
	}

	public void setCycle(String cycle) {
		this.cycle.set(cycle);
	}

	public String getCity() {
		return city.get();
	}

	public String getCycle() {
		return cycle.get();
	}

	public String getDiscount() {
		return discount.get();
	}
}

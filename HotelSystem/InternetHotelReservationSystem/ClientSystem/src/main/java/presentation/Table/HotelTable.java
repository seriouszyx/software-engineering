package presentation.Table;

import javafx.beans.property.SimpleStringProperty;

public class HotelTable {
	public SimpleStringProperty hotelID;
	public SimpleStringProperty hotelName;
	public SimpleStringProperty address;
	public SimpleStringProperty city;
	public SimpleStringProperty cycle;
	public SimpleStringProperty hasOrder;
	public SimpleStringProperty minPrice;
	public SimpleStringProperty level;
	public SimpleStringProperty score;
	public HotelTable(String hotelID, String hotelName,String address, String city,String cycle,String hasOrder,String minPrice, String level,String score) {
		this.hotelID = new SimpleStringProperty(hotelID);
		this.hotelName = new SimpleStringProperty(hotelName);
		this.address = new SimpleStringProperty(address);
		this.city = new SimpleStringProperty(city);
		this.cycle = new SimpleStringProperty(cycle);
		this.hasOrder = new SimpleStringProperty(hasOrder);
		this.minPrice = new SimpleStringProperty(minPrice);
		this.level = new SimpleStringProperty(level);
		this.score = new SimpleStringProperty(score);

	}
	public String getHotelID() {
		return hotelID.get();
	}
}

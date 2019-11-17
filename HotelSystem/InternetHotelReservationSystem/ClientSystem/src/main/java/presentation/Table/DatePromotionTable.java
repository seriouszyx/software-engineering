package presentation.Table;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;

public class DatePromotionTable {
	public final SimpleStringProperty name;
	public final SimpleStringProperty discount;
	public final SimpleStringProperty startDate;
	public final SimpleStringProperty endDate;
	
	public DatePromotionTable(String name, String  discount, LocalDate startDate, LocalDate endDate) {
		this.name = new SimpleStringProperty(name);
		this.discount = new SimpleStringProperty(discount);
		this.startDate = new SimpleStringProperty(startDate.toString());
		this.endDate = new SimpleStringProperty(endDate.toString());
	}
	public DatePromotionTable(String name, String  discount){
		this.name = new SimpleStringProperty(name);
		this.discount = new SimpleStringProperty(discount);
		this.startDate = null;
		this.endDate = null;
	}

	public void setDiscount(String discount) {
		this.discount.set(discount);
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public void setStartDate(String  startDate) {
		this.startDate.set(startDate);
	}
	
	public void setEndDate(String  endDate) {
		this.endDate.set(endDate);
	}


	public String getDiscount() {
		return  discount.get();
	}

	public String getName() {
		return name.get();
	}

	public LocalDate getStartDate() {
		String[] str=startDate.get().split("-");
//		System.out.println(startDate.get());
		return LocalDate.of(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
	}
	
	public LocalDate getEndDate() {
		String[] str=endDate.get().split("-");
		return LocalDate.of(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
	}
}

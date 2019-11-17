package businessLogic.promotionBL.discountCalculation.discountOfPromotions;

import java.time.LocalDate;

import businessLogic.promotionBL.discountCalculation.CalculateDiscount;

public class SpecialSpanDiscount implements CalculateDiscount{

	private LocalDate today;
	private LocalDate startDate;
	private LocalDate endDate;
	private double discount;
	
	public SpecialSpanDiscount(double discount, LocalDate startDate, LocalDate endDate, LocalDate today) {
		this.discount = discount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.discount = discount;
		this.today = today;
	}

	@Override
	public double getDiscount() {
		if(isInSpan()){
			return discount;
		}
		return 1;
	}

	private boolean isInSpan(){
		if(today.isAfter(startDate)||today.isEqual(startDate)){
			if(today.isBefore(endDate)||today.isEqual(startDate)){
				return true;
			}
		}
		return false;
	}

}

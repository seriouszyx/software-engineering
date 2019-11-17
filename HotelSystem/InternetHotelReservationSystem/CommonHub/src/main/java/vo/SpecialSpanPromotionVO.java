package vo;

import java.time.LocalDate;

import po.SpecialSpanPromotionPO;

public class SpecialSpanPromotionVO{

	public String userID;
	public String promotionName;	
	public double discount;
	public LocalDate startDate;
	public LocalDate endDate;

	public SpecialSpanPromotionVO(SpecialSpanPromotionPO specialSpanPromotionPO) {
		this.userID = specialSpanPromotionPO.getUserID();
		this.promotionName = specialSpanPromotionPO.getPromotionName();
		this.discount = specialSpanPromotionPO.getDiscount();
		this.startDate = specialSpanPromotionPO.getStartDate();
		this.endDate = specialSpanPromotionPO.getEndDate();
	}


	public SpecialSpanPromotionVO() {
		// TODO 自动生成的构造函数存根
	}
	public SpecialSpanPromotionVO(String promotionName, double discount, LocalDate startDate, LocalDate endDate) {

		this.promotionName = promotionName;
		this.discount =  discount;
		this.startDate =startDate;
		this.endDate = endDate;
	}

}

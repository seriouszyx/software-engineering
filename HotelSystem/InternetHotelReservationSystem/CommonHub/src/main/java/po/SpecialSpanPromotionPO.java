package po;

import java.io.Serializable;
import java.time.LocalDate;

import utilities.enums.PromotionType;
import vo.SpecialSpanPromotionVO;

public class SpecialSpanPromotionPO implements Serializable{

	private String userID;
	private String promotionName;
	private double discount;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public SpecialSpanPromotionPO(String userID, PromotionType promotionType, String promotionName, double discount,
			LocalDate startDate, LocalDate endDate) {
		super();
		this.userID = userID;
		this.promotionName = promotionName;
		this.discount = discount;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public SpecialSpanPromotionPO() {
	}
	
	public SpecialSpanPromotionPO(SpecialSpanPromotionVO specialSpanPromotionVO) {
		this.userID = specialSpanPromotionVO.userID;
		this.discount = specialSpanPromotionVO.discount;
		this.startDate = specialSpanPromotionVO.startDate;
		this.endDate = specialSpanPromotionVO.endDate;
		this.promotionName = specialSpanPromotionVO.promotionName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "SpecialSpanPromotionPO [userID=" + userID + ", promotionName=" + promotionName + ", discount="
				+ discount + "]";
	}
	
	

}

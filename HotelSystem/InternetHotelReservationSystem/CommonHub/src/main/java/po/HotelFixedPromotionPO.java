package po;

import java.io.Serializable;

import utilities.enums.PromotionType;
import vo.HotelFixedPromotionVO;

public class HotelFixedPromotionPO implements Serializable{

	private String hotelID;
	private PromotionType promotionType;
	private double discount;

	public HotelFixedPromotionPO(HotelFixedPromotionVO hotelFixedPromotionVO) {
		this.hotelID = hotelFixedPromotionVO.hotelID;
		this.promotionType = hotelFixedPromotionVO.promotionType;
		this.discount = hotelFixedPromotionVO.discount;
	}

	public HotelFixedPromotionPO(String hotelID, PromotionType promotionType, double discount) {
		super();
		this.hotelID = hotelID;
		this.promotionType = promotionType;
		this.discount = discount;
	}

	public HotelFixedPromotionPO() {
	}

	public String getHotelID() {
		return hotelID;
	}

	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}

	public PromotionType getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(PromotionType prmotionType) {
		this.promotionType = prmotionType;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discout) {
		this.discount = discout;
	}

	@Override
	public String toString() {
		return "HotelFixedPromotionPO [hotelID=" + hotelID + ", promotionType=" + promotionType.toString() + ", discount="
				+ discount + "]";
	}

}

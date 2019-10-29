package po;

import java.io.Serializable;

import common.HotelPromotionType;

/**
 * @version 2016-12-07
 * @author 金灵益
 * @description 客户生日特惠促销策略
 */
public class BirthdayProPo implements Serializable {
	private static final long serialVersionUID = 1L;
	private final HotelPromotionType type = HotelPromotionType.BIRTHDAY;
	private double discount;
	
	public BirthdayProPo(double discount){
		this.discount = discount;
	}
	
	public HotelPromotionType getPromotionType(){
		return type;
	}
	
	public void setDiscount(double d){
		this.discount = d;
	}
	
	public double getDiscount(){
		return discount;
	}
}

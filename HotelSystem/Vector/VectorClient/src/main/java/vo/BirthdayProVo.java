package vo;

import java.io.Serializable;

import common.HotelPromotionType;
import po.BirthdayProPo;

/**
 * @version 2016-12-08
 * @author 金灵益
 * @description 客户生日特惠促销策略
 */
public class BirthdayProVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private final HotelPromotionType type = HotelPromotionType.BIRTHDAY;
	private double discount;
	
	public BirthdayProVo(BirthdayProPo po){
		this.discount = po.getDiscount();
	}
	
	public BirthdayProVo(){}
	
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

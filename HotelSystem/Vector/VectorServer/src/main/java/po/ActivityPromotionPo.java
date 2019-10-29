package po;

import java.io.Serializable;
import java.util.Date;

import common.HotelPromotionType;

/**
 * @version 2016-12-10
 * @author 金灵益
 * @description 活动促销策略，属性：（促销类型），名称，开始时间，结束时间，折扣，（折扣范围）
 *              酒店、网站通用
 */
public class ActivityPromotionPo implements Serializable {
	private static final long serialVersionUID = 1L;
	private final HotelPromotionType type = HotelPromotionType.ACTIVITY;
	private String promotionName;
	private Date startDate;
	private Date endDate;
	private double discount;                 //折扣
	
	public ActivityPromotionPo(String promotionName, Date startDate, Date endDate, 
			                   double discount){
		this.promotionName = promotionName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.discount = discount;
	}
	
	public HotelPromotionType getPromotionType(){
		return type;
	}
	
	public void setPromotionName(String name){
		this.promotionName = name;
	}
	
	public String getPromotionName(){
		return promotionName;
	}
	
	public void setStartDate(Date date){
		this.startDate = date;
	}
	
	public Date getStartDate(){
		return startDate;
	}
	
	public void setEndDate(Date date){
		this.endDate = date;
	}
	
	public Date getEndDate(){
		return endDate;
	}
	
	public void setDiscount(double dis){
		this.discount = dis;
	}
	
	public double getDiscount(){
		return discount;
	}

}

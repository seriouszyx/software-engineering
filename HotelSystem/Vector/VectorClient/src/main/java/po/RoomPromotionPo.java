package po;

import java.io.Serializable;

import common.HotelPromotionType;
/**
 * @version 2016-12-10
 * @author 金灵益
 * @description 预定多间房间优惠
 */
public class RoomPromotionPo implements Serializable {
	private static final long serialVersionUID = 1L;
	private final HotelPromotionType type = HotelPromotionType.ORDERROOM;
	private String name;               //促销策略名称
	private int number;                //预订数量
	private double discount;           //折扣
	
	public RoomPromotionPo(String name, int number, double discount){
		this.name = name;
		this.number = number;
		this.discount = discount;
	}
	
	public HotelPromotionType getPromotionType(){
		return type;
	}
	
	public String getPromotionName(){
		return name;
	}
	
	public int getNumOfRoom(){
		return number;
	}
	
	public double getDiscount(){
		return discount;
	}
}

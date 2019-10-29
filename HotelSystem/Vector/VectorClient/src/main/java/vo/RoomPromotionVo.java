package vo;

import java.io.Serializable;

import common.HotelPromotionType;
import po.RoomPromotionPo;
/**
 * @version 2016-12-07
 * @author 金灵益
 * @description 预定多间房间优惠
 */
public class RoomPromotionVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private final HotelPromotionType type = HotelPromotionType.ORDERROOM;
	private String name;               //促销策略名称
	private int number;                //预订数量
	private double discount;           //折扣
	
	public RoomPromotionVo(RoomPromotionPo po){
		this.name = po.getPromotionName();
		this.number = po.getNumOfRoom();
		this.discount = po.getDiscount();
	}
	
	public RoomPromotionVo(String name, int number, double discount){
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


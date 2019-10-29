package po;

import java.io.Serializable;

import common.RoomType;

/**
 * @version 2016/12/04
 * @author 金灵益
 * @description 酒店总体拥有的房间类型，每种房间类型拥有数量，原始价格属性
 */
public class HotelTypeRoomPo implements Serializable {
	private static final long serialVersionUID = 1L;

	private RoomType type;
	private int number;            //房间数量
	private int price;
	
	public HotelTypeRoomPo(RoomType type, int number, int price){
		this.type = type;
		this.number = number;
		this.price = price;
	}
	
	public RoomType getType(){
		return type;
	}
	
	public int getNumOfTypeRoom(){
		return number;
	}
	
	public int getPrice(){
		return price;
	}
}

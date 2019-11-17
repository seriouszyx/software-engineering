package vo;

import po.RoomInfoPO;
import utilities.enums.RoomType;

public class RoomInfoVO {
	
	// 酒店编号
	public String hotelID;
	
	// 房间类型
	public RoomType roomType;
	
	// 房间数
	public int roomNum;
	
	// 剩余房间数量
	public int remainNum; 
	
	// 价格
	public int price;
	
	public RoomInfoVO(String hotelID, RoomType roomType, int roomNum, int remainNum ,int price) {
		this.hotelID = hotelID;
		this.roomType = roomType;
		this.roomNum = roomNum;
		this.remainNum = remainNum;
		this.price = price;
	}

	public RoomInfoVO(RoomInfoPO roomInfoPO) {
		this.hotelID = roomInfoPO.getHotelID();
		this.roomType = roomInfoPO.getRoomType();
		this.roomNum = roomInfoPO.getRoomNum();
		this.remainNum = roomInfoPO.getRemainNum();
		this.price = roomInfoPO.getPrice();
	}

	public RoomInfoVO() {
		// TODO 自动生成的构造函数存根
	}
}

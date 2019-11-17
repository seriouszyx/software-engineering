package po;

import java.io.Serializable;

import utilities.enums.RoomType;
import vo.RoomInfoVO;

/**
 * @Description:保存客房信息的持久化对象，信息包括：
 * 酒店编号，房间类型，房间名称，房间数，剩余房间数量，价格
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2016年12月6日 下午1:32:12
 */
public class RoomInfoPO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6089646464437203567L;

	// 酒店编号
	private String hotelID;
	
	// 房间类型
	private RoomType roomType;
	
	// 房间数
	private int roomNum;
	
	// 剩余房间数量
	private int remainNum;
	
	// 价格
	private int price;
	
	public RoomInfoPO(String hotelID,RoomType roomType, int roomNumCount, int remainNum, int price) {
		super();
		this.hotelID = hotelID;
		this.roomType = roomType;	
		this.roomNum = roomNumCount;
		this.remainNum = remainNum;
		this.price = price;
	}
	
	public RoomInfoPO(RoomInfoVO roomInfoVO) {
		this.hotelID = roomInfoVO.hotelID;
		this.roomType = roomInfoVO.roomType;
		this.roomNum = roomInfoVO.roomNum;
		this.remainNum = roomInfoVO.remainNum;
		this.price = roomInfoVO.price;
	}
	public RoomInfoPO() {
	}

	public String getHotelID() {
		return hotelID;
	}
	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;	
	}
	public RoomType getRoomType() {
		return roomType;
	}
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNumCount) {
		this.roomNum = roomNumCount;
	}
	
	public int getRemainNum() {
		return remainNum;
	}

	public void setRemainNum(int remainNum) {
		this.remainNum = remainNum;
	}
	
	public int getPrice() {			
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

}

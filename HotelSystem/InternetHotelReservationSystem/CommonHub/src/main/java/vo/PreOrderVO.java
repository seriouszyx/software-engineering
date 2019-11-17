package vo;

import java.time.LocalDate;

import utilities.enums.RoomType;

public class PreOrderVO {
	
	// 客户编号
	public String guestID;
	
	// 酒店编号
	public String hotelID;
	
	// 预计入住时间
//	public LocalDateTime expectExecuteTime;
	
	// 预计入住日期
	public LocalDate expectExecuteDate;

	// 房间数
	public int roomNum;

	//入住天数
	public int lastDays;
	
	//选择的房间类型;
	public RoomType roomType;
	
	public PreOrderVO(OrderVO orderVO){
		this.guestID = orderVO.orderGeneralVO.guestID;
		this.hotelID = orderVO.orderGeneralVO.hotelID;
//		this.expectExecuteTime = orderVO.orderGeneralVO.expectExecuteTime;
		this.expectExecuteDate = orderVO.orderGeneralVO.expectExecuteTime.toLocalDate();
		this.roomNum=orderVO.roomNumCount;
		this.lastDays = orderVO.orderGeneralVO.expectLeaveTime.toLocalDate().getDayOfYear()
				- orderVO.orderGeneralVO.expectExecuteTime.toLocalDate().getDayOfYear();
		
	}

	public PreOrderVO(String guestID, String hotelID, LocalDate expectExecuteDate, int roomNum, int lastDays) {
		super();
		this.guestID = guestID;
		this.hotelID = hotelID;
		this.expectExecuteDate = expectExecuteDate;
		this.roomNum = roomNum;
		this.lastDays = lastDays;
	}

	public PreOrderVO() {
		// TODO 自动生成的构造函数存根
	}
	
	

}

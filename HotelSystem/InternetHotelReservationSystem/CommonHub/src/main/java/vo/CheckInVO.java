package vo;

import java.time.LocalDateTime;

/**
 * 
 * @author 61990
 * lastChangedBy charles
 * updateTime 2016/12/5
 *
 */
public class CheckInVO {

	//	订单编号
	public String orderID;
	
	//	房间号
	public String roomNumber;
	
	//	入住时间
	public LocalDateTime checkInTime;	
	
	//	预计离开时间
	public LocalDateTime expectLeaveTime;
	/**
	 * 
	 * @author 61990
	 * lastChangedBy charles
	 * updateTime 2016/12/5
	 */
	public CheckInVO(String orderID, String roomNumber, LocalDateTime checkInTime, LocalDateTime expectLeaveTime) {
		this.orderID = orderID;
		this.roomNumber = roomNumber;
		this.checkInTime = checkInTime;
		this.expectLeaveTime = expectLeaveTime;
	}
}

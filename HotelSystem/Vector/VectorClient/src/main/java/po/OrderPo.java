package po;

import java.io.Serializable;
import java.util.Date;

import common.OrderCondition;
import common.RoomType;

/**
 * @ author Aobang 
 * @ version 2016/11/27 
 * @ description
 */
public class OrderPo  implements Serializable {
	private String orderId;
	private OrderCondition condition;
	private String memberId;
	private String memberName;
	private Date createTime;
	private Date planCheckInTime;
	private Date checkInTime;
	private Date checkOutTime;
	private Date revokeTime;
	private String hotel;
	private String hotelId;
	private int numOfDays;
	private RoomType roomType;
	private int numOfRoom;
	private int numOfGuest;
	private boolean childExist;
	private int originalPrice;
	private double discount;
	private int discountedPrice;

	public OrderPo(String orderId, OrderCondition condition, String memberId, String memberName, Date createTime,
			Date planCheckInTime, Date checkInTime, Date checkOutTime, Date revokeTime, String hotel, String hotelId,
			int numOfDays, RoomType roomType, int numOfRoom, int numOfGuest, boolean childExist, int originalPrice,
			double discount,
			int discountedPrice) {
		this.orderId = orderId;
		this.condition = condition;
		this.memberId = memberId;
		this.memberName = memberName;
		this.createTime = createTime;
		this.planCheckInTime = planCheckInTime;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.revokeTime = revokeTime;
		this.hotel = hotel;
		this.hotelId = hotelId;
		this.numOfDays = numOfDays;
		this.roomType = roomType;
		this.numOfRoom = numOfRoom;
		this.numOfGuest = numOfGuest;
		this.childExist = childExist;
		this.originalPrice = originalPrice;
		this.discount = discount;
		this.discountedPrice = discountedPrice;
	}
	
	public String getOrderId() {
		return this.orderId;
	}
	public OrderCondition getCondition() {
		return this.condition;
	}
	public String getMemberId() {
		return this.memberId;
	}
	public String getMemberName() {
		return this.memberName;
	}
	public Date getCreateTime() {
		return this.createTime;
	}
	public Date getPlanCheckInTime() {
		return this.planCheckInTime;
	}
	public Date getCheckInTime() {
		return this.checkInTime;
	}
	public Date getCheckOutTime() {
		return this.checkOutTime;
	}
	public Date getRevokeTime() {
		return this.revokeTime;
	}
	public String getHotel() {
		return this.hotel;
	}
	public String getHotelId() {
		return this.hotelId;
	}
	public int getNumOfDays() {
		return this.numOfDays;
	}
	public RoomType getRoomType() {
		return this.roomType;
	}
	public int getNumOfRoom() {
		return this.numOfRoom;
	}
	public int getNumOfGuest() {
		return this.numOfGuest;
	}
	public boolean getChildExist() {
		return this.childExist;
	}
	public int getOriginalPrice() {
		return this.originalPrice;
	}
	public double getDiscount() {
		return this.discount;
	}
	public int getDiscountedPrice() {
		return this.discountedPrice;
	}
	
	public void setCondition(OrderCondition condition) {
		this.condition = condition;
	}
	public void setCreateTime(Date time) {
		this.createTime = time;
	}
	public void setCheckInTime(Date time) {
		this.checkInTime = time;
	}
	public void setCheckOutTime(Date time) {
		this.checkOutTime = time;
	}
	public void setRevokeTime(Date time) {
		this.revokeTime = time;
	}
}

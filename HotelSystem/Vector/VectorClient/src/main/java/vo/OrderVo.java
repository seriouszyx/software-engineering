package vo;

import java.io.Serializable;
import java.util.Date;

import common.OrderCondition;
import common.RoomType;
import po.OrderPo;

public class OrderVo  implements Serializable {
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
	
	public OrderVo(OrderPo po) {
		this.orderId = po.getOrderId();
		this.condition = po.getCondition();
		this.memberId = po.getMemberId();
		this.memberName = po.getMemberName();
		this.createTime = po.getCreateTime();
		this.planCheckInTime = po.getPlanCheckInTime();
		this.checkInTime = po.getCheckInTime();
		this.checkOutTime = po.getCheckOutTime();
		this.revokeTime = po.getRevokeTime();
		this.hotel = po.getHotel();
		this.hotelId = po.getHotelId();
		this.numOfDays = po.getNumOfDays();
		this.roomType = po.getRoomType();
		this.numOfRoom = po.getNumOfRoom();
		this.numOfGuest = po.getNumOfGuest();
		this.childExist = po.getChildExist();
		this.originalPrice = po.getOriginalPrice();
		this.discount = po.getDiscount();
		this.discountedPrice = po.getDiscountedPrice();
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

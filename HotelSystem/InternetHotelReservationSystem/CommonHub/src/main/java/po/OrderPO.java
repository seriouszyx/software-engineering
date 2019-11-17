package po;

import java.io.Serializable;
import java.time.LocalDateTime;

import utilities.enums.OrderState;
import utilities.enums.RoomType;
import vo.OrderVO;

/**
 * 
 * @author 61990
 * lastChangedBy charles
 * updateTime 2016/12/5
 *
 */
public class OrderPO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6528720659331679661L;

	//	订单编号(create时无) 客户编号 酒店编号 酒店名 酒店地址 最后预定价格(create时无) 
	//	最晚订单执行时间／预计入住时间 预计离开时间 订单状态 已评论 入住人姓名 联系方式
	private OrderGeneralPO orderGeneralPO;
	
	//	原价
	private int previousPrice;
	
	//	订单生成时间(create时无)
	private LocalDateTime createTime;
	
	//	入住时间(create时无)
	private LocalDateTime checkInTime;
	
	//	退房时间（实际离开时间）(create时无)
	private LocalDateTime checkOutTime;

	//	房间类型
	private RoomType roomType;
	
	//	房间数
	private int roomNumCount;
	
	//	房间号
	private String roomNumber;
	
	//	预计入住人数
	private int expectGuestNumCount;
	
	//	特别要求
	private String message;
	
	//	评分(create时无)
	private double score;
		
	//	评论(create时无)
	private String comment;
	
	public OrderPO(){
		this.orderGeneralPO = new OrderGeneralPO();
	}
	
	public OrderPO(OrderVO orderVO) {
		this.orderGeneralPO = new OrderGeneralPO(orderVO.orderGeneralVO);
		
		this.previousPrice = orderVO.previousPrice;
		this.createTime = orderVO.createTime;
		this.checkInTime = orderVO.checkInTime;
		this.checkOutTime = orderVO.checkOutTime;
		this.roomType = orderVO.roomType;
		this.roomNumCount = orderVO.roomNumCount;
		this.roomNumber = orderVO.roomNumber;
		this.expectGuestNumCount = orderVO.expectGuestNumCount;
		this.message = orderVO.message;
		this.score = orderVO.score;
		this.comment = orderVO.comment;
	}

	public OrderPO(String orderID, String guestID, String hotelID, String hotelName, String hotelAddress, 
			int previousPrice, int price, LocalDateTime createTime, LocalDateTime checkInTime,
			LocalDateTime checkOutTime, LocalDateTime expectExecuteTime, LocalDateTime expectLeaveTime, 
			OrderState state, boolean hasCommented, boolean hasCheckOut, RoomType roomType, 
			int roomNumCount, String roomNumber, int expectGuestNumCount, String name, String phone, 
			String message, double score, String comment) {
		super();
		this.orderGeneralPO = new OrderGeneralPO(orderID, guestID, hotelID, hotelName, hotelAddress,
				price, expectExecuteTime, expectLeaveTime, state, hasCommented, hasCheckOut, name, phone);
		
		this.previousPrice = previousPrice;
		this.createTime = createTime;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.roomType = roomType;
		this.roomNumCount = roomNumCount;
		this.roomNumber = roomNumber;
		this.expectGuestNumCount = expectGuestNumCount;
		this.message = message;
		this.score = score;
		this.comment = comment;
	}

	public OrderPO(OrderGeneralPO orderGeneralPO, int previousPrice, LocalDateTime createTime, 
			LocalDateTime checkInTime, LocalDateTime checkOutTime, RoomType roomType, int roomNumCount, 
			String roomNumber, int expectGuestNumCount, String message, double score, String comment) {
		super();
		this.orderGeneralPO = orderGeneralPO;
		
		this.previousPrice = previousPrice;
		this.createTime = createTime;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.roomType = roomType;
		this.roomNumCount = roomNumCount;
		this.roomNumber = roomNumber;
		this.expectGuestNumCount = expectGuestNumCount;
		this.message = message;
		this.score = score;
		this.comment = comment;
	}
	
	public String getOrderID() {
		return orderGeneralPO.getOrderID();
	}

	public OrderGeneralPO getOrderGeneralPO() {
		return orderGeneralPO;
	}

	public void setOrderGeneralPO(OrderGeneralPO orderGeneralPO) {
		this.orderGeneralPO = orderGeneralPO;
	}

	public void setOrderID(String orderID) {
		orderGeneralPO.setOrderID(orderID);
	}

	public String getGuestID() {
		return orderGeneralPO.getGuestID();
	}

	public void setGuestID(String guestID) {
		orderGeneralPO.setGuestID(guestID);
	}

	public String getHotelID() {
		return orderGeneralPO.getHotelID();
	}

	public void setHotelID(String hotelID) {
		orderGeneralPO.setHotelID(hotelID);
	}

	public String getHotelName() {
		return orderGeneralPO.getHotelName();
	}

	public void setHotelName(String hotelName) {
		orderGeneralPO.setHotelName(hotelName);
	}

	public String getHotelAddress() {
		return orderGeneralPO.getHotelAddress();
	}

	public void setHotelAddress(String hotelAddress) {
		orderGeneralPO.setHotelAddress(hotelAddress);
	}

	public int getPrice() {
		return orderGeneralPO.getPrice();
	}

	public void setPrice(int price) {
		orderGeneralPO.setPrice(price);
	}

	public int getPreviousPrice() {
		return previousPrice;
	}

	public void setPreviousPrice(int previousPrice) {
		this.previousPrice = previousPrice;
	}
	
	public LocalDateTime getExpectExecuteTime() {
		return orderGeneralPO.getExpectExecuteTime();
	}

	public void setExpectExecuteTime(LocalDateTime expectExecuteTime) {
		orderGeneralPO.setExpectExecuteTime(expectExecuteTime);
	}

	public OrderState getState() {
		return orderGeneralPO.getState();
	}

	public void setState(OrderState state) {
		orderGeneralPO.setState(state);
	}

	public boolean getHasCommented() {
		return orderGeneralPO.getHasCommented();
	}

	public void setHasCommented(boolean hasCommented) {
		orderGeneralPO.setHasCommented(hasCommented);
	}
	
	public boolean getHasCheckOut() {
		return orderGeneralPO.getHasCheckOut();
	}

	public void setHasCheckOut(boolean hasCheckOut) {
		orderGeneralPO.setHasCheckOut(hasCheckOut);
	}
	
	public LocalDateTime getExpectLeaveTime() {
		return orderGeneralPO.getExpectLeaveTime();
	}

	public void setExpectLeaveTime(LocalDateTime expectLeaveTime) {
		orderGeneralPO.setExpectLeaveTime(expectLeaveTime);
	}
	
	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(LocalDateTime checkInTime) {
		this.checkInTime = checkInTime;
	}

	public LocalDateTime getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(LocalDateTime checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public int getRoomNumCount() {
		return roomNumCount;
	}

	public void setRoomNumCount(int roomNumCount) {
		this.roomNumCount = roomNumCount;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getExpectGuestNumCount() {
		return expectGuestNumCount;
	}

	public void setExpectGuestNumCount(int expectGuestNumCount) {
		this.expectGuestNumCount = expectGuestNumCount;
	}
	
	public String getName() {
		return orderGeneralPO.getName();
	}

	public void setName(String name) {
		this.orderGeneralPO.setName(name);
	}

	public String getPhone() {
		return this.orderGeneralPO.getPhone();
	}

	public void setPhone(String phone) {
		this.orderGeneralPO.setPhone(phone);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}

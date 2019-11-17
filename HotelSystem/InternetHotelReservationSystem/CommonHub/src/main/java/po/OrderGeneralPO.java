package po;

import java.io.Serializable;
import java.time.LocalDateTime;

import utilities.enums.OrderState;
import vo.OrderGeneralVO;

public class OrderGeneralPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3557271803001378634L;

	// 订单编号
	private String orderID;

	// 客户编号
	private String guestID;

	// 酒店编号
	private String hotelID;

	// 酒店名
	private String hotelName;

	// 酒店地址
	private String hotelAddress;

	// 最后预定价格
	private int price;

	// 最晚订单执行时间／预计入住时间
	private LocalDateTime expectExecuteTime;

	// 预计离开时间
	private LocalDateTime expectLeaveTime;

	// 订单状态
	private OrderState state;

	// 已评论
	private boolean hasCommented;

	// 已退房
	private boolean hasCheckOut;

	// 入住人姓名
	private String name;

	// 联系方式
	private String phone;

	public OrderGeneralPO() {
	}

	public OrderGeneralPO(String orderID, String guestID, String hotelID, String hotelName, String hotelAddress,
			int price, LocalDateTime expectExecuteTime, LocalDateTime expectLeaveTime, OrderState state,
			boolean hasCommented, boolean hasCheckOut, String name, String phone) {
		super();
		this.orderID = orderID;
		this.guestID = guestID;
		this.hotelID = hotelID;
		this.hotelName = hotelName;
		this.hotelAddress = hotelAddress;
		this.price = price;
		this.expectExecuteTime = expectExecuteTime;
		this.expectLeaveTime = expectLeaveTime;
		this.state = state;
		this.hasCommented = hasCommented;
		this.hasCheckOut = hasCheckOut;
		this.name = name;
		this.phone = phone;
	}

	public OrderGeneralPO(OrderGeneralVO orderGeneralVO) {
		this.orderID = orderGeneralVO.orderID;
		this.guestID = orderGeneralVO.guestID;
		this.hotelID = orderGeneralVO.hotelID;
		this.hotelName = orderGeneralVO.hotelName;
		this.hotelAddress = orderGeneralVO.hotelAddress;
		this.price = orderGeneralVO.price;
		this.expectExecuteTime = orderGeneralVO.expectExecuteTime;
		this.expectLeaveTime = orderGeneralVO.expectLeaveTime;
		this.state = orderGeneralVO.state;
		this.hasCommented = orderGeneralVO.hasCommented;
		this.hasCheckOut = orderGeneralVO.hasCheckOut;
		this.name = orderGeneralVO.name;
		this.phone = orderGeneralVO.phone;
	}

	public OrderGeneralPO(OrderPO orderPO) {
		this.orderID = orderPO.getOrderID();
		this.guestID = orderPO.getGuestID();
		this.hotelID = orderPO.getHotelID();
		this.hotelName = orderPO.getHotelName();
		this.hotelAddress = orderPO.getHotelAddress();
		this.price = orderPO.getPrice();
		this.expectExecuteTime = orderPO.getExpectExecuteTime();
		this.expectLeaveTime = orderPO.getExpectLeaveTime();
		this.state = orderPO.getState();
		this.hasCommented = orderPO.getHasCommented();
		this.hasCheckOut = orderPO.getHasCheckOut();
		this.name = orderPO.getName();
		this.phone = orderPO.getPhone();
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getGuestID() {
		return guestID;
	}

	public void setGuestID(String guestID) {
		this.guestID = guestID;
	}

	public String getHotelID() {
		return hotelID;
	}

	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelAddress() {
		return hotelAddress;
	}

	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public LocalDateTime getExpectExecuteTime() {
		return expectExecuteTime;
	}

	public void setExpectExecuteTime(LocalDateTime expectExecuteTime) {
		this.expectExecuteTime = expectExecuteTime;
	}

	public LocalDateTime getExpectLeaveTime() {
		return expectLeaveTime;
	}

	public void setExpectLeaveTime(LocalDateTime expectLeaveTime) {
		this.expectLeaveTime = expectLeaveTime;
	}

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public boolean getHasCommented() {
		return hasCommented;
	}

	public void setHasCommented(boolean hasCommented) {
		this.hasCommented = hasCommented;
	}

	public boolean getHasCheckOut() {
		return hasCheckOut;
	}

	public void setHasCheckOut(boolean hasCheckOut) {
		this.hasCheckOut = hasCheckOut;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}

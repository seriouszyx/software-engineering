package vo;

import java.time.LocalDateTime;

import po.OrderGeneralPO;
import utilities.enums.OrderState;

/**
 * 
 * @author 61990 lastChangedBy charles updateTime 2016/12/5
 */
public class OrderGeneralVO {

	// 订单编号
	public String orderID;

	// 客户编号
	public String guestID;

	// 酒店编号
	public String hotelID;

	// 酒店名
	public String hotelName;

	// 酒店地址
	public String hotelAddress;

	// 最后预定价格
	public int price;

	// 最晚订单执行时间／预计入住时间
	public LocalDateTime expectExecuteTime;

	// 预计离开时间
	public LocalDateTime expectLeaveTime;

	// 订单状态
	public OrderState state;

	// 已评论
	public boolean hasCommented;

	// 已退房
	public boolean hasCheckOut;

	// 入住人姓名
	public String name;

	// 联系方式
	public String phone;

	/**
	 * 
	 * @author 61990 lastChangedBy charles updateTime 2016/12/5
	 */
	public OrderGeneralVO(String orderID, String guestID, String hotelID, String hotelName, String hotelAddress,
			int price, LocalDateTime expectExecuteTime, LocalDateTime expectLeaveTime, OrderState state,
			boolean hasCommented, boolean hasCheckOut, String name, String phone) {
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
		this.hasCheckOut= hasCheckOut;
		this.name = name;
		this.phone = phone;
	}

	/**
	 * 
	 * @author 61990 lastChangedBy charles updateTime 2016/12/16
	 * 
	 *         因为订单编号、价格、状态、已评论不能从界面层直接得到 所以order create的时候，提供一个专门的构造器
	 */
	public OrderGeneralVO(String guestID, String hotelID, String hotelName, String hotelAddress,
			LocalDateTime expectExecuteTime, LocalDateTime expectLeaveTime, String name, String phone) {
		this.orderID = null;
		this.price = -1;
		this.state = OrderState.UNEXECUTED;
		this.hasCommented = false;
		this.hasCheckOut = false;

		this.guestID = guestID;
		this.hotelID = hotelID;
		this.hotelName = hotelName;
		this.hotelAddress = hotelAddress;
		this.expectExecuteTime = expectExecuteTime;
		this.expectLeaveTime = expectLeaveTime;
		this.name = name;
		this.phone = phone;
	}

	/**
	 * 
	 * @author 61990 lastChangedBy charles updateTime 2016/12/5
	 * 
	 *         便于界面监听getTempPrice()
	 */
	public OrderGeneralVO(String guestID, String hotelID, LocalDateTime expectExecuteTime) {
		this.orderID = null;
		this.price = -1;
		this.state = OrderState.UNEXECUTED;
		this.hasCommented = false;
		this.hasCheckOut = false;

		this.guestID = guestID;
		this.hotelID = hotelID;
		this.expectExecuteTime = expectExecuteTime;
	}

	/**
	 * 
	 * @author 61990 lastChangedBy charles updateTime 2016/12/5
	 */
	public OrderGeneralVO(OrderGeneralPO orderGeneralPO) {
		this.orderID = orderGeneralPO.getOrderID();
		this.guestID = orderGeneralPO.getGuestID();
		this.hotelID = orderGeneralPO.getHotelID();
		this.hotelName = orderGeneralPO.getHotelName();
		this.hotelAddress = orderGeneralPO.getHotelAddress();
		this.price = orderGeneralPO.getPrice();
		this.expectExecuteTime = orderGeneralPO.getExpectExecuteTime();
		this.expectLeaveTime = orderGeneralPO.getExpectLeaveTime();
		this.state = orderGeneralPO.getState();
		this.hasCommented = orderGeneralPO.getHasCommented();
		this.hasCheckOut = orderGeneralPO.getHasCheckOut();
		this.name = orderGeneralPO.getName();
		this.phone = orderGeneralPO.getPhone();
	}
}

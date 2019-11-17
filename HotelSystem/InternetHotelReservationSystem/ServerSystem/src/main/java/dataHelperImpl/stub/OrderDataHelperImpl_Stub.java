package dataHelperImpl.stub;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dataHelper.OrderDataHelper;
import po.OrderPO;
import utilities.enums.OrderState;
import utilities.enums.ResultMessage;
import utilities.enums.RoomType;

/**
 * 
 * @author 董金玉 
 * @lastChangedBy Harvey
 * @updateTime 2016/12/5
 *
 */
public class OrderDataHelperImpl_Stub implements OrderDataHelper {

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param orderPO orderInfo载体
	 * @return ResultMessage 是否成功添加到数据库中
	 */
	public ResultMessage add(final OrderPO orderPO) {
		return ResultMessage.SUCCESS;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param orderID 订单编号
	 * @param state 需要被修改的状态
	 * @return ResultMessage 是否成功修改订单状态
	 */
	public ResultMessage setState(final String orderID, final OrderState state) {
		return ResultMessage.SUCCESS;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/5
	 * @param orderID  
	 * @param state  需要修改的状态
	 * @return ResultMessage 是否成功修改orderInfo
	 */
	public ResultMessage setHasCommentBool(String orderID) {
		return ResultMessage.SUCCESS;
	}
	
	/**
	 * @author 董金玉
	 * @lastChangedBy charles
	 * @updateTime 2016/12/4
	 * @param orderID 此次订单评价的订单编号
	 * @param score 此次订单评价的评分
	 * @param comment 此次订单评价的评论
	 * @return ResultMessage 是否成功修改orderInfo
	 */
	public ResultMessage setEvaluation(final String orderID, final double score, final String comment) {
		return ResultMessage.SUCCESS;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/5
	 * @param orderID 此次入住的订单编号
	 * @param roomNumber 房间号
	 * @param checkInTime 入住时间
	 * @return ResultMessage 是否成功修改orderInfo
	 */
	public ResultMessage setCheckIn(final String orderID, final String roomNumber, 
			final LocalDateTime checkInTime, LocalDateTime expectLeaveTime) {
		return ResultMessage.SUCCESS;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/4
	 * @param orderID 此次退房的订单编号
	 * @param checkOutTime 退房时间
	 * @return ResultMessage 是否成功修改orderInfo
	 */
	public ResultMessage setCheckOut(final String orderID, final LocalDateTime checkOutTime) {
		return ResultMessage.SUCCESS;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param orderID 订单编号
	 * @return OrderPO orderInfo载体
	 */
	public OrderPO getSingleOrder(final String orderID) {
		final LocalDateTime createTime = LocalDateTime.of(2016, 2, 2, 18, 20);
		final LocalDateTime checkInTime = LocalDateTime.of(2016, 2, 3, 11, 23);
		final LocalDateTime checkOutTime = LocalDateTime.of(2016, 2, 4, 10, 58);
		final LocalDateTime expectExecuteTime = LocalDateTime.of(2016, 2, 3, 14, 00);
		final LocalDateTime expectLeaveTime = LocalDateTime.of(2016, 2, 4, 12, 00);

		final OrderState orderState = OrderState.EXECUTED;
		final RoomType roomType = RoomType.BUSINESS_SUITE;
		
		
		return new OrderPO("123456789012", "1234567890", "12345678", "thisHotel", "address", 200, 200,
				createTime, checkInTime, checkOutTime, expectExecuteTime, expectLeaveTime, orderState, false, true, 
				roomType, 2, "301  302", 2, "zhangsan","13554321234", "no", 4.3, "good");
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/5
	 * @param guestID 客户ID
	 * @return List<OrderPO> 指定客户的所有orderInfo载体
	 */
	public List<OrderPO> getAllOrderOfGuest(final String guestID) {
		List<OrderPO> list = new ArrayList<OrderPO>();
		final LocalDateTime createTime = LocalDateTime.of(2016, 2, 2, 18, 20);
		final LocalDateTime checkInTime = LocalDateTime.of(2016, 2, 3, 11, 23);
		final LocalDateTime checkOutTime = LocalDateTime.of(2016, 2, 4, 10, 58);
		final LocalDateTime expectExecuteTime = LocalDateTime.of(2016, 2, 3, 14, 00);
		final LocalDateTime expectLeaveTime = LocalDateTime.of(2016, 2, 4, 12, 00);

		final OrderState orderState = OrderState.EXECUTED;
		final RoomType roomType = RoomType.BUSINESS_SUITE;
		
		
		list.add(new OrderPO("123456789012", "1234567890", "12345678", "thisHotel", "address", 200, 200,
				createTime, checkInTime, checkOutTime, expectExecuteTime, expectLeaveTime, orderState, false, true, 
				roomType, 2, "301  302", 2, "zhangsan","13554321234", "no", 4.3, "good"));
		list.add(new OrderPO("123456789012", "1234567890", "12345678", "thisHotel", "address", 200, 200,
				createTime, checkInTime, checkOutTime, expectExecuteTime, expectLeaveTime, orderState, false, true, 
				roomType, 2, "301  302", 2, "zhangsan","13554321234", "no", 4.3, "good"));
		return list;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param hotelID 酒店ID
	 * @return List<OrderPO> 指定酒店的所有orderInfo载体
	 */
	public List<OrderPO> getAllOrderOfHotel(final String hotelID) {
		List<OrderPO> list = new ArrayList<OrderPO>();
		final LocalDateTime createTime = LocalDateTime.of(2016, 2, 2, 18, 20);
		final LocalDateTime checkInTime = LocalDateTime.of(2016, 2, 3, 11, 23);
		final LocalDateTime checkOutTime = LocalDateTime.of(2016, 2, 4, 10, 58);
		final LocalDateTime expectExecuteTime = LocalDateTime.of(2016, 2, 3, 14, 00);
		final LocalDateTime expectLeaveTime = LocalDateTime.of(2016, 2, 4, 12, 00);

		final OrderState orderState = OrderState.EXECUTED;
		final RoomType roomType = RoomType.BUSINESS_SUITE;
		
		
		list.add(new OrderPO("123456789012", "1234567890", "12345678", "thisHotel", "address", 200, 200,
				createTime, checkInTime, checkOutTime, expectExecuteTime, expectLeaveTime, orderState, false, true, 
				roomType, 2, "301  302", 2, "zhangsan","13554321234", "no", 4.3, "good"));
		return list;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param date 需要查询的日期
	 * @return List<OrderPO> 指定日期的所有异常orderInfo载体
	 */
	public List<OrderPO> getAbnormal() {
		List<OrderPO> list = new ArrayList<OrderPO>();
		final LocalDateTime createTime = LocalDateTime.of(2016, 2, 2, 18, 20);
		final LocalDateTime checkInTime = LocalDateTime.of(2016, 2, 3, 11, 23);
		final LocalDateTime checkOutTime = LocalDateTime.of(2016, 2, 4, 10, 58);
		final LocalDateTime expectExecuteTime = LocalDateTime.of(2016, 2, 3, 14, 00);
		final LocalDateTime expectLeaveTime = LocalDateTime.of(2016, 2, 4, 12, 00);

		final OrderState orderState = OrderState.ABNORMAL;
		final RoomType roomType = RoomType.BUSINESS_SUITE;
		
		
		list.add(new OrderPO("123456789012", "1234567890", "12345678", "thisHotel", "address", 200, 200,
				createTime, checkInTime, checkOutTime, expectExecuteTime, expectLeaveTime, orderState, false, false, 
				roomType, 2, "301  302", 2, "zhangsan","13554321234", "no", 4.3, "good"));
		return list;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param date 需要查询的日期
	 * @return List<OrderPO> 指定日期的所有未执行orderInfo载体
	 */
	public List<OrderPO> getUnexecuted() {
		List<OrderPO> list = new ArrayList<OrderPO>();
		final LocalDateTime createTime = LocalDateTime.of(2016, 2, 2, 18, 20);
		final LocalDateTime checkInTime = LocalDateTime.of(2016, 2, 3, 11, 23);
		final LocalDateTime checkOutTime = LocalDateTime.of(2016, 2, 4, 10, 58);
		final LocalDateTime expectExecuteTime = LocalDateTime.of(2016, 2, 3, 14, 00);
		final LocalDateTime expectLeaveTime = LocalDateTime.of(2016, 2, 4, 12, 00);

		final OrderState orderState = OrderState.UNEXECUTED;
		final RoomType roomType = RoomType.BUSINESS_SUITE;
		
		
		list.add(new OrderPO("123456789012", "1234567890", "12345678", "thisHotel", "address", 200, 200,
				createTime, checkInTime, checkOutTime, expectExecuteTime, expectLeaveTime, orderState, false, false,
				roomType, 2, "301  302", 2, "zhangsan","13554321234", "no", 4.3, "good"));
		return list;
	}

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/1
	 * @param
	 * @return
	 */
	public void close() {
	}
}

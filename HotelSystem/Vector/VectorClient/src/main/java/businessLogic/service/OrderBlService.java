package businessLogic.service;

import java.util.Date;
import java.util.List;

import common.OrderCondition;
import common.ResultMessage;
import common.RoomType;
import vo.OrderVo;

/**
 * @author Aobang
 * @version 2017/1/1
 * @description order模块的接口规范
 */
public interface OrderBlService {
	/**
	 * @param orderId
	 * @return 得到相应order的Vo对象
	 */
	public OrderVo getOrder(String orderId);
	
	/**
	 * @param orderId
	 * @return 得到相应order的Id
	 */
	public String getOrderId(String orderId);
	
	/**
	 * @param orderId
	 * @return 得到相应order的状态（枚举类）
	 */
	public OrderCondition getOrderCondition(String orderId);
	
	/**
	 * @param orderId
	 * @return 得到相应order的客户Id
	 */
	public String getMemberId(String orderId);
	
	/**
	 * @param orderId
	 * @return 得到相应order的客户名
	 */
	public String getMemberName(String orderId);
	
	/**
	 * @param orderId
	 * @return 得到相应order的创建时间
	 */
	public Date getCreateTime(String orderId);
	
	/**
	 * @param orderId
	 * @return 得到相应order的入住时间
	 */
	public Date getCheckInTime(String orderId);
	
	/**
	 * @param orderId
	 * @return 得到相应order的退房时间
	 */
	public Date getCheckOutTime(String orderId);
	
	/**
	 * @param orderId
	 * @return 得到相应order的酒店名
	 */
	public String getHotelName(String orderId);
	
	/**
	 * @param orderId
	 * @return 得到相应order的酒店Id
	 */
	public String getHotelId(String orderId);
	
	/**
	 * @param orderId
	 * @return 得到相应order的房间种类（枚举类）
	 */
	public RoomType getRoomType(String orderId);
	
	/**
	 * @param orderId
	 * @return 得到相应order的房间数量
	 */
	public int getNumOfRoom(String orderId);
	
	/**
	 * @param orderId
	 * @return 得到相应order的客人数量
	 */
	public int getNumOfGuest(String orderId);
	
	/**
	 * @param orderId
	 * @return 得到相应order的是否存在儿童的布尔值
	 */
	public boolean getChildExist(String orderId);
	
	/**
	 * @param orderId
	 * @return 得到相应order的初始单价
	 */
	public int getOriginalPrice(String orderId);
	
	/**
	 * @param orderId
	 * @return 得到相应order的折扣
	 */
	public double getDiscount(String orderId);
	
	/**
	 * @param orderId
	 * @return 得到相应order的最终价格
	 */
	public int getDiscountedPrice(String orderId);
	
	/**
	 * @param hotelId
	 * @return 得到相应酒店的所有订单Vo对象
	 */
	public List<OrderVo> getAllOrdersByHotel(String hotelId);
	
	/**
	 * @param memberId
	 * @return 得到相应客户的所有订单Vo对象
	 */
	public List<OrderVo> getAllOrdersByMember(String memberId);
	
	/**
	 * @param hotelId
	 * @param condition
	 * @return 得到相应酒店的特定状态的订单Vo对象
	 */
	public List<OrderVo> getOrdersInConditionByHotel(String hotelId, OrderCondition condition);
	
	/**
	 * @param memberId
	 * @param condition
	 * @return 得到相应客户的特定状态的订单Vo对象
	 */
	public List<OrderVo> getOrdersInConditionByMember(String memberId, OrderCondition condition);
	
	/**
	 * @return 网站营销人员得到所有未执行订单的Vo对象
	 */
	public List<OrderVo> getNotExecutedOrderByWebSite();
	
	/**
	 * @return 网站营销人员得到所有未执行订单的Vo对象
	 */
	public List<OrderVo> getAbnormalByWebSite();
	
	/**planCheckInTimeStr格式为yyyy-MM-dd HH:mm:ss
	 * @param memberId
	 * @param planCheckInTimeStr
	 * @param hotelId
	 * @param numOfDays
	 * @param RoomType
	 * @param numOfRoom
	 * @param numOfGuest
	 * @param childExist
	 * @return 订单是否成功提交
	 */
	public ResultMessage submit(String memberId, String planCheckInTimeStr, String hoteId, int numOfDays,
			RoomType roomType, int numOfRoom, int numOfGuest, boolean childExist);

	/**
	 * @return 订单是否成功设为异常
	 */
	public ResultMessage setToAbnormal();
	
	/**
	 * @param orderId
	 * @return 订单是否成功取消
	 */
	public ResultMessage cancel(String orderId);
	
	/**
	 * @param orderId
	 * @return 入住是否成功
	 */
	public ResultMessage checkIn(String orderId);

	/**
	 * @param orderId
	 * @return 延迟入住是否成功
	 */
	public ResultMessage abnormalCheckIn(String orderId);
	
	/**
	 * @param orderId
	 * @return 延迟退房是否成功
	 */
	public ResultMessage checkOut(String orderId);
	
	/**
	 * @param orderId
	 * @param allOrHalf 选择恢复全部还是一半的信用值
	 * @return 是否成功撤销订单
	 */
	public ResultMessage revoke(String orderId, double allOrHalf);
	
	/**
	 * @param orderId
	 * @return 是否成功删除订单
	 */
	public ResultMessage delete(String orderId);
	
	/**
	 * @param orderId
	 * @return 订单是否成功设为已完成状态
	 */
	public ResultMessage setToFinished(String orderId); //提供给Hotel评价使用
}

package dataHelper;

import java.time.LocalDateTime;
import java.util.List;

import po.OrderPO;
import utilities.enums.OrderState;
import utilities.enums.ResultMessage;

/**
 * 
 * @author 董金玉
 * lastChangedBy 董金玉
 * updateTime 2016/11/29
 *
 */
public interface OrderDataHelper {

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param orderPO  order的信息载体
	 * @return ResultMessage 是否成功添加orderInfo到数据库中
	 */
	ResultMessage add(OrderPO orderPO);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param orderID  
	 * @param state  需要修改的状态
	 * @return ResultMessage 是否成功修改orderInfo
	 */
	ResultMessage setState(String orderID, OrderState state);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/12/5
	 * @param orderID  
	 * @param state  需要修改的状态
	 * @return ResultMessage 是否成功修改orderInfo
	 */
	ResultMessage setHasCommentBool(String orderID);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy charles
	 * @updateTime 2016/12/4
	 * @param orderID 此次订单评价的订单编号
	 * @param score 此次订单评价的评分
	 * @param comment 此次订单评价的评论
	 * @return ResultMessage 是否成功修改orderInfo
	 */
	ResultMessage setEvaluation(String orderID, double score, String comment);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy charles
	 * @updateTime 2016/12/5
	 * @param orderID 此次入住的订单编号
	 * @param roomNumber 房间号
	 * @param checkInTime 入住时间
	 * @return ResultMessage 是否成功修改orderInfo
	 */
	ResultMessage setCheckIn(String orderID, String roomNumber, LocalDateTime checkInTime, LocalDateTime expectLeaveTime);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy charles
	 * @updateTime 2016/12/4
	 * @param orderID 此次退房的订单编号
	 * @param checkOutTime 退房时间
	 * @return ResultMessage 是否成功修改orderInfo
	 */
	ResultMessage setCheckOut(String orderID, LocalDateTime checkOutTime);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param orderID  
	 * @return OrderPO orderInfo载体
	 */
	OrderPO getSingleOrder(String orderID);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param guestID  
	 * @return List<OrderPO> 据guestID获得的所有orderInfo载体
	 */
	List<OrderPO> getAllOrderOfGuest(String guestID);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param hotelID  
	 * @return List<OrderPO> 据hotelID获得的所有orderInfo载体
	 */
	List<OrderPO> getAllOrderOfHotel(String hotelID);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @return List<OrderPO> 据date获得的所有异常orderInfo载体
	 */
	List<OrderPO> getAbnormal();
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @return List<OrderPO> 据date获得的所有未执行orderInfo载体
	 */
	List<OrderPO> getUnexecuted();
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param 
	 * @return 
	 */
	void close();
	
}

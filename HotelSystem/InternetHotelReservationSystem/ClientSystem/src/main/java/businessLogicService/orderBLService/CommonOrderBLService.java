package businessLogicService.orderBLService;

import java.util.Iterator;

import utilities.enums.OrderState;
import utilities.enums.UserType;
import vo.HotelEvaluationVO;
import vo.OrderGeneralVO;
import vo.OrderVO;

/**
 * 
 * @author cuihua
 * lastChangedBy charles
 * updateTime 2016/12/8
 *
 */
public interface CommonOrderBLService {

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/8
	 * @param userID 客户编号
	 * @param userType 客户类型：客户／酒店工作人员
	 * @return 特定用户类型的全部订单
	 */
	Iterator<OrderGeneralVO> getAllOrderGenerals(String userID, UserType userType);
	
	/**
	 * @author Harvey
	 * @lastChangedBy charles
	 * @updateTime 2016/12/8
	 * @param userID 客户编号
	 * @param userType 客户类型：客户／酒店工作人员
	 * @param orderState <所有某种特定类型>包括：未执行、已执行、异常、已撤销
	 * @return 需要得到的<所有某种特定类型>的order
	 */
	Iterator<OrderGeneralVO> getSpecialOrderGenerals(String userID, UserType userType, OrderState orderState);
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param orderID 客户／酒店工作人员／网站营销人员当前需要查看的订单的订单号
	 * @return 此被需要订单的详情载体
	 */
	OrderVO getOrderDetail(String orderID);
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/2
	 * @param hotelID 客户／酒店工作人员查看酒店的评论
	 * @return 此酒店的所有评价
	 */
	Iterator<HotelEvaluationVO> getEvaluations(String hotelID);
}

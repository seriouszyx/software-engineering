package businessLogicService.orderBLService;

import java.util.Iterator;

import utilities.enums.ResultMessage;
import vo.GuestEvaluationVO;
import vo.OrderGeneralVO;
import vo.OrderVO;
import vo.PreOrderVO;

/**
 * 
 * @author cuihua
 * lastChangedBy charles
 * updateTime 2016/12/10
 *
 */
public interface GuestOrderBLService {
	
	/**
	 * @Description:通过preOrderVO中的信息计算订单的总价格
	 * @param preOrderVO 订单信息载体
	 * @return 订单价格
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月14日 上午12:25:05
	 */
	int getCalculatedPrice(PreOrderVO preOrderVO);
	
	/**
	 * 
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param orderVO 从客户界面层传下来的Order载体
	 * @return 客户是否成功创建此订单
	 */
	ResultMessage createOrder(OrderVO orderVO);
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param orderID 客户当前需要撤销的正常订单的订单号
	 * @return 客户是否成功撤销此正常订单
	 */
	ResultMessage undoNormalOrder(String orderID);
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/2
	 * @param evaluationVO 客户评价单个订单时产生的订单
	 * @return 客户是否成功评价该订单
	 */
	ResultMessage addEvaluation(GuestEvaluationVO evaluationVO);
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/8
	 * @param guestID 客户编号
	 * @param hasCommented 状态：已评价／未评价
	 * @return 客户<已评价／未评价>订单
	 */
	Iterator<OrderGeneralVO> getAllGuestCommentOrderGeneral(String guestID, boolean hasCommented);
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/10
	 * @param guestID 客户编号
	 * @param hotelID 目标酒店编号
	 * @return 客户在目标酒店的所有订单记录
	 */
	Iterator<OrderGeneralVO> getMyOrdersOfThisHotel(String guestID, String hotelID);

}

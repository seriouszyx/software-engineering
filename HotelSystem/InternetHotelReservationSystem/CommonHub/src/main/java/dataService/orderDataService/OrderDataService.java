package dataService.orderDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

import po.CheckInPO;
import po.CheckOutPO;
import po.GuestEvaluationPO;
import po.HotelEvaluationPO;
import po.OrderGeneralPO;
import po.OrderPO;
import utilities.enums.ResultMessage;

/**
 * 
 * @author cuihua
 * lastChangedBy charles
 * updateTime 2016/12/5
 *
 */
public interface OrderDataService extends Remote {
	
	/**
	 * 
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @Descriptiion： 创建order
	 * @param order 从逻辑层层传下来的Order载体
	 * @return 客户是否成功创建此订单
	 * @throws RemoteException RMI
	 */
	ResultMessage createOrder(OrderPO order) throws RemoteException;
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @Description: 执行订单
	 * @param orderID 酒店工作人员当前需要执行订单的订单号
	 * @return 酒店工作人员是否成功执行此订单
	 * @throws RemoteException RMI
	 */
	ResultMessage executeOrder(String orderID) throws RemoteException;
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/8
	 * @Description：撤销异常订单
	 * @param orderID 网站营销人员当前需要撤销的异常订单的订单号
	 * @param percent 撤销后需要恢复的信用值比例
	 * @return 网站营销人员是否成功按比例撤销此异常订单
	 * @throws RemoteException RMI
	 */
	ResultMessage undoAbnormalOrder(String orderID, double percent) throws RemoteException;
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @Description： 撤销未执行订单
	 * @param orderID 客户当前需要撤销的正常订单的订单号
	 * @return 客户是否成功撤销此正常订单
	 * @throws RemoteException RMI
	 */
	ResultMessage undoNormalOrder(String orderID) throws RemoteException;
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @Description： 获取订单详情
	 * @param orderID 用户当前需要查看的订单的订单号
	 * @return 此被需要订单的详情载体
	 * @throws RemoteException RMI
	 */
	OrderPO getOrderDetail(String orderID) throws RemoteException;
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @Description： 获取客户的所有订单
	 * @param guestID 客户要查看个人所有订单时，客户的编号
	 * @return 客户个人所有订单
	 * @throws RemoteException RMI
	 */
	List<OrderGeneralPO> getAllGuestOrderGeneral(String guestID) throws RemoteException;
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @Description： 获取酒店的所有订单
	 * @param hotelID 酒店要查看本酒店所有订单时，酒店的编号
	 * @return 此酒店所有的所有订单
	 * @throws RemoteException RMI
	 */
	List<OrderGeneralPO> getAllHotelOrderGeneral(String hotelID) throws RemoteException;
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @Description: 获取所有异常订单
	 * @param date 网站营销人员撤销异常订单时输入的指定日期
	 * @return 网站营销人员需要查看的当天所有的异常订单
	 * @throws RemoteException RMI
	 */
	List<OrderGeneralPO> getAllAbnormalOrderGeneral(LocalDate date) throws RemoteException;
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/29
	 * @Description： 获取所有未执行订单
	 * @param date 网站营销人员查看未执行订单时输入的指定日期
	 * @return 网站营销人员需要查看的当天所有的未执行订单
	 * @throws RemoteException RMI
	 */
	List<OrderGeneralPO> getAllUnexecutedOrderGeneral(LocalDate date) throws RemoteException;
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/4
	 * @Description： 更新入住信息
	 * @param checkInPO 酒店工作人员更新订单入住信息
	 * @return 是否成功更新
	 * @throws RemoteException RMI
	 */
	ResultMessage updateCheckIn(CheckInPO checkInPO) throws RemoteException;

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/4
	 * @Description： 更新退房信息
	 * @param checkOutPO 酒店工作人员更新订单退房信息
	 * @return 是否成功更新
	 * @throws RemoteException RMI
	 */
	ResultMessage updateCheckOut(CheckOutPO checkOutPO) throws RemoteException;
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/4
	 * @Description： 添加评价记录
	 * @param guestEvaluationPO 客户评价单个订单时产生的订单
	 * @return 客户是否成功评价该订单
	 * @throws RemoteException RMI
	 */
	ResultMessage addEvaluation(GuestEvaluationPO guestEvaluationPO) throws RemoteException;
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/4
	 * @Description 获取评价记录
	 * @param hotelID 酒店工作人员／客户查看酒店的评论
	 * @return 此酒店的所有评价
	 * @throws RemoteException RMI
	 */
	List<HotelEvaluationPO> getEvaluations(String hotelID) throws RemoteException;
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @Description： 查看已预定过的酒店
	 * @param guestID 客户需要查看个人定过的酒店时依照的客户个人编号
	 * @return 客户定过的酒店列表
	 * @throws RemoteException RMI
	 */
	List<String> getBookedHotels(String guestID) throws RemoteException;
}

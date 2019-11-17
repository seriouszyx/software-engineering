package businessLogic.orderBL;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import businessLogic.hotelBL.hotel.Hotel;
import businessLogic.orderBL.order.CommonOrder;
import businessLogic.orderBL.order.GuestOrder;
import businessLogic.orderBL.order.HotelWorkerOrder;
import businessLogic.orderBL.order.OrderForHotelModule;
import businessLogic.orderBL.order.WebMarketerOrder;
import businessLogic.promotionBL.DiscountCalculator;
import businessLogic.promotionBL.DiscountInSpan;
import businessLogicService.orderBLService.OrderBLService;
import exception.verificationException.CheckInException;
import exception.verificationException.CheckOutException;
import exception.verificationException.UserInexistException;
import utilities.enums.OrderState;
import utilities.enums.ResultMessage;
import utilities.enums.UserType;
import vo.CheckInVO;
import vo.CheckOutVO;
import vo.GuestEvaluationVO;
import vo.HotelEvaluationVO;
import vo.OrderGeneralVO;
import vo.OrderVO;
import vo.PreOrderVO;

/**
 * 
 * @author cuihua
 * lastChangedBy charles
 * updateTime 2016/12/10
 * 
 * 对Order模块重构
 */
public final class OrderBLController implements OrderBLService {

	private CommonOrder commonOrder;
	private GuestOrder guestOrder;
	private HotelWorkerOrder hotelWorkerOrder;
	private WebMarketerOrder webMarketerOrder;
	private OrderForHotelModule orderForHotelModule;

	private static OrderBLController orderController = new OrderBLController();

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/7
	 * 构造函数，初始化成员变量
	 */
	private OrderBLController() {
		// init the orders
		commonOrder = new CommonOrder();
		guestOrder = new GuestOrder();
		hotelWorkerOrder = new HotelWorkerOrder();
		webMarketerOrder = new WebMarketerOrder();
		orderForHotelModule = new OrderForHotelModule();
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @return order controller的实例，单例化
	 */
	public static OrderBLController getInstance() {
		return orderController;
	}

	/*
	 * commonOrder的接口
	 */
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/8
	 * @param userID 客户编号
	 * @param userType 客户类型：客户／酒店工作人员
	 * @return 特定用户类型的全部订单
	 */
	@Override
	public Iterator<OrderGeneralVO> getAllOrderGenerals(String userID, UserType userType) {
		return commonOrder.getAllOrderGenerals(userID, userType);
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/8
	 * @param userID 客户编号
	 * @param userType 客户类型：客户／酒店工作人员
	 * @param orderState <所有某种特定类型>包括：未执行、已执行、异常、已撤销
	 * @return 需要得到的<所有某种特定类型>的order
	 */
	@Override
	public Iterator<OrderGeneralVO> getSpecialOrderGenerals(String userID, UserType userType, OrderState orderState) {
		return commonOrder.getSpecialOrderGenerals(userID, userType, orderState);
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param orderID 用户当前需要查看的订单的订单号
	 * @return 此被需要订单的详情载体
	 */
	@Override
	public OrderVO getOrderDetail(final String orderID) {
		return commonOrder.getOrderDetail(orderID);
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/2
	 * @param hotelID 酒店工作人员／客户查看酒店的评论
	 * @return 此酒店的所有评价
	 */
	@Override
	public Iterator<HotelEvaluationVO> getEvaluations(String hotelID) {
		return commonOrder.getEvaluations(hotelID);
	}






	/*
	 * guestOrder的接口
	 */

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param orderVO 从客户界面层传下来的Order载体
	 * @return 客户是否成功创建此订单
	 */
	@Override
	public ResultMessage createOrder(final OrderVO orderVO) {
		return guestOrder.createOrder(orderVO);
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param orderID 客户当前需要撤销的正常订单的订单号
	 * @return 客户是否成功撤销此正常订单
	 */
	@Override
	public ResultMessage undoNormalOrder(final String orderID) {
		return guestOrder.undoNormalOrder(orderID);
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/7
	 * @param guestID 客户要查看个人<已执行／未执行>订单时，客户的编号
	 * @return 客户个人<已执行／未执行>订订单
	 * 
	 * <<已执行／未执行>只包含一种
	 */
	public Iterator<OrderGeneralVO> getAllGuestCommentOrderGeneral(String guestID, boolean hasCommented) {
		return guestOrder.getAllGuestCommentOrderGeneral(guestID, hasCommented);
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/2
	 * @param evaluationVO 客户评价单个订单时产生的订单
	 * @return 客户是否成功评价该订单
	 */
	@Override
	public ResultMessage addEvaluation(GuestEvaluationVO evaluationVO) {
		return guestOrder.addEvaluation(evaluationVO);
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/10
	 * @param guestID 客户编号
	 * @param hotelID 目标酒店编号
	 * @return 客户在目标酒店的所有订单记录
	 */
	@Override
	public Iterator<OrderGeneralVO> getMyOrdersOfThisHotel(String guestID, String hotelID) {
		return guestOrder.getMyOrdersOfThisHotel(guestID, hotelID);
	}






	/*
	 * hotelWorkerOrder的接口
	 */
//	/**
//	 * @author charles
//	 * @lastChangedBy charles
//	 * @updateTime 2016/11/27
//	 * @param orderID 酒店工作人员当前需要执行订单的订单号
//	 * @return 酒店工作人员是否成功执行此订单
//	 */
//	@Override
//	public ResultMessage executeOrder(final String orderID) {
//		return hotelWorkerOrder.executeOrder(orderID);
//	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/4
	 * @param checkInVO 酒店工作人员更新订单入住信息
	 * @return 是否成功更新
	 */
	public ResultMessage updateCheckIn (CheckInVO checkInVO) throws CheckInException {
		return hotelWorkerOrder.updateCheckIn(checkInVO);
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/4
	 * @param checkInVO 酒店工作人员更新订单退房信息
	 * @return 是否成功更新
	 */
	public ResultMessage updateCheckOut (CheckOutVO checkOutVO) throws CheckOutException {
		return hotelWorkerOrder.updateCheckOut(checkOutVO);
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/15
	 * @param hotelID 酒店编号
	 * @param hasCheckOut 状态：已退房／未退房
	 * @return 客户<已退房／未退房>订单
	 */
	@Override
	public Iterator<OrderGeneralVO> getAllHotelCheckOutOrderGeneral(String hotelID, boolean hasCheckOut) {
		return hotelWorkerOrder.getAllHotelCheckOutOrderGeneral(hotelID, hasCheckOut);
	}
	





	/*
	 * webMarketerOrder的接口
	 */
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/8
	 * @param orderID 网站营销人员当前需要撤销的异常订单的订单号
	 * @param percent 撤销后需要恢复的信用值比例
	 * @return 网站营销人员是否成功按比例撤销此异常订单
	 */
	@Override
	public ResultMessage undoAbnormalOrder(final String orderID, double percent) {
		return webMarketerOrder.undoAbnormalOrder(orderID, percent);
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param date 网站营销人员撤销异常订单时输入的指定日期
	 * @return 网站营销人员需要查看的当天所有的异常订单
	 */
	@Override
	public List<OrderGeneralVO> getAllAbnormalOrderGeneral(final LocalDate date) {
		return webMarketerOrder.getAllAbnormalOrderGeneral(date);
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/29
	 * @param date 网站营销人员查看未执行订单时输入的指定日期
	 * @return 网站营销人员需要查看的当天所有的未执行订单
	 */
	@Override
	public List<OrderGeneralVO> getAllUnexecutedOrderGeneral(final LocalDate date) {
		return webMarketerOrder.getAllUnexecutedOrderGeneral(date);
	}






	/*
	 * 为酒店模块单独的接口
	 * 也可单独提出来
	 */
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param guestID 客户需要查看个人定过的酒店时依照的客户个人编号
	 * @return 客户定过的酒店列表
	 */
	public List<String> getBookedHotels(final String guestID) {
		return orderForHotelModule.getBookedHotels(guestID);
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/4
	 * @param guestID 此客户的客户编号
	 * @param hotelID 此客户相对的酒店编号
	 * @return 此客户在此相应酒店预定过的订单状态
	 */
	public OrderState getOrderState(String guestID, String hotelID) {
		return orderForHotelModule.getOrderState(guestID, hotelID);
	}

	/**
	 * @Description:通过preOrderVO中的信息计算订单的总价格
	 * @param preOrderVO
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月14日 上午12:25:05
	 */
	@Override
	public int getCalculatedPrice(PreOrderVO preOrderVO) {
		/*
		 * TODO fjj我把你那个计算价格的改写了一下，感觉你那个要的参数太多了，你看这个方法放哪里比较好
		 * 我不知道放哪里
		 */
		double total = 0;
		double originPrice = new Hotel().getOriginPrice(preOrderVO.hotelID, preOrderVO.roomType);
		DiscountInSpan discount = new DiscountCalculator();
		Iterator<Double> discounts = null;
		try {
			discounts = discount.getDiscountInSpan(preOrderVO);
		} catch (UserInexistException e) {
			e.printStackTrace();
		}
		while(discounts.hasNext()){
			total = total + originPrice*discounts.next()*preOrderVO.roomNum;
		}
		return (int)total;
	}
}

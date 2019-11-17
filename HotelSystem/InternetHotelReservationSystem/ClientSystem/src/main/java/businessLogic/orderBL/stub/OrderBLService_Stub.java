package businessLogic.orderBL.stub;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import businessLogicService.orderBLService.OrderBLService;
import utilities.enums.OrderState;
import utilities.enums.ResultMessage;
import utilities.enums.RoomType;
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
 * updateTime 2016/12/8
 */
public class OrderBLService_Stub implements OrderBLService {
	
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * new 一个stub来进行连接测试
	 */
	public OrderBLService_Stub() {
	}
	
//	/**
//	 * 
//	 * @author charles
//	 * @lastChangedBy charles
//	 * @updateTime 2016/12/4
//	 * @param orderVO 从客户界面层传下来的Order载体
//	 * @return 若客户创建此订单，需要付的款项
//	 */
//	public double getTempPrice(OrderVO orderVO) {
//		return 200;
//	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param orderVO 从客户界面层传下来的Order载体
	 * @return 客户成功创建此订单
	 */
	public ResultMessage createOrder(final OrderVO orderVO) {
		return ResultMessage.SUCCESS;
	}

//	/**
//	 * @author charles
//	 * @lastChangedBy charles
//	 * @updateTime 2016/11/27
//	 * @param orderID 酒店工作人员当前需要执行订单的订单号
//	 * @return 酒店工作人员成功执行此订单
//	 */
//	public ResultMessage executeOrder(final String orderID) {
//		return ResultMessage.SUCCESS;
//	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/8
	 * @param orderID 网站营销人员当前需要撤销的异常订单的订单号
	 * @param percent 撤销后需要恢复的信用值比例
	 * @return 网站营销人员是否成功按比例撤销此异常订单
	 */
	public ResultMessage undoAbnormalOrder(final String orderID, final double percent) {
		return ResultMessage.SUCCESS;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param orderID 客户当前需要撤销的正常订单的订单号
	 * @return 客户是否成功撤销此正常订单
	 */
	public ResultMessage undoNormalOrder(final String orderID) {
		return ResultMessage.SUCCESS;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/5
	 * @param orderID 用户当前需要查看的订单的订单号
	 * @return 一个简单的测试OrderVO
	 */
	public OrderVO getOrderDetail(final String orderID) {
		final LocalDateTime createTime = LocalDateTime.of(2016, 2, 2, 18, 30);
		final LocalDateTime checkInTime = LocalDateTime.of(2016, 2, 3, 11, 23);
		final LocalDateTime checkOutTime = LocalDateTime.of(2016, 2, 4, 10, 58);
		final LocalDateTime expectExecuteTime = LocalDateTime.of(2016, 2, 3, 14, 00);
		final LocalDateTime expectLeaveTime = LocalDateTime.of(2016, 2, 4, 12, 00);
		
		final OrderState orderState = OrderState.EXECUTED;
		final RoomType roomType = RoomType.BUSINESS_SUITE;
		
		return new OrderVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 200, 200,
				createTime, checkInTime, checkOutTime, expectExecuteTime, expectLeaveTime, orderState, false, true,
				roomType, 2, "301  302", 2, "zhangsan","13554321234", "no", 4.3, "good");
	}

//	/**
//	 * @author charles
//	 * @lastChangedBy charles
//	 * @updateTime 2016/12/5
//	 * @param guestID 客户要查看个人所有订单时，客户的编号
//	 * @return 客户个人所有订单
//	 */
//	public List<OrderGeneralVO> getAllGuestOrderGeneral(final String guestID) {
//		final List<OrderGeneralVO> orderGenerals = new ArrayList<OrderGeneralVO>();
//		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
//				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
//				OrderState.EXECUTED, false, true, "zhangsan","13554321234"));
//		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
//				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
//				OrderState.UNEXECUTED, false, false, "zhangsan","13554321234"));
//		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
//				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
//				OrderState.ABNORMAL, false, false, "zhangsan","13554321234"));
//		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
//				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
//				OrderState.CANCELLED, false, false, "zhangsan","13554321234"));
//		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
//				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
//				OrderState.EXECUTED, true, true, "zhangsan","13554321234"));
//		return orderGenerals;
//	}

//	/**
//	 * @author charles
//	 * @lastChangedBy charles
//	 * @updateTime 2016/12/7
//	 * @param guestID 客户要查看个人<所有未执行>订单时，客户的编号
//	 * @return 客户个人<所有未执行>订单
//	 */
//	public List<OrderGeneralVO> getAllGuestUnexecutedOrderGeneral(final String guestID) {
//		final List<OrderGeneralVO> orderGenerals = new ArrayList<OrderGeneralVO>();
//		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
//				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
//				OrderState.UNEXECUTED, false, false, "zhangsan","13554321234"));
//		return orderGenerals;
//	}
//	
//	/**
//	 * @author charles
//	 * @lastChangedBy charles
//	 * @updateTime 2016/12/7
//	 * @param guestID 客户要查看个人<所有已执行>订单时，客户的编号
//	 * @return 客户个人<所有已执行>订单
//	 */
//	public List<OrderGeneralVO> getAllGuestExecutedOrderGeneral(final String guestID) {
//		final List<OrderGeneralVO> orderGenerals = new ArrayList<OrderGeneralVO>();
//		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
//				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
//				OrderState.EXECUTED, false, true, "zhangsan","13554321234"));
//		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
//				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
//				OrderState.EXECUTED, true, true, "zhangsan","13554321234"));
//		return orderGenerals;
//	}
	
//	/**
//	 * @author charles
//	 * @lastChangedBy charles
//	 * @updateTime 2016/12/7
//	 * @param guestID 客户要查看个人<所有异常>订单时，客户的编号
//	 * @return 客户个人<所有异常>订单
//	 */
//	public List<OrderGeneralVO> getAllGuestAbnormalOrderGeneral(final String guestID) {
//		final List<OrderGeneralVO> orderGenerals = new ArrayList<OrderGeneralVO>();
//		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
//				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
//				OrderState.ABNORMAL, false, false, "zhangsan","13554321234"));
//		return orderGenerals;
//	}
//	
//	/**
//	 * @author charles
//	 * @lastChangedBy charles
//	 * @updateTime 2016/12/7
//	 * @param guestID 客户要查看个人<所有已撤销>订单时，客户的编号
//	 * @return 客户个人<所有已撤销>订单
//	 */
//	public List<OrderGeneralVO> getAllGuestCancelledOrderGeneral(final String guestID) {
//		final List<OrderGeneralVO> orderGenerals = new ArrayList<OrderGeneralVO>();
//		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
//				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
//				OrderState.CANCELLED, false, false, "zhangsan","13554321234"));
//		return orderGenerals;
//	}
//	
//	/**
//	 * @author charles
//	 * @lastChangedBy charles
//	 * @updateTime 2016/12/7
//	 * @param guestID 客户要查看个人<所有已评论>订单时，客户的编号
//	 * @return 客户个人<所有已评论>订单
//	 */
//	public List<OrderGeneralVO> getAllGuestCommentedOrderGeneral(final String guestID) {
//		final List<OrderGeneralVO> orderGenerals = new ArrayList<OrderGeneralVO>();
//		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
//				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
//				OrderState.EXECUTED, true, true, "zhangsan","13554321234"));
//		return orderGenerals;
//	}
	
//	/**
//	 * @author charles
//	 * @lastChangedBy charles
//	 * @updateTime 2016/12/7
//	 * @param guestID 客户要查看个人<所有未评价>订单时，客户的编号
//	 * @return 客户个人<所有未评价>订单
//	 */
//	public List<OrderGeneralVO> getAllGuestUncommentedOrderGeneral(final String guestID) {
//		final List<OrderGeneralVO> orderGenerals = new ArrayList<OrderGeneralVO>();
//		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
//				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
//				OrderState.EXECUTED, false, true, "zhangsan","13554321234"));
//		return orderGenerals;
//	}
	
//	/**
//	 * @author charles
//	 * @lastChangedBy charles
//	 * @updateTime 2016/12/10
//	 * @param guestID 客户编号
//	 * @param hotelID 目标酒店编号
//	 * @return 客户在目标酒店的所有订单记录
//	 */
//	public Iterator<OrderGeneralVO> getMyOrdersOfThisHotel(String guestID, String hotelID) {
//		final List<OrderGeneralVO> orderGenerals = new ArrayList<OrderGeneralVO>();
//		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
//				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
//				OrderState.EXECUTED, false, true, "zhangsan","13554321234"));
//		return orderGenerals.iterator();
//	}
	
//	/**
//	 * @author charles
//	 * @lastChangedBy charles
//	 * @updateTime 2016/12/5
//	 * @param hotelID 酒店要查看本酒店所有订单时，酒店的编号
//	 * @return 此酒店所有的所有订单
//	 */
//	public List<OrderGeneralVO> getAllHotelOrderGeneral(final String hotelID) {
//		final List<OrderGeneralVO> orderGenerals = new ArrayList<OrderGeneralVO>();
//		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
//				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
//				OrderState.EXECUTED, false, true, "zhangsan","13554321234"));
//		return orderGenerals;
//	}
//
//	/**
//	 * @author charles
//	 * @lastChangedBy charles
//	 * @updateTime 2016/12/7
//	 * @param hotelID 酒店要查看本酒店<所有未执行>订单时，酒店的编号
//	 * @return 此酒店<所有未执行>的所有订单
//	 */
//	public List<OrderGeneralVO> getAllHotelUnexecutedOrderGeneral(final String hotelID) {
//		final List<OrderGeneralVO> orderGenerals = new ArrayList<OrderGeneralVO>();
//		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
//				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
//				OrderState.UNEXECUTED, false, false, "zhangsan","13554321234"));
//		return orderGenerals;
//	}
//	
//	/**
//	 * @author charles
//	 * @lastChangedBy charles
//	 * @updateTime 2016/12/7
//	 * @param hotelID 酒店要查看本酒店<所有已执行>订单时，酒店的编号
//	 * @return 此酒店<所有已执行>订单
//	 */
//	public List<OrderGeneralVO> getAllHotelExecutedOrderGeneral(final String hotelID) {
//		final List<OrderGeneralVO> orderGenerals = new ArrayList<OrderGeneralVO>();
//		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
//				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
//				OrderState.EXECUTED, false, true, "zhangsan","13554321234"));
//		return orderGenerals;
//	}
//	
//	/**
//	 * @author charles
//	 * @lastChangedBy charles
//	 * @updateTime 2016/12/7
//	 * @param hotelID 酒店要查看本酒店<所有异常>订单时，酒店的编号
//	 * @return 此酒店所有异常>订单
//	 */
//	public List<OrderGeneralVO> getAllHotelAbnormalOrderGeneral(final String hotelID) {
//		final List<OrderGeneralVO> orderGenerals = new ArrayList<OrderGeneralVO>();
//		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
//				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
//				OrderState.ABNORMAL, false, false, "zhangsan","13554321234"));
//		return orderGenerals;
//	}
	
//	/**
//	 * @author charles
//	 * @lastChangedBy charles
//	 * @updateTime 2016/12/7
//	 * @param hotelID 酒店要查看本酒店<所有已撤销>订单时，酒店的编号
//	 * @return 此酒店<所有已撤销>订单
//	 */
//	public List<OrderGeneralVO> getAllHotelCancelledOrderGeneral(final String hotelID) {
//		final List<OrderGeneralVO> orderGenerals = new ArrayList<OrderGeneralVO>();
//		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
//				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
//				OrderState.CANCELLED, false, false, "zhangsan","13554321234"));
//		return orderGenerals;
//	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/5
	 * @param date 网站营销人员撤销异常订单时输入的指定日期
	 * @return 网站营销人员需要查看的当天所有的异常订单
	 */
	public List<OrderGeneralVO> getAllAbnormalOrderGeneral(final LocalDate date) {
		final List<OrderGeneralVO> orderGenerals = new ArrayList<OrderGeneralVO>();
		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
				OrderState.ABNORMAL, false, false, "zhangsan","13554321234"));
		return orderGenerals;
	}
	
//	/**
//	 * @author charles
//	 * @lastChangedBy charles
//	 * @updateTime 2016/12/5
//	 * @return 网站营销人员需要查看的所有的异常订单，按倒序排列
//	 */
//	public List<OrderGeneralVO> getAllAbnormalOrderGeneral() {
//		final List<OrderGeneralVO> orderGenerals = new ArrayList<OrderGeneralVO>();
//		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
//				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
//				OrderState.ABNORMAL, false, false, "zhangsan","13554321234"));
//		return orderGenerals;
//	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/5
	 * @param date 网站营销人员查看未执行订单时输入的指定日期
	 * @return 网站营销人员需要查看的当天所有的未执行订单
	 */
	@Override
	public List<OrderGeneralVO> getAllUnexecutedOrderGeneral(final LocalDate date) {
		final List<OrderGeneralVO> orderGenerals = new ArrayList<OrderGeneralVO>();
		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
				OrderState.UNEXECUTED, false, false, "zhangsan","13554321234"));
		return orderGenerals;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/4
	 * @param checkInVO 酒店工作人员更新订单入住信息
	 * @return 是否成功更新
	 */
	public ResultMessage updateCheckIn (CheckInVO checkInVO) {
		return ResultMessage.SUCCESS;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/4
	 * @param checkInVO 酒店工作人员更新订单退房信息
	 * @return 是否成功更新
	 */
	public ResultMessage updateCheckOut (CheckOutVO checkOutVO) {
		return ResultMessage.SUCCESS;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/2
	 * @param evaluationVO 客户评价单个订单时产生的订单
	 * @return 客户是否成功评价该订单
	 */
	@Override
	public ResultMessage addEvaluation(final GuestEvaluationVO evaluationVO) {
		return ResultMessage.SUCCESS;
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
		final List<HotelEvaluationVO> hotelEvaluations = new ArrayList<HotelEvaluationVO>();
		
		hotelEvaluations.add(new HotelEvaluationVO("1234567890", LocalDate.of(2016, 2, 3), 4.5, "very good"));
		hotelEvaluations.add(new HotelEvaluationVO("1234567891", LocalDate.of(2016, 4, 17), 4.5, "good"));
		hotelEvaluations.add(new HotelEvaluationVO("1234567891", LocalDate.of(2016, 11, 23), 4.3, "ok"));
		return hotelEvaluations.iterator();
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param guestID 客户需要查看个人定过的酒店时依照的客户个人编号
	 * @return 客户定过的酒店列表
	 */
	public List<String> getBookedHotels(final String guestID) {
		final List<String> bookedHotels = new ArrayList<String>();
		bookedHotels.add("12345678");
		bookedHotels.add("12345679");
		return bookedHotels;
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
		return OrderState.EXECUTED;
	}

	@Override
	public Iterator<OrderGeneralVO> getAllGuestCommentOrderGeneral(String guestID, boolean b) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Iterator<OrderGeneralVO> getAllOrderGenerals(String userID, UserType userType) {
		final List<OrderGeneralVO> orderGenerals = new ArrayList<OrderGeneralVO>();
		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
				OrderState.EXECUTED, false, true, "zhangsan","13554321234"));
		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
				OrderState.UNEXECUTED, false, false, "zhangsan","13554321234"));
		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
				OrderState.ABNORMAL, false, false, "zhangsan","13554321234"));
		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
				OrderState.CANCELLED, false, false, "zhangsan","13554321234"));
		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
				OrderState.EXECUTED, true, true, "zhangsan","13554321234"));
		return orderGenerals.iterator();
	}

	@Override
	public Iterator<OrderGeneralVO> getSpecialOrderGenerals(String userID, UserType userType, OrderState orderState) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCalculatedPrice(PreOrderVO preOrderVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<OrderGeneralVO> getAllHotelCheckOutOrderGeneral(String hotelID, boolean hasCheckOut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<OrderGeneralVO> getMyOrdersOfThisHotel(String guestID, String hotelID) {
		// TODO Auto-generated method stub
		return null;
	}

}

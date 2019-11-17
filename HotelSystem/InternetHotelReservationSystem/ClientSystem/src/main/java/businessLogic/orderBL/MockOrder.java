package businessLogic.orderBL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import businessLogic.promotionBL.DiscountCalculator;
import utilities.enums.OrderState;
import utilities.enums.ResultMessage;
import utilities.enums.RoomType;
import vo.GuestEvaluationVO;
import vo.HotelEvaluationVO;
import vo.OrderGeneralVO;
import vo.OrderVO;

/**
 * 
 * @author cuihua
 * lastChangedBy charles
 * updateTime 2016/12/5
 */
public class MockOrder extends Order {

	private DiscountCalculator discountInSpan;

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * 构造函数，初始化成员变量
	 */
	public MockOrder() {
		discountInSpan = new DiscountCalculator();
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param orderID 酒店工作人员当前需要执行订单的订单号
	 * @return 酒店工作人员是否成功执行此订单
	 */
	@Override
	public ResultMessage executeOrder(final String orderID) {
		return ResultMessage.SUCCESS;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/8
	 * @param orderID 网站营销人员当前需要撤销的异常订单的订单号
	 * @param percent 撤销后需要恢复的信用值比例
	 * @return 网站营销人员是否成功按比例撤销此异常订单
	 */
	@Override
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
	@Override
	public ResultMessage undoNormalOrder(final String orderID) {
		return ResultMessage.SUCCESS;
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
		final LocalDateTime createTime = LocalDateTime.of(2016, 2, 2, 18, 30);
		final LocalDateTime checkInTime = LocalDateTime.of(2016, 2, 3, 11, 23);
		final LocalDateTime checkOutTime = LocalDateTime.of(2016, 2, 4, 10, 58);
		final LocalDateTime expectExecuteTime = LocalDateTime.of(2016, 2, 3, 14, 00);
		final LocalDateTime expectLeaveTime = LocalDateTime.of(2016, 2, 4, 12, 00);
		
		final OrderState orderState = OrderState.EXECUTED;
		final RoomType roomType = RoomType.SINGLE_BED;
		
		final OrderVO orderVO = new OrderVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
				200, 200, createTime, checkInTime, checkOutTime, expectExecuteTime, expectLeaveTime, 
				orderState, false, true, roomType, 2, "301  302", 2, "zhangsan","13554321234", "no", 4.3, "good");
		
		return orderVO;

	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/5
	 * @param guestID 客户要查看个人所有订单时，客户的编号
	 * @return 客户个人所有订单
	 */
	@Override
	public List<OrderGeneralVO> getAllGuestOrderGeneral(final String guestID) {
		final List<OrderGeneralVO> orderGenerals = new ArrayList<OrderGeneralVO>();
		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
				OrderState.EXECUTED, false, true, "zhangsan","13554321234"));
		return orderGenerals;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/5
	 * @param hotelID 酒店要查看本酒店所有订单时，酒店的编号
	 * @return 此酒店所有的所有订单
	 */
	@Override
	public List<OrderGeneralVO> getAllHotelOrderGeneral(final String hotelID) {
		final List<OrderGeneralVO> orderGenerals = new ArrayList<OrderGeneralVO>();
		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 200, 
				LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
				OrderState.EXECUTED, false, true, "zhangsan","13554321234"));
		return orderGenerals;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/5
	 * @param date 网站营销人员撤销异常订单时输入的指定日期
	 * @return 网站营销人员需要查看的当天所有的异常订单
	 */
	@Override
	public List<OrderGeneralVO> getAllAbnormalOrderGeneral(final LocalDate date) {
		final List<OrderGeneralVO> orderGenerals = new ArrayList<OrderGeneralVO>();
		orderGenerals.add(new OrderGeneralVO("123456789012", "1234567890", "12345678", "thisHotel", "address", 
				200, LocalDateTime.of(2016, 2, 3, 14, 0), LocalDateTime.of(2016, 2, 4, 12, 0), 
				OrderState.ABNORMAL, false, false, "zhangsan","13554321234"));
		return orderGenerals;
	}
	
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
	 * @updateTime 2016/12/2
	 * @param evaluationVO 客户评价单个订单时产生的订单
	 * @return 客户是否成功评价该订单
	 */
	public ResultMessage addEvaluation(GuestEvaluationVO evaluationVO){
		return ResultMessage.SUCCESS;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/5
	 * @param hotelID 酒店工作人员／客户查看酒店的评论
	 * @return 此酒店的所有评价
	 */
	@Override
	public Iterator<HotelEvaluationVO> getEvaluations(String hotelID) {
		final List<HotelEvaluationVO> hotelEvaluations = new ArrayList<HotelEvaluationVO>();
		final LocalDate checkInTime = LocalDateTime.of(2016, 2, 3, 11, 23).toLocalDate();
		
		hotelEvaluations.add(new HotelEvaluationVO("1234567890", checkInTime, 4.5, "good"));
		return hotelEvaluations.iterator();
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param guestID 客户需要查看个人定过的酒店时依照的客户个人编号
	 * @return 客户定过的酒店列表
	 */
	@Override
	public List<String> getBookedHotels(final String guestID) {
		final List<String> bookedHotels = new ArrayList<String>();
		bookedHotels.add("12345678");
		bookedHotels.add("12345679");
		return bookedHotels;
	}

	/**
	 * @Description:获取订单的状态
	 * @param guestID
	 * @param hotelID
	 * @return
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月6日 上午9:12:40
	 */
	@Override
	public OrderState getOrderState(String guestID, String hotelID) {
		return OrderState.ABNORMAL;
	}
	
	
}

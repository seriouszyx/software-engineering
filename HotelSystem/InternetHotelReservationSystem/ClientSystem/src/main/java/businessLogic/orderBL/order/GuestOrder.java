package businessLogic.orderBL.order;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import businessLogic.creditBL.CreditController;
import businessLogic.hotelBL.hotel.Hotel;
import businessLogic.hotelBL.hotel.HotelInfoOperation;
import businessLogic.promotionBL.DiscountCalculator;
import businessLogic.promotionBL.DiscountInSpan;
import businessLogic.userBL.UserController;
import businessLogicService.creditBLService.CreditBLService;
import businessLogicService.orderBLService.GuestOrderBLService;
import businessLogicService.userBLService.UserBLService;
import dataService.orderDataService.OrderDataService;
import exception.verificationException.UserInexistException;
import po.GuestEvaluationPO;
import po.OrderPO;
import rmi.ClientRemoteHelper;
import utilities.enums.CreditRecord;
import utilities.enums.OrderState;
import utilities.enums.ResultMessage;
import utilities.enums.UserType;
import vo.CreditVO;
import vo.GuestEvaluationVO;
import vo.GuestVO;
import vo.OrderGeneralVO;
import vo.OrderVO;
import vo.PreOrderVO;

/**
 * 
 * @author charles lastChangedBy charles updateTime 2016/12/20
 *
 */
public class GuestOrder implements GuestOrderBLService {

	private OrderDataService orderDataService;

	private CommonOrder commonOrder;

	// creidt
	private CreditBLService creditBLService;

	// hotel
	private HotelInfoOperation hotelInterface;

	// promotion
	private DiscountInSpan discountCalculator;

	// user
	private UserBLService userBLService;

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27 构造函数，初始化成员变量
	 */
	public GuestOrder() {
		orderDataService = ClientRemoteHelper.getInstance().getOrderDataService();

		// try {
		// orderDataService = new OrderDataService_Stub();
		// } catch (RemoteException e) {
		// e.printStackTrace();
		// }

		commonOrder = new CommonOrder();

		creditBLService = CreditController.getInstance();		
		hotelInterface = new Hotel();
		discountCalculator = new DiscountCalculator();
		userBLService = UserController.getInstance();
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
		double total = 0;
		double originPrice = new Hotel().getOriginPrice(preOrderVO.hotelID, preOrderVO.roomType);
		Iterator<Double> discounts = null;
		try {
			discounts = discountCalculator.getDiscountInSpan(preOrderVO);
		} catch (UserInexistException e) {
			e.printStackTrace();
		}
		while(discounts.hasNext()){
			total = total + originPrice*discounts.next()*preOrderVO.roomNum;
		}
		return (int)total;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/18
	 * @param orderVO
	 *            从客户界面层传下来的Order载体
	 * @return 客户是否成功创建此订单
	 */
	public ResultMessage createOrder(final OrderVO orderVO) {
		ResultMessage msg1 = ResultMessage.FAIL;
		ResultMessage msg2 = ResultMessage.FAIL;

		GuestVO thisGuest = null;
		try {
			thisGuest = (GuestVO) userBLService.getSingle(orderVO.orderGeneralVO.guestID);
		} catch (UserInexistException e1) {
			e1.printStackTrace();
		}

		if (thisGuest.credit < 0) {
			// 信用值小于0时，不能预订酒店
			return ResultMessage.FAIL;
		} else {
			// 向数据层写入order数据
			if (orderVO.orderGeneralVO.orderID == null && orderVO.checkInTime == null && orderVO.checkOutTime == null
					&& orderVO.roomNumber == "" && orderVO.score == -1 && orderVO.comment == "") {
				try {
					msg1 = orderDataService.createOrder(new OrderPO(orderVO));
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}

			// 更新酒店剩余房间信息
			System.out.println(orderVO.orderGeneralVO.hotelID);
			System.out.println(orderVO.roomType);
			System.out.println(orderVO.roomNumCount);

			msg2 = hotelInterface.checkIn(orderVO.orderGeneralVO.hotelID, orderVO.roomType, orderVO.roomNumCount);

			if (msg1 == ResultMessage.SUCCESS && msg2 == ResultMessage.SUCCESS) {
				return ResultMessage.SUCCESS;
			} else {
				return ResultMessage.FAIL;
			}
		}
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/20
	 * @param orderID
	 *            客户当前需要撤销的正常订单的订单号
	 * @return 客户是否成功撤销此正常订单
	 */
	public ResultMessage undoNormalOrder(final String orderID) {
		ResultMessage msg1 = ResultMessage.FAIL;
		ResultMessage msg2 = ResultMessage.FAIL;

		OrderVO thisOrder = commonOrder.getOrderDetail(orderID);
		// 撤销未执行订单，改变此订单状态
		final OrderState thisOrderState = thisOrder.orderGeneralVO.state;
		if (thisOrderState == OrderState.UNEXECUTED) {
			try {
				msg1 = orderDataService.undoNormalOrder(orderID);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		// 如果撤销时间距离订单最晚执行时间不足6小时，扣除订单价值一半的信用值
		if (in6Hours(thisOrder.orderGeneralVO.expectExecuteTime)) {
			try {
				final GuestVO thisGuest = (GuestVO) userBLService.getSingle(thisOrder.orderGeneralVO.guestID);
				final double previousCredit = thisGuest.credit;
				final double afterCredit = previousCredit - thisOrder.orderGeneralVO.price / 2;
				CreditVO creditVO = new CreditVO(thisOrder.orderGeneralVO.guestID, LocalDateTime.now(), orderID,
						previousCredit, afterCredit, CreditRecord.CANCEL_IN_SIX_HOURS);

				creditBLService.addCreditRecord(creditVO);
			} catch (UserInexistException e) {
				e.printStackTrace();
			}
		}

		// 更新此订单撤销后的酒店剩余房间数
		msg2 = hotelInterface.updateRemainRoomNumForUndoOrder(thisOrder.orderGeneralVO.hotelID, thisOrder.roomType,
				thisOrder.roomNumCount);

		if (msg1 == ResultMessage.SUCCESS && msg2 == ResultMessage.SUCCESS) {
			return ResultMessage.SUCCESS;
		} else {
			return ResultMessage.FAIL;
		}
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/8
	 * @param evaluationVO
	 *            客户评价单个订单时产生的订单
	 * @return 客户是否成功评价该订单
	 */
	public ResultMessage addEvaluation(GuestEvaluationVO evaluationVO) {
		ResultMessage msg1 = ResultMessage.FAIL;
		ResultMessage msg2 = ResultMessage.FAIL;

		try {
			msg1 = orderDataService.addEvaluation(new GuestEvaluationPO(evaluationVO));

			String hotelID = commonOrder.getOrderDetail(evaluationVO.orderID).orderGeneralVO.hotelID;
			msg2 = hotelInterface.scoreUpdate(hotelID, evaluationVO.score);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		if (msg1 == ResultMessage.SUCCESS && msg2 == ResultMessage.SUCCESS) {
			return ResultMessage.SUCCESS;
		} else {
			return ResultMessage.FAIL;
		}

	}

	/**
	 * @author charles
	 * @lastChangedBy Harvey
	 * @updateTime 2016/12/8
	 * @param guestID
	 *            客户要查看个人<已执行／未执行>订单时，客户的编号
	 * @return 客户个人<已执行／未执行>订订单
	 * 
	 *         <<已执行／未执行>只包含一种
	 */
	public Iterator<OrderGeneralVO> getAllGuestCommentOrderGeneral(String guestID, boolean hasCommented) {
		List<OrderGeneralVO> result = new ArrayList<OrderGeneralVO>();
		System.out.println("guestID: " + guestID);

		final Iterator<OrderGeneralVO> orderGenerals = commonOrder.getAllOrderGenerals(guestID, UserType.GUEST);

		while (orderGenerals.hasNext()) {
			OrderGeneralVO thisOrderGeneral = orderGenerals.next();
			if (thisOrderGeneral.state == OrderState.EXECUTED && thisOrderGeneral.hasCommented == hasCommented) {
				result.add(thisOrderGeneral);
			}
		}
		return result.iterator();
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/10
	 * @param guestID
	 *            客户编号
	 * @param hotelID
	 *            目标酒店编号
	 * @return 客户在目标酒店的所有订单记录
	 */
	public Iterator<OrderGeneralVO> getMyOrdersOfThisHotel(String guestID, String hotelID) {
		List<OrderGeneralVO> result = new ArrayList<OrderGeneralVO>();
		final Iterator<OrderGeneralVO> orderGenerals = commonOrder.getAllOrderGenerals(guestID, UserType.GUEST);

		while (orderGenerals.hasNext()) {
			OrderGeneralVO thisOrderGeneral = orderGenerals.next();
			if (thisOrderGeneral.hotelID.equals(hotelID)) {
				result.add(thisOrderGeneral);
			}
		}
		return result.iterator();
	}

	private boolean in6Hours(LocalDateTime expectExecuteTime) {
		final LocalDateTime last = LocalDateTime.now().plusHours(6);
		if (last.getYear() < expectExecuteTime.getYear()) {
			return false;
		} else {
			// 同一年
			if (last.getDayOfYear() < expectExecuteTime.getDayOfYear()) {
				return false;
			} else if (last.getDayOfYear() > expectExecuteTime.getDayOfYear()) {
				// 原本不足6小时，加了之后增加了一天
				return true;
			} else {
				// 同一天
				final int lastSecond = last.getHour() * 3600 + last.getMinute() * 60 + last.getSecond();
				final int expectSecond = expectExecuteTime.getHour() * 3600 + expectExecuteTime.getMinute() * 60
						+ expectExecuteTime.getSecond();
				int differ = expectSecond - lastSecond;
				if (differ >= 0) {
					return false;
				} else {
					return true;
				}
			}
		}
	}
}

package dataServiceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dataHelper.OrderDataHelper;
import dataHelperImpl.DataFactoryImpl;
import dataService.orderDataService.OrderDataService;
import po.CheckInPO;
import po.CheckOutPO;
import po.GuestEvaluationPO;
import po.HotelEvaluationPO;
import po.OrderGeneralPO;
import po.OrderPO;
import utilities.Ciphertext;
import utilities.enums.OrderState;
import utilities.enums.ResultMessage;

/**
 * 
 * @author charles lastChangedBy charles updateTime 2016/12/7
 *
 */
public class OrderDataServiceImpl extends UnicastRemoteObject implements OrderDataService {

	private static final long serialVersionUID = -1210458390069208485L;

	private OrderDataHelper orderDataHelper;

	// utilities
	private Ciphertext ciphertext;

	public OrderDataServiceImpl() throws RemoteException {
		super();
		orderDataHelper = DataFactoryImpl.getInstance().getOrderDataHelper();

		ciphertext = new Ciphertext();
	}

	/**
	 * 
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/10
	 * @param order
	 *            从逻辑层层传下来的Order载体
	 * @return 客户是否成功创建此订单
	 * @throws RemoteException
	 *             RMI
	 */
	@Override
	public ResultMessage createOrder(final OrderPO order) throws RemoteException {
		String random = formateRandomNumber((int) (Math.random() * 10000));
		String date = formateDate(order.getCreateTime().toLocalDate());

		order.setOrderID(random + date);

		// 对写入的订单数据项加密
		order.setName(ciphertext.encrypt(order.getName()));
		order.setPhone(ciphertext.encrypt(order.getPhone()));

		return orderDataHelper.add(order);
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/10
	 * @param orderID
	 *            酒店工作人员当前需要执行订单的订单号
	 * @return 酒店工作人员是否成功执行此订单
	 * @throws RemoteException
	 *             RMI
	 */
	@Override
	public ResultMessage executeOrder(final String orderID) throws RemoteException {
		return orderDataHelper.setState(orderID, OrderState.EXECUTED);
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/10
	 * @param orderID
	 *            网站营销人员当前需要撤销的异常订单的订单号
	 * @return 网站营销人员是否成功撤销此异常订单
	 * @throws RemoteException
	 *             RMI
	 */
	@Override
	public ResultMessage undoAbnormalOrder(final String orderID, final double percent) throws RemoteException {
		return orderDataHelper.setState(orderID, OrderState.CANCELLED);
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/7
	 * @param orderID
	 *            客户当前需要撤销的正常订单的订单号
	 * @return 客户是否成功撤销此正常订单
	 * @throws RemoteException
	 *             RMI
	 */
	@Override
	public ResultMessage undoNormalOrder(final String orderID) throws RemoteException {
		return orderDataHelper.setState(orderID, OrderState.CANCELLED);
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param orderID
	 *            用户当前需要查看的订单的订单号
	 * @return 此被需要订单的详情载体
	 * @throws RemoteException
	 *             RMI
	 */
	@Override
	public OrderPO getOrderDetail(final String orderID) throws RemoteException {
		OrderPO resultPO = orderDataHelper.getSingleOrder(orderID);

		// 解密
		resultPO.setName(ciphertext.decode(resultPO.getName()));
		resultPO.setPhone(ciphertext.decode(resultPO.getPhone()));

		return resultPO;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param guestID
	 *            客户要查看个人所有订单时，客户的编号
	 * @return 客户个人所有订单
	 * @throws RemoteException
	 *             RMI
	 */
	@Override
	public List<OrderGeneralPO> getAllGuestOrderGeneral(final String guestID) throws RemoteException {
		return convertPOsToDecodedGenerals(orderDataHelper.getAllOrderOfGuest(guestID));
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param hotelID
	 *            酒店要查看本酒店所有订单时，酒店的编号
	 * @return 此酒店所有的所有订单
	 * @throws RemoteException
	 *             RMI
	 */
	@Override
	public List<OrderGeneralPO> getAllHotelOrderGeneral(final String hotelID) throws RemoteException {
		return convertPOsToDecodedGenerals(orderDataHelper.getAllOrderOfHotel(hotelID));
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/5
	 * @param date 网站营销人员撤销异常订单时输入的指定日期
	 * @return 网站营销人员需要查看的当天所有的异常订单
	 * @throws RemoteException RMI
	 * 
	 * 直接从本层getAllAbnormalOrderGeneral()走
	 */
	public List<OrderGeneralPO> getAllAbnormalOrderGeneral(LocalDate date) throws RemoteException {
		List<OrderGeneralPO> abnormalGenerals = getAllAbnormalOrderGeneral();
		
		for (int i = 0; i < abnormalGenerals.size(); i++) {
			LocalDate temp = abnormalGenerals.get(i).getExpectExecuteTime().toLocalDate();
			if (!(temp.getYear() == date.getYear() && temp.getMonth() == date.getMonth()
					&& temp.getDayOfMonth() == date.getDayOfMonth())) {
				abnormalGenerals.remove(abnormalGenerals.get(i));
				System.out.println("remove: " +abnormalGenerals.get(i).getOrderID());
				//因为移除此结点，序数下标恢复
				i--;
			}
		}

		return abnormalGenerals;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/5
	 * @param date 网站营销人员查看未执行订单时输入的指定日期
	 * @return 网站营销人员需要查看的当天所有的未执行订单
	 * @throws RemoteException RMI
	 */
	@Override
	public List<OrderGeneralPO> getAllUnexecutedOrderGeneral(final LocalDate date) throws RemoteException {
		List<OrderGeneralPO> unexecutedGenerals = convertPOsToDecodedGenerals(orderDataHelper.getUnexecuted());

		for (int i = 0; i < unexecutedGenerals.size(); i++) {
			LocalDate temp = unexecutedGenerals.get(i).getExpectExecuteTime().toLocalDate();
			if (!(temp.getYear() == date.getYear() && temp.getMonth() == date.getMonth()
					&& temp.getDayOfMonth() == date.getDayOfMonth())) {
				unexecutedGenerals.remove(unexecutedGenerals.get(i));

				//因为移除此结点，序数下标恢复
				i--;
			}
			
		}

		return unexecutedGenerals;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/7
	 * @param checkInPO
	 *            酒店工作人员更新订单入住信息
	 * @return 是否成功更新
	 */
	@Override
	public ResultMessage updateCheckIn(CheckInPO checkInPO) throws RemoteException {
		// 更新订单的入住信息
		ResultMessage msg1 = orderDataHelper.setCheckIn(checkInPO.getOrderID(), checkInPO.getRoomNumber(),
				checkInPO.getCheckInTime(), checkInPO.getExpectLeaveTime());
		// 更新订单状态
		ResultMessage msg2 = orderDataHelper.setState(checkInPO.getOrderID(), OrderState.EXECUTED);

		if (msg1 == ResultMessage.SUCCESS && msg2 == ResultMessage.SUCCESS) {
			System.out.println("Success");
			return ResultMessage.SUCCESS;
		} else {
			System.out.println("FAIl");
			return ResultMessage.FAIL;
		}
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/7
	 * @param checkOutPO
	 *            酒店工作人员更新订单退房信息
	 * @return 是否成功更新
	 */
	@Override
	public ResultMessage updateCheckOut(CheckOutPO checkOutPO) throws RemoteException {
		return orderDataHelper.setCheckOut(checkOutPO.getOrderID(), checkOutPO.getCheckOutTime());
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/7
	 * @param guestEvaluationPO
	 *            客户评价单个订单时产生的订单
	 * @return 客户是否成功评价该订单
	 * @throws RemoteException
	 *             RMI
	 */
	@Override
	public ResultMessage addEvaluation(GuestEvaluationPO guestEvaluationPO) throws RemoteException {
		ResultMessage msg1 = orderDataHelper.setEvaluation(guestEvaluationPO.getOrderID(), guestEvaluationPO.getScore(),
				guestEvaluationPO.getComment());
		ResultMessage msg2 = orderDataHelper.setHasCommentBool(guestEvaluationPO.getOrderID());

		if (msg1 == ResultMessage.SUCCESS && msg2 == ResultMessage.SUCCESS) {
			return ResultMessage.SUCCESS;
		} else {
			return ResultMessage.FAIL;
		}
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/4
	 * @param hotelID
	 *            酒店工作人员／客户查看酒店的评论
	 * @return 此酒店的所有评价
	 * @throws RemoteException
	 *             RMI
	 */
	@Override
	public List<HotelEvaluationPO> getEvaluations(String hotelID) throws RemoteException {
		List<OrderPO> hotelPOs = orderDataHelper.getAllOrderOfHotel(hotelID);
		List<HotelEvaluationPO> result = new ArrayList<HotelEvaluationPO>();

		for (int i = 0; i < hotelPOs.size(); i++) {
			OrderPO thisOrder = hotelPOs.get(i);
			result.add(new HotelEvaluationPO(thisOrder.getGuestID(), thisOrder.getCheckInTime().toLocalDate(),
					thisOrder.getScore(), thisOrder.getComment()));
		}
		return result;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param guestID
	 *            客户需要查看个人定过的酒店时依照的客户个人编号
	 * @return 客户定过的酒店列表
	 * @throws RemoteException
	 *             RMI
	 */
	@Override
	public List<String> getBookedHotels(final String guestID) throws RemoteException {
		List<OrderPO> guestOrders = orderDataHelper.getAllOrderOfGuest(guestID);
		List<String> result = new ArrayList<String>();

		for (OrderPO guestOrder : guestOrders) {
			String thisHotelID = guestOrder.getHotelID();

			// 遍历result中是否已加入此酒店
			boolean alreadyHasThisHotel = false;
			for (String resultOne : result) {
				if (resultOne.equals(thisHotelID)) {
					alreadyHasThisHotel = true;
					break;
				}
			}

			if (alreadyHasThisHotel == false) {
				result.add(thisHotelID);
			}
		}

		return result;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param randomNumber
	 *            随机生成的4位数，便于验证
	 * @return 标准格式化此4位数之后的String
	 */
	private String formateRandomNumber(int randomNumber) {
		if (randomNumber >= 1000 && randomNumber <= 9999) {
			return String.valueOf(randomNumber);
		} else if (randomNumber > 100 && randomNumber <= 999) {
			return "0" + String.valueOf(randomNumber);
		} else if (randomNumber > 10 && randomNumber <= 99) {
			return "00" + String.valueOf(randomNumber);
		} else {
			return "000" + String.valueOf(randomNumber);
		}
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param localDate
	 *            订单的createDate
	 * @return 标准格式化此时间之后的String
	 */
	private String formateDate(LocalDate localDate) {
		String temp = localDate.toString();
		return temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8);
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/5
	 * @return 网站营销人员需要查看的所有的异常订单，按倒序排列
	 * @throws RemoteException RMI
	 */
	private List<OrderGeneralPO> getAllAbnormalOrderGeneral(){
		return convertPOsToDecodedGenerals(orderDataHelper.getAbnormal());
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/15
	 * @param orders 需要被转换的List<orderPO>
	 * @return 数据解密之后的订单概况们
	 */
	private List<OrderGeneralPO> convertPOsToDecodedGenerals(List<OrderPO> orders) {
		List<OrderGeneralPO> result = new ArrayList<OrderGeneralPO>();
		for (OrderPO guestOrder : orders) {
			guestOrder.setName(ciphertext.decode(guestOrder.getName()));
			guestOrder.setPhone(ciphertext.decode(guestOrder.getPhone()));
			result.add(new OrderGeneralPO(guestOrder));
		}
		return result;
	}
}

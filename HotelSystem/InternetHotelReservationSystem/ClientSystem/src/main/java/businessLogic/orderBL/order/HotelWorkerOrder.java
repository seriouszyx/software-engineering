package businessLogic.orderBL.order;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import businessLogic.creditBL.CreditController;
import businessLogic.hotelBL.hotel.Hotel;
import businessLogic.hotelBL.hotel.HotelInfoOperation;
import businessLogic.userBL.UserController;
import businessLogicService.creditBLService.CreditBLService;
import businessLogicService.orderBLService.HotelWorkerOrderBLService;
import businessLogicService.userBLService.UserBLService;
import dataService.orderDataService.OrderDataService;
import exception.verificationException.CheckInException;
import exception.verificationException.CheckOutException;
import exception.verificationException.UserInexistException;
import po.CheckInPO;
import po.CheckOutPO;
import rmi.ClientRemoteHelper;
import utilities.enums.CreditRecord;
import utilities.enums.OrderState;
import utilities.enums.ResultMessage;
import utilities.enums.UserType;
import vo.CheckInVO;
import vo.CheckOutVO;
import vo.CreditVO;
import vo.GuestVO;
import vo.OrderGeneralVO;
import vo.OrderVO;

/**
 * 
 * @author charles
 * lastChangedBy charles
 * updateTime 2016/12/9
 *
 */
public class HotelWorkerOrder implements HotelWorkerOrderBLService {

	private OrderDataService orderDataService;

	private CommonOrder commonOrder;
	
	//hotel
	private HotelInfoOperation hotelInterface;
	
	//creidt
	private CreditBLService creditBLService;
	
	//user
	private UserBLService userBLService;
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * 构造函数，初始化成员变量
	 */
	public HotelWorkerOrder() {
		orderDataService = ClientRemoteHelper.getInstance().getOrderDataService();
		
//		try {
//			orderDataService = new OrderDataService_Stub();
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
		
		commonOrder = new CommonOrder();
		
		hotelInterface = new Hotel();
		creditBLService = CreditController.getInstance();
		userBLService = UserController.getInstance();
	}
	
//	/**
//	 * @author charles
//	 * @lastChangedBy charles
//	 * @updateTime 2016/12/9
//	 * @param orderID 酒店工作人员当前需要执行订单的订单号
//	 * @return 酒店工作人员是否成功执行此订单
//	 */
//	public ResultMessage executeOrder(final String orderID) {
//		ResultMessage msg1 = ResultMessage.FAIL;
//		ResultMessage msg2 = ResultMessage.FAIL;
//		
//		OrderState thisOrderState = commonOrder.getOrderDetail(orderID).orderGeneralVO.state;
//		if (thisOrderState == OrderState.UNEXECUTED || thisOrderState == OrderState.ABNORMAL) {
//			try {
//				//执行订单，修改订单状态
//				msg1 = orderDataService.executeOrder(orderID);
//				
//				//添加信用记录
//				final OrderVO thisOrder = commonOrder.getOrderDetail(orderID);
//				final GuestVO thisGuest = (GuestVO) userBLService.getSingle(thisOrder.orderGeneralVO.guestID);
//				
//				final LocalDateTime time = LocalDateTime.now();
//				
//				CreditVO creditVO = null;
//				double afterCredit = 0;
//				if (thisOrderState == OrderState.UNEXECUTED) {
//					afterCredit = thisGuest.credit + thisOrder.orderGeneralVO.price;
//					creditVO = new CreditVO(thisOrder.orderGeneralVO.guestID, time, 
//							thisOrder.orderGeneralVO.orderID, thisGuest.credit, afterCredit, CreditRecord.EXECUTE);
//				}else {
//					//因为置为异常时被扣了相应的信用值，故在此先加回来再增加
//					afterCredit = thisGuest.credit + 2*thisOrder.orderGeneralVO.price;
//					creditVO = new CreditVO(thisOrder.orderGeneralVO.guestID, time, 
//							thisOrder.orderGeneralVO.orderID, thisGuest.credit, afterCredit, CreditRecord.ABNORMAL_EXECUTE);
//				}
//				msg2 = creditBLService.addCreditRecord(creditVO);
//			} catch (RemoteException | UserInexistException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		if (msg1 == ResultMessage.SUCCESS && msg2 == ResultMessage.SUCCESS) {
//			return ResultMessage.SUCCESS;
//		}else {
//			return ResultMessage.FAIL;
//		}
//	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/15
	 * @param checkInVO 酒店工作人员更新订单入住信息
	 * @return 是否成功更新
	 * @throws CheckInException 
	 */
	public ResultMessage updateCheckIn (CheckInVO checkInVO) throws CheckInException {
		ResultMessage msg1 = ResultMessage.FAIL;
		ResultMessage msg2 = ResultMessage.FAIL;
		
		final OrderVO thisOrder = commonOrder.getOrderDetail(checkInVO.orderID);
		final OrderState thisOrderState = thisOrder.orderGeneralVO.state;	
		
		if (thisOrderState == OrderState.UNEXECUTED || thisOrderState == OrderState.ABNORMAL) {

			// 检查入住日期的正确
			if (!checkInVO.checkInTime.toLocalDate() .equals( thisOrder.orderGeneralVO.expectExecuteTime.toLocalDate())) {
				System.out.println(checkInVO.checkInTime.toLocalDate());
				System.out.println(thisOrder.orderGeneralVO.expectExecuteTime.toLocalDate());
				throw new CheckInException();
			}
			
			try {
		
				//更新订单入住信息和状态
				msg1 = orderDataService.updateCheckIn(new CheckInPO(checkInVO));

				// 添加信用记录
				final GuestVO thisGuest = (GuestVO) userBLService.getSingle(thisOrder.orderGeneralVO.guestID);

				final LocalDateTime time = LocalDateTime.now();

				CreditVO creditVO = null;
				double afterCredit = 0;
				if (thisOrderState == OrderState.UNEXECUTED) {
					afterCredit = thisGuest.credit + thisOrder.orderGeneralVO.price;
					creditVO = new CreditVO(thisOrder.orderGeneralVO.guestID, time, thisOrder.orderGeneralVO.orderID,
							thisGuest.credit, afterCredit, CreditRecord.EXECUTE);
				} else {
					// 因为置为异常时被扣了相应的信用值，故在此先加回来再增加
					afterCredit = thisGuest.credit + 2 * thisOrder.orderGeneralVO.price;
					creditVO = new CreditVO(thisOrder.orderGeneralVO.guestID, time, thisOrder.orderGeneralVO.orderID,
							thisGuest.credit, afterCredit, CreditRecord.ABNORMAL_EXECUTE);
				}
				msg2 = creditBLService.addCreditRecord(creditVO);

			} catch (RemoteException | UserInexistException e) {
				e.printStackTrace();
			}
		}
		
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
	 * @param checkInVO 酒店工作人员更新订单退房信息
	 * @return 是否成功更新
	 * @throws CheckOutException 
	 */
	public ResultMessage updateCheckOut (CheckOutVO checkOutVO) throws CheckOutException {
		ResultMessage msg1 = ResultMessage.FAIL;
		ResultMessage msg2 = ResultMessage.FAIL;
		
		OrderVO thisOrder = commonOrder.getOrderDetail(checkOutVO.orderID);
		
		// 检查入住日期的正确
		if (!checkOutVO.checkOutTime.toLocalDate() .equals( thisOrder.orderGeneralVO.expectLeaveTime.toLocalDate())) {
			throw new CheckOutException();
		}

		//更新订单
		try {
			msg1 = orderDataService.updateCheckOut((new CheckOutPO(checkOutVO)));
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		//更新酒店剩余房间信息
		msg2 = hotelInterface.checkOut(thisOrder.orderGeneralVO.hotelID, thisOrder.roomType, thisOrder.roomNumCount);
		
		if (msg1 == ResultMessage.SUCCESS && msg2 == ResultMessage.SUCCESS) {
			return ResultMessage.SUCCESS;
		}else {
			return ResultMessage.FAIL;
		}
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
		List<OrderGeneralVO> result = new ArrayList<OrderGeneralVO>(); 
		System.out.println("HotelID: " + hotelID);
		
		final Iterator<OrderGeneralVO> orderGenerals = commonOrder.getAllOrderGenerals(hotelID, UserType.HOTEL_WORKER);

		while(orderGenerals.hasNext()){
			OrderGeneralVO thisOrderGeneral = orderGenerals.next();
			if(thisOrderGeneral.state == OrderState.EXECUTED && thisOrderGeneral.hasCheckOut == hasCheckOut){
				result.add(thisOrderGeneral);
			}
		}
		return result.iterator();
	}

}

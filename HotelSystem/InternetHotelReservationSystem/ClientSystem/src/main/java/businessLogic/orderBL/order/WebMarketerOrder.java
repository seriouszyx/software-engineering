package businessLogic.orderBL.order;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import businessLogic.creditBL.CreditController;
import businessLogic.userBL.UserController;
import businessLogicService.creditBLService.CreditBLService;
import businessLogicService.orderBLService.WebMarketerOrderBLService;
import businessLogicService.userBLService.UserBLService;
import dataService.orderDataService.OrderDataService;
import exception.verificationException.UserInexistException;
import po.OrderGeneralPO;
import rmi.ClientRemoteHelper;
import utilities.enums.CreditRecord;
import utilities.enums.OrderState;
import utilities.enums.ResultMessage;
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
public class WebMarketerOrder implements WebMarketerOrderBLService {

	private OrderDataService orderDataService;

	private CommonOrder commonOrder;
	
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
	public WebMarketerOrder() {
		orderDataService = ClientRemoteHelper.getInstance().getOrderDataService();
		
//		try {
//			orderDataService = new OrderDataService_Stub();
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
		
		commonOrder = new CommonOrder();
		
		creditBLService = CreditController.getInstance();
		userBLService = UserController.getInstance();
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/9
	 * @param orderID 网站营销人员当前需要撤销的异常订单的订单号
	 * @param percent 撤销后需要恢复的信用值比例
	 * @return 网站营销人员是否成功按比例撤销此异常订单
	 */
	public ResultMessage undoAbnormalOrder(final String orderID, final double percent) {		
		ResultMessage msg1 = ResultMessage.FAIL;
		ResultMessage msg2 = ResultMessage.FAIL;
		
		OrderVO thisOrder = commonOrder.getOrderDetail(orderID);
		OrderState thisOrderState = thisOrder.orderGeneralVO.state;
		if (thisOrderState == OrderState.ABNORMAL) {
			try {
				//撤销异常订单，将其状态只为已撤销
				msg1 = orderDataService.undoAbnormalOrder(orderID, percent);
				
				//修改信用值并添加信用记录
				/*
				 * 因为数据的问题，此时getOrderDetail得到的是一个UNEXECUTED对象，所以执行会抛异常
				 * 但是若是数据正确的话，就没有问题
				 */
				GuestVO thisGuest = (GuestVO)userBLService.getSingle(thisOrder.orderGeneralVO.guestID);
				final LocalDateTime time = LocalDateTime.now();
				final double afterCredit = thisGuest.credit - thisOrder.orderGeneralVO.price * percent;
				final CreditVO creditVO = new CreditVO(thisOrder.orderGeneralVO.guestID, time, 
						thisOrder.orderGeneralVO.orderID, thisGuest.credit, afterCredit, CreditRecord.UNDO_ABNORMAL);
				msg2 = creditBLService.addCreditRecord(creditVO);
			} catch (RemoteException | UserInexistException e) {
				e.printStackTrace();
			}
		}
		
		if (msg1 == ResultMessage.SUCCESS && msg2 == ResultMessage.SUCCESS) {
			return ResultMessage.SUCCESS;
		}else {
			return ResultMessage.FAIL;
		}
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param date 网站营销人员撤销异常订单时输入的指定日期
	 * @return 网站营销人员需要查看的当天所有的异常订单
	 */
	public List<OrderGeneralVO> getAllAbnormalOrderGeneral(final LocalDate date) {
		final List<OrderGeneralVO> result = new ArrayList<OrderGeneralVO>();
		
		List<OrderGeneralPO> orderGeneralPOs = null;
		try {
			orderGeneralPOs = orderDataService.getAllAbnormalOrderGeneral(date);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		if (orderGeneralPOs != null) {
			for (int i = 0; i < orderGeneralPOs.size(); i++) {
				result.add(new OrderGeneralVO(orderGeneralPOs.get(i)));
			}
		}
		
		return result;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/29
	 * @param date 网站营销人员查看未执行订单时输入的指定日期
	 * @return 网站营销人员需要查看的当天所有的未执行订单
	 */
	public List<OrderGeneralVO> getAllUnexecutedOrderGeneral(final LocalDate date) {
		final List<OrderGeneralVO> result = new ArrayList<OrderGeneralVO>();
		
		List<OrderGeneralPO> orderGeneralPOs = null;
		try {
			orderGeneralPOs = orderDataService.getAllUnexecutedOrderGeneral(date);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		if (orderGeneralPOs != null) {
			for (int i = 0; i < orderGeneralPOs.size(); i++) {
				result.add(new OrderGeneralVO(orderGeneralPOs.get(i)));
			}
		}
		
		return result;
	}
}

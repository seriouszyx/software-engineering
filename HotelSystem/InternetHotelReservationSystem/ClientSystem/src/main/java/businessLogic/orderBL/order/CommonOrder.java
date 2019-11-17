package businessLogic.orderBL.order;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import businessLogicService.orderBLService.CommonOrderBLService;
import dataService.orderDataService.OrderDataService;
import po.HotelEvaluationPO;
import po.OrderGeneralPO;
import rmi.ClientRemoteHelper;
import utilities.enums.OrderState;
import utilities.enums.UserType;
import vo.HotelEvaluationVO;
import vo.OrderGeneralVO;
import vo.OrderVO;

/**
 * 
 * @author charles
 * lastChangedBy charles
 * updateTime 2016/12/8
 *
 */
public class CommonOrder implements CommonOrderBLService {

	private OrderDataService orderDataService;

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * 构造函数，初始化成员变量
	 */
	public CommonOrder() {
		orderDataService = ClientRemoteHelper.getInstance().getOrderDataService();

//		try {
//			orderDataService = new OrderDataService_Stub();
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/8
	 * @param userID 客户编号
	 * @param userType 客户类型：客户／酒店工作人员
	 * @return 特定用户类型的全部订单
	 */
	public Iterator<OrderGeneralVO> getAllOrderGenerals(String userID, UserType userType) {
		List<OrderGeneralVO> result = new ArrayList<OrderGeneralVO>();
		List<OrderGeneralPO> orderGeneralPOs = null;

		try {
			if(userType == UserType.GUEST){
				orderGeneralPOs = orderDataService.getAllGuestOrderGeneral(userID);
			}else{
				orderGeneralPOs = orderDataService.getAllHotelOrderGeneral(userID);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		if (orderGeneralPOs != null) {
			for (OrderGeneralPO orderGeneralPO : orderGeneralPOs) {
				result.add(new OrderGeneralVO(orderGeneralPO));
			}
		}
		
		
		
		return result.iterator();
	}
	
	/**
	 * @author Harvey
	 * @lastChangedBy charles
	 * @updateTime 2016/12/8
	 * @param userID 客户编号
	 * @param userType 客户类型：客户／酒店工作人员
	 * @param orderState <所有某种特定类型>包括：未执行、已执行、异常、已撤销
	 * @return 需要得到的<所有某种特定类型>的order
	 */
	public Iterator<OrderGeneralVO> getSpecialOrderGenerals(String userID, UserType userType, OrderState orderState) {
		List<OrderGeneralVO> result = new ArrayList<OrderGeneralVO>();
		List<OrderGeneralPO> orderGeneralPOs = null;

		try {
			if(userType == UserType.GUEST){
				orderGeneralPOs = orderDataService.getAllGuestOrderGeneral(userID);
			}else{
				orderGeneralPOs = orderDataService.getAllHotelOrderGeneral(userID);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		//filter the expect ones
		if (orderGeneralPOs != null) {
			for (OrderGeneralPO po : orderGeneralPOs) {
				if (po.getState() == orderState) {
					result.add(new OrderGeneralVO(po));
				}
			}
		}

		return result.iterator();
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param orderID 用户当前需要查看的订单的订单号
	 * @return 此被需要订单的详情载体
	 */
	public OrderVO getOrderDetail(final String orderID) {
		OrderVO thisOrderVO = null;

		try {
			thisOrderVO = new OrderVO(orderDataService.getOrderDetail(orderID));
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return thisOrderVO;
	}

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/8
	 * @param hotelID 酒店工作人员／客户查看酒店的评论
	 * @return 此酒店的所有评价
	 */
	public Iterator<HotelEvaluationVO> getEvaluations(String hotelID) {
		final List<HotelEvaluationVO> result = new ArrayList<HotelEvaluationVO>();

		List<HotelEvaluationPO> hotelEvaluationPOs = null;
		try {
			hotelEvaluationPOs = orderDataService.getEvaluations(hotelID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		if (hotelEvaluationPOs != null) {
			for (HotelEvaluationPO hotelEvaluationPO : hotelEvaluationPOs) {
				if (hotelEvaluationPO.getScore() != -1) {
					result.add(new HotelEvaluationVO(hotelEvaluationPO));
				}
			}
		}

		return result.iterator();
	}
	
}

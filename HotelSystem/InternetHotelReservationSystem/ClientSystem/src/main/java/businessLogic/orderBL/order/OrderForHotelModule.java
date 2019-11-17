package businessLogic.orderBL.order;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import businessLogicService.orderBLService.OrderForHotelModuleBLService;
import dataService.orderDataService.OrderDataService;
import rmi.ClientRemoteHelper;
import utilities.enums.OrderState;
import utilities.enums.UserType;
import vo.OrderGeneralVO;
/**
 * @author charles
 * @lastChangedBy Harvey
 * @updateTime 2016/12/8
 */
public class OrderForHotelModule implements OrderForHotelModuleBLService{

	private OrderDataService orderDataService;

	private CommonOrder commonOrder;
	
	public OrderForHotelModule() {
		orderDataService = ClientRemoteHelper.getInstance().getOrderDataService();
		
//		try {
//			orderDataService = new OrderDataService_Stub();
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
		
		commonOrder = new CommonOrder();
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param guestID 客户需要查看个人定过的酒店时依照的客户个人编号
	 * @return 客户定过的酒店列表
	 */
	public List<String> getBookedHotels(final String guestID) {
		List<String> result = null;
		
		try {
			result = orderDataService.getBookedHotels(guestID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/7
	 * @param guestID 此客户的客户编号
	 * @param hotelID 此客户相对的酒店编号
	 * @return 此客户在此相应酒店预定过的订单状态
	 * 若此客户在此酒店没有订单记录，就返回null
	 * 
	 * 直接从本层本模块getAllGuestOrderGeneral走
	 */
	public OrderState getOrderState(String guestID, String hotelID) {
		Iterator<OrderGeneralVO> guestOrders = commonOrder.getAllOrderGenerals(guestID, UserType.GUEST);
		
		List<OrderState> states = new ArrayList<OrderState>();
		if (guestOrders != null) {
			while(guestOrders.hasNext()){
				OrderGeneralVO thisOrderGeneral = guestOrders.next();
				if (thisOrderGeneral.hotelID.equals(hotelID)) {
					states.add(thisOrderGeneral.state);
				}
			}
		}
		
		if (states.size() == 0) {
			return null;
		}else {
			return getMaxOrderState(states);
		}
	}
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/5
	 * @param states 此客户在此相应酒店预定过的所有订单状态
	 * @return 优先级最高的订单状态（已取消 < 异常 < 未执行 < 已执行）
	 */
	private OrderState getMaxOrderState(List<OrderState> states) {
		List<Integer> integers = new ArrayList<Integer>();
		for (int i = 0; i < states.size(); i++) {
			integers.add(states.get(i).ordinal());
		}
		
		int indexOfMax = 0;
		for (int i = 0; i < integers.size(); i++) {
			if (integers.get(i) > integers.get(indexOfMax)) {
				indexOfMax = i;
			}
		}
		return OrderState.values()[integers.get(indexOfMax)];
	}
}

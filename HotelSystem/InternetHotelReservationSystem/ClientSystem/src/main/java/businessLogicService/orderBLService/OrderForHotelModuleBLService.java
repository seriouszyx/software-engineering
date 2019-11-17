package businessLogicService.orderBLService;

import java.util.List;

import utilities.enums.OrderState;

/**
 * 
 * @author cuihua
 * lastChangedBy charles
 * updateTime 2016/12/7
 *
 */
public interface OrderForHotelModuleBLService {

	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/11/27
	 * @param guestID 客户需要查看个人定过的酒店时依照的客户个人编号
	 * @return 客户定过的酒店列表
	 */
	List<String> getBookedHotels(String guestID);
	
	/**
	 * @author charles
	 * @lastChangedBy charles
	 * @updateTime 2016/12/4
	 * @param guestID 此客户的客户编号
	 * @param hotelID 此客户相对的酒店编号
	 * @return 此客户在此相应酒店预定过的订单状态
	 */
	OrderState getOrderState(String guestID, String hotelID);
}

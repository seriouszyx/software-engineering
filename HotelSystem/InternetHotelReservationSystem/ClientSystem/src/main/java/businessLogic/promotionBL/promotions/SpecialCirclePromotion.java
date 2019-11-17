package businessLogic.promotionBL.promotions;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import businessLogic.hotelBL.hotel.Hotel;
import businessLogic.hotelBL.hotel.HotelInfoOperation;
import businessLogic.memberBL.Member;
import dataService.promotionDataService.PromotionDataService;
import exception.verificationException.UserInexistException;
import po.AddressPO;
import rmi.ClientRemoteHelper;
import utilities.Address;
import utilities.enums.MemberType;
import utilities.enums.ResultMessage;
import vo.AddressVO;

/**
 * @Description:对于VIP会员特定商圈专属折扣
 * @author:Harvey Gong
 * @time:2016年12月1日 下午9:29:44
 */
public class SpecialCirclePromotion {

	private PromotionDataService promotionDataService;
	private List<AddressPO> specialCirclePromotions;

	public SpecialCirclePromotion() {
		promotionDataService = ClientRemoteHelper.getInstance().getPromotionDataService();
	}
	
	/**
	 * @Description:根据所选城市，返回该城市所有的商圈和对应商圈的vip折扣
	 * @param city
	 * @return
	 * Iterator<AddressVO>
	 * @author: Harvey Gong
	 * @time:2016年12月1日 下午9:41:48
	 */
	public Iterator<AddressVO> getSpecialCirclePromoitons(String city){
		initSpecialCirclePromotions(city);
		return convertPOListToVOIterator(specialCirclePromotions);
	}
	
	/**
	 * @Description:更新商圈的vip折扣,单条更新
	 * @param addressVO
	 * @return
	 * ResultMessage
	 * @author: Harvey Gong
	 * @time:2016年12月1日 下午9:45:02
	 */
	public ResultMessage updateSpecialCirclePromotion(AddressVO addressVO){
		try {
			return promotionDataService.updateSepecialCirclePromotion(new AddressPO(addressVO));
		} catch (RemoteException e) {
			return ResultMessage.FAIL;
		}
	}

	/**
	 * @Description:根据guestID判断是否是会员，若是再根据hotelID分别获得该酒店的城市和商圈
	 * @param guestID
	 * @param hotelID
	 * @return
	 * double
	 * @author: Harvey Gong
	 * @time:2016年12月2日 下午7:08:54
	 */
	public double getDiscount(String guestID,String hotelID){
		if(isVIP(guestID)){
			HotelInfoOperation hotel = new Hotel();
			Address hotelAddress = hotel.getHotelAddress(hotelID);
			String city = hotelAddress.city;
			String cycle = hotelAddress.circle;
			try {
				return promotionDataService.getSpecialCircleDiscount(city,cycle);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}
		
	/**
	 * @Description:判断是否是普通会员
	 * @param guestID
	 * @return
	 * boolean
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月14日 上午11:57:14
	 */
	private boolean isVIP(String guestID){
		try {
			return new Member().isMember(guestID, MemberType.COMMON);
		} catch (UserInexistException e) {
			return false;
		}
	}
	
	/**
	 * @Description:将poList转化为vo的iterator
	 * @param POList
	 * @return
	 * Iterator<AddressVO>
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月14日 上午11:57:34
	 */
	private Iterator<AddressVO> convertPOListToVOIterator(List<AddressPO> POList){
		List<AddressVO> specialCirclePromotionVOList = new ArrayList<AddressVO>();
		for(AddressPO specialCirclePromotion: POList){
			specialCirclePromotionVOList.add(new AddressVO(specialCirclePromotion));
		}
		return specialCirclePromotionVOList.iterator();
	}
	
	/**
	 * @Description:根据city获取该城市的所有vip特定商圈的策略
	 * @param city
	 * void
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月14日 上午11:58:08
	 */
	private void initSpecialCirclePromotions(String city) {
		try {
			specialCirclePromotions = promotionDataService.getSpecialCirclePromotion(city);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description:根据城市、商圈获取该特定商圈的折扣
	 * @param city
	 * @param newCircle
	 * @return
	 * double
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2016年12月14日 上午11:58:44
	 */
	public double getSpecialCirclePromoiton(String city, String newCircle) {
		initSpecialCirclePromotions(city);
		for(AddressPO po : specialCirclePromotions){
			if(po.getCircle().equals(newCircle)){
				return po.getDiscout();
			}
		}
		return 1;
	}
}

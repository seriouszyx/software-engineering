package dataService.promotionDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import po.AddressPO;
import po.HotelFixedPromotionPO;
import po.SpecialSpanPromotionPO;
import utilities.enums.ResultMessage;

/**
 * @Description:TODO
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2017年1月2日 下午12:20:27
 */
public interface PromotionDataService extends Remote{
	
	/**
	 * @Description:获取数据库中酒店特定策略集合
	 * @param hotelWorkerID 酒店工作人员id
	 * @return 酒店特定策略集合
	 * @throws RemoteException
	 * List<HotelFixedPromotionPO>
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 下午12:20:09
	 */
	public List<HotelFixedPromotionPO> getHotelFixedPromotion(String hotelWorkerID) throws RemoteException;
	
	/**
	 * @Description:更新数据库中一条酒店特定促销策略
	 * @param hotelFixedPromotionPO 酒店特定促销策略持久化信息载体
	 * @return 结果信息
	 * @throws RemoteException
	 * ResultMessage
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 下午12:20:12
	 */
	public ResultMessage updateHotelFixedPromotion(HotelFixedPromotionPO hotelFixedPromotionPO) throws RemoteException;
	
	// 对特定期间策略的操作，get、add、update、delete

	/**
	 * @Description:获取数据库中酒店特定期间策略集合
	 * @param hotelID 酒店id
	 * @return 特定期间策略持久化对象集合
	 * @throws RemoteException
	 * List<SpecialSpanPromotionPO>
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 下午12:20:14
	 */
	public List<SpecialSpanPromotionPO> getHotelSpecialSpanPromotion(String hotelID) throws RemoteException;
	
	/**
	 * @Description:获取网站特定期间促销策略集合
	 * @return 特定期间策略持久化对象集合
	 * @throws RemoteException
	 * List<SpecialSpanPromotionPO>
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 下午12:20:16
	 */
	public List<SpecialSpanPromotionPO> getWebSpecialSpanPromotion() throws RemoteException;
	
	/**
	 * @Description:添加特定期间策略
	 * @param specialSpanPromotionPO 特定期间策略持久化载体
	 * @return 结果信息
	 * @throws RemoteException
	 * ResultMessage
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 下午12:20:18
	 */
	public ResultMessage addSpecialSpanPromotion(SpecialSpanPromotionPO specialSpanPromotionPO) throws RemoteException;
	
	/**
	 * @Description:更新特定期间策略
	 * @param specialSpanPromotionPO 特定期间策略载体
	 * @return 结果信息
	 * @throws RemoteException
	 * ResultMessage
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 下午12:20:21
	 */
	public ResultMessage updateSpecialSpanPromotion(SpecialSpanPromotionPO specialSpanPromotionPO) throws RemoteException;
	
	/**
	 * @Description:删除特定期间策略
	 * @param userID 用户id
	 * @param promotionName 策略名称
	 * @return 结果信息
	 * @throws RemoteException
	 * ResultMessage
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 下午12:20:23
	 */
	public ResultMessage deleteSpecialSpanPromotion(String userID,String promotionName) throws RemoteException;
	
	// 对会员专属商圈策略的操作，get、update、根据城市、商圈直接获得相应的折扣
	
	/**
	 * @Description:获取vip特定商圈策略
	 * @param city 城市
	 * @return 结果信息
	 * @throws RemoteException
	 * List<AddressPO>
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 下午12:20:25
	 */
	public List<AddressPO> getSpecialCirclePromotion(String city) throws RemoteException;

	/**
	 * @Description:更行vip特定商圈策略
	 * @param addressPO vip特定商圈策略持久化信息载体
	 * @return 结果信息
	 * @throws RemoteException
	 * ResultMessage
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 下午12:20:29
	 */
	public ResultMessage updateSepecialCirclePromotion(AddressPO addressPO) throws RemoteException;

	/**
	 * @Description:获取vip特定商圈折扣
	 * @param city 城市
	 * @param cycle 商圈
	 * @return vip特定商圈折扣
	 * @throws RemoteException
	 * double
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 下午12:20:31
	 */
	public double getSpecialCircleDiscount(String city, String cycle) throws RemoteException;

}

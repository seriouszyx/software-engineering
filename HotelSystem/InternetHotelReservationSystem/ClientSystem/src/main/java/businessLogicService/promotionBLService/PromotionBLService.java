package businessLogicService.promotionBLService;

import java.util.Iterator;

import utilities.enums.ResultMessage;
import vo.AddressVO;
import vo.HotelFixedPromotionVO;
import vo.SpecialSpanPromotionVO;

/**
 * @Description:策略模式
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2017年1月2日 下午12:04:59
 */
public interface PromotionBLService {

	/**
	 * @Description:获取酒店固定促销策略
	 * @param hotelID 酒店id
	 * @return
	 * Iterator<HotelFixedPromotionVO> 酒店固定促销策略集合
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:48:40
	 */
	public Iterator<HotelFixedPromotionVO> getHotelFixedPromotions(String hotelID);

	/**
	 * @Description:更新酒店固定策略
	 * @param hotelFixedPromotionVO 酒店固定策略载体 
	 * @return
	 * ResultMessage 结果信息
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:48:42
	 */
	public ResultMessage updateHotelFixedPromotion(HotelFixedPromotionVO hotelFixedPromotionVO);

	//对特定期间的折扣的操作,get,add,delete,单条操作
	/**
	 * @Description:获取酒店特定期间策略
	 * @param userID 用户id
	 * @return
	 * Iterator<SpecialSpanPromotionVO> 酒店特定期间促销策略集合
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:48:44
	 */
	public Iterator<SpecialSpanPromotionVO> getHotelSpecialSpanPromotions(String userID);

	/**
	 * @Description:获取网站特定期间策略
	 * @return
	 * Iterator<SpecialSpanPromotionVO> 网站特定期间促销策略集合
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:48:47
	 */
	public Iterator<SpecialSpanPromotionVO> getWebSpecialSpanPromotions();

	/**
	 * @Description:添加酒店特定期间策略
	 * @param specialSpanPromotionVO 特定期间促销策略载体
	 * @return
	 * ResultMessage 结果信息
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:48:49
	 */
	public ResultMessage addHotelSpecialSpanPromotion(SpecialSpanPromotionVO specialSpanPromotionVO);
	
	/**
	 * @Description:添加网站特定期间策略
	 * @param specialSpanPromotionVO 特定期间促销策略载体
	 * @return
	 * ResultMessage 结果信息
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:48:52
	 */
	public ResultMessage addWebSpecialSpanPromotion(SpecialSpanPromotionVO specialSpanPromotionVO);

	/**
	 * @Description:更新酒店特定期间策略
	 * @param specialSpanPromotionVO 特定期间促销策略信息载体
	 * @return
	 * ResultMessage 结果信息
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:48:54
	 */
	public ResultMessage updateHotelSpecialSpanPromotion(SpecialSpanPromotionVO specialSpanPromotionVO);
	
	/**
	 * @Description:更新网站特定期间策略
	 * @param specialSpanPromotionVO 特定期间促销策略信息载体
	 * @return
	 * ResultMessage 结果信息
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:48:56
	 */
	public ResultMessage updateWebSpecialSpanPromotion(SpecialSpanPromotionVO specialSpanPromotionVO);
	
	/**
	 * @Description:删除酒店特定期间策略
	 * @param userID 酒店id
	 * @param promotionName 特定期间促销策略名称
	 * @return
	 * ResultMessage 结果信息
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:48:58
	 */
	public ResultMessage deleteHotelSpecialSpanPromotion(String userID,String promotionName);
	
	/**
	 * @Description:删除网站特定期间策略
	 * @param promotionName 策略名称
	 * @return
	 * ResultMessage 结果信息
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:49:00
	 */
	public ResultMessage deleteWebSpecialSpanPromotion(String promotionName);

	/**
	 * @Description:获取vip特定商圈策略
	 * @param city 城市
	 * @return
	 * Iterator<AddressVO> vip特定商圈策略集合
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:49:03
	 */
	public Iterator<AddressVO> getSpecialCirclePromotions(String city);

	/**
	 * @Description:更新vip特定商圈策略
	 * @param addressVO vip特定商圈策略信息载体
	 * @return
	 * ResultMessage 结果信息
	 * @author: Harvey Gong
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:49:06
	 */
	public ResultMessage updateSpecialCirclePromotions(AddressVO addressVO);
	
	/**
	 * @Description:获取特定商圈策略
	 * @param city 城市
	 * @param circle 商圈
	 * @return vip特定商圈策略的折扣
	 * @author: Harvey Gong 
	 * @lastChangedBy: Harvey Gong
	 * @time:2017年1月2日 上午11:49:08
	 */
	public double getSpecialCirclePromotion(String city, String circle);

}

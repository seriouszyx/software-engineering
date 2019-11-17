package dataHelper;

import java.util.List;

import po.SpecialSpanPromotionPO;
import utilities.enums.ResultMessage;

/**
 * @Description:提供给dataService获取所有城市，所有星级，所有房间类型的方法
 * @author:Harvey Gong
 * @lastChangedBy:Harvey Gong
 * @time:2016年12月7日 下午5:02:01
 */
public interface SpecialSpanPromotionDataHelper {

	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param roomInfoPO 酒店房间信息载体
	 * @return ResultMessage  是否成功添加房间信息
	 */
	public List<SpecialSpanPromotionPO> getHotelSpecialSpanPromotion(String hotelID);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param roomInfoPO 酒店房间信息载体
	 * @return ResultMessage  是否成功添加房间信息
	 */
	public List<SpecialSpanPromotionPO> getWebSpecialSpanPromotion();
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param roomInfoPO 酒店房间信息载体
	 * @return ResultMessage  是否成功添加房间信息
	 */
	public ResultMessage addSpecialSpanPromotion(SpecialSpanPromotionPO specialSpanPromotionPO);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param roomInfoPO 酒店房间信息载体
	 * @return ResultMessage  是否成功添加房间信息
	 */
	public ResultMessage updateSpecialSpanPromotion(SpecialSpanPromotionPO specialSpanPromotionPO);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param roomInfoPO 酒店房间信息载体
	 * @return ResultMessage  是否成功添加房间信息
	 */
	public ResultMessage deleteSpecialSpanPromotion(String userID, String promotionName);

}

package dataHelper;

import java.util.List;

import po.HotelFixedPromotionPO;
import utilities.enums.ResultMessage;

/**
 * @Description:对数据库中hotel策略表的操作
 * @author:Harvey Gong
 * @time:2016年12月4日 上午10:10:46
 */
public interface HotelFixedPromotionDataHelper {
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param hotelWorkerID  酒店工作人员ID
	 * @return List<HotelFixedPromotionPO> 酒店策略信息载体列表
	 */
	public List<HotelFixedPromotionPO> getHotelFixedPromotion(String hotelWorkerID);
	
	/**
	 * @author 董金玉
	 * @lastChangedBy 董金玉
	 * @updateTime 2016/11/29
	 * @param hotelFixedPromotionPO  酒店策略信息载体
	 * @return ResultMessage 是否成功更新策略信息
	 */
	public ResultMessage updateHotelFixedPromotion(HotelFixedPromotionPO hotelFixedPromotionPO);
}
